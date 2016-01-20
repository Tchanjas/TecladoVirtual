package Dicionario;

import Structures.TrieHash;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Dictionary {

    private static TrieHash dictionary = new TrieHash();
    static ArrayList<ArrayList<String>> wordList = new ArrayList<ArrayList<String>>(1);
    static ArrayList<String> keyboardCombinations = new ArrayList<>();

    /**
     * Loads every word on a .txt to a TrieHash
     * @throws IOException 
     */
    public static void load() throws IOException {
        //Paths used to store our dictionary and Trie
        String pathDictionary = "dictionary/books/oslusiadas.txt";
        String pathStructure = "dictionary/dictionary.dat";

        //Check for dictionary file existence
        File file = new File(pathDictionary);
        if (!file.exists()) {
            return;
        }

        File trieStructure = new File(pathStructure);
        //If Trie already present, load it instead to save time read reading 
        //every word again and creating the structure
        if (trieStructure.exists()) {
            dictionary = loadTrie(pathStructure);
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (true) {
                String str = reader.readLine();
                if (str == null) {
                    break;
                } else {
                    //Replace every special character, with its normal counterpart
                    // (?iu) its the insensitive flag
                    str = str.replaceAll("(?iu)á|à|ã|â", "a");
                    str = str.replaceAll("(?iu)é|è|ẽ|ê", "e");
                    str = str.replaceAll("(?iu)í|ì|ĩ|î", "i");
                    str = str.replaceAll("(?iu)ó|ò|õ|ô", "o");
                    str = str.replaceAll("(?iu)ú|ù|ũ|û", "u");
                    str = str.replaceAll("(?iu)ç", "c");
                    //Only get letters in sucession, removing all special characters like ,.!"'( etc.
                    String[] aux = str.split("[^A-Za-z]+");

                    for (int i = 0; i < aux.length; i++) {
                        TrieHash currentTrie = dictionary;
                        //Lowercase everyword to make the trie easier to read
                        aux[i] = aux[i].toLowerCase();
                        for (int j = 0; j < aux[i].length(); j++) {
                            //If last character has been reacher, increments frequency on that node
                            if (j + 1 == aux[i].length()) {
                                currentTrie.add(aux[i].charAt(j) + "", true);
                            } else {
                                currentTrie.add(aux[i].charAt(j) + "", false);
                                currentTrie = currentTrie.children.get(aux[i].charAt(j) + "");
                            }

                        }
                    }
                }
            }
            saveTrie(dictionary, pathStructure);
        }
    }

    /**
     * Saves the complete structure to .dat file
     * @param structure Our Trie to be saved to a file
     * @param path Save path
     */
    public static void saveTrie(TrieHash structure, String path) {
        try {
            FileOutputStream fileoutput = new FileOutputStream(path);
            ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
            objectoutput.writeObject(structure);
            objectoutput.close();
        } catch (Exception e) {
            System.out.println("Erro na gravação da estrutura");
        }
    }

    /**
     * //Reads structure from file
     * @param path to read from
     * @return TrieHash from .dat file
     */
    public static TrieHash loadTrie(String path) {
        File structure = new File(path);
        TrieHash trieStructure = new TrieHash();
        if (!structure.exists()) {
            return null;
        } else {
            try {
                FileInputStream fileInput = new FileInputStream(structure);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                trieStructure = (TrieHash) objectInput.readObject();
            } catch (Exception e) {
                System.out.println("Erro no carregamento da estrutura");
            }
            return trieStructure;
        }
    }

    /**
     * Stores every combinations of letters according to the project rules
     * @param word Word getting combinations from
     * @return An arraylist containing all possible combinations with error checking
     */
    static ArrayList<String> keyboardCombinations(String word) {
        //Letters next to each other. Ex. Next to A -> ZSQ
        String[] letterCombinations = {"azsq", "bvgn", "cxdv", "decsf", "ewdr",
            "frvdg", "gfhtb", "hygnj", "iuko", "juhmk", "kijl", "lko", "mnj",
            "nbhm", "oilp", "pol", "qasw", "reft", "swaxd", "uyji", "tyhu",
            "vcfb", "wqse", "xzsc", "ythu", "zax"};

        //keyboardcombo will always be empty on first letter from word so it always enter this check
        if (keyboardCombinations.isEmpty()) {
            //Goes through each combo according to index, since charAt(0)- 'a' returns position in array
            for (int i = 0; i < letterCombinations[word.charAt(0) - 'a'].length(); i++) {
                keyboardCombinations.add(letterCombinations[word.charAt(0) - 'a'].charAt(i) + "");
            }
            if (!word.substring(1).isEmpty()) {
                //Recursively call this method on next letter
                keyboardCombinations(word.substring(1));
            }
        } else {
            int size = keyboardCombinations.size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < letterCombinations[word.charAt(0) - 'a'].length(); j++) {
                    //Adds remaning combinations incase of input error from user
                    keyboardCombinations.add(keyboardCombinations.get(0) + "" + letterCombinations[
                             word.charAt(0) - 'a'].charAt(j));
                }
                //Remove single combinations from array since the word passed has more chars than 1
                keyboardCombinations.remove(0);
            }
            if (!word.substring(1).isEmpty()) {
                keyboardCombinations(word.substring(1));
            }
        }
        return keyboardCombinations;
    }

    /**
     * Sorts our wordlist according to frequency in decreasing order
     */
    static void listSort() {
        Collections.sort(wordList, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> i, ArrayList<String> j) {
                //We must use doubles instead of integers due to precision
                //Compares the frequency of each node, since its always stored on index 0 of each array
                return new Double(j.get(0)).compareTo(new Double(i.get(0))
                );
            }
        });
    }

    /**
     * Finds words on our trie, with or without error sugestions
     * @param word Word to get sugestions from
     * @param comb Boolean from Checkbox from keyboard
     * @return Arraylist of words relevant to the word passed
     */
    public static ArrayList find(String word, boolean comb) {
        //Clears our wordList and comboList to find only words relevant to the word passed
        keyboardCombinations.clear();
        wordList.clear();
        //If the checkbox is ticked, it will find all words with error combinations 
        if (comb) {
            keyboardCombinations(word);
            for (int i = 0; i < keyboardCombinations.size(); i++) {
                wordList.addAll(dictionary.find(keyboardCombinations.get(i)));
            }
        } else {
            //Just find all regular combinations
            wordList.addAll(dictionary.find(word));
        }
        listSort();
        return wordList;
    }
}

package Dicionario;

import Structures.TrieArray;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DictionaryArray {

    private static TrieArray dictionary = new TrieArray("");
    static ArrayList<ArrayList<String>> wordList = new ArrayList<ArrayList<String>>(1);
    static ArrayList<String> keyboardCombinations = new ArrayList<>();

    public static void load() throws IOException {

        String pathDictionary = "dictionary/books/oslusiadas.txt";
        String pathStructure = "dictionary/dictionaryArray.dat";

        File file = new File(pathDictionary);
        if (!file.exists()) {
            return;
        }

        File trieStructure = new File(pathStructure);

        if (trieStructure.exists()) {
            dictionary = loadTrie(pathStructure);
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (true) {
                String str = reader.readLine();
                if (str == null) {
                    break;
                } else {
                    // (?iu) é a case insensitive flag
                    str = str.replaceAll("(?iu)á|à|ã|â", "a");
                    str = str.replaceAll("(?iu)é|è|ẽ|ê", "e");
                    str = str.replaceAll("(?iu)í|ì|ĩ|î", "i");
                    str = str.replaceAll("(?iu)ó|ò|õ|ô", "o");
                    str = str.replaceAll("(?iu)ú|ù|ũ|û", "u");
                    str = str.replaceAll("(?iu)ç", "c");
                    String[] aux = str.split("[^A-Za-z]+");

                    for (int i = 0; i < aux.length; i++) {
                        TrieArray currentTrie = dictionary;
                        aux[i] = aux[i].toLowerCase();
                        for (int j = 0; j < aux[i].length(); j++) {
                            if (j + 1 == aux[i].length()) {
                                currentTrie.add(aux[i].charAt(j) + "", true);
                            } else {
                                currentTrie.add(aux[i].charAt(j) + "", false);
                                currentTrie = currentTrie.children[aux[i].charAt(j) - 'a'];
                            }

                        }
                    }
                }
            }
            saveTrie(dictionary, pathStructure);
        }
    }

    public static void saveTrie(TrieArray structure, String path) {
        try {
            FileOutputStream fileoutput = new FileOutputStream(path);
            ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
            objectoutput.writeObject(structure);
            objectoutput.close();
        } catch (Exception e) {
            System.out.println("Erro na gravação da estrutura");
        }
    }

    public static TrieArray loadTrie(String path) {
        File structure = new File(path);
        TrieArray trieStructure = new TrieArray("");
        if (!structure.exists()) {
            return null;
        } else {
            try {
                FileInputStream fileInput = new FileInputStream(structure);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                trieStructure = (TrieArray) objectInput.readObject();
            } catch (Exception e) {
                System.out.println("Erro no carregamento da estrutura");
            }
            return trieStructure;
        }
    }

    static ArrayList<String> keyboardCombinations(String word) {

        String[] letterCombinations = {"azsq", "bvgn", "cxdv", "decsf", "ewdr",
            "frvdg", "gfhtb", "hygnj", "iuko", "juhmk", "kijl", "lko", "mnj",
            "nbhm", "oilp", "pol", "qasw", "reft", "swaxd", "uyji", "tyhu",
            "vcfb", "wqse", "xzsc", "ythu", "zax"};

        if (keyboardCombinations.isEmpty()) {
            for (int i = 0; i < letterCombinations[word.charAt(0) - 'a'].length(); i++) {
                keyboardCombinations.add(letterCombinations[word.charAt(0) - 'a'].charAt(i) + "");
            }
            if (!word.substring(1).isEmpty()) {
                keyboardCombinations(word.substring(1));
            }
        } else {
            int size = keyboardCombinations.size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < letterCombinations[word.charAt(0) - 'a'].length(); j++) {
                    keyboardCombinations.add(keyboardCombinations.get(0) + "" + letterCombinations[word.charAt(0) - 'a'].charAt(j));
                }
                keyboardCombinations.remove(0);
            }
            if (!word.substring(1).isEmpty()) {
                keyboardCombinations(word.substring(1));
            }
        }
        return keyboardCombinations;
    }

    static void listSort() {
        Collections.sort(wordList, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> i, ArrayList<String> j) {
                // estava-se a usar Integers aqui mas tem de se 
                // usar doubles invés devido à imprecisão (?)
                // http://stackoverflow.com/questions/28346750/comparator-of-arraylistobject-doesnt-work-properly
                return new Double(j.get(0)).compareTo(new Double(i.get(0))
                );
            }
        });
    }

    public static ArrayList find(String word) {
        keyboardCombinations.clear();
        wordList.clear();
        keyboardCombinations(word);
        for (int i = 0; i < keyboardCombinations.size(); i++) {
            wordList.addAll(dictionary.find(keyboardCombinations.get(i)));
        }
        listSort();
        return wordList;
    }
}

package Structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TrieHash implements Serializable {

    int frequency;
    public HashMap<String, TrieHash> children;
    ArrayList<ArrayList<String>> wordList = new ArrayList<ArrayList<String>>(1);

    public TrieHash() {
        children = new HashMap<>();
        frequency = 0;
    }

    void addFrequency() {
        frequency++;
    }

    /**
     * Add a new letter to the children of the trie
     * @param letter a string to add to the children
     */
    void add(String letter) {
        add(letter, false);
    }

    /**
     * Add a new letter to the children of the trie
     * @param letter a string to add to the children
     * @param increments if true also increments the frequency of the letter
     */
    public void add(String letter, boolean increments) {
        if (!children.containsKey(letter)) {
            children.put(letter, new TrieHash());
        }
        if (increments) {
            children.get(letter).addFrequency();
        }
    }

    /**
     *  Find a word and any word that begins with that word
     * @param word word to find
     * @return an ArrayList with the words found and their respective frequency
     */
    public ArrayList find(String word) {
        TrieHash trie = this;
        wordList.clear();
        // find the node of the last character of the word
        for (int i = 0; i < word.length(); i++) {
            if (trie != null) {
                trie = trie.children.get(word.charAt(i) + "");
            } else {
                break;
            }
        }
        if (trie != null) {
            find(trie, word);
            return wordList;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Find every children of a passed trie
     * @param trie the current trie to travel by its children
     * @param word the current word to add on the wordList if its frequency is > 0
     */
    private void find(TrieHash trie, String word) {
        // add the current node if the frequency if > 0
        if (trie.frequency > 0) {
            ArrayList temp_arrayList = new ArrayList<>();
            temp_arrayList.add(trie.frequency + "");
            temp_arrayList.add(word);
            wordList.add(temp_arrayList);
        }
        // if there's children on the trie then do a recursive find
        if (!trie.children.isEmpty()) {
            for (String key : trie.children.keySet()) {
                find(trie.children.get(key), word + key);
            }
        }
    }

    public int getFrequency() {
        return frequency;
    }

    public HashMap<String, TrieHash> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Trie{" + "frequencia=" + frequency + ", filhos=" + children + '}';
    }

}

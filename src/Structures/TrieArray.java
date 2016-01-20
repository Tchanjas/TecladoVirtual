package Structures;

import java.io.Serializable;
import java.util.ArrayList;

public class TrieArray implements Serializable {

    int frequency;
    String root;
    public TrieArray[] children;
    ArrayList<ArrayList<String>> wordList;

    public TrieArray(String root) {
        children = new TrieArray[26];
        frequency = 0;
        this.root = root;
        wordList = new ArrayList<>(1);
    }

    void addFrequency() {
        frequency++;
    }

    /**
     * Add a new letter to the children of the trie
     *
     * @param letter a string to add to the children
     */
    void add(String letter) {
        add(letter, false);
    }

    /**
     * Add a new letter to the children of the trie
     *
     * @param letter a string to add to the children
     * @param increments if true also increments the frequency of the letter
     */
    public void add(String letter, boolean increments) {
        int index = letter.charAt(0) - 'a';
        if (children[index] == null) {
            children[index] = new TrieArray(letter.charAt(0) + "");
        }
        if (increments) {
            children[index].frequency += 1;
        }
    }

    /**
     *  Find a word and any word that begins with that word
     * @param word word to find
     * @return an ArrayList with the words found and their respective frequency
     */
    public ArrayList find(String word) {
        TrieArray trie = this;
        wordList.clear();

        for (int i = 0; i < word.length(); i++) {
            if (trie != null) {
                trie = trie.children[word.charAt(i) - 'a'];
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
    private void find(TrieArray trie, String word) {
        // add the current node if the frequency if > 0
        if (trie.frequency > 0) {
            ArrayList temp_arrayList = new ArrayList<>();
            temp_arrayList.add(trie.frequency + "");
            temp_arrayList.add(word);
            wordList.add(temp_arrayList);
        }
        // if there's children on the trie then do a recursive find
        for (int i = 0; i < trie.children.length; i++) {
            if (trie.children[i] != null) {
                find(trie.children[i], word + trie.children[i].root);
            }
        }
    }

    public int getFrequency() {
        return frequency;
    }

    public TrieArray[] getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Trie{" + "frequencia=" + frequency + ", filhos=" + children + '}';
    }

}

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

    void add(String letter) {
        add(letter, false);
    }

    //adiciona um nodo à arvore e incrementa a frequency porque é o último caractér da palavra
    public void add(String letter, boolean increments) {
        int index = letter.charAt(0) - 'a';
        if (children[index] == null) {
            children[index] = new TrieArray(letter.charAt(0) + "");
        }
        if (increments) {
            children[index].frequency += 1;
        }
    }

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

    private void find(TrieArray trie, String word) {
        if (trie.frequency > 0) {
            wordList.add(new ArrayList<>());
            wordList.get(wordList.size() - 1).add(trie.frequency + "");
            wordList.get(wordList.size() - 1).add(word);
        }

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

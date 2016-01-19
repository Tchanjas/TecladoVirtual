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

    void add(String letra) {
        add(letra, false);
    }

    //adiciona um nodo à arvore e incrementa a frequency porque é o último caractér da palavra
    public void add(String letter, boolean increments) {
        if (!children.containsKey(letter)) {
            children.put(letter, new TrieHash());
        }
        if (increments) {
            children.get(letter).addFrequency();
        }
    }

    public ArrayList find(String word) {
        TrieHash trie = this;
        wordList.clear();
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

    private void find(TrieHash trie, String word) {
        if (trie.frequency > 0) {
            wordList.add(new ArrayList<>());
            wordList.get(wordList.size() - 1).add(trie.frequency + "");
            wordList.get(wordList.size() - 1).add(word);
        }
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

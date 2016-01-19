package Structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TrieHash implements Serializable{

    int frequencia;
    public HashMap<String, TrieHash> filhos;
    ArrayList<ArrayList<String>> listaPalavras = new ArrayList<ArrayList<String>>(1);

    public TrieHash() {
        filhos = new HashMap<>();
        frequencia = 0;
    }

    void incrementaFrequencia() {
        frequencia++;
    }

    void add(String letra) {
        add(letra, false);
    }

    //adiciona um nodo à arvore e incrementa a frequencia porque é o último caractér da palavra
    public void add(String letra, boolean increments) {
        if (!filhos.containsKey(letra)) {
            filhos.put(letra, new TrieHash());
        }
        if (increments) {
            filhos.get(letra).incrementaFrequencia();
        }
    }

    public ArrayList find(String palavra) {
        TrieHash trie = this;
        listaPalavras.clear();

        for (int i = 0; i < palavra.length(); i++) {
            if (trie != null) {
                trie = trie.filhos.get(palavra.charAt(i) + "");
            } else {
                break;
            }
        }

        if (trie != null) {
            find(trie, palavra);
            return listaPalavras;
        } else {
            return new ArrayList<>();
        }
    }

    private void find(TrieHash trie, String palavra) {
        if (trie.frequencia > 0) {
            listaPalavras.add(new ArrayList<>());
            listaPalavras.get(listaPalavras.size() - 1).add(trie.frequencia + "");
            listaPalavras.get(listaPalavras.size() - 1).add(palavra);
        }

        if (!trie.filhos.isEmpty()) {
            for (String key : trie.filhos.keySet()) {
                find(trie.filhos.get(key), palavra + key);
            }
        }
    }

    public int getFrequencia() {
        return frequencia;
    }

    public HashMap<String, TrieHash> getFilhos() {
        return filhos;
    }

    @Override
    public String toString() {
        return "Trie{" + "frequencia=" + frequencia + ", filhos=" + filhos + '}';
    }

}

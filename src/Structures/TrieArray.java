package Structures;

import java.io.Serializable;
import java.util.ArrayList;

public class TrieArray implements Serializable{

    int frequencia;
    String root;
    public TrieArray[] filhos;
    ArrayList<ArrayList<String>> listaPalavras;

    public TrieArray(String root) {
        filhos = new TrieArray[26];
        frequencia = 0;
        this.root = root;
        listaPalavras =  new ArrayList<>(1);
    }

    void incrementaFrequencia() {
        frequencia++;
    }

    void add(String letra) {
        add(letra, false);
    }

    //adiciona um nodo à arvore e incrementa a frequencia porque é o último caractér da palavra
    public void add(String letra, boolean increments) {
        int index = letra.charAt(0) - 'a';
        if (filhos[index] == null) {
            filhos[index] = new TrieArray(letra.charAt(0) + "");
        }
        if (increments) {
            filhos[index].frequencia += 1;
        }
    }

    public ArrayList find(String palavra) {
        TrieArray trie = this;
        listaPalavras.clear();

        for (int i = 0; i < palavra.length(); i++) {
            if (trie != null) {
                trie = trie.filhos[palavra.charAt(i) - 'a'];
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

    private void find(TrieArray trie, String palavra) {
        if (trie.frequencia > 0) {
            listaPalavras.add(new ArrayList<>());
            listaPalavras.get(listaPalavras.size() - 1).add(trie.frequencia + "");
            listaPalavras.get(listaPalavras.size() - 1).add(palavra);
        }
        
        for (int i = 0; i < trie.filhos.length; i++) {
            if (trie.filhos[i] != null) {
                find(trie.filhos[i], palavra + trie.filhos[i].root);
            }
        }
    }

    public int getFrequencia() {
        return frequencia;
    }

    public TrieArray[] getFilhos() {
        return filhos;
    }

    @Override
    public String toString() {
        return "Trie{" + "frequencia=" + frequencia + ", filhos=" + filhos + '}';
    }

}

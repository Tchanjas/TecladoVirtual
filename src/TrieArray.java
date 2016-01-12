import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TrieArray implements Serializable{

    int frequencia;
    String root;
    TrieArray[] filhos;
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
    void add(String letra, boolean increments) {
        int index = letra.charAt(0) - 'a';
        if (filhos[index] == null) {
            filhos[index] = new TrieArray(letra.charAt(0) + "");
        }
        if (increments) {
            filhos[index].frequencia += 1;
        }
    }

    ArrayList find(String palavra) {
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
            listaSort();
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
        
        for (int i = 0; i < filhos.length; i++) {
            if (trie.filhos[palavra.charAt(i) - 'a'] != null) {
                find(trie.filhos[palavra.charAt(i) - 'a'], palavra + trie.filhos[palavra.charAt(i) - 'a'].root);
            }
        }
    }

    void listaSort() {
        Collections.sort(listaPalavras, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> i, ArrayList<String> j) {
                return j.get(0).compareTo(i.get(0));
            }
        });
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

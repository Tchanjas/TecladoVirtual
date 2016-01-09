import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Trie {

    int frequencia;
    HashMap<String, Trie> filhos;
    ArrayList<ArrayList<String>> listaPalavras = new ArrayList<ArrayList<String>>(1);

    public Trie() {
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
    void add(String letra, boolean increments) {
        if (!filhos.containsKey(letra)) {
            filhos.put(letra, new Trie());
        }
        if (increments) {
            filhos.get(letra).incrementaFrequencia();
        }
    }

    ArrayList find(String palavra) {
        Trie trie = this;

        for (int i = 0; i < palavra.length(); i++) {
            trie = trie.filhos.get(palavra.charAt(i) + "");
        }

        if (trie != null) {
            find(trie, palavra);
            listaSort();
            return listaPalavras;
        } else {
            return new ArrayList<>();
        }
    }

    private void find(Trie trie, String palavra) {
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

    public HashMap<String, Trie> getFilhos() {
        return filhos;
    }

    @Override
    public String toString() {
        return "Trie{" + "frequencia=" + frequencia + ", filhos=" + filhos + '}';
    }

}

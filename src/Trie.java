import java.util.HashMap;

public class Trie {

    Nodo root;
    HashMap<String,Trie> filhos;

    public Trie(Nodo nodo) {
        this.root = nodo;
        filhos = new HashMap<String,Trie>();
    }
    
    void incrementaFrequencia(){
        root.frequencia++;
    }
    
    public int getFrequencia() {
        return root.frequencia;
    }

    void add(Nodo nodo){
        if (filhos.containsKey(nodo.getLetra())) {
            filhos.get(nodo.getLetra()).incrementaFrequencia();
        } else {
            filhos.put(nodo.getLetra(), new Trie(nodo));
        }
    }

    @Override
    public String toString() {
        return "{root=" + root + ", filhos=" + filhos + "}";
    }
    
    
}

import java.util.HashMap;

public class Trie {

    NodoTrie root;
    HashMap<String,Trie> filhos;

    public Trie(NodoTrie nodo) {
        this.root = nodo;
        filhos = new HashMap<String,Trie>();
    }
    
    void incrementaFrequencia(){
        root.frequencia++;
    }
    
    public int getFrequencia() {
        return root.frequencia;
    }
        
    void add(NodoTrie nodo){
        add(nodo,false);
    }
    
    //adiciona um nodo à arvore e incrementa a frequencia porque é o último caractér da palavra
    void add(NodoTrie nodo, boolean increments){
        if (!filhos.containsKey(nodo.getLetra())) {
            filhos.put(nodo.getLetra(), new Trie(nodo));
        }
        if (increments) {
            filhos.get(nodo.getLetra()).incrementaFrequencia();
        }
    }

    @Override
    public String toString() {
        return "{root=" + root + ", filhos=" + filhos + "}";
    }
    
    
}

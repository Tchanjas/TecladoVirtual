import java.util.HashMap;

public class Trie {

    int frequencia;
    HashMap<String,Trie> filhos;

    public Trie() {
        filhos = new HashMap<>();
        frequencia = 0;
    }
    
    void incrementaFrequencia(){
        frequencia++;
    }
        
    void add(String letra){
        add(letra,false);
    }
    
    //adiciona um nodo à arvore e incrementa a frequencia porque é o último caractér da palavra
    void add(String letra, boolean increments){
        if (!filhos.containsKey(letra)) {
            filhos.put(letra, new Trie());
        }
        if (increments) {
            filhos.get(letra).incrementaFrequencia();
        }
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

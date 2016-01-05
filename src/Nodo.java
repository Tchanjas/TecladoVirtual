import java.util.HashMap;

public class Nodo {
    String letra;
    int frequencia;
    HashMap<String,Trie> filhos;

    public Nodo(String letra) {
        this.letra = letra;
        frequencia = 1;
        filhos = new HashMap<String, Trie>();
    }

    public String getLetra() {
        return letra;
    }

    void incrementaFrequencia(){
        frequencia++;
    }

    @Override
    public String toString() {
        return letra.toString();
    }
}

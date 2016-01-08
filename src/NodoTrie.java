
public class NodoTrie extends Nodo {

    int frequencia;

    public NodoTrie(String letra) {
        super(letra);
    }

    void incrementaFrequencia() {
        frequencia++;
    }

    public int getFrequencia() {
        return frequencia;
    }

}

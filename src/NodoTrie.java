
public class NodoTrie extends Nodo {

    int frequencia;

    public NodoTrie(String letra) {
        super(letra);
        frequencia = 0;
    }

    void incrementaFrequencia() {
        frequencia++;
    }

    public int getFrequencia() {
        return frequencia;
    }

}

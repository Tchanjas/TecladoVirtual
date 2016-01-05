public class Nodo {
    String letra;
    int frequencia;

    public Nodo(String letra) {
        this.letra = letra;
        frequencia = 1;
    }

    public String getLetra() {
        return letra;
    }

    void incrementaFrequencia(){
        frequencia++;
    }

    public int getFrequencia() {
        return frequencia;
    }

    @Override
    public String toString() {
        return letra.toString();
    }
}

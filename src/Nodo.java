public class Nodo {
    String letra;
    
    public Nodo(String letra) {
        this.letra = letra;
    }

    public String getLetra() {
        return letra;
    }

    @Override
    public String toString() {
        return letra.toString();
    }
}

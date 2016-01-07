public class test {
    public static void main(String[] args) {
        Trie arv = new Trie(new Nodo("a"));
        arv.add(new Nodo("a"));
        arv.add(new Nodo("b"));
        arv.add(new Nodo("a"));
        arv.filhos.get("a").add(new Nodo("c"));
        
//        System.out.println(arv);
//        System.out.println(arv.getFrequencia());
//        System.out.println(arv.filhos.get("a").getFrequencia());
        
        Trie nodoAtual = arv;
        System.out.println(nodoAtual);
        nodoAtual = nodoAtual.filhos.get("a");
        System.out.println(nodoAtual);
        nodoAtual = nodoAtual.filhos.get("c");
        System.out.println(nodoAtual);
    }
}

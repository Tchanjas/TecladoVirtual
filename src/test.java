
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException{
        Dictionary.load("dictionary/test.txt");
        Trie arv = new Trie();
        arv.add("a", true);
        arv.add("b");
        arv.add("a",true);
        arv.filhos.get("a").add("c", true);
        arv.filhos.get("a").filhos.get("c").add("c", true);
        arv.filhos.get("a").add("d", true);
        arv.filhos.get("a").add("e", true);
        arv.filhos.get("a").add("e", true);
        arv.filhos.get("a").add("e", true);
        
        System.out.println(arv);
        System.out.println(arv.getFrequencia());
        System.out.println(arv.filhos.get("a").getFrequencia());
        
        Trie nodoAtual = arv;
        System.out.println(nodoAtual);
        nodoAtual = nodoAtual.filhos.get("a");
        System.out.println(nodoAtual);
        nodoAtual = nodoAtual.filhos.get("c");
        System.out.println(nodoAtual);
        
        System.out.println(arv.find("a"));

    }
}

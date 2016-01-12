
import java.io.IOException;

public class Benchmark {
    public static void main(String[] args) throws IOException{
        
        System.out.println("\nDicionario Trie Load():");
        long StartTime = System.currentTimeMillis();
        Dictionary.load("dictionary/books/oslusiadas.txt");
        long StopTime = System.currentTimeMillis();
        Trie arv = Dictionary.loadTrie("dictionary/dictionaryStructure.dat");
        System.out.println("Tempo: " + (StopTime - StartTime) + " ms");
        
        
        System.out.println("Dicionario TrieArray Load():");
        StartTime = System.currentTimeMillis();
        DictionaryArray.load("dictionary/test.txt");
        StopTime = System.currentTimeMillis();
        System.out.println("Tempo: " + (StopTime - StartTime) + " ms");
        Trie arvArr = Dictionary.loadTrie("dictionary/dictionaryArrayStructure.dat");
        
        
        System.out.println("---------------------");
        
        
        System.out.println("Trie find():");
        StartTime = System.nanoTime();
        System.out.println(arv.find("o"));
        StopTime = System.nanoTime();
        System.out.println("Tempo: " + (StopTime - StartTime) + " ns");
        
        System.out.println("TrieArray find():");
        StartTime = System.nanoTime();
        System.out.println(arvArr.find("o"));
        StopTime = System.nanoTime();
        System.out.println("Tempo: " + (StopTime - StartTime) + " ns");
    }
}

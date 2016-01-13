
import java.io.IOException;

public class Benchmark {
    public static void main(String[] args) throws IOException{
        
        System.out.println("\nDicionario Trie Load():");
        long StartTime = System.currentTimeMillis();
        Dictionary.load();
        long StopTime = System.currentTimeMillis();
        System.out.println("Tempo: " + (StopTime - StartTime) + " ms");
        
        System.out.println("Dicionario TrieArray Load():");
        StartTime = System.currentTimeMillis();
        DictionaryArray.load();
        StopTime = System.currentTimeMillis();
        System.out.println("Tempo: " + (StopTime - StartTime) + " ms");
        
        
        System.out.println("---------------------");
        
        
        System.out.println("Trie find():");
        StartTime = System.currentTimeMillis();
        System.out.println(Dictionary.find("o"));
        StopTime = System.currentTimeMillis();
        System.out.println("Tempo: " + (StopTime - StartTime) + " ms");
        
        System.out.println("TrieArray find():");
        StartTime = System.currentTimeMillis();
        System.out.println(DictionaryArray.find("o"));
        StopTime = System.currentTimeMillis();
        System.out.println("Tempo: " + (StopTime - StartTime) + " ms");
    }
}

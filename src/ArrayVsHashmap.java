
import java.util.HashMap;
import java.util.Map.Entry;

public class ArrayVsHashmap {

    public static void main(String[] args) {
        Object[][] letrasArray = {{"A", 1}, {"B", 1}, {"C", 1}, {"D", 1}, {"E", 1},
            {"F", 1}, {"G", 1}, {"H", 1}, {"I", 1}, {"J", 1}, {"K", 1}, {"L", 1}, {"M", 1},
            {"N", 1}, {"O", 1}, {"P", 1}, {"Q", 1}, {"R", 1}, {"S", 1}, {"U", 1}, {"T", 1},
            {"V", 1}, {"W", 1}, {"X", 1}, {"Y", 1}, {"Z", 1}};

        HashMap<String, Integer> letrasHash = new HashMap<String, Integer>();
        letrasHash.put("A", 1);
        letrasHash.put("B", 1);
        letrasHash.put("C", 1);
        letrasHash.put("D", 1);
        letrasHash.put("E", 1);
        letrasHash.put("F", 1);
        letrasHash.put("G", 1);
        letrasHash.put("H", 1);
        letrasHash.put("I", 1);
        letrasHash.put("J", 1);
        letrasHash.put("K", 1);
        letrasHash.put("L", 1);
        letrasHash.put("M", 1);
        letrasHash.put("N", 1);
        letrasHash.put("O", 1);
        letrasHash.put("P", 1);
        letrasHash.put("Q", 1);
        letrasHash.put("R", 1);
        letrasHash.put("S", 1);
        letrasHash.put("U", 1);
        letrasHash.put("T", 1);
        letrasHash.put("V", 1);
        letrasHash.put("W", 1);
        letrasHash.put("X", 1);
        letrasHash.put("Y", 1);
        letrasHash.put("Z", 1);
        
        
        long StartTime = 0;
        long StopTime = 0;
        int n = 1001;
 
        System.out.println("Tempo de percorrer por ordem.");
        
        StartTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < letrasArray.length; j++) {
                System.out.print(letrasArray[j][0] + " ");
            }
        }
        StopTime = System.nanoTime();
        System.out.println("\nArray: \n" + (StopTime - StartTime) + " ns \n");
        
        StartTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            for (String key : letrasHash.keySet()) {
                System.out.print(key + " ");
            }
        }
        StopTime = System.nanoTime();
        System.out.println("\nHashmap (opção 1): \n" + (StopTime - StartTime) + " ns \n"); 
        
        StartTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            for (Entry<String, Integer> entry : letrasHash.entrySet()) {
                System.out.print(entry.getKey() + " ");
            }
        }
        StopTime = System.nanoTime();
        System.out.println("\nHashmap (opção 2): \n" + (StopTime - StartTime) + " ns \n");
        
        
        System.out.println("\n------------------------------\n");
        
        
        System.out.println("Tempo de obter uma frequencia de uma letra conhecida.");
        
        StartTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            System.out.print(letrasArray[8][1]);
        }
        StopTime = System.nanoTime();
        System.out.println("\nArray: \n" + (StopTime - StartTime) + " ns \n");
        
        StartTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            System.out.print(letrasHash.get("I"));
        }
        StopTime = System.nanoTime();
        System.out.println("\nHash: \n" + (StopTime - StartTime) + " ns \n");
    }
}

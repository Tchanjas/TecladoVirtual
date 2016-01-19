package Structures;

import java.util.HashMap;
import java.util.Map.Entry;

public class ArrayVsHashmap {

    public static void main(String[] args) {
        Object[][] arrayLetters = {{"A", 1}, {"B", 1}, {"C", 1}, {"D", 1}, {"E", 1},
        {"F", 1}, {"G", 1}, {"H", 1}, {"I", 1}, {"J", 1}, {"K", 1}, {"L", 1}, {"M", 1},
        {"N", 1}, {"O", 1}, {"P", 1}, {"Q", 1}, {"R", 1}, {"S", 1}, {"U", 1}, {"T", 1},
        {"V", 1}, {"W", 1}, {"X", 1}, {"Y", 1}, {"Z", 1}};

        HashMap<String, Integer> hashLetters = new HashMap<String, Integer>();
        hashLetters.put("A", 1);
        hashLetters.put("B", 1);
        hashLetters.put("C", 1);
        hashLetters.put("D", 1);
        hashLetters.put("E", 1);
        hashLetters.put("F", 1);
        hashLetters.put("G", 1);
        hashLetters.put("H", 1);
        hashLetters.put("I", 1);
        hashLetters.put("J", 1);
        hashLetters.put("K", 1);
        hashLetters.put("L", 1);
        hashLetters.put("M", 1);
        hashLetters.put("N", 1);
        hashLetters.put("O", 1);
        hashLetters.put("P", 1);
        hashLetters.put("Q", 1);
        hashLetters.put("R", 1);
        hashLetters.put("S", 1);
        hashLetters.put("U", 1);
        hashLetters.put("T", 1);
        hashLetters.put("V", 1);
        hashLetters.put("W", 1);
        hashLetters.put("X", 1);
        hashLetters.put("Y", 1);
        hashLetters.put("Z", 1);

        long StartTime = 0;
        long StopTime = 0;
        int n = 1001;

        System.out.println("Tempo de percorrer por ordem.");

        StartTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < arrayLetters.length; j++) {
                System.out.print(arrayLetters[j][0] + " ");
            }
        }
        StopTime = System.nanoTime();
        System.out.println("\nArray: \n" + (StopTime - StartTime) + " ns \n");

        StartTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            for (String key : hashLetters.keySet()) {
                System.out.print(key + " ");
            }
        }
        StopTime = System.nanoTime();
        System.out.println("\nHashmap (opção 1): \n" + (StopTime - StartTime) + " ns \n");

        StartTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            for (Entry<String, Integer> entry : hashLetters.entrySet()) {
                System.out.print(entry.getKey() + " ");
            }
        }
        StopTime = System.nanoTime();
        System.out.println("\nHashmap (opção 2): \n" + (StopTime - StartTime) + " ns \n");

        System.out.println("\n------------------------------\n");

        System.out.println("Tempo de obter uma frequencia de uma letra conhecida.");

        StartTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            System.out.print(arrayLetters[8][1]);
        }
        StopTime = System.nanoTime();
        System.out.println("\nArray: \n" + (StopTime - StartTime) + " ns \n");

        StartTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            System.out.print(hashLetters.get("I"));
        }
        StopTime = System.nanoTime();
        System.out.println("\nHash: \n" + (StopTime - StartTime) + " ns \n");
    }
}

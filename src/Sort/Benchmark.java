package Sort;

import static Sort.QuickSort.QuickSort;
import java.util.Arrays;

public class Benchmark {
    public static void main(String[] args) {
        int[] x = {24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12};
        int[] y = x;
        System.out.println(Arrays.toString(x));
        
        System.out.println("\nQuick Sort:");
        long StartTime = System.nanoTime();
        QuickSort(y, 0, x.length - 1);
        long StopTime = System.nanoTime();
        System.out.println(Arrays.toString(y));
        System.out.println("Tempo: " + (StopTime - StartTime) + " ns");
        
        y = x;
        
        System.out.println("\nJava Built-in Array Sort:");
        StartTime = System.nanoTime();
        Arrays.sort(y);
        StopTime = System.nanoTime();
        System.out.println(Arrays.toString(y));
        System.out.println("Tempo: " + (StopTime - StartTime) + " ns");
        
        Object[][] z = {{2, "A"}, {2, "B"}, {45, "Bat"}, {20, "Ban"}, {56, "Bo"}, {75, "Bz"},
            {23, "Bon"}, {56, "Bono"}, {99, "Bon"}, {67, "Bzn"}, {12, "Bznut"}};
        for (int i = 0; i < z.length; i++) {
            System.out.print(Arrays.toString(z[i]) + " ");
        }
        Arrays.sort(z);
        System.out.println(Arrays.toString(z));
    }
    
}

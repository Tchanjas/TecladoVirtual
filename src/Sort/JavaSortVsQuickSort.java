package Sort;

import static Sort.QuickSort.QuickSort;
import java.util.Arrays;
import java.util.Comparator;

public class JavaSortVsQuickSort {

    public static void main(String[] args) {
        int[] x = {24, 2, 45, 20, 56, 75, 2, 56, 99, 53, 12};
        int[] y = x;

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

        System.out.println("\n------------------------------------\n");

        String[][] z = {{"2", "A"}, {"2", "B"}, {"45", "Bat"}, {"20", "Ban"}, {"56", "Bo"}, {"75", "Bz"},
        {"23", "Bon"}, {"56", "Bono"}, {"99", "Bon"}, {"67", "Bzn"}, {"12", "Bznut"}};
        System.out.println(Arrays.deepToString(z));

        StartTime = System.nanoTime();
        Arrays.sort(z, new Comparator<String[]>() {
            public int compare(String[] i, String[] j) {
                return Integer.valueOf(j[0]).compareTo(
                        Integer.valueOf(i[0])
                );
            }
        });
        StopTime = System.nanoTime();

        System.out.println(Arrays.deepToString(z));
        System.out.println("Tempo: " + (StopTime - StartTime) + " ns");
    }

}

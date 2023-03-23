package BasicSortingAlgo;

import java.util.Arrays;

public class SelectionSort {
    public static int[] selectionSort(int[] array) {
        // corner case
        if (array == null || array.length <= 1) {
            return array;
        }
        /*
        for (int i = array.length - 1; i >= 1; i--) {
            // assume the current Index if the largest one, and we can change it accordingly
            int globalMax = i;
            for (int j = i - 1; j >= 0; j--) {
                if (array[globalMax] < array[j]) {
                    globalMax = j;
                }
            }
            swap(array, globalMax, i);
        }
         */
        for (int i = 0; i < array.length - 1; i++) {
            int globalMax = i;
            for (int j = i + 1; j <= array.length - 1; j++) {
                if (array[j] > array[globalMax]) {
                    globalMax = j;
                }
            }
            swap(array, globalMax, i);
        }
        return array;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        int[] array = {3, 5, 1, 2, 4, 8};
        System.out.println(Arrays.toString(selectionSort(array)));
    }
}

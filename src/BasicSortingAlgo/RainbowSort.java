package BasicSortingAlgo;

import java.util.Arrays;

public class RainbowSort {
    public static int[] rainbowSort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        // use three variables i/j/k
        // [0, i) the elements that we had already finished sorting
        // [i, j) we had seen before but may need to update it later
        // [j, k) we haven't processed yet.
        // [k, array.length - 1] elements that had already locked.
        int i = 0;
        int j = 0;
        int k = array.length - 1;
        while (j <= k) {
            //Three cases:
            //[-1, 0, 1]
            if (array[j] == -1) {
                swap(array, i++, j++);
            } else if (array[j] == 0) {
                j++;
            } else if (array[j] == 1) {
                swap(array, j, k--);
            }
        }
        return array;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        int[] array = {1,1,0,-1,0,1,-1};
        System.out.println(Arrays.toString(rainbowSort(array)));
    }
}

package BasicSortingAlgo;

import java.util.Arrays;

import static BasicSortingAlgo.QuickSort.partition;

public class QuickSelect {
    public static int[] kSmallest(int[] array, int k) {
        // corner case check
        if (array == null || array.length <= 1 || k == 0) {
            return array;
        }
        // use quick select so we can quick find out the correct binary part
        // after calling the quickSelect, we are able to find a list of array
        // so the array have K smallest elements, if you need sorted one
        quickSelect(array, 0, array.length - 1, k - 1);
        int[] result = Arrays.copyOf(array, k);
        // apply a sort algo to implement it.
        Arrays.sort(result);
        return result;
    }

    private static void quickSelect(int[] array, int left, int right, int target) {
        int pivot = partition(array, left, right);
        // case1: pivot is already the smallest k, we done!
        if (pivot == target) {
            return;
        }
        // case2: pivot is in the left partition
        else if (target < pivot) {
            quickSelect(array, left, pivot - 1, target);
        }
        // case3: pivot is in the right partition
        else {
            quickSelect(array, pivot + 1, right, target);
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 9, 1, 8};
        int k = 2;
        System.out.println(Arrays.toString(kSmallest(array, 2)));
    }
}

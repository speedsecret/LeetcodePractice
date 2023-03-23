package BasicSortingAlgo;

import java.util.Arrays;

public class MergeSort {
    public static int[] mergeSort(int[] array) {
        // divide the array into the minimal parts
        // then do the merge from the bottom to the top
        if (array == null || array.length == 0) {
            return new int[0];
        }
        int[] bufferArray = new int[array.length];
        mergeSort(0, array.length - 1, bufferArray, array);
        return array;
    }

    private static void mergeSort(int left, int right, int[] bufferArray, int[] array) {
        // base case:
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(left, mid, bufferArray, array);
        mergeSort(mid + 1, right, bufferArray, array);
        merge(left, mid, right, bufferArray, array);
    }

    private static void merge(int left, int mid, int right, int[] bufferArray, int[] array) {
        // copy the current parital array from left to right to bufferArray
        for (int i = left; i <= right; i++) {
            bufferArray[i] = array[i];
        }
        int leftIndex = left;
        int rightIndex = mid + 1;
        while (leftIndex <= mid && rightIndex <= right) {
            if (bufferArray[leftIndex] <= bufferArray[rightIndex]) {
                array[left++] = bufferArray[leftIndex++];
            } else {
                array[left++] = bufferArray[rightIndex++];
            }
        }
        while (leftIndex <= mid) {
            array[left++] = bufferArray[leftIndex++];
        }
    }

    public static void main(String[] args) {
        int[] array = {3, 5, 1, 2, 4, 8};
        System.out.println("New sorted array is: " + Arrays.toString(mergeSort(array)));
        int[] array2 = {};
        System.out.println("New sorted array is: " + Arrays.toString(mergeSort(array2)));
        System.out.println("New sorted array is: " + Arrays.toString(mergeSort(null)));
//        int left = 0;
//        int right = 99;
//        for (int i = 0; i <= 19; i++){
//            System.out.print(left + (int)(Math.random() * (right - left + 1)));
//            System.out.print(" ");
//        }
    }
}

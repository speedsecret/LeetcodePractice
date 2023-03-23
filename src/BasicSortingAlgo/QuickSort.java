package BasicSortingAlgo;

import java.util.Arrays;

public class QuickSort {
    public static int[] quickSort(int[] array) {
        // Find a pivot
        // swap the pivot all the way back to the end
        // then to compare the size
        if (array == null || array.length == 0) {
            return array;
        }
        quickSort(0, array.length - 1, array);
        return array;
    }

    private static void quickSort(int left, int right, int[] array) {
        // base case
        if (left >= right) {
            return;
        }
        int pivot = partition(array, left, right);
        quickSort(left, pivot - 1, array);
        quickSort(pivot + 1, right, array);
    }

    public static int partition(int[] array, int left, int right) {
        int pivotIndex = findPivot(left, right);
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, right);
        int leftBound = left;
        int rightBound = right - 1;
        while (leftBound <= rightBound) {
            if (array[leftBound] < pivot) {
                leftBound++;
            } else if (array[rightBound] > pivot) {
                rightBound--;
            } else {
                swap(array, leftBound++, rightBound--);
            }
        }
        swap(array, leftBound, right);
        return leftBound;
    }

    private static int findPivot(int left, int right) {
        return left + (int)(Math.random() * (right - left + 1));
    }

    public static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void main(String[] args) {
        int[] array = {5, 9, 1, 8};
        System.out.println(Arrays.toString(quickSort(array)));
    }
}

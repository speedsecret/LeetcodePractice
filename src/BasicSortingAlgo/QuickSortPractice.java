package BasicSortingAlgo;

public class QuickSortPractice {
    public static int[] quickSort(int[] array) {
        if (array == null || array.length <= 0) {
            return new int[0];
        }
        quickSortPractice(array, 0, array.length - 1);
        return array;
    }

    private static void quickSortPractice(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        // find a pivot
        int pivot = partitionPractice(array, left, right);
        quickSortPractice(array, left, pivot - 1);
        quickSortPractice(array, pivot + 1, right);
    }

    private static int partitionPractice(int[] array, int left, int right) {
        int pivotIndex = findIndex(left, right);
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
        // swap back
        swap(array, leftBound, right);
        return leftBound;
    }

    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    private static int findIndex(int left, int right) {
        return left + (int)(Math.random() * (right - left + 1));
    }
}

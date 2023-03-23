package BinarySearch;

/*
Given an array, which has been sorted, then given a target as well
find the target, return an index
 */

public class ClassicalBinarySearch {
    public static int classicalBinarySearch(int[] array, int target) {
        //Clarification:
        //This array could be null or empty
        //target could be in the array or not
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] < target) {
                //which means the current target must be in the right-hand area in the array or not exist.
                left = mid + 1;
            } else if (array[mid] > target) {
                //similarly, this would need in left-hand side of the left area of this array
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {2, 9, 12, 23, 33, 34, 55, 56, 57, 59, 80};
        int target = 33;
        System.out.printf("Current target index == " + classicalBinarySearch(array, target));
    }
}

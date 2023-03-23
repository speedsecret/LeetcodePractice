package MergeIntervals;

import java.util.Arrays;

public class MergeTwoSortedArray {
    public static int[] merge(int[] one, int[] two) {
        // 12.31.2022
        int[] newArray = new int[one.length + two.length];
        int indexOne = 0;
        int indexTwo = 0;
        int indexNew = 0;
        while (indexOne < one.length && indexTwo < two.length) {
            // compare
            if (one[indexOne] <= two[indexTwo]) {
                newArray[indexNew++] = one[indexOne++];
            } else {
                newArray[indexNew++] = two[indexTwo++];
            }
        }
        while (indexOne < one.length) {
            newArray[indexNew++] = one[indexOne++];
        }
        while (indexTwo < two.length) {
            newArray[indexNew++] = two[indexTwo++];
        }
        return newArray;
    }

    public static void main(String[] args) {
        int[] one = {0,5,5,5,8,10};
        int[] two = {2,3,4,4,5,7};
        System.out.println("Current sorted new array is: " + Arrays.toString(merge(one, two)));
    }
}

/*
75. Sort colors
https://leetcode.com/problems/sort-colors/

Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
*/


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

package Array;
/*
Given an array of integers that contains only 0s
and 1s and a positive integer k, you can flip at most k 0s to 1s,
 return the longest subarray that contains only integer 1 after flipping.
 */

public class LongestSubArrayOnlyContains1s {
    public static int longestSubArrayOnlyContains1s(int[] array, int k) {
        // use two pointers
        // fast and slow to represent the current the tail and head
        // use count to represent how many flips we had used.
        if (array == null || array.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        int count = 0;
        int globalMax = 0;
        while (fast < array.length) {
            if (array[fast] == 1) {
                globalMax = Math.max(globalMax, ++fast - slow);
            } else if (count < k) {
                count++;
                globalMax = Math.max(globalMax, ++fast - slow);
            } else if (array[slow++] == 0) {
                count--;
            }
        }
        return globalMax;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 1, 1, 0, 0, 1, 0, 0, 0};
        int k = 2;
        System.out.printf("The length of the longest subArray is= " + longestSubArrayOnlyContains1s(array, k));
    }
}

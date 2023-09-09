/*
969. Pancake Sorting
https://leetcode.com/problems/pancake-sorting/

Given an array of integers arr, sort the array by performing a series of pancake flips.

In one pancake flip we do the following steps:

Choose an integer k where 1 <= k <= arr.length.
Reverse the sub-array arr[0...k-1] (0-indexed).
For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3, we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.

Return an array of the k-values corresponding to a sequence of pancake flips that sort arr. Any valid answer that sorts the array within 10 * arr.length flips will be judged as correct.

 

Example 1:

Input: arr = [3,2,4,1]
Output: [4,2,4,3]
Explanation: 
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: arr = [3, 2, 4, 1]
After 1st flip (k = 4): arr = [1, 4, 2, 3]
After 2nd flip (k = 2): arr = [4, 1, 2, 3]
After 3rd flip (k = 4): arr = [3, 2, 1, 4]
After 4th flip (k = 3): arr = [1, 2, 3, 4], which is sorted.
Example 2:

Input: arr = [1,2,3]
Output: []
Explanation: The input is already sorted, so there is no need to flip anything.
Note that other answers, such as [3, 3], would also be accepted.
 

Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= arr.length
All integers in arr are unique (i.e. arr is a permutation of the integers from 1 to arr.length).
*/

// Methodology:
// The elements in the arr must be from 1 to length of array
// we can always swap twice to make any elements to the any target position.
// Started from the largest element to 1:
// -check if the element is currently in the right position
// -if not --> add the first swap to the final result, then do the first swap(index from 0 to pos) 
//             then second swap(index from 0 to i - 1), add two swaps into the final results on the fly.
// -if yes --> continue for the next element

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> result = new ArrayList<>();
        int n = A.length;

        // started from the largest element to the smallest.
        // the element must within the range of [1 to k]
        for (int i = n; i > 0; i--) {
            int pos = findPosition(A, i);
            
            // If the current element is already in the correct position, continue to the next iteration.
            if (pos == i - 1) {
                continue;
            }
            
            // If the current element is not at the beginning of the array, flip to move it to the beginning.
            if (pos != 0) {
                result.add(pos + 1); // Index 0 based. Add the position to flip to.
                reverseArray(A, 0, pos); // Reverse the subarray to move the element to the beginning.
            }
            // Flip to move the current element to its correct position at the end of the remaining array.
            result.add(i); // Add the position to flip to (i is the value of the current element).
            reverseArray(A, 0, i - 1); // Reverse the subarray to move the element to its correct position.
        }
        
        return result;
    }

    // Helper function to find the position of a target value in the array.
    private int findPosition(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1; // Not found, should not happen
    }

    // Helper function to reverse a subarray.
    private void reverseArray(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}


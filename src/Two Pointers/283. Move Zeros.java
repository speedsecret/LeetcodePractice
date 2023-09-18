/*
283. Move Zeros.java
https://leetcode.com/problems/move-zeroes/

Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.
Example 1:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
  
Example 2:
Input: nums = [0]
Output: [0]
Constraints:
1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1
 
Follow up: Could you minimize the total number of operations done?
*/

// Abstraction:
// Move all zeros to the end of the arr

// Methodology:
// 1.Method1: find how many zeros we have
// 2.Method2: two pointers, use two pointers to swap all zeros when slow pointer iterate the arr.

class Solution {
    public void moveZeroes(int[] nums) {
        // // Step1: loop the array and find how many 0 we have
        // // Step2: loop the array again, use two pointer to replace the element in-place
        // int numberOfZeros = 0;
        // int slow = 0;
        // for (int fast = 0; fast < nums.length; fast++) {
        //     if (nums[fast] == 0) {
        //         numberOfZeros++;
        //     } else {
        //         nums[slow++] = nums[fast];
        //     }
        // }
        // while (slow < nums.length) {
        //     nums[slow++] = 0;
        // }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            // case1:
            if (nums[fast] == 0) {
                fast++;
            } else {
                // make sure that elements in the left side of slow
                // has been checked and contains non-zeros.
                if (nums[slow] == 0) {
                    swap(nums, slow++, fast++);
                } else {
                    fast++;
                    slow++;
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

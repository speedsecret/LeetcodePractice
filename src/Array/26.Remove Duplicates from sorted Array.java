/*
26.Remove Duplicates from sorted Array.java
https://leetcode.com/problems/remove-duplicates-from-sorted-array/

Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
Return k.

Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
 

Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.


*/

class Solution {
    public int removeDuplicates(int[] nums) {
        // i, j物理意义是什么
        // 所有在i左边的array 都是已经处理过并且swap过的
        // 所有在j左边的array 都是已经处理过的
        int start = 1;
        int end = 1;
        while (end < nums.length) {
            // one step further if met duplicates
            if (nums[end - 1] == nums[end]) {
                end++;
                continue;
            }
            nums[start++] = nums[end++];
        }
        return start;
    }
}

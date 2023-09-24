/*
81. Search In Rotated Sorted Array II.java
https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/

There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
 

Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104
 

Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
*/

class Solution {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
          return false;
        }
        int low = 0, high = nums.length - 1;
        while (low <= high) {
          // deduplications
          // 同样也是只保留重复的元素当中的最后一个
          // 为什么不能用这个？
          // while (low > 0 && nums[low - 1] == nums[low]) {
          //    low++;
          // }
          // while (high < nums.length - 1 && nums[high] == nums[high + 1]) {
          //    high--;
          // }
          while (low < nums.length - 1 && nums[low] == nums[low + 1]) {
            low++;
          }
          while (high > 0 && nums[high] == nums[high - 1]) {
            high--;
          }

          // binary Search: find the middle
          int mid = low + (high - low) / 2;
          // find the target
          if (nums[mid] == target) {
            return true;
          }
          // focus one the left part(if applicable)
          if (nums[mid] >= nums[low]) {
            // if the target is exactly in left part
            // do the classcical binary search in the left part
            if (target >= nums[low] && target < nums[mid]) {
              high = mid - 1;
            } else {
              low = mid + 1;
            }
          } 
          // focus on the right part(if applicable)
          if (nums[mid] <= nums[high]) {
            // if the target is exactly in right part
            // do the classical binary search in the right part
            if (target <= nums[high] && target > nums[mid]) {
              low = mid + 1;
            } else {
              high = mid - 1;
            }
          }
        }
        return false;
    }
}

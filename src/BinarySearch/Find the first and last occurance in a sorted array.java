/*
34.Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]

We could use binary search to achieve logN time complexity,
the thing is we could leverage the helper function so to check
the firstOccuranceElement Index and the lastOccuranceElement index
use int variable result to note down which index is used currently.
use a boolean variable so to update the binary search logic a bit.
*/



class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int firstOccurance = findElement(nums, target, true);
        int lastOccurance = findElement(nums, target, false);
        return new int[]{firstOccurance, lastOccurance};
    }

    private int findElement(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                // for the first occured element
                if (isFirst) {
                    right = mid - 1;
                } 
                // for the last occured element
                else {
                    left = mid + 1;
                }
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}

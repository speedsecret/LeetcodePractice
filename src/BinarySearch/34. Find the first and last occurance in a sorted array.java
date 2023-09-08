/*
34.Find First and Last Position of Element in Sorted Array
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

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
        // use one helper function to find the first element
        // and the last element
        // use 'int res == -1' as the searching index, if target not found, return res(-1) 
        int firstElementIndex = searchRange(nums, target, true);
        int lastElementIndex = searchRange(nums, target, false);
        return new int[]{firstElementIndex, lastElementIndex};
    }

    private int searchRange(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                // note down the current target Index, as a potential candidate
                res = mid;
                // This narrows down the search range to the left half, ensuring that we continue searching for the first occurrence to the left of the current mid position.
                if (isFirst) {
                    right = mid - 1;
                } 
                // This narrows down the search range to the right half, ensuring that we continue searching for the last occurrence to the right of the current mid position.
                else {
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
}

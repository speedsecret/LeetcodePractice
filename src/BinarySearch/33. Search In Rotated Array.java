/*
There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is 
[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1
*/

// Methodology
// check if both parties 
// condition1: [left, mid) would have a linear increasing or decreasing order
// plus to check if the target within range if (target >= nums[left] && target < nums[right])
// condition2: (mid, right] would have a linear increasing or decreasing order
// plus to check if the target within range if (target <= nums[right] && target > nums[left])
// also, the terminal condition of the while loop should be left <= right.


class Solution {
    public int search(int[] nums, int target) {
        // What is pivot
        // Pivot is the smallest element in the array, currently represent the start of rotated subarray;
        // Method 1: Find the pivotIndex then do the binary Search in each section
        // Method 2: Do the binary Search in one time, adding logic check

        // For Method 2:
        int left = 0;
        int right = nums.length - 1;
        // similar to the classicial binary search, it should including '==' signal here.
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            // case1: find the target
            if (nums[mid] == target) {
                return mid;
            }

            // case2: compare nums[mid] with leftBoundary, required the nums[mid] >= nums[left];
            // case2.1 target in [left, mid)
            // case2.2 else
            // left part is sorted, "==" should be included.
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // case3: compare nums[mid] with rightBoundary, required the nums[mid] <= nums[right];
            // case3.1 target in (mid, right]
            // case3.2 else
            // right part is sorted, "==" should be included.
            if (nums[mid] <= nums[right]) {
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

  
        /*
        // for Method1 && method2
        int left = 0;
        int right = nums.length - 1;
        int rightMost = right;
        // find the pivot
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] > nums[rightMost]) {
                left = mid + 1;
            } else {
                if (nums[mid] == target) {
                    return mid;
                }
                right = mid - 1;
            }
        }

        int res = classicalBinarySearch(nums, 0, left - 1, target);
        if (res != -1) {
            return res;
        }

        return classicalBinarySearch(nums, left, rightMost, target);
    }

        private int classicalBinarySearch(int[] nums, int left, int right, int target) {
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }
        */
}

/*
1283. Find the Smallest Divisor Given a Threshold
https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/

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
*/


class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        // Method1: naive way of finding divisor
        // we can started from the nums[i] to 1;
        // However, it contains too many duplicates computing which proves not necessary
        // Method2: We can started from divisor equals to nums[i];
        // but find the divisor in a binary way
        int left = 1, right = (int)1e6;
        while (left < right) {
            int sum = 0;
            int mid = (right - left) / 2 + left;
            for (int num : nums) {
                // would suggest to use (num + mid - 1) to get rid of the Math function call.
                sum += Math.ceil((double)num / mid); 
            }
            if (sum > threshold) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

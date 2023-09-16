/*
Leetcode 713. Subarray Product less than K
https://leetcode.com/problems/subarray-product-less-than-k/description/

Given an array of integers nums and an integer k, 
return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

Example 1:
Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

Example 2:
Input: nums = [1,2,3], k = 0
Output: 0
*/

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // Method1: the intuition is try to loop the array
        // from start to the end, to check all possible arrays
        // O(N^2)

        // Method2: Use two pointers, find the eligble elements in a sliding window
        // rapidly to have right side of the elements coming into the sliding window to see if that works.
        /*
        int left = 0, right = 0, curProduct = 1, counter = 0;
        while (right < nums.length) {
            int curRight = right;
            curProduct = 1;
            // check all possible product between start and right
            while (curProduct < k && curRight >= left) {
                curProduct *= nums[curRight--];
                if (curProduct < k) {
                    counter++;
                } else {
                    left++;
                    break;
                }
            }
            right++;
        }
        while (left < nums.length) {
            int curRight = nums.length - 1;
            curProduct = 1;
            while (curRight >= left && left <= right) {
                curProduct *= nums[curRight--];
            }
            left++;
        }
        return counter;
        */
        // Method3: Using sliding window but using right - left + 1
        // so we can add a bunch once at a time;
        int res = 0, left = 0, right = 0, product = 1;
        while (right < nums.length) {
            product *= nums[right];
            while (left <= right && product >= k) {
                product /= nums[left++];
            }
            res += right - left + 1;
            right++;
        }
        return res;
    }
}

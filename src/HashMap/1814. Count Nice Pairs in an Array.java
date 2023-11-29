/*
1814. Count Nice Pairs in an Array.java
https://leetcode.com/problems/count-nice-pairs-in-an-array/description/

You are given an array nums that consists of non-negative integers. 
Let us define rev(x) as the reverse of the non-negative integer x. For example, rev(123) = 321, 
and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all of the following conditions:

0 <= i < j < nums.length
nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.

Example 1:
Input: nums = [42,11,1,97]
Output: 2
Explanation: The two pairs are:
 - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
Example 2:
Input: nums = [13,10,35,24,76]
Output: 4

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
*/

class Solution {
    public int countNicePairs(int[] nums) {
        // Method:
        // Use HashMap after converting the each elements in nums into 
        // every piece of new result in the nums array
        // Step1: loop the whole nums array and established the array result
        // Step2: Initilized a hashMap and working on processing the updated array
        // Step3: calculate the result on the fly
        // nums[i] + rev(nums[j]) == nums[j] + rev(nums[i]);
        // nums[i] - rev(nums[i]) == nums[j] - rev(nums[j]);

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] - rev(nums[i]);
        }
        Map<Integer, Integer> dict = new HashMap<>();
        int res = 0;
        int MOD = (int)1e9 + 7;
        for (int i = 0; i < nums.length; i++) {
            // each time we would like to check if element is exist
            // if element has not seen, then the res is keep the same
            // if the element has seen, adding the value into previous result
            // then use that new res to mod the MOD.
            int ele = nums[i];
            res = (res + dict.getOrDefault(ele, 0)) % MOD;
            // update the dict.
            dict.put(ele, dict.getOrDefault(ele, 0) + 1);
        }
        return res;
    }

    private int rev(int ele) {
        int result = 0;
        while (ele > 0) {
            result = result * 10 + ele % 10;
            ele /= 10;
        }
        return result;
    }
}



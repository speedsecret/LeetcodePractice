/*
416. Partition Equal Subset Sum.java
https://leetcode.com/problems/partition-equal-subset-sum/description/

Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
*/


class Solution {
    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }
        // there can not exist a pair of same sum subsets.
        if (sum % 2 != 0) {
            return false;
        }
        // dp[value] represent if we could find some elements consisting subsets to make the sum as value
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = sum; i >= 0; i--) {
                if (dp[i]) {
                    dp[i + num] = true;
                }
            }
            // always to check if there is any possibilities that the nums can be divided into two parts.
            if (dp[sum / 2]) {
                return true;
            }
        }
        return dp[sum / 2];
    }
}

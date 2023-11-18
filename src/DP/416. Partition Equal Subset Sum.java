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
        // find if it is possible to find two subsets that consist of the same sum/2
        // methodology:
        // 1. calculate sum first
        // 2. check the edge case to see if the sum can be divide to 2 parts evenly
        // if (sum % 2 != 0) return false directly.
        // 3. use a boolean[] dp to check all possible subset part.(it is a bit of redundancy but it is proves high-quality.)
        // 4. loop the nums array, and check each possible subset combination by traversing the element from sum to 1.
        // 5. return false if not found a valid pair.

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // edge case check
        if (sum % 2 != 0) {
            return false;
        }
        boolean[] dp = new boolean[sum + 1];
        // key initialization
        // we knew it is always true to split 0 into two empty subsets, right?
        dp[0] = true;
        for (int num : nums) {
            for (int i = sum; i >= 0; i--) {
                if (dp[i]) {
                    dp[i + num] = true;
                }
            }
            if (dp[sum / 2]) {
                return true;
            }
        }
        return false;
    }
}

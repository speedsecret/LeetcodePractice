/*
494. target Sum.java
https://leetcode.com/problems/target-sum/

You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:
Input: nums = [1], target = 1
Output: 1
 
Constraints:
1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
*/
// Mindset:
// Use a dynamic Programing algorithm
// firstly, calculate the total, can create a dp[2 * total + 1] to represent all possible sum by using the int[] nums
// secondly, edge case check
// initialize addition and subtraction for the first elements, for nums[0]
// then use a for-loop to perform dp how?
// create a temparory array, as the same size as dp
// loop all possiblities(from -total to total)
// int addition = sum + nums[i] + total
// int subtraction = sum - nums[i] + total
// nextDp[addition] += dp[sum + total];
// nextDP[subtraction] += dp[sum + total];

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // Approach 1: DFS seems acceptable however the time complexity is too high -> 2^n
        // Apporach 2: DP 2D matrix
        // https://www.youtube.com/watch?v=r6Wz4W1TbuI 
        // set up array with
        // row == nums.length
        // column == 2 * total + 1;
        int total = Arrays.stream(nums).sum();
        int[] dp = new int[2 * total + 1];
        if (Math.abs(target) > total) {
            return 0;
        }

        // Initialize the DP array for the first element in nums
        dp[nums[0] + total] = 1;
        dp[-nums[0] + total] += 1;

        for (int i = 1; i < nums.length; i++) {
            // created a temperoary array
            int[] nextDp = new int[2 * total + 1];
            for (int sum = -total; sum <= total; sum++) {
                // it is important to check the current count ways are positive, which means it must be valid.
                if (dp[sum + total] > 0) {
                    // calculate the new sums by adding and subtracting the current element
                    int sumWithAddition = sum + nums[i] + total;
                    int sumWithSubtraction = sum - nums[i] + total;

                    // update the next DP array with the counts from the previous state
                    nextDp[sumWithAddition] += dp[sum + total];
                    nextDp[sumWithSubtraction] += dp[sum + total];
                }
            }
            // update the DP array for the next element
            dp = nextDp;
        }

        return dp[target + total];
    }
}

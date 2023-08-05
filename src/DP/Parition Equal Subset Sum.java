/*
Given an integer array nums, return true if you can partition the array into two subsets such that 
the sum of the elements in both subsets is equal or false otherwise.

eg1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

eg2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
*/

class Solution {
  public boolean canPartition(int[] nums) {
    if (nums == null || nums.length < 2) {
      return false;
    }
    int sum = 0;
    for (int num: nums) {
      sum += num;
    }
    if (sum % 2 != 0) {
      return false;
    }
    // Use DP
    boolean[] dp = new boolean[sum + 1];
    for (int num: nums) {
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
  
  public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] nums1 = {1, 5, 11, 5}; // Returns true (11 + 5 = 16 and 1 + 5 = 6)
        int[] nums2 = {1, 2, 3, 5};  // Returns false (No way to partition into two equal sums)
        
        boolean result1 = solution.canPartition(nums1);
        boolean result2 = solution.canPartition(nums2);
        
        System.out.println("Result for nums1: " + result1); // Expected: true
        System.out.println("Result for nums2: " + result2); // Expected: false
  }
}

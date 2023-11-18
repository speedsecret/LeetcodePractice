/*
Given an integer array nums and an integer k,
return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:

Input: nums = [4,3,2,3,5,2,1], k = 4
Output: true
Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Example 2:

Input: nums = [1,2,3,4], k = 3
Output: false

Constraints:
1 <= k <= nums.length <= 16
1 <= nums[i] <= 104
The frequency of each element is in the range [1, 4].
*/

public class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        return partitionToSubsets(nums.length - 1, new int[k], sum / k, nums);
    }

    private boolean partitionToSubsets(int startIndex, int[] candidateSums, int targetSum, int[] nums) {
        // base case:
        if (startIndex == -1) {
            return true;
        }

        for (int j = 0; j < candidateSums.length; ++j) {
            // deduplication
            if (j > 0 && candidateSums[j - 1] == candidateSums[j]) {
                continue;
            }
            // use nums[startIndex] == num as the important starting point.
            int num = nums[startIndex];
            // if adding the current num is larger than targetSum
            // move to the next candidateSum.
            if (num + candidateSums[j] > targetSum) {
                continue;
            }
            // when num + candidatesSum[j] <= targetSum
            // please eat in
            candidateSums[j] += num;
            // recursively to call the smallest question
            if (partitionToSubsets(startIndex - 1, candidateSums, targetSum, nums)) {
                return true;
            }
            // emit out
            candidateSums[j] -= num;
        }

        return false;
    }
}

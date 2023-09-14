/*
259. 3Sum smaller than k.java


Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i < j < k < n that 
satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example 1:

Input: nums = [-2,0,1,3], target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
[-2,0,1]
[-2,0,3]
Example 2:

Input: nums = [], target = 0
Output: 0
Example 3:

Input: nums = [0], target = 0
Output: 0
 
Constraints:

n == nums.length
0 <= n <= 3500
-100 <= nums[i] <= 100
-100 <= target <= 100
*/

// Methodology:
// Sort the array first, then use an int variable count to record the current available triplets.
// Outer loop, fix one index then use two pointer to loop the rest of the array
// Inner loop, compare the target, adding possible value counts back to the final result.
// T: O(n^2)
// S: O(1)

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int count = 0;
        Arrays.sort(nums);
        for (int start = 0; start < nums.length - 2; start++) {
            int left = start + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[start] + nums[left] + nums[right];
                if (sum < target) {
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }
}

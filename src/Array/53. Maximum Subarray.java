/*
53. Maximum Subarray.java
https://leetcode.com/problems/maximum-subarray/description/

Given an integer array nums, find the 
subarray with the largest sum, and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Example 2:
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
 

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

// Methodology:
// The objective is to determine the maximum sum within a subarray without the need to track the indices
// of this subarray. Instead, we can utilize two integer variables: `curSum` and `maxSum`.
// At each step, we decide whether to continue with the previous sum or to start anew with the current
// element.

class Solution {
    public int maxSubArray(int[] nums) {
        int curMax = nums[0], globalMax = nums[0];
        // index starts from i = 1;
        for (int i = 1; i < nums.length; i++) {
            // nums[i] represent we start it over
            // nums[i] + curMax means we would concatenate it from last member.
            curMax = Math.max(nums[i] + curMax, nums[i]);
            globalMax = Math.max(curMax, globalMax);
        }
        return globalMax;
    }
}

        // Method 2:
        // use divide and conquer
        /*
        class Solution {
            public int maxSubArray(int[] nums) {
                return maxSubArrayDivideAndConquer(nums, 0, nums.length - 1);
            }

            private int maxSubArrayDivideAndConquer(int[] nums, int left, int right) {
                if (left == right) {
                    return nums[left]; // Base case: Single element, return itself
                }

                int mid = left + (right - left) / 2; // Calculate the middle index

                // Recursively find the maximum subarray sum in the left and right halves
                int leftMax = maxSubArrayDivideAndConquer(nums, left, mid);
                int rightMax = maxSubArrayDivideAndConquer(nums, mid + 1, right);

                // Find the maximum subarray sum that crosses the middle
                int crossMax = maxCrossingSubArray(nums, left, mid, right);

                // Return the maximum of the three sums
                return Math.max(Math.max(leftMax, rightMax), crossMax);
            }

            private int maxCrossingSubArray(int[] nums, int left, int mid, int right) {
                int leftSum = Integer.MIN_VALUE;
                int sum = 0;

                // Find the maximum sum on the left side of the middle
                for (int i = mid; i >= left; i--) {
                    sum += nums[i];
                    leftSum = Math.max(leftSum, sum);
                }

                int rightSum = Integer.MIN_VALUE;
                sum = 0;

                // Find the maximum sum on the right side of the middle
                for (int i = mid + 1; i <= right; i++) {
                    sum += nums[i];
                    rightSum = Math.max(rightSum, sum);
                }

                // Return the sum of the maximum left and right subarrays
                return leftSum + rightSum;
            }
        }
        */
    }
}

/*
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

class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize variables to keep track of the maximum sum and the current sum
        int maxSum = nums[0]; // Assume the maximum sum is the first element
        int curSum = 0; // Initialize the current sum to 0

        // Loop through the array to find the maximum subarray sum
        for (int i = 0; i < nums.length; i++) {
            // The current sum is either the current element itself (starting a new subarray)
            // or the sum of the current element and the current sum (extending the subarray)
            curSum = Math.max(nums[i], curSum + nums[i]);

            // Update the maximum sum if the current sum is greater
            maxSum = Math.max(curSum, maxSum);
        }
        // Return the maximum sum found
        return maxSum;

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

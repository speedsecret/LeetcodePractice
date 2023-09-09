/*
Given an integer array nums, return the length of the longest strictly increasing 
subsequence.

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1

Constraints:
1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 
Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/

// Methodology:
// So the result must be a incresing list(sorted list)
// to dynamically maintaining a list, the intuition is can we use the binary search
// yes we can, we should maintain a List to store elements that can be the candidates of the final output
// as long as the current ele is strictly larger than the previous one in the list, we should add the current element into it
// otherwise, replace the smallest element which is greater or equals to current element by using binary search
// return the size of the longest subsequence.

// paying attention to the terminal condition in the while loop.

class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> res = new ArrayList<>();
        res.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > res.get(res.size() - 1)) {
                res.add(nums[i]);
            } else {
                int index = binarySearch(nums, res, nums[i]);
                res.set(index, nums[i]);
            }
        }
        return res.size();
    }

    private int binarySearch(int[] nums, List<Integer> res, int target) {
        int left = 0, right = res.size() - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (res.get(mid) < target) {
                left = mid + 1;
            } else if (res.get(mid) >= target) {
                right = mid;
            }
        }
        return left;
    }
}

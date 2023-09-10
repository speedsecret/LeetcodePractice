/*
4. Sum
https://leetcode.com/problems/4sum/

Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
*/


class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // we could use two pointers, but instead of resolving the 4sum problems alone
        // considering it as a kSum problem
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    private List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();

        // return res once start reaches to the end of nums
        if (start == nums.length) {
            return res;
        }

        // small trick here: calculate the average value of the element
        // if the smaller one is larger than the average or the larger one is smaller than average --> return res;
        long averageValue = target / k;
        if (nums[start] > averageValue || nums[nums.length - 1] < averageValue) {
            return res;
        }

        if (k == 2) {
            return twoSum(nums, target, start);
        }

        for (int i = start; i < nums.length; i++) {
            if (i == start || nums[i] != nums[i - 1]) {
                for (List<Integer> list : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(list);
                }
            }
        }
        return res;
    }

    private List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int low = start;
        int high = nums.length - 1;

        while (low < high) {
            int curSum = nums[low] + nums[high];
            if (curSum < target || (low > start && nums[low] == nums[low - 1])) {
                low++;
            } else if (curSum > target || (high < nums.length - 1 && nums[high] == nums[high + 1])) {
                high--;
            } else {
                res.add(Arrays.asList(nums[low++], nums[high--]));
            }
        }
        return res;
    }
}

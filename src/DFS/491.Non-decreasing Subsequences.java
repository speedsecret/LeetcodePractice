/*
Given an integer array nums, return all the different possible non-decreasing
subsequences of the given array with at least two elements. You may return the answer in any order.

Example 1:
Input: nums = [4,6,7,7]
Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]

Example 2:
Input: nums = [4,4,3,2,1]
Output: [[4,4]]
*/

class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(nums, res, list, 0);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> list, int index) {
        // base case
        if (index == nums.length) {
            // based on the details from problem
            if (list.size() >= 2) {
                res.add(new ArrayList<>(list));
            }
            return;
        }

        // recursive rule
        // case1: under the condition that we should choose the current elements
        // when the current list is empty or the current element is larger or equals to previous element.
        if (list.isEmpty() || list.get(list.size() - 1) <= nums[index]) {
            list.add(nums[index]);
            dfs(nums, res, list, index + 1);
            list.remove(list.size() - 1);
        }

        // check duplication when the we append and remove element finished.
        // deduplications
        if (list.size() > 0 && list.get(list.size() - 1) == nums[index]) {
            return;
        }

        // case2: under the condition that we should NOT choose the current elements
        dfs(nums, res, list, index + 1);
    }
}

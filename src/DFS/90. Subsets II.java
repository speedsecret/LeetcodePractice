/*
90. Subsets II.java
https://leetcode.com/problems/subsets-ii/description/

Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

Example 2:
Input: nums = [0]
Output: [[],[0]]

Constraints:
1 <= nums.length <= 10
-10 <= nums[i] <= 10
*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDup(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private void subsetsWithDup(int[] nums, List<List<Integer>> res, List<Integer> sol, int index) {
        // base case
        res.add(new ArrayList<>(sol));

        if (index == nums.length) {
            return;
        }

        // recursive rule
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i - 1] == nums[i]) {
                continue;
            }
            sol.add(nums[i]);
            // attention pls
            // this needs to be i + 1 instead of index + 1!!!
            // as we would need to use i to extract the element from the list.
            subsetsWithDup(nums, res, sol, i + 1);
            sol.remove(sol.size() - 1);
        }
    }
}

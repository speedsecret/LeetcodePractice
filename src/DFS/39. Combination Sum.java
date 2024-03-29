/*
39. Combination Sum.java
https://leetcode.com/problems/combination-sum/description/

Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target.
You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1: 
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:
Input: candidates = [2], target = 1
Output: []

Constraints:
1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40
*/

// Methodology:
// DFS and loop the index from i to candidates.length - 1
// terminal condition is when the target equals 0 or target smaller than 0
// for the recursive rule:
// the index parameter will keep the same and won't needs to be added up.

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int index) {
        // base case:
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        } else if (target < 0) {
            return;
        }

        // recursive rule:
        for (int i = index; i < candidates.length; i++) {
            // as i must within the range from [0, nums.length - 1]
            // so it is possible to give it a upper limit.
            list.add(candidates[i]);
            dfs(res, list, candidates, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
}

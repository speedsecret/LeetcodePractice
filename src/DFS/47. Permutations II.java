/*
47. Permutations II.java
https://leetcode.com/problems/permutations-ii/description/

Given a collection of numbers, nums, that might contain duplicates, 
return all possible unique permutations in any order.

Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Constraints:

1 <= nums.length <= 8
-10 <= nums[i] <= 10
*/
// Use a hashSet<List<Integer>> set
// Deduplication
// input parameters: index = index + 1

class Solution {
    private Set<List<Integer>> set = new HashSet<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        // sort and deduplication
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> sol, int index) {
        // base case:
        if (index == nums.length) {
            if (set.add(new ArrayList<>(sol))) {
                res.add(new ArrayList<>(sol));
            }
            return;
        }

        // recursive rule plus
        // deduplication
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            swap(nums, i, index);
            sol.add(nums[index]);
            dfs(nums, res, sol, index + 1);
            sol.remove(sol.size() - 1);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

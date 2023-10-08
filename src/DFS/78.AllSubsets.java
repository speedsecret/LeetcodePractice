/*
78.AllSubsets.java
https://leetcode.com/problems/subsets/description/


Given an integer array nums of unique elements, return all possible 
subsets(the power set). The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
*/

public class AllSubsets {
    // Methodology
    // This can be printed out as long as the index met the length of the array nums
    // Instead of just print it out no matter what in the recursive function

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, nums, new ArrayList<>(), 0);
        return res;    
    }

    private void dfs(List<List<Integer>> res, int[] nums, List<Integer> sol, int index) {
        // base case
        if (index == nums.length) {
            res.add(new ArrayList<>(sol));
            return;
        }

        // recursive rule
        // four lines of code
        sol.add(nums[index]); //--------> add the current element into the solution list
        dfs(res, nums, sol, index + 1); //--------> recursion based on adding current element in the solution list
        sol.remove(sol.size() - 1); //--------> remove the last element from the solution list
        dfs(res, nums, sol, index + 1); //--------> recursion based on not adding current element in the solution list
    }

    public static void main(String[] args) {
        String input = "abc";
        List<String> result = allSubsets(input);
        while (result.size() > 0) {
            System.out.println(result.get(result.size() - 1) + " ");
            result.remove(result.size() - 1);
        }
    }
}

/*
77. Combination.java
https://leetcode.com/problems/combinations/description/

Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
You may return the answer in any order.
Example 1:

Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
Example 2:

Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.

Constraints:
1 <= n <= 20
1 <= k <= n
*/

// Methodology: DFS
// it is possible to use 1 ~ n as the consisted component in the solution without duplication
// so to apply a for loop in the recursively rule
// Base case: sol.size() == k
// recursive rule: dfs(res, n, k, sol, i + 1);

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combination(res, n, k, new ArrayList<>(), 0);
        return res;
    }

    private void combination(List<List<Integer>> res, int n, int k, List<Integer> sol, int index) {
        // base case
        if (sol.size() == k) {
            res.add(new ArrayList<>(sol));
            return;
        }
        // recursive rule
        for (int i = index; i < n; i++) {
            sol.add(i + 1);
            combination(res, n, k, sol, i + 1);
            sol.remove(sol.size() - 1);
        }
    }
}

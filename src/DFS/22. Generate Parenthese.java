/*
22. Generate Parenthese.java
https://leetcode.com/problems/generate-parentheses/

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8
*/

// Methodology
// Use DFS with int variable left, right to record how many left/right bracket still remained to use.

class Solution {
    public List<String> generateParenthesis(int n) {
        // DFS use stringBuilder
        // Utilize two int variable left and right
        // the number of left side of parentheses must smaller or equals to right, otherwise return
        // the right must be larger than 0, else return
        // base case if both equals to 0, return
        List<String> res = new ArrayList<>();
        DFS(res, new StringBuilder(), n, n);
        return res;
    }

    private void DFS(List<String> res, StringBuilder sb, int left, int right) {
        // base case
        if (left == 0 && right == 0) {
            res.add(sb.toString());
            return;
        }

        // subproblem and recursive rules
        if (left > 0 && left <= right) {
            sb.append("(");
            DFS(res, sb, left - 1, right);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right > 0 && right > left) {
            sb.append(")");
            DFS(res, sb, left, right - 1);
            // we would need backtrack in this dfs solution
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

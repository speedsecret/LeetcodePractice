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

// 10.09.2023 version
// note: we still need backtrack.
class Solution {
    public List<String> generateParenthesis(int n) {
        // use two int variables, left and right
        // and initilize left equals to n, right equals to n too.
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), n, n);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, int leftRemain, int rightRemain) {
        // base case
        if (leftRemain == 0 && rightRemain == 0) {
            res.add(new String(sb));
            return;
        }
        if (leftRemain < 0 || rightRemain < 0 || leftRemain > rightRemain) {
            return;
        }
        // recursive rules:
        // No.1 check the leftRemain
        if (leftRemain > 0) {
            sb.append('(');
            dfs(res, sb, leftRemain - 1, rightRemain);
            sb.deleteCharAt(sb.length() - 1);
        }
        // No.2 check the rightRemain, it must have more leftover than leftRemain
        if (rightRemain > leftRemain) {
            sb.append(')');
            dfs(res, sb, leftRemain, rightRemain - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

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

/*
301. Remove Invalid Parentheses
https://leetcode.com/problems/remove-invalid-parentheses/description/
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.

Example 1:

Input: s = "()())()"
Output: ["(())()","()()()"]
Example 2:

Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]
Example 3:

Input: s = ")("
Output: [""]
 

Constraints:

1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.
*/

// Methodology:
// 1. DFS with leftRemovableParentheses, rightRemovableParentheses
// 2. Calculate and figure out the first two variables first
// 3. DFS recursively remove rightParentheses or leftParentheses

// the base case has two conditions, but both needs return
// the recursive rule include a for loop with dynamic start and an end index

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int l = 0; // Count of '(' parentheses needs to be removed
        int r = 0; // Count of ')' parentheses
        
        // Step 1: Calculate the minimum number of '(' and ')' parentheses to remove
        for (char ch : s.toCharArray()) {
            l += (ch == '(') ? 1 : 0;
            if (l == 0) {
                r += (ch == ')') ? 1 : 0;
            } else {
                l -= (ch == ')') ? 1 : 0;
            }
        }
        
        List<String> ans = new ArrayList<>();
        dfs(s, 0, l, r, ans);
        return ans;
    }
    
    private void dfs(String s, int start, int l, int r, List<String> ans) {
        // Step 2: Base case - No more parentheses to remove
        if (l == 0 && r == 0) {
            if (isValid(s)) {
                ans.add(s); // Add valid expression to the result
            }
            return;
        }
        
        // Step 3: Explore all possible combinations after removing parentheses
        for (int i = start; i < s.length(); ++i) {
            // Skip duplicates to avoid duplicates in result
            // However, work on the first character when we met consecutive characters.
            if (i != start && s.charAt(i) == s.charAt(i - 1)) {
                continue; 
            }
            
            // skip it when the char is smallcase letter
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                String curr = s.substring(0, i) + s.substring(i + 1); // Remove current parentheses
                // little trick here: remove right parentheses first
                if (s.charAt(i) == ')' && r > 0) {
                    // so in the subproblem the i is not moved one steps further.
                    dfs(curr, i, l, r - 1, ans); // Recursively explore with ')' removed
                } else if (s.charAt(i) == '(' && l > 0) {
                    dfs(curr, i, l - 1, r, ans); // Recursively explore with '(' removed
                }
            }
        }
    }

    private boolean isValid(String s) {
        int count = 0; // Count of '(' parentheses encountered
        for (char ch : s.toCharArray()) {
            // check if ch equals to left parenthese
            if (ch == '(') {
                count++;
            }
            if (ch == ')') {
                count--;
            }
            // At any given time, if ')' appears before '(' return false
            if (count < 0) {
                return false; 
            }
        }
        return count == 0; // Valid if count is 0 at the end
    }
}

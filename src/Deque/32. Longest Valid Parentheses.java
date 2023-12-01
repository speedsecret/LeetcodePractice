/*
32. Longest Valid Parentheses.java
https://leetcode.com/problems/longest-valid-parentheses/description/ 

Given a string containing just the characters '(' and ')', 
return the length of the longest valid (well-formed) parentheses substring.
Example 1:

Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
Example 2:

Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
Example 3:

Input: s = ""
Output: 0

Constraints:

0 <= s.length <= 3 * 104
s[i] is '(', or ')'.
*/

// Use a stack, and only input the left bracket index into the stack whenever we met it.
// Also, initlize the stack and push -1 into the stack at the beginning in case we found "()"

class Solution {
    // use a stack and only put the left bracket index into the stack
    // ues a int result to calculate the length of longest valid parentheses substring
    public int longestValidParentheses(String s) {
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // Initialize with a sentinel value
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                /*
                When we encounter a closing parenthesis, we pop an index from the stack. If the stack becomes empty after this pop operation, it means that there are no more valid substrings before this point, so we push a new sentinel value (the current index) onto the stack to "reset" it for the next potential valid substring.
                */
                if (stack.isEmpty()) {
                    stack.push(i); // Reset with a new sentinel
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}

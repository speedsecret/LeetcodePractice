/*
Leetcode 20: Valid Parentheses.java
https://leetcode.com/problems/valid-parentheses/

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false
*/

// Methodology:
// Use a stack, implemented by ArrayDeque to contain only left parentheses.
// Use a Map to store right parentheses as key, and left parentheses as value.
// return stack.isEmpty();

class Solution {
    Map<Character, Character> dict = new HashMap<>();

    public boolean isValid(String s) {
        // use a stack and only add the left bracket into the stack
        // if we met the right brack, check the stack first
        // case1: if it is not empty, 
        // case1.1: we should check if the there is a match, if so, pop it out
        // case1.2: if there is not a match, we should return false.
        // case2: if the stack is empty, return false;
        Deque<Character> stack = new ArrayDeque<>();
        dict.put('(', ')');
        dict.put('[', ']');
        dict.put('{', '}');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // case1:
            // if it is the left bracket, we should push it into the stack
            if (dict.containsKey(c)) {
                stack.offerLast(c);
            } 
            // case2:
            // if it is the right bracket, check the status of stack.
            else {
                // case2.1: the stack is empty, which means the right bracket is in front of left bracket, which is not acceptable.
                if (stack.isEmpty()) {
                    return false;
                } 
                // case2.2: the stack is NOT empty, pollLast() out and check if there is a match.
                else {
                    char preChar = stack.pollLast();
                    if (dict.get(preChar) != c) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}

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
    private static Map<Character, Character> map = new HashMap<>();

    public boolean isValid(String s) {
        map.put(']', '[');
        map.put(')', '(');
        map.put('}', '{');
        // then use a stack to only store the left bracket
        // cuz once it meets the right bracket, we would like to check if they can paired
        // if so just pop it out or just return false;
        // we never store right bracket into the stack only store left Bracket into the stack.
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            // the current curChar is right bracket, it must be paired or return false;
            if (map.containsKey(curChar)) {
                char firstCharInStack = stack.isEmpty() ? '#' : stack.peekFirst();
                if (map.get(curChar) == firstCharInStack) {
                    stack.pollFirst();
                }
                else{
                    return false;
                }
            } else {
                stack.offerFirst(curChar);
            }
        }
        return stack.isEmpty();
    }
}

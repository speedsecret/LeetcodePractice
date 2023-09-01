/*
Leetcode 224.Basic Calculator
https://leetcode.com/problems/basic-calculator/description/

Given a string s representing a valid expression, implement a basic calculator to evaluate it, 
and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
Input: s = "1 + 1"
Output: 2

Example 2:
Input: s = " 2-1 + 2 "
Output: 3

Example 3:
Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23

Constraints:

1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
*/

class Solution {
    public int calculate(String s) {
        // use stack
        // the other stack is used for keeping operation + - or ( or )
        // iterate the array and figure out there are 5 different cases. 
        Deque<Integer> stack = new ArrayDeque<>();
        int curVal = 0, result = 0, sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // construct the curVal so we can add it then
            if (Character.isDigit(ch)) {
                curVal = 10 * curVal + (int)(ch - '0');
            } 
            // adding the elements from left expression
            else if (ch == '+') {
                result += sign * curVal;
                sign = 1;
                // reset curVal
                curVal = 0;
            } else if (ch == '-') {
                result += sign * curVal;
                // set sign to -1
                sign = -1;
                // reset curVal
                curVal = 0;
            } else if (ch == '(') {
                stack.offerFirst(result);
                stack.offerFirst(sign);

                // to start a fresh: reset sign and current result
                sign = 1;
                result = 0;
            } else if (ch == ')') {
                result += sign * curVal;

                result *= stack.pollFirst();
                result += stack.pollFirst();

                curVal = 0;
            }
        }
        return result + (sign * curVal);
    }
}

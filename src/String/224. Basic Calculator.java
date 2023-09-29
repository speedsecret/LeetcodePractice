/*
224. Basic Calculator.java
https://leetcode.com/problems/basic-calculator/

Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

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

// Methodology
// we are basically needs to divide this situation into multiple cases
// case1: Character.isDigit
// case2: '+'
// case3: '-'
// case4: '('
// case5: ')'
// case6: ' '

class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0, curVal = 0, sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                curVal = 10 * curVal + (ch - '0');
            } else if (ch == '+') {
                result += curVal * sign;
                sign = 1;
                curVal = 0;
            } else if (ch == '-') {
                result += curVal * sign;
                sign = -1;
                curVal = 0;
            } else if (ch == '(') {
                // stack offerFirst result and sign
                // reset sign and result
                stack.offerFirst(result);
                stack.offerFirst(sign);

                sign = 1;
                result = 0;
            } else if (ch == ')') {
                // 收网
                result += sign * curVal;

                result *= stack.pollFirst();
                result += stack.pollFirst();

                // reset curVal
                curVal = 0;
            }
        }
        return result + curVal * sign;
    }
}

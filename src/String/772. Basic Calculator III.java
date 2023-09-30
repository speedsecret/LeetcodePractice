/*
772. Basic Calculator III.java
https://leetcode.com/problems/basic-calculator-iii/

Implement a basic calculator to evaluate a simple expression string.
The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. 
The integer division should truncate toward zero.
You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
Input: s = "1+1"
Output: 2
Example 2:
Input: s = "6-4/2"
Output: 4
Example 3:
Input: s = "2*(5+5*2)/3+(6/2+8)"
Output: 21

Constraints:
1 <= s <= 104
s consists of digits, '+', '-', '*', '/', '(', and ')'.
s is a valid expression.
*/

// Methodology
// Process the String from left to right
// Use an int variable index
// When we encounter '(' Do it recursively, when we encountered ')' we broke.

class Solution {
    int index = 0;
        
    public int calculate(String s) {
        int lastNumber = 0, currentNumber = 0, result = 0;
        char operator = '+';

        while (index < s.length()) {
            char ch = s.charAt(index++);

            if (Character.isDigit(ch)) {
                // Parse digits and construct the current number
                currentNumber = currentNumber * 10 + (ch - '0');
            } else if (ch == '(') {
                currentNumber = calculate(s);
            } else if (ch == ')') {
                break;
            } else if (ch != ' ') {
                // When an operator is encountered, perform the corresponding operation.
                // Update result and reset variables for the next operation.
                lastNumber = performOperation(lastNumber, currentNumber, operator);
                if (ch == '+' || ch == '-') {
                    result += lastNumber;
                    lastNumber = 0;
                }
                // update operator and current number
                currentNumber = 0;
                operator = ch;
            }
        }

        result += performOperation(lastNumber, currentNumber, operator);
        return result;
    }

    private int performOperation(int lastNum, int curNum, char op) {
        if (op == '+') {
            return lastNum + curNum;
        } else if (op == '-') {
            return lastNum - curNum;
        } else if (op == '*') {
            return lastNum * curNum;
        }
        return lastNum / curNum;
    }
}

/*
43. Multiply Strings.java
https://leetcode.com/problems/multiply-strings/description/

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"

Constraints:
1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
*/

// Methodology
// Construct to two StringBuilders from String num1 and String num2, reverse both StringBuilders
// Pre-reserve a (length == num1.length() + num2.length() StringBuilder)
// calculate the digit in each characters (two while loop, the inner loop also starts from 0)
// Reverse StringBuilders back

class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        StringBuilder firstNum = new StringBuilder(num1);
        StringBuilder secondNum = new StringBuilder(num2);

        firstNum.reverse();
        secondNum.reverse();

        // set a pre-reserved StringBuilder
        int length = num1.length() + num2.length();
        for (int i = 0; i < length; i++) {
            res.append('0');
        }

        // StringBuilder.charAt(xx) --> getting the character.
        // StringBuilder.charAt(xx) - '0' --> getting a single digit int variable.
        for (int i = 0; i < num2.length(); i++) {
            int digit2 = secondNum.charAt(i) - '0';

            for (int j = 0; j < num1.length(); j++) {
                int digit1 = firstNum.charAt(j) - '0';
                // figure out the index
                int pos = i + j;

                // read from previous result
                int carryOver = res.charAt(pos) - '0';  
                // calculate the current value
                int product = digit1 * digit2 + carryOver;
                // set the pos into the product 
                res.setCharAt(pos, (char) (product % 10 + '0'));
                
                // calculate the carryValue
                int carryValue = product / 10 + (res.charAt(pos + 1) - '0');
                res.setCharAt(pos + 1, (char)(carryValue + '0'));
            }
        }

        // check if the pre-reserved element is 0, if so, we just discard it
        if (res.charAt(res.length() - 1) == '0') {
            res.deleteCharAt(res.length() - 1);
        }

        return res.reverse().toString();
    }
}

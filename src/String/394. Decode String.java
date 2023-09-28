/*
394. Decode String.java
https://leetcode.com/problems/decode-string/

Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
The test cases are generated so that the length of the output will never exceed 105.


Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
*/

// Methodology
// Use two stacks, with int variable k to store the required repeated times.
// Use StringBuilder to store strings that we need to decode

class Solution {
    public String decodeString(String s) {
        // use two stacks
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            // case 1: if the current char is a digit
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } 
            // case 2: if the char is left bracket
            else if (ch == '[') {
                countStack.offerFirst(k);
                stringStack.offerFirst(currentString);
                // reset the currentString
                currentString = new StringBuilder();
                // reset the k
                k = 0;
            }
            // case 3: if the char is right bracket
            else if (ch == ']') {
                StringBuilder decodedString = stringStack.pollFirst();
                // start to append the decodedString back to the final result
                for (int i = countStack.pollFirst(); i > 0 ; i--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            }
            // case 4: char is lowerCase Letter
            else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }
}

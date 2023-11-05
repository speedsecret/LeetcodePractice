/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.
Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
*/

// Methodology:
// Using DP to started from bottom to the up corner.
// Use 2D array to check each possible char accordingly.

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // Method1: Initial DP
        int[][] dpGrid = new int[text1.length() + 1][text2.length() + 1];

        for (int col = text2.length() - 1; col >= 0; col--) {
            for (int row = text1.length() - 1; row >= 0; row--) {
                if (text1.charAt(row) == text2.charAt(col)) {
                    dpGrid[row][col] = 1 + dpGrid[row + 1][col + 1];
                } else {
                    dpGrid[row][col] = Math.max(dpGrid[row + 1][col], dpGrid[row][col + 1]);
                }
            }
        }
        return dpGrid[0][0];
        // Method2: Optimized DP with a better space complexity
        // use DP to check their match relationships
        /*
        if (text1.length() > text2.length()) {
            String temp = text2;
            text1 = text2;
            text2 = temp;
        }
        int[] previous = new int[text1.length() + 1];
        int[] current = new int[text1.length() + 1];
        for (int j = text2.length() - 1; j >= 0; j--) {
            for (int i = text1.length() - 1; i >= 0; i--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    current[i] = 1 + previous[i + 1];
                } else {
                    current[i] = Math.max(previous[i], current[i + 1]);
                }
            }
            int[] temp = previous;
            previous = current;
            current = temp;
        }
        return previous[0];
        */
    }
}

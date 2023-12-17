/*
72. Edit Distance.java
https://leetcode.com/problems/edit-distance/

Given two strings word1 and word2, return the minimum number of operations
required to convert word1 to word2.
You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 
Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
*/

class Solution {
    public int minDistance(String word1, String word2) {
        // use DP with a 2-D int matrix
        // why, there are three possiblities, the first is adding operation
        // the second is removing operations, the final one is delete operation

        // check edge case first
        // then we would need to check when either word1 is empty or word2 is empty cases.

        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }

        // the newly created int[][] matrix should be with length equals to word1.length() + 1
        // with width length equals to word2.length() + 1
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // for all rows, in another word, for the vertical line:
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        // for all coloumns, in another word, for the horizontal line:
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                // case1:
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // do nothing
                    // just copy whatever the value in the anti-diagnoal cell
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                // case2:
                else {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}

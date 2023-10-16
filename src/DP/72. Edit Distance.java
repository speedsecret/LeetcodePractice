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
    // https://www.youtube.com/watch?v=b6AGUjqIPsA
    public int minDistance(String word1, String word2) {
        int word1Length = word1.length(), word2Length = word2.length();
        if (word1Length == 0) {
            return word2Length;
        }
        if (word2Length == 0) {
            return word1Length;
        }

        int[][] grid = new int[word1Length + 1][word2Length + 1];
        // update the first row
        // update the first col
        for (int i = 1; i <= word1Length; i++) {
            grid[i][0] = grid[i - 1][0] + 1;
        }
        for (int j = 1; j <= word2Length; j++) {
            grid[0][j] = grid[0][j - 1] + 1;
        }
        for (int word1Index = 1; word1Index <= word1Length; word1Index++) {
            for (int word2Index = 1; word2Index <= word2Length; word2Index++) {
                // check if two characters are the same
                if (word1.charAt(word1Index - 1) == word2.charAt(word2Index - 1)) {
                    // so just copy the diagonal direction of cell into the current one.
                    grid[word1Index][word2Index] = grid[word1Index - 1][word2Index - 1];
                } else {
                    grid[word1Index][word2Index] = Math.min(grid[word1Index - 1][word2Index],
                                        Math.min(grid[word1Index][word2Index - 1], grid[word1Index - 1][word2Index - 1])) + 1;
                }
            }
        }
        return grid[word1Length][word2Length];
    }
}

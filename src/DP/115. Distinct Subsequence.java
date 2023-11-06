/*
115. Distinct Subsequence.java
https://leetcode.com/problems/distinct-subsequences/


Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
 

Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
*/
// Methodology
// Kept using DP.

class Solution {
    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        // Brutal force:
        // check all subsequences that s have
        // then count how many are matching with String t

        // use DP, but I am not sure the implementation details.
        // so the base case if how many subsequence can be matched from a empty String to a empty String? --> ans: 1.
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        // when the t is empty while the s is non-empty
        // so it is determined to have 1 match
        for (int j = 0; j <= t.length(); j++) {
            dp[s.length()][j] = 0;
        }
        for (int i = 0; i <= s.length(); i++) {
            dp[i][t.length()] = 1;
        }
        
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = t.length() - 1; j >= 0; j--) {
                // If the characters at the current positions match
                if (s.charAt(i) == t.charAt(j)) {
                    // Count the subsequences including the current characters (match case)
                    // only added the bottom and diagonal status into the current dp cell.
                    dp[i][j] = dp[i + 1][j] + dp[i + 1][j + 1];
                } else {
                    // No match: count subsequences without the current character of s
                    // we are not seen a match at the current i + 1 position, hence
                    // we need to shift 1 index in string s and hopefully to find match in the next round, without advance any indcies in string t.
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
}

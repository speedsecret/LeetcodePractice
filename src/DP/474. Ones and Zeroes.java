/*
474. Ones and Zeroes.java
https://leetcode.com/problems/ones-and-zeroes/description/

You are given an array of binary strings strs and two integers m and n.
Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
A set x is a subset of a set y if all elements of x are also elements of y.

Example 1:

Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
{"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
Example 2:

Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 

Constraints:

1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] consists only of digits '0' and '1'.
1 <= m, n <= 100
*/
// Methodology
// This is the classcial backpacking problem!

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // classcial backpacking question
        // given an array, you need to get the most elements, within max of m 0s and max of n 1s.
        // return the maximum result.
        // DP
        int[][] dp = new int[m + 1][n + 1];
        
        for (String s : strs) {
            int[] count = countZerosAndOnes(s);
            // Iterate through the dp matrix in reverse order to avoid overwriting values.
            for (int zeros = m; zeros >= count[0]; zeros--) {
                for (int ones = n; ones >= count[1]; ones--) {
                    // Update dp[zeroes][ones] with the maximum between including the
                    // current string or not including it.
                    dp[zeros][ones] = Math.max(1 + dp[zeros - count[0]][ones - count[1]], dp[zeros][ones]);
                }
            }
        }
        return dp[m][n];
    }

    // use an int[] array to record how many 0s and how many 1s in each string.
    private int[] countZerosAndOnes(String s) {
        int[] count = new int[2];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - '0']++;
        }
        return count;
    }
}

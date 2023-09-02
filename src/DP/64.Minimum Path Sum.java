/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12
*/

class Solution {
    public int minPathSum(int[][] grid) {

        // [Preferred]Method 1: use current matrix not taking extra space.
        int m = grid.length, n = grid[0].length;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j != n - 1) {
                    grid[i][j] = grid[i][j] + grid[i][j + 1];
                } else if (i != m - 1 && j == n - 1) {
                    grid[i][j] = grid[i][j] + grid[i + 1][j];
                } else if (i != m - 1 && j != n - 1) {
                    grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j], grid[i][j + 1]);
                }
            }
        }
        return grid[0][0];

        /*
        // Method 2: use DP with an extra size n * n matrix and traverse from bottom right to top left iteratively
        int m = grid.length, n = grid[0].length;
        // actually there are 4 different position that applys to different DP strategies.
        // starting point
        // bottom level not the starting point
        // rightMost col not the starting point
        // other wise, look back at down and right position.
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // bottom right the most(starting point)
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = grid[i][j];
                }
                // bottom level, look back in right
                else if (i == m - 1 && j != n - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } 
                // rightMost col, look back in down
                else if (j == n - 1 && i != m - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } 
                // otherwise
                else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                } 
            }
        }
        return dp[0][0];
        */
    }
}

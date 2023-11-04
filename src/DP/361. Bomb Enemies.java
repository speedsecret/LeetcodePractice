/*
361. Bomb Enemies.java
https://leetcode.com/problems/bomb-enemy/description/

Given an m x n matrix grid where each cell is either a wall 'W', an enemy 'E' or empty '0', return the maximum enemies you can kill using one bomb.
You can only place the bomb in an empty cell.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since it is too strong to be destroyed.

Example 1:
Input: grid = [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3
Example 2:
Input: grid = [["W","W","W"],["0","0","0"],["E","E","E"]]
Output: 1

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 'W', 'E', or '0'.
*/

// BFS approach:
public class Solution {
    private static final char WALL = 'W';
    private static final char ENEMY = 'E';
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int maxResult = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] count = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '0') {
                    int enemies = countEnemiesKilled(grid, i, j, rows, cols);
                    count[i][j] = enemies;
                    maxResult = Math.max(maxResult, enemies);
                }
            }
        }

        return maxResult;
    }

    private int countEnemiesKilled(char[][] grid, int x, int y, int rows, int cols) {
        int enemies = 0;
        for (int[] dir : dirs) {
            int neiRow = x + dir[0];
            int neiCol = y + dir[1];
            
            while (neiRow >= 0 && neiRow < rows && neiCol >= 0 && neiCol < cols && grid[neiRow][neiCol] != WALL) {
                if (grid[neiRow][neiCol] == ENEMY) {
                    enemies++;
                }
                neiRow += dir[0];
                neiCol += dir[1];
            }
        }
        return enemies;
    }
}


// DP approach:
public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        
        int maxKill = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W') {
                    continue;
                }

                if (grid[i][j] == '0') {
                    int up = i == 0 ? 0 : dp[i - 1][j];
                    int left = j == 0 ? 0 : dp[i][j - 1];

                    int bottom = i == m - 1 ? 0 : countBottom(grid, i, j, m);
                    int right = j == n - 1 ? 0 : countRight(grid, i, j, n);
                    
                    maxKill = Math.max(maxKill, up + left + bottom + right);
                }
            }
        }
        return maxKill;
    }

    private int countBottom(char[][] grid, int i, int j, int m) {
        int count = 0;
        for (int k = i + 1; k < m && grid[k][j] != 'W'; k++) {
            if (grid[k][j] == 'E') {
                count++;
            }
        }
        return count;
    }

    private int countRight(char[][] grid, int i, int j, int n) {
        int count = 0;
        for (int k = j + 1; k < n && grid[i][k] != 'W'; k++) {
            if (grid[i][k] == 'E') {
                count++;
            }
        }
        return count;
    }
}

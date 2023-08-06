/*
You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). 
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The testcases are generated so that the answer will be less than or equal to 2 * 109.

Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/


class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Attention please:
        // 1 is obstacle
        // 0 is okay
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        obstacleGrid[0][0] = 1;
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        for (int i = 1; i < row; i++) {
            // equals to 1 here represent the previous path is workable, instead of a obstacle!
            // which means when previous path works plus the current one is not an obstacle, then we mark the current path as a workable solution.
            obstacleGrid[i][0] = (obstacleGrid[i - 1][0] == 1 && obstacleGrid[i][0] == 0) ? 1 : 0;
        }
        for (int i = 1; i < col; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i - 1] == 1 && obstacleGrid[0][i] == 0) ? 1 : 0;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // Case1: this is a workable spot in any of the workable solutions, check the relevant paths.
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1] + obstacleGrid[i - 1][j];
                } 
                // Case2: this isn't work, but we need to reinforced that this should equal to 0.
                else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[row - 1][col - 1];
    }
}

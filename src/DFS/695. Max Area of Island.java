/*
695. Max Area of Island.java
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.
Return the maximum area of an island in grid. If there is no island, return 0.

Example 1:
Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],
[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0
 
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
*/

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        // Methodology
        // DFS to find all possibilities that can concatenate into a new island
        // initilize curIslandSize == 0 to begin with in each DFS call stack.
        // after getting the result back, compare with the globalMaxIsland, to update the globalMaxIsland if applicable.
        int globalMaxIsland = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int curIsLandSize = dfs(i, j, grid);
                    globalMaxIsland = Math.max(globalMaxIsland, curIsLandSize);
                }
            }
        }
        return globalMaxIsland;
    }

    private int dfs(int row, int col, int[][] grid) {
        // base case:
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return 0;
        }
        // flip the current cell into 0 to avoid duplicate visiting.
        grid[row][col] = 0;
        return 1 + dfs(row + 1, col, grid)
                 + dfs(row - 1, col, grid)
                 + dfs(row, col + 1, grid)
                 + dfs(row, col - 1, grid);
    }
}

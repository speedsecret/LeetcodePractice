/*
Leetcode 994:
https://leetcode.com/problems/rotting-oranges/description/

You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
*/

class Solution {
    private final int[][] dirs = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
    public int orangesRotting(int[][] grid) {
        // BFS to traverse elements level by level
        // use an int variable to calculate how many mins it would take
        // use boolean[][] visited to keep an order of elements have been visited.
        // revisited the grid again at the final and to see if anything missed, return -1 if there still fresh orange persist.
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        Deque<int[]> queue = new ArrayDeque<>();
        int step = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    visited[i][j] = true;
                    queue.addFirst(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] rottenPos = queue.pollLast();
                int curRow = rottenPos[0];
                int curCol = rottenPos[1];
                for (int[] dir : dirs) {
                    int neiRow = curRow + dir[0];
                    int neiCol = curCol + dir[1];
                    if (neiRow >= 0 && neiRow < row && neiCol >= 0 && neiCol < col && !visited[neiRow][neiCol] && grid[neiRow][neiCol] == 1) {
                        visited[neiRow][neiCol] = true;
                        grid[neiRow][neiCol] = 2;
                        queue.addFirst(new int[]{neiRow, neiCol});
                    }
                }
            }
            if (queue.isEmpty()) {
                break;
            }
            step++;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] == false && grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return step;
    }
}

/*
542. 01Matrix.java
https://leetcode.com/problems/01-matrix/

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1.

Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:
Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.
*/

/*
Methodoloy:
Start from all 0, then we would traverse the rest of the matrix
Use boolean[][] matrix to note down elements we had visited so far,
  the first touched elements must be the one have the closest distance
Also, we need to construct a new class to store row, col, and steps(or an int[]{row, col, distance} arr)
*/

class State {
    int row;
    int col;
    int steps;
    public State(int row, int col, int steps) {
        this.row = row;
        this.col = col;
        this.steps = steps;
    }
}

class Solution {
    private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] matrix = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        Deque<State> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offerFirst(new State(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        // BFS logic starts here and updated elements step by step
        while (!queue.isEmpty()) {
            State curState = queue.pollLast();
            int curRow = curState.row, curCol = curState.col, steps = curState.steps;
            // four directions
            for (int[] dir : dirs) {
                int neiRow = curRow + dir[0];
                int neiCol = curCol + dir[1];
                // the neiRow and neiCol is within the current Boundary
                // they have not visited before
                // all other unvisited spots are defaulted to 1, because all 0 have been visited.
                if (neiRow >= 0 && neiRow < m && neiCol >= 0 && neiCol < n && !visited[neiRow][neiCol]) {
                    visited[neiRow][neiCol] = true;
                    queue.offerFirst(new State(neiRow, neiCol, steps + 1));
                    matrix[neiRow][neiCol] = steps + 1;
                }
            }
        }
        return matrix;
    }
}

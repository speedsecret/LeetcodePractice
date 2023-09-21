/*
490. The Maze.java
https://leetcode.com/problems/the-maze/description/

There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). 
The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling 
until hitting a wall. When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.

You may assume that the borders of the maze are all walls (see examples).

Example:
Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
*/

// Methodology:
// We should basically ask about ourselves what are the unique characters for this problem?
// In that cases, determine what are the terminal conditions, and what are the recursive rule.

class Solution {
    public static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // DFS
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return DFS(maze, start[0], start[1], destination, visited);
    }

    private boolean DFS(int[][] maze, int row, int col, int[] destination, boolean[][] visited) {
        // base case 1 --> return false
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || maze[row][col] == 1 || visited[row][col]) {
            return false;
        }
        // base case 2 which means we can reach out to the dest --> return true
        if (row == destination[0] && col == destination[1]) {
            return true;
        }

        // mark current spot has been visited
        // also as we won't back track the spot we visited, so we don't reset it back to false
        // Once you change direction, you won't return to the previous cell because there's no need to backtrack in the maze-solving context.
        visited[curRow][curCol] = true;
        for (int[] dir : directions) {
            int neiRow = row + dir[0];
            int neiCol = col + dir[1];

            // the ball wouldn't stop until it hit the wall
            while (neiRow >= 0 && neiRow < maze.length && neiCol >= 0 && neiCol < maze[0].length && maze[neiRow][neiCol] == 0) {
                neiRow = neiRow + dir[0];
                neiCol = neiCol + dir[1];
            }

            // back one step to make sure that spot is within the boundary.
            neiRow -= dir[0];
            neiCol -= dir[1];

            // To check if it has been visited && recursively check neighbor
            if (!visited[neiRow][neiCol] && DFS(maze, neiRow, neiCol, destination, visited)) {
                return true;
            }
        }
        return false;
    }
}


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
// Use DFS to traverse the possible rolling path all the way to the target destination
// In the nested DFS method, mark the current visited spot as true which means it spot has visited
// and no longer need to revisit again.
// In that cases, determine what are the terminal conditions(is the index coordinator is out of the boundary,
// and what are the recursive rule: the recursive rule is check if the valid (neiRow, neiCol) spot
// which has not been visited is returning true

class Solution {
    // DFS with a boolean matrix
    private int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(visited, maze, start[0], start[1], destination);
    }

    private boolean dfs(boolean[][] visited, int[][] maze, int row, int col, int[] dest) {
        // base case:
        // also check if the current spot has been visited before, if so just return false
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length
            || maze[row][col] == 1 || visited[row][col]) {
            return false;
        } 
        if (row == dest[0] && col == dest[1]) {
            return true;
        }
        // recursive call:
        visited[row][col] = true;
        for (int[] dir : dirs) {
            int neiRow = row + dir[0];
            int neiCol = col + dir[1];

            while (neiRow >= 0 && neiRow < maze.length && neiCol >= 0 && neiCol < maze[0].length && maze[neiRow][neiCol] == 0) {
                neiRow += dir[0];
                neiCol += dir[1];
            }

            neiRow -= dir[0];
            neiCol -= dir[1];
            if (dfs(visited, maze, neiRow, neiCol, dest)) {
                return true;
            }
        }
        return false;
    }
}

/*
class Solution {
    // DFS with a boolean matrix
    private int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(visited, maze, start[0], start[1], destination);
    }

    private boolean dfs(boolean[][] visited, int[][] maze, int row, int col, int[] dest) {
        // base case:
        // I am not care if this node has been visited before, as it can still be a valid part of a path
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || maze[row][col] == 1) {
            return false;
        } 
        if (row == dest[0] && col == dest[1]) {
            return true;
        }
        // recursive call:
        visited[row][col] = true;
        for (int[] dir : dirs) {
            int neiRow = row + dir[0];
            int neiCol = col + dir[1];

            while (neiRow >= 0 && neiRow < maze.length && neiCol >= 0 && neiCol < maze[0].length && maze[neiRow][neiCol] == 0) {
                neiRow += dir[0];
                neiCol += dir[1];
            }

            neiRow -= dir[0];
            neiCol -= dir[1];
            // double check if the visited[neiRow][neiCol] is false.
            if (!visited[neiRow][neiCol] && dfs(visited, maze, neiRow, neiCol, dest)) {
                return true;
            }
        }
        return false;
    }
}
*/

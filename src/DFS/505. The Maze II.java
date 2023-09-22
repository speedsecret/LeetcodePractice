/*
505. The Maze II.java
https://leetcode.com/problems/the-maze-ii/description/

There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling
up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol]
return the shortest distance for the ball to stop at the destination. If the ball cannot stop at destination, return -1.

The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).

You may assume that the borders of the maze are all walls (see examples).

Example:
Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: 12
  
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
The length of the path is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
*/

// Methodology: PQ + BFS + dijkstra(shortest distance)
// Use a priorityQueue to store the smallest distances element(State class)
// Use an int[][] distance to record previous distance configuration for every spot in the matrix
// Use a new Class State to record the row, col and distance
// Traverse the maze from the starting point, updating the distance matrix on the fly.
// this.State.distance represent the distance from starting point to current spot

class State {
    int row;
    int col;
    int distance;
    public State (int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}
class Solution {
    private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        int[][] distance = new int[m][n];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, distance, start);
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }

    private void dijkstra(int[][] maze, int[][] distance, int[] start) {
       // BFS + PQ
       PriorityQueue<State> pq = new PriorityQueue<>(
           (a, b) -> a.distance - b.distance
       );
       pq.add(new State(start[0], start[1], 0));
       while (!pq.isEmpty()) {
           State curState = pq.poll();
           int curRow = curState.row;
           int curCol = curState.col;
           if (distance[curRow][curCol] < curState.distance) {
               continue;
           }
           for (int[] dir : dirs) {
               int neiRow = curRow + dir[0];
               int neiCol = curCol + dir[1];
	       int count = 0;
               while (neiRow >= 0 && neiRow < maze.length && neiCol >= 0 && neiCol < maze[0].length && maze[neiRow][neiCol] == 0) {
                   neiRow += dir[0];
                   neiCol += dir[1];
                   count++;
               }

               // check which distance is smaller, if the current distance is smaller, adding it into the queue
               // we need to subtract 1 index for both row and col, given we need to return to the previous position.
               neiRow -= dir[0];
               neiCol -= dir[1];
               if (distance[curRow][curCol] + count < distance[neiRow][neiCol]) {
                   distance[neiRow][neiCol] = distance[curRow][curCol] + count;
                   pq.add(new State(neiRow, neiCol, distance[neiRow][neiCol]));
               }
           }
       }
    }
}

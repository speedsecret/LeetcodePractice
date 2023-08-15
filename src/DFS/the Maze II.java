/*
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

class Solution {
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // BFS + Dijkstra + PriorityQueue Method
        int[][] distance = new int[maze.length][maze[0].length];
        // fill in each element with maximum integer.
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, start, distance);
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }

    private void dijkstra(int[][] maze, int[] start, int[][] distance) {
        // No base case
        // adding boolean[][] matrix
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        PriorityQueue<int[]> minDistanceQueue = new PriorityQueue<>(
            (a, b) -> a[2] - b[2]
        );
        minDistanceQueue.offer(new int[]{start[0], start[1], 0});
        while (!minDistanceQueue.isEmpty()) {
            // O(logmn) 
            int[] s = minDistanceQueue.poll();
            // if the distance from current node to start node is smaller then the previous one(s[2]), we would skip it.
            if (distance[s[0]][s[1]] < s[2]) {
                continue;
            }
            visited[s[0]][s[1]] = true;
            // then exhuasted all possible directions
            for (int[] dir : dirs) {
                int neiRow = s[0] + dir[0];
                int neiCol = s[1] + dir[1];
                int count = 0;
                // until the ball hit the wall, the count wouldn't stop iterating.
                while (neiRow >= 0 && neiRow < maze.length && neiCol >= 0 && neiCol < maze[0].length && maze[neiRow][neiCol] == 0) {
                    neiRow += dir[0];
                    neiCol += dir[1];
                    count++;
                }
                // while current path take shorter distance will keep adding min elements into the queue 
                // attention please, you need to return row and col index one step back
                if (distance[s[0]][s[1]] + count < distance[neiRow - dir[0]][neiCol - dir[1]]) {
                    distance[neiRow - dir[0]][neiCol - dir[1]] = distance[s[0]][s[1]] + count;
                    minDistanceQueue.offer(new int[]{neiRow - dir[0], neiCol - dir[1], distance[neiRow - dir[0]][neiCol - dir[1]]});
                }
            }
        } 
    }
}

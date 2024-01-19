/*
499. Maze III.java
https://leetcode.com/problems/the-maze-iii/description/

There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). 
The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls onto the hole.

Given the m x n maze, the ball's position ball and the hole's position hole, where ball = [ballrow, ballcol] 
and hole = [holerow, holecol], return a string instructions of all the instructions that the ball should follow to drop in the hole with the shortest distance possible. 
If there are multiple valid instructions, return the lexicographically minimum one. If the ball can't drop in the hole, return "impossible".

If there is a way for the ball to drop in the hole, the answer instructions should contain the characters 'u' (i.e., up), 
'd' (i.e., down), 'l' (i.e., left), and 'r' (i.e., right).

The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).

You may assume that the borders of the maze are all walls (see examples).
 
*/

// mindset of this question:
// key challenges: how the ball is moving, the ball would drop in the hole once met
// there are 4 directions the ball can move to, "l", "r", "u", "d";

class State {
    int row;
    int col;
    int dist;
    String path;
    
    public State(int row, int col, int dist, String path) {
        this.row = row;
        this.col = col;
        this.dist = dist;
        this.path = path;
    }
}

class Solution {
    int[][] directions = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    String[] textDirections = {"l", "u", "r", "d"};
    int m, n;
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        m = maze.length;
        n = maze[0].length;
        
        // not only we need to compare the distance
        // but also if the distance is the same
        // the comparator should be the lexicographical order
        PriorityQueue<State> heap = new PriorityQueue<>((a, b) -> { 
            if (a.dist == b.dist) {
                return a.path.compareTo(b.path);
            }
            return a.dist - b.dist;
        });
        
        // initilized an boolean matrix
        boolean[][] seen = new boolean[m][n];
        // set up the heap.
        heap.add(new State(ball[0], ball[1], 0, ""));
        
        while (!heap.isEmpty()) {
            State curr = heap.remove();
            int row = curr.row;
            int col = curr.col;
            
            if (seen[row][col]) {
                continue;
            }
            
            // once we hit the hole, we could automatically return the result as
            // the heap is sorted by two policies:
            // 1. the distance
            // 2. if the distance is the same, compare its string chornological value
            if (row == hole[0] && col == hole[1]) {
                return curr.path;
            }
            
            // mark the current point is true
            seen[row][col] = true;
            
            // check all the neighbors of the current state
            for (State nextState: getNeighbors(row, col, maze, hole)) {
                int nextRow = nextState.row;
                int nextCol = nextState.col;
                int nextDist = nextState.dist;
                String nextChar = nextState.path;
                heap.add(new State(nextRow, nextCol, curr.dist + nextDist, curr.path + nextChar));
            }
        }
        
        return "impossible";
    }
    
    private List<State> getNeighbors(int row, int col, int[][] maze, int[] hole) {
        List<State> neighbors = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            int dy = directions[i][0];
            int dx = directions[i][1];
            String direction = textDirections[i];
            
            int currRow = row;
            int currCol = col;
            int dist = 0;
            
            while (valid(currRow + dy, currCol + dx, maze)) {
                currRow += dy;
                currCol += dx;
                dist++;
                if (currRow == hole[0] && currCol == hole[1]) {
                    break;
                }
            }
            
            neighbors.add(new State(currRow, currCol, dist, direction));
        }
        
        return neighbors;
    }

    private boolean valid(int row, int col, int[][] maze) {
        if (row < 0 || row >= m || col < 0 || col >= n || maze[row][col] != 0) {
            return false;
        }
        return true;
    }
}

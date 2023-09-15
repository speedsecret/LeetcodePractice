/*
773. Sliding Puzzle.java
https://leetcode.com/problems/sliding-puzzle/description/ 

On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally
adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given the puzzle board board, return the least number of moves required so that the state of the board is solved. 
If it is impossible for the state of the board to be solved, return -1.
Example 1:
Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Example 2:
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Example 3:
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]

Constraints:

board.length == 2
board[i].length == 3
0 <= board[i][j] <= 5
Each value board[i][j] is unique.
*/

class Pair {
    String state;
    int index;

    public Pair(String state, int index) {
        this.state = state;
        this.index = index;
    }
}

class Solution {
    // this idx int[] array is something we need to figure out by understanding when '0' 
    // has been placed in the 6 spots, what are the index of its swappable spots.
    final int[][] idx = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
    public int slidingPuzzle(int[][] board) {
        // Methodology:
        // Using BFS and tune to board into 1D String, by using StringBuilder
        // Starts with '0' and its all exchangeable neighbors
        // After doing the swap, validate and to know if the new string meets the goal string
        // If not added the current swapped string the queue and to the visited hashSet
        // return -1 if traversed all possible routes.
        String goal = "123450";
        String start = boardToString(board);

        if (start.equals(goal)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        int steps = 0;
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(start, start.indexOf("0")));

        // BFS and find minimum number of moves
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            while (size-- > 0) {
                Pair curPos = queue.poll();
                for (int index : idx[curPos.index]) {
                    // Generate the next state by swapping the empty cell with its neighbor
                    String nextState = swap(curPos.state, curPos.index, index);

                    // check if the new state has been visited before
                    if (visited.contains(nextState)) {
                        continue;
                    }

                    // check if we have reached the goal state
                    if (nextState.equals(goal)) {
                        return steps;
                    }

                    // Mark the new State as visited and add it to the queue for further exploration
                    visited.add(nextState);
                    queue.add(new Pair(nextState, index));
                }
            }
        }
        return -1;
    }

    private String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append((char)(board[i][j] + '0'));
            }
        }
        return sb.toString();
    }

    private String swap(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}

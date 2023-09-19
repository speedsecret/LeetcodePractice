/*
Leetcode 51: N Queen
https://leetcode.com/problems/n-queens/description/

The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
*/

// Abstraction:
// Return a list of solved chessBoard that fits all (N) queens in the given board.
// Return an empty list if no solution found.

// Methodology:
// To use three hashSet, cols, diagonal, antiDiagonals sets.
class Solution {
    private int size;
    private List<List<String>> solutions = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
       size = n;
       char[][] emptyBoard = new char[n][n];
       for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               emptyBoard[i][j] = '.';
           }
       }

       backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>(), emptyBoard);
       return solutions;
    }

    private void backtrack(int row, Set<Integer> diagonals, Set<Integer> antiDiagonals, Set<Integer> cols, char[][] state) {
        // base case
        if (row == size) {
            solutions.add(createBoard(state));
            return;
        }

        for (int col = 0; col < size; col++) {
            int currDiagonal = row - col;
            int currAntiDiagonal = row + col;
            // skip if in the same col, diagonal, anti-diagonal
            if (cols.contains(col) || diagonals.contains(currDiagonal) || antiDiagonals.contains(currAntiDiagonal)) {
                continue;
            }

            // add Queen into the board
            cols.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);
            state[row][col] = 'Q';

            // move on to the next row with the updated board state
            backtrack(row + 1, diagonals, antiDiagonals, cols, state);

            cols.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
            // put the '.' back to the board.
            state[row][col] = '.';
        }
    }

    private List<String> createBoard(char[][] state) {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            String curRow = new String(state[row]);
            board.add(curRow);
        }
        return board;
    }
}

package DFS;

public class NumberOfIsland {
    // Leetcode 200
    public static int numberOfIsland(int[][] matrix) {
        // use DFS
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int numsOfIslands = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    numsOfIslands++;
                    DFS(matrix, i, j);
                }
            }
        }
        return numsOfIslands;
    }

    private static void DFS(int[][] matrix, int row, int col) {
        // base case
        // hit the boundary or encounter a '0'
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || matrix[row][col] == 0) {
            return;
        }
        // subproblem
        matrix[row][col] = 0;
        DFS(matrix, row + 1, col);
        DFS(matrix, row - 1, col);
        DFS(matrix, row, col + 1);
        DFS(matrix, row, col - 1);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
            {1, 0, 0, 1},
            {1, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        };
        System.out.println("How many islands do we have: " + numberOfIsland(matrix));
    }
}

package DFS;

public class NumbersOfIslands {
    public static int numIslands(char[][] grid) {
        // corner case if the gird is null or col or row is empty
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int numsOfIsland = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    numsOfIsland++;
                    DFS(grid, r, c);
                }
            }
        }
        return numsOfIsland;
    }

    private static void DFS(char[][] grid, int r, int c) {
        // base case
        // if we reach to the boundary of the rectangular
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }
        // flip the current 1 island as 0 cuz we already detect it. However we don't want to revisited it again
        grid[r][c] ='0';
        DFS(grid, r - 1, c);
        DFS(grid, r + 1, c);
        DFS(grid, r, c - 1);
        DFS(grid, r, c + 1);
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '1'}};
        System.out.println("How many islands we have in this grid? " + numIslands(grid));
    }
}

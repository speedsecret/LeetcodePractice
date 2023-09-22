/*
733. Flood Fill.java
https://leetcode.com/problems/flood-fill/

An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting 
from the pixel image[sr][sc].

To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the 
starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to
those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.

Return the modified image after performing the flood fill.
Example 1:
Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels 
connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
Example 2:
Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
Output: [[0,0,0],[0,0,0]]
Explanation: The starting pixel is already colored 0, so no changes are made to the image.
*/

// Recommended:
// DFS approach
class Solution {
    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private boolean[][] visited;
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }
        // for starting points' neighbors', as long as it stays the same color, it can be tuned to final color.
        int initialColor = image[sr][sc];
        visited = new boolean[image.length][image[0].length];
        DFS(image, sr, sc, color, initialColor);
        return image;
    }

    private void DFS(int[][] image, int row, int col, int color, int initialColor) {
        // base case:
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] == color 
            || image[row][col] != initialColor || visited[row][col] == true) {
            return;
        }
        visited[row][col] = true;
        image[row][col] = color;

        for (int[] dir : directions) {
            int neiRow = row + dir[0];
            int neiCol = col + dir[1];
            DFS(image, neiRow, neiCol, color, initialColor);
        }
    }
}


// BFS
/*
class Solution {
    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // use BFS
        if (image[sr][sc] == color) {
            return image;
        }
        Deque<int[]> queue = new ArrayDeque<>();
        int m = image.length, n = image[0].length;
        boolean[][] visited = new boolean[m][n];
        queue.offerLast(new int[]{sr, sc});
        int originColor = image[sr][sc];
        image[sr][sc] = color;
        visited[sr][sc] = true;
        
        // process the queue
        while (!queue.isEmpty()) {
            int[] pre = queue.pollFirst();
            int preRow = pre[0], preCol = pre[1];
            for (int[] dir : dirs) {
                int neiRow = preRow + dir[0];
                int neiCol = preCol + dir[1];
                if (neiRow >= 0 && neiRow < m && neiCol >= 0 && neiCol < n && !visited[neiRow][neiCol]) {
                    if (image[neiRow][neiCol] == originColor) {
                        queue.offerLast(new int[]{neiRow, neiCol});
                        image[neiRow][neiCol] = color;
                    }
                    visited[neiRow][neiCol] = true;
                }
            }
        }
        return image;
    }
}
*/

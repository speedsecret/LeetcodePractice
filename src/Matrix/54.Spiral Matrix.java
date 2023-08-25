/*
Leetcode 54: spiral-matrix
https://leetcode.com/problems/spiral-matrix/description/

Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // use an integer of list to represent the result
        // traverse the matrix while keeping a dynamic boundary
        List<Integer> res = new ArrayList<>();
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = cols - 1, up = 0, down = rows - 1;
        // check the size of the result list, while the size is not full, keep working on it.
        while (res.size() < rows * cols) {
            // traverse from left to right
            for (int col = left; col <= right; col++) {
                res.add(matrix[up][col]);
            }

            // traverse from top to bottom
            for (int row = up + 1; row <= down; row++) {
                res.add(matrix[row][right]);
            }

            // traverse from right to left while make sure we are in a different row
            if (up != down) {
                for (int col = right - 1; col >= left; col--) {
                    res.add(matrix[down][col]);
                }
            }

            // traverse from bottom to top while make sure we are in a different col
            if (left != right) {
                for (int row = down - 1; row > up; row--) {
                    res.add(matrix[row][left]);
                }
            }
            left += 1;
            up += 1;
            right -= 1;
            down -= 1;
        }
        return res;
    }
}

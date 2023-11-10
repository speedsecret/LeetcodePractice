/*
305. Number of Islands II.java
https://leetcode.com/problems/number-of-islands-ii/

You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. 
Initially, all the cells of grid are water cells (i.e., all the cells are 0's).
We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.
Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
Output: [1,1,2,3]
Explanation:
Initially, the 2d grid is filled with water.
- Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
- Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
- Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
- Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
Example 2:

Input: m = 1, n = 1, positions = [[0,0]]
Output: [1]
 
Constraints:

1 <= m, n, positions.length <= 104
1 <= m * n <= 104
positions[i].length == 2
0 <= ri < m
0 <= ci < n
*/

// Use the unionfind
// for each element in the positions
// check if it is current water, if not
// check with its all possible neighbors
// if the neighbor is valid which means it is land
// and then perform union find
// if the current element and it is neighbor are different root
// union them together as it is attached to each other
// decrement currentLandCount once
// put the currentLandCount to the result

class UnionFind {
    private int[] parents;
    private int[] sizes;

    public UnionFind(int n) {
        // Initialize parent array and sizes array for Union-Find
        parents = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i; // Each element is initially its own parent
            sizes[i] = 1;    // Each set initially has size 1
        }
    }

    // Find the root (representative) of the set that element i belongs to
    public int find(int i) {
        while (i != parents[i]) {
            parents[i] = parents[parents[i]]; // Path compression for optimization
            i = parents[i];
        }
        return i;
    }

    // Union two sets represented by elements p and q
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        
        // Elements p and q are already in the same set
        if (rootP == rootQ) return; 

        // Union by size to merge the smaller set into the larger set
        if (sizes[rootP] < sizes[rootQ]) {
            parents[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
        } else {
            parents[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
        }
    }
}

public class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        int[][] grid = new int[m][n];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        UnionFind uf = new UnionFind(m * n);
        int islandCount = 0;

        for (int[] position : positions) {
            int row = position[0];
            int col = position[1];

            if (grid[row][col] == 0) {
                // mark the current spot into a land
                grid[row][col] = 1;
                // increment 1 automatically
                islandCount++;

                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    // the neighbor is already land too
                    // the neighbor is within the boundary as well as it is already land too
                    // we need to check if they belongs to the same root.
                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n 
                        && grid[newRow][newCol] == 1) {
                        int current = row * n + col;
                        int neighbor = newRow * n + newCol;
                        // If the current and neighbor are from the diff root, 
                        // So for now these two lands are jointed together
                        // Decrement the isLandCount;
                        if (uf.find(current) != uf.find(neighbor)) {
                            uf.union(current, neighbor);
                            islandCount--;
                        }
                    }
                }
            }

            result.add(islandCount);
        }

        return result;
    }
}

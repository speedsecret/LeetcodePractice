/*
Leetcode 261 Graph Valid Tree
https://leetcode.com/problems/graph-valid-tree/description/

You have a graph of n nodes labeled from 0 to n - 1. 
You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.
 
Example 1:
Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true

Example 2:
Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false
*/

class Solution {
    public boolean validTree(int n, int[][] edges) {
        // use union find
        // union elements --> got diff elements in diff group together into one group
        // find elements' head(representative)
        int[] nums = new int[n];
        Arrays.fill(nums, -1);

        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);

            // if two found equals
            if (x == y) {
                return false;
            }

            nums[x] = y;
        }

        return edges.length == n - 1;
    }

    private int find(int[] nums, int ele) {
        if (nums[ele] == -1) {
            return ele;
        }
        return find(nums, nums[ele]);
    }
}

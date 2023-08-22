/*
Leetcode 230: Kth Smallest Element in BST
https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // Method1: Use two int[] arrs ans and count, find out elements.
        int[] ans = new int[1];
        int[] count = new int[1];
        DFS(root, k, ans, count);
        return ans[0];
    }

    private void DFS(TreeNode root, int k, int[] ans, int[] count) {
        // base case
        if (root == null) {
            return;
        }

        // recursive rule
        // prioritize of getting the leftSubTree traversed.
        DFS(root.left, k, ans, count);
        count[0]++;
        // check if current element already reaches to k
        if (count[0] == k) {
            ans[0] = root.val;
        }

        DFS(root.right, k, ans, count);
    }

     /*
     // Method2: use the BST features, build up an inorder list.
     public int kthSmallest(TreeNode root, int k) {
        List<Integer> res = buildInorderList(root, new ArrayList<>());
        return res.get(k - 1);
     }

     private List<Integer> buildInorderList(TreeNode root, List<Integer> list) {
        // base case
        if (root == null) {
            return list;
        }

        // recursive rule
        buildInorderList(root.left, list);
        list.add(root.val);
        buildInorderList(root.right, list);
        
        return list;
     }
     */
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// This time, TreeNode p and q became an array of nodes.
// put them into a for loop.
// the fundamental part of this question is still No.236.

class Solution {
    // write a solution which based on 2nd approach of leetcode 235
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        // do it recursively check to its left subTree and rightSubTree
        if (root == null) {
            return null;
        }

        for (TreeNode node : nodes) {
            if (node == root) {
                return node;
            }
        }
        TreeNode left = lowestCommonAncestor(root.left, nodes);
        TreeNode right = lowestCommonAncestor(root.right, nodes);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}

/*
Given the root of a binary tree, return all root-to-leaf paths in any order.

A leaf is a node with no children.

Example 1:
Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]

Example 2:
Input: root = [1]
Output: ["1"]
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
    public List<String> binaryTreePaths(TreeNode root) {
        // DSF to traverse all options
        // Set up a List<String> res
        // Adding the current path into the final res when met the leaf node
        List<String> res = new ArrayList<>();
        DFS(root, res, new StringBuilder());
        return res;
    }

    private void DFS(TreeNode root, List<String> res, StringBuilder sb) {
        // base case
        if (root == null) {
            return;
        }

        // subproblem
        String str = String.valueOf(root.val);
        if (root.left == null && root.right == null) {
            sb.append(str);
            res.add(sb.toString());
            sb.setLength(sb.length() - str.length());
            return;
        }

        sb.append(str).append("->");
        DFS(root.left, res, sb);
        DFS(root.right, res, sb);

        sb.setLength(sb.length() - str.length() - 2);
    }
}

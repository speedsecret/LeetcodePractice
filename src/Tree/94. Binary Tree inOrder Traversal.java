/*
94. Binary Tree inOrder Traversal.java
https://leetcode.com/problems/binary-tree-inorder-traversal/description/

Given the root of a binary tree, return the inorder traversal of its nodes' values.
Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [1]
Output: [1]

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 

Follow up: Recursive solution is trivial, could you do it iteratively?
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
    public List<Integer> inorderTraversal(TreeNode root) {
        // List<Integer> res = new ArrayList<>();
        // inorderTraversalRecursively(root, res);
        // return res;
        return inorderTraversalIteratively(root);
    }

    private void inorderTraversalRecursively(TreeNode root, List<Integer> res) {
        // base case
        if (root == null) {
            return;
        }
        // recursive rule
        inorderTraversalRecursively(root.left, res);
        res.add(root.val);
        inorderTraversalRecursively(root.right, res);
    }

    private List<Integer> inorderTraversalIteratively(TreeNode root) {
        // use a ArrayList<>() res and a stack to execute this implementation.
        // not actively adding element to the res.
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        // Two while loops
        // not adding elements into the stack before hand
        // poll leftMost first then middle, then rightMost
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            }
            cur = stack.pollFirst();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}

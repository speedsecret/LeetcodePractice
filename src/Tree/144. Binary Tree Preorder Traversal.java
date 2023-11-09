/*
144. Binary Tree Preorder Traversal.java
Given the root of a binary tree, return the preorder traversal of its nodes' values. 

Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]
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
 // Methodology
 // When using the stack data structure, we are leveraging the LIFO strategy
 // Proactively add the root into the final result.

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pollFirst();
            res.add(cur.val);
            // LIFO strategy
            // Last in first out, 
            // if one want to print the left first
            // one should add right first
            if (cur.right != null) {
                stack.offerFirst(cur.right);
            }
            if (cur.left != null) {
                stack.offerFirst(cur.left);
            }
        }
        return res;
    }
}

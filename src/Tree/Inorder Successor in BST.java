/*
Leetcode 285: Inorder Successor in BST
https://leetcode.com/problems/inorder-successor-in-bst/description/

Given the root of a binary search tree and a node p in it, return the in-order successor of that node in the BST. 
If the given node has no in-order successor in the tree, return null.

The successor of a node p is the node with the smallest key greater than p.val.

Example 1:
Input: root = [2,1,3], p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.

Example 2:
Input: root = [5,3,6,2,4,null,null,1], p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // use the features that BST offered
        // the left parts must be smaller then the root value if all elements are distinct.
        TreeNode res = null;
        while (root != null) {
            // compare the value
            if (p.val >= root.val) {
                root = root.right;
            } else {
                // the current root could be the candidate, assign it as current for now.
                res = root;
                root = root.left;
            }
        }
        return res;
    }
}

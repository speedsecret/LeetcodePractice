/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 2

Example 2:
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5

Constraints:

The number of nodes in the tree is in the range [0, 105].
-1000 <= Node.val <= 1000
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

// Methodology:
// DFS to traverse the Tree recurrsively,
// need to handle the case when one side of the tree is empty.

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // handle when the one side is empty
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        // for tree have both subLeftTree and subRightTree
        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);

        return Math.min(leftMinDepth, rightMinDepth) + 1;
    }
}

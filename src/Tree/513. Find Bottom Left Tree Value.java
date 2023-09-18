/*
513. Find Bottom Left Tree Value.java
https://leetcode.com/problems/find-bottom-left-tree-value/

Given the root of a binary tree, return the leftmost value in the last row of the tree.

Example 1:
Input: root = [2,1,3]
Output: 1
Example 2:
Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7

Constraints:
The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
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
    public int findBottomLeftValue(TreeNode root) {
        // [Preferred] Method DFS, with an int[] arr, arr[0] = level, arr[1] = candidate.val
        int[] ans = {0, root.val};
        DFS(root, ans, 0);        
        return ans[1];
    }

    // Preferred Method: DFS
    // check left and right subTree recursively
    private void DFS(TreeNode root, int[] ans, int level) {
        // base case
        if (root == null) {
            return;
        }

        // check with the left subTree
        // to see if the root.left is non-null
        // if it is, then if the current level + 1 larger than previous ans, if so, we update the res
        if (root.left != null) {
            if (level + 1 > ans[0]) {
                ans[0] = level + 1;
                ans[1] = root.left.val;
            }
            DFS(root.left, ans, level + 1);
        }

        // check right subTree
        if (root.right != null) {
            DFS(root.right, ans, level + 1);
        }

        // only if the current node has processed the left subnode as well as right subnode
        // check whether we need to get it updated.
        // if current level larger than previous answer, we should update the res and value.
        if (level > ans[0]) {
            ans[0] = level;
            ans[1] = root.val;
        }
    }

    // Method1: use BFS with TreeNode variable candidate, int size to control printing order
    // queue to store treeNode
    // push TreeNode into the queue from right to left, poll from left to right
    private int bfsFindBottomLeftValue(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode candidate = root;
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            // check size
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    candidate = queue.peekFirst();
                }
                TreeNode cur = queue.pollFirst();
                if (cur.left != null) {
                    queue.offerLast(cur.left);
                }
                if (cur.right != null) {
                    queue.offerLast(cur.right);
                }
            }
        }
        return candidate.val;
    }

}

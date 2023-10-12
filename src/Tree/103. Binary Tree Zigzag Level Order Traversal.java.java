/*
103. Binary Tree Zigzag Level Order Traversal.java.java
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // BFS with an int variable level, determine the logic from left to right, or vice versa. 
        // use an int variable level to understand if we need to print the current level
        // from left to right or from right to the left
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        int level = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> sol = new ArrayList<>();
            if (level % 2 == 0) {
                // logic 1
                while (size-- > 0) {
                    TreeNode cur = deque.pollFirst();
                    sol.add(cur.val);
                    if (cur.left != null) {
                        deque.offerLast(cur.left);
                    }
                    if (cur.right != null) {
                        deque.offerLast(cur.right);
                    }
                }
            } else {
                // logic 2
                while (size-- > 0) {
                    TreeNode cur = deque.pollLast();
                    sol.add(cur.val);
                    if (cur.right != null) {
                        deque.offerFirst(cur.right);
                    }
                    if (cur.left != null) {
                        deque.offerFirst(cur.left);
                    }
                }
            }
            level += 1;
            res.add(sol);
        }
        return res;
    }
}

/*
199. Binary Tree Right side view.java
https://leetcode.com/problems/binary-tree-right-side-view/

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
Example 1:
Input: root = [1,2,3,null,5,null,4]
Output: [1,3,4]
Example 2:
Input: root = [1,null,3]
Output: [1,3]
Example 3:
Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
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
    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        /*
        * Method2: DFS
        */
        if (root == null) {
            return res;
        }
        DFS(root, 0);
        return res;
    }

    private void DFS(TreeNode root, int level) {
        // base case
        if (level == res.size()) {
            res.add(root.val);
        }

        // recursive rule and subproblem
        // always put the rightMost element into the res list
        if (root.right != null) {
            DFS(root.right, level + 1);
        }

        if (root.left != null) {
            DFS(root.left, level + 1);
        }
    }

   /* Method1: BFS
        * BFS wiz a queue, insert in addFirst(), pollLast()
        * pull the most right handside elements in each level
        private List<Integer> bfsRightSideVide(root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.offerFirst(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    if (i == 0) {
                        res.add(queue.peekLast().val);
                    }
                    TreeNode cur = queue.pollLast();
                    if (cur.right != null) {
                        queue.offerFirst(cur.right);
                    }
                    if (cur.left != null) {
                        queue.offerFirst(cur.left);
                    }
                }
            }
            return res;
        }
        *
        */
}

package Tree;

/*
for each subTree, either it doesn't have a kid or it has two kids
use a BFS to use a queue to store each element encountered
then check left and right

 case1: if cur.left == null && cur.right == null
 continue;
 case2: if cur.left == null || cur.right == null
 return false(as it must be one child exist which is not consistent with above requirement
 case3: else: they do have two kids
 kept adding these kids into the queue
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class IsFullBinaryTree {
    public static boolean isFullBinaryTree_BFS_Queue(TreeNode root) {
        // corner case
        if (root == null) {
            return true;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // case1:
            if (cur.left == null && cur.right == null) {
                continue;
            }
            // case2:
            else if (cur.left == null || cur.right == null) {
                return false;
            }
            // case3:
            else {
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return true;
    }

    // DFS solution
    // base case + subproblem recursive rule
    public static boolean isFullBinaryTree_DFS(TreeNode root) {
        // base case
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        boolean isLeftFullBinaryTree = isFullBinaryTree_DFS(root.left);
        boolean isRightFullBinaryTree = isFullBinaryTree_DFS(root.right);
        // 左边不是 右边也不是
        if (!isLeftFullBinaryTree || !isRightFullBinaryTree) {
            return false;
        }
        if (root.left != null && root.right != null) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(11);
        System.out.printf("Is this a full binary tree BFS = " + isFullBinaryTree_BFS_Queue(root));
        System.out.printf("Is this a full binary tree DFS = " + isFullBinaryTree_DFS(root));
    }
}

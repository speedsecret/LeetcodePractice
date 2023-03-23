package Tree;

import java.util.ArrayDeque;
import java.util.Queue;

/*
A perfect binary tree is a type of binary Tree in which every internal node has exactly
two child nodes and all the leaf nodes and all the leaf nodes are at the same level.
 */
public class IsBinaryTreePerfect {
    public boolean isPerfectBinaryTree_BFS(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int preSize = 0;
        int level = 0;
        while (!queue.isEmpty()) {
            // attention pls: this is not poll element out
            // we should check the size
            int numberOfNodeInThisLevel = queue.size();
            // do a quick sanity check
            if (level > 0 && numberOfNodeInThisLevel != 2 * preSize) {
                return false;
            }
            for (int i = 0; i < numberOfNodeInThisLevel; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // update preSize and level;
            preSize = numberOfNodeInThisLevel;
            level++;
        }
        return true;
    }

    // use a new class type
    public boolean isPerfectBinaryTree_Recursion(TreeNode root) {
        if (root == null) {
            return true;
        }
        ReturnType_PerfectTree result = recursion(root);
        return result.isPerfect;
    }

    private ReturnType_PerfectTree recursion(TreeNode root) {
        // base case
        if (root == null) {
            return new ReturnType_PerfectTree(0, true);
        }
        if (root.left == null && root.right == null) {
            return new ReturnType_PerfectTree(1, true);
        }

        // subProblem
        ReturnType_PerfectTree left = recursion(root.left);
        ReturnType_PerfectTree right = recursion(root.right);

        // what is our core business logic?
        if (left.isPerfect && right.isPerfect && left.height == right.height) {
            return new ReturnType_PerfectTree(left.height + 1, true);
        }
        return new ReturnType_PerfectTree(-1, false);
    }

    static class ReturnType_PerfectTree {
        int height;
        boolean isPerfect;
        public ReturnType_PerfectTree(int height, boolean isPerfect) {
            this.height = height;
            this.isPerfect = isPerfect;
        }
    }
}

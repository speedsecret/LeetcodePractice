package Tree;
/*
Check if a tree a binary Search Tree?
Use a recursive rule
 */

public class IsBinarySearchTree {
    public static boolean isBinarySearchTree(TreeNode root) {
        // corner case
        if (root == null) {
            return true;
        }
        return recursiveRule_method_1(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // ReturnTypeBinarySearchTree res = recursiveRule_method_2(root);
        // return res.isBST;
    }

    private static boolean recursiveRule_method_1(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.key <= min || root.key >= max) {
            return false;
        }
        return recursiveRule_method_1(root.left, min, root.key)
                && recursiveRule_method_1(root.right, root.key, max);
    }

    private static ReturnTypeBinarySearchTree recursiveRule_method_2(TreeNode root) {
        // base case
        if (root == null) {
            return new ReturnTypeBinarySearchTree(Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        }
        if (root.left == null && root.right == null) {
            return new ReturnTypeBinarySearchTree(root.key, root.key, true);
        }
        // subproblem
        ReturnTypeBinarySearchTree left = recursiveRule_method_2(root.left);
        ReturnTypeBinarySearchTree right = recursiveRule_method_2(root.right);

        // recursive rule:
        if (!left.isBST || !right.isBST) {
            return new ReturnTypeBinarySearchTree(-1, -1, false);
        }
        if (left.max >= root.key || right.min <= root.key) {
            return new ReturnTypeBinarySearchTree(-1, -1, false);
        }
        return new ReturnTypeBinarySearchTree(Math.max(root.key, right.max), Math.max(root.key, left.min), true);
    }
}

class ReturnTypeBinarySearchTree{
    int max;
    int min;
    boolean isBST;
    public ReturnTypeBinarySearchTree(int max, int min, boolean isBST) {
        this.max = max;
        this.min = min;
        this.isBST = isBST;
    }
}

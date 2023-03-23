package Tree;
/*
Determine if it is a height-balanced, which means every subtree only have the delta
of height is <= 1.
 */

import static Tree.GetHeight.getHeight;

public class IsBinaryTreeBalanced {
    // Method 1: isBinaryTreeBalanced.
    public static boolean isBinaryTreeBalanced(TreeNode root) {
        // base case
        if (root == null) {
            return true;
        }

        // subproblem: question which is smaller than current one
        boolean isLeftTreeBalanced = isBinaryTreeBalanced(root.left);
        boolean isRightTreeBalanced = isBinaryTreeBalanced(root.right);

        if (!isLeftTreeBalanced || !isRightTreeBalanced) {
            return false;
        }

        // recursive rule
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        return true;
    }

    // Method 2: use multi-field as a return type
    public boolean isBinaryTreeBalanced_method_2(TreeNode root) {
        // edge case
        if (root == null) {
            return true;
        }
        ReturnTypeIsBalanced result = recursion(root);
        return result.isBalanced;
    }

    private ReturnTypeIsBalanced recursion(TreeNode root) {
        // base case
        if (root == null) {
            return new ReturnTypeIsBalanced(0, true);
        }

        // subproblem
        ReturnTypeIsBalanced leftResult = recursion(root.left);
        ReturnTypeIsBalanced rightResult = recursion(root.right);

        // recursive rule
        int currentHeight = Math.max(leftResult.height, rightResult.height) + 1;
        boolean currentBalance = false;

        // when update the current balance as a true?
        if (leftResult.isBalanced && rightResult.isBalanced &&
        Math.abs(leftResult.height - rightResult.height) <= 1) {
            currentBalance = true;
        }

        return new ReturnTypeIsBalanced(currentHeight, currentBalance);
    }

    // T: O(n) as we only need to loop the entire tree once.
    // S: O(height) + 每一层 object 返回上来的值

    private static final int UNBALANCE = -1;
    public boolean isBinaryTreeBalanced_method_3(TreeNode root) {
        return recursion_method_2(root) != UNBALANCE;
    }

    private int recursion_method_2(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }
        // subproblem
        int left = recursion_method_2(root.left);
        int right = recursion_method_2(root.right);

        // recursive rule
        if (left == UNBALANCE || right == UNBALANCE || Math.abs(left - right) > 1) {
            return UNBALANCE;
        }
        return Math.max(left, right) + 1;
    }

    private ReturnTypeIsBalanced recursionReturnType(TreeNode root) {
        // base case
        if (root == null) {
            return new ReturnTypeIsBalanced(0, true);
        }
        // subproblem
        ReturnTypeIsBalanced left = recursionReturnType(root.left);
        ReturnTypeIsBalanced right = recursionReturnType(root.right);

        // recursive rule
        int curHeight = 0;
        boolean curIsBalanced = false;
        if (left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1) {
            curIsBalanced = true;
        }
        curHeight = Math.max(left.height, right.height) + 1;
        return new ReturnTypeIsBalanced(curHeight, curIsBalanced);
    }
}

class ReturnTypeIsBalanced{
    boolean isBalanced;
    int height;
    public ReturnTypeIsBalanced(int h, boolean isB) {
        this.height = h;
        this.isBalanced = isB;
    }
}

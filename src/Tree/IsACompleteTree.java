package Tree;

/*
Check if this tree a complete tree.

Method 1:
Use a deque
BFS and use a boolean value to check if we see a non-null node
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class IsACompleteTree {
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        // BFS
        boolean method1 = isCompleteBinaryTree_method_1(root);
        //ReturnType method2 = isCompleteBinaryTree_method_2(root);
        return method1;
        // return method2.isComplete;
    }

    private static boolean isCompleteBinaryTree_method_1(TreeNode root) {
       // use a BFS queue
        // use a boolean
        // expand and generate
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean existNull = false;
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode == null) {
                existNull = true;
            } else if (existNull) {
                return false;
            } else {
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            }
        }
        return true;
        // T: O(n)
        // S: O(n)
    }

    public ReturnType isCompleteBinaryTree_method_2(TreeNode root) {
        // base case
        if (root == null) {
            return new ReturnType(true, true, 0);
        }
        // subproblem
        ReturnType leftTree = isCompleteBinaryTree_method_2(root.left);
        if (!leftTree.isComplete) {
            return new ReturnType(false, false, -1);
        }
        ReturnType rightTree = isCompleteBinaryTree_method_2(root.right);
        if (!rightTree.isComplete) {
            return new ReturnType(false, false, -1);
        }
        // recursive rule
        int curHeight = Math.max(leftTree.height, rightTree.height) + 1;
        boolean isPerfect = leftTree.isPerfect && rightTree.isPerfect
                && leftTree.height == rightTree.height;
        // case 1: last node is in the left subTree
        // left C && right P && height gap is just equals to 1
        // case 2: last node is in the right subTree
        // left P && right C && equal height
        boolean isComplete =
                (leftTree.isComplete && rightTree.isPerfect && leftTree.height == rightTree.height + 1)
                        || (leftTree.isPerfect && rightTree.isComplete && leftTree.height == rightTree.height);
        return new ReturnType(isPerfect, isComplete, curHeight);
    }
    // T: O(N)
    // S: O(logN)

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println("Current tree is a completeBinaryTree?" + isCompleteBinaryTree(root));
    }
}

class ReturnType{
    boolean isPerfect;
    boolean isComplete;
    int height;
    public ReturnType(boolean isPerfect, boolean isComplete, int height) {
        this.isPerfect = isPerfect;
        this.isComplete = isComplete;
        this.height = height;
    }
}

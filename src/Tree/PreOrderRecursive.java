package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class PreOrderRecursive {
    public static List<Integer> preOrderRecursiveRule(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrderRecursiveRule(root, res);
        return res;
    }

    private static void preOrderRecursiveRule(TreeNode root, List<Integer> res) {
        // base case
        if (root == null) {
            return;
        }
        // add element first
        res.add(root.key);
        preOrderRecursiveRule(root.left, res);
        preOrderRecursiveRule(root.right, res);
    }

    // use a stack
    // offer the right handside first
    private static List<Integer> preOrder_UseStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pollFirst();
            res.add(curNode.key);
            if (curNode.right != null) {
                stack.offerFirst(curNode.right);
            }
            if (curNode.left != null) {
                stack.offerFirst(curNode.left);
            }
        }
        return res;
    }

    class PreOrderIterator implements Iterator<TreeNode> {
        Deque<TreeNode> stack;
        public PreOrderIterator(TreeNode root) {
            stack = new ArrayDeque<>();
            if (root != null) {
                stack.offerFirst(root);
            }
        }

        @Override
        public TreeNode next() {
            if (hasNext()) {
                TreeNode cur = stack.pollFirst();
                if (cur.right != null) {
                    stack.offerFirst(cur.right);
                }
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                }
                return cur;
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}

package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class GetKeysInBinaryTreeLayerByLayerZigZagOrder {
    public static List<Integer> getKeysInBinaryTreeLayerByLayerZigZagOrder(TreeNode root) {
        // corner case
        // use a BFS a queue to put nodes into the queue on the fly
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        int layer = 0;
        // for each lay, we check the size
        // so we can print them on one layer scan as one go
        while (!queue.isEmpty()) {
            // 偶数层 从右边向左拿
            //          那么从左边放先放右边
            // 奇数层 从左边向有边拿
            //          那么从右边放先放左边
           int size = queue.size();
           if (layer == 0) {
               for (int i = 0; i < size; i++) {
                   TreeNode cur = queue.pollLast();
                   res.add(cur.key);
                   // q: 5
                   //
                   if (cur.right != null) {
                       queue.offerFirst(cur.right);
                   }
                   if (cur.left != null) {
                       queue.offerFirst(cur.left);
                   }
               }
           } else {
               for (int i = 0; i < size; i++) {
                   TreeNode cur = queue.pollFirst();
                   res.add(cur.key);
                   if (cur.left != null) {
                       queue.offerLast(cur.left);
                   }
                   if (cur.right != null) {
                       queue.offerLast(cur.right);
                   }
               }
           }
           layer = 1 - layer;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(11);
        LinkedList<Integer> res = (LinkedList<Integer>) getKeysInBinaryTreeLayerByLayerZigZagOrder(root);
        while (res.size() > 0) {
            System.out.printf("Current key in the list is: " + res.removeFirst() + "\n");
        }
    }
}


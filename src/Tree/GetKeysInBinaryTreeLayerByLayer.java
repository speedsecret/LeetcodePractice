package Tree;
/*
Get the list of list of keys in a given binary tree layer by layer. Each layer is represented by a list of keys and the keys are traversed from left to right.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

the result is [ [5], [3, 8], [1, 4, 11] ]
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class GetKeysInBinaryTreeLayerByLayer {
    // use a Deque<TreeNode> queue
    // setup a list within the queue
    // check both sides
    public static List<List<Integer>> getKeysInBinaryTreeLayerByLayer(TreeNode root) {
        // corner case
        if (root == null) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // for each loop, we create a list to handle those elements from the queue
            List<Integer> curList = new ArrayList<>();
            // getting into each loop now!
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curList.add(curNode.key);
                // generate more node and put it into the queue
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            result.add(curList);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(2);
        node.right = new TreeNode (3);
        node.right.left = new TreeNode(1);
        node.right.right = new TreeNode(10);
        System.out.println(getKeysInBinaryTreeLayerByLayer(node));
    }
}

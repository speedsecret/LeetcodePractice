/*
297. Serialize and Deserialize Binary Tree
https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example 1:
Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]

Example 2:
Input: root = []
Output: []
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Using StringBuilder to construct the String dynamically
// for deserialization, convert the string to String[] arr
// then convert the arr to linkedList then reconstruct a tree it recursively.

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        reserialization(root, sb);
        return sb.toString();
    }

    private void reserialization(TreeNode root, StringBuilder sb) {
        // base case
        if (root == null) {
            sb.append("null,");
        } else {
            sb.append(root.val).append(",");
            reserialization(root.left, sb);
            reserialization(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // convert the string into String[] arr
        // then convert the arr into a linkedList
        // then do it recursively, in the recursion function
        // base case: current string equals to null, remove the current element and return null
        // recursive rule: construct a root, and then recursively connect root.left and root.right;
        String[] arr = data.split(",");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(arr));
        return redeserialization(list);
    }

    private TreeNode redeserialization(LinkedList<String> list) {
        // base case:
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        TreeNode newNode = new TreeNode(Integer.valueOf(list.get(0)));
        // dynamically adjust the list
        list.remove(0);

        newNode.left = redeserialization(list);
        newNode.right = redeserialization(list);
        return newNode;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

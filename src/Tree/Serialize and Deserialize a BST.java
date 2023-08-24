/*
Serialization is converting a data structure or object into a sequence of bits
so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. 
You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
The encoded string should be as compact as possible.


*/

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        int[] pos = new int[]{0};
        return deserialize(data, pos, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode deserialize(String s, int[] pos, int min, int max) {
        // base case 
        if (pos[0] >= s.length()) {
            return null;
        }

        // find the the comma position
        int commaPos = s.indexOf(",", pos[0]);
        int val = Integer.parseInt(s.substring(pos[0], commaPos));
        if (val < min || val > max) {
            return null;
        }

        // updated the pos[0];
        pos[0] = commaPos + 1;
        TreeNode node = new TreeNode(val);
        node.left = deserialize(s, pos, min, node.val);
        node.right = deserialize(s, pos, node.val, max);
        return node;
    }
}

/*
449. Serialize and Deserialize BST.java
https://leetcode.com/problems/serialize-and-deserialize-bst/

Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed 
later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. 
You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.
Example 1:
Input: root = [2,1,3]
Output: [2,1,3]
Example 2:
Input: root = []
Output: [] 
Constraints:
The number of nodes in the tree is in the range [0, 104].
0 <= Node.val <= 104
The input tree is guaranteed to be a binary search tree.
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

// DFS + inorder traversal with StringBuilder
// deserialize process would process the each single string
// by its delimiter "," and use String.indexOf(",", pos[0]) to find the commaPos
// then we advance the commaPos one index further.

// related topic: No.98 Validate BST. https://leetcode.com/problems/validate-binary-search-tree/description/

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
        // base case:
        if(pos[0] == s.length()) {
            return null;
        }
        // find the commaPos at the beginning
        int commaPos = s.indexOf(",", pos[0]);
        // get the string curStr
        // by using s.substring(i, j)
        String curStr = s.substring(pos[0], commaPos);
        int val = Integer.parseInt(curStr);
        // check the val with the min as well as the max
        if (val < min || val > max) {
            return null;
        }
        // update the pos[0] in advance as this current value must be valid.
        pos[0] = commaPos + 1;
        TreeNode newNode = new TreeNode(val);
        newNode.left = deserialize(s, pos, min, val);
        newNode.right = deserialize(s, pos, val, max);
        return newNode;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;

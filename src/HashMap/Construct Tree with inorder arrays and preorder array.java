/*

Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree 
and inorder is the inorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
  
Example 2:
Input: preorder = [-1], inorder = [-1]
Output: [-1]
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int preOrderIndex = 0;
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Methodology in your words:
        // Recursively call the helper method to build subTree
        // Use a hashMap to store the inOrder mapping to its index in the inorder array
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode newRoot = treeBuilder(preorder, 0, preorder.length - 1);
        return newRoot;
    }

    private TreeNode treeBuilder(int[] preorder, int left, int right) {
        // base case
        if (left > right) {
            return null;
        }

        // recursive rule
        // find the rootValue and build the Tree
        int rootValue = preorder[preOrderIndex++];
        TreeNode newNode = new TreeNode(rootValue);

        int splitIndex = map.get(rootValue);
        newNode.left = treeBuilder(preorder, left, splitIndex - 1);
        newNode.right = treeBuilder(preorder, splitIndex + 1, right);
        return newNode;
    }
}

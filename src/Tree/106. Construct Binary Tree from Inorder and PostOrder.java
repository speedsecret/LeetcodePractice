/*
106. Construct Binary Tree from Inorder and PostOrder.java
https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:
Input: inorder = [-1], postorder = [-1]
Output: [-1]

Constraints:

1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
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

// Methodology:
// Recursively check leftSide as well as check the rightSide.
// Create a map to represent the index-element relationship 
// Construct the Tree by using the postOrder array and its postOrderIndex.

class Solution {
    int postOrderIndex;
    int[] postorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        postOrderIndex = postorder.length - 1;
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }
        // int left = 0, int right = postorder.length - 1 to 
        // initialize the recursion tree process
        return helper(inOrderMap, postorder, 0, postorder.length - 1);
    }

    private TreeNode helper(Map<Integer, Integer> map, int[] postOrder, int left, int right) {
        // base case
        if (left > right) {
            return null;
        }
        
        int rootValue = postOrder[postOrderIndex];
        TreeNode root = new TreeNode(rootValue);

        postOrderIndex--;
        // find current boundary, to prepare for recursion
        int splitIndex = map.get(rootValue);
        root.right = helper(map, postOrder, splitIndex + 1, right);
        root.left = helper(map, postOrder, left, splitIndex - 1);
        return root;
    }
}

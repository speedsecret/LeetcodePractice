/* 
889. Construct Binary Tree with PreOrder and PostOrder arrays.
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/

Given two integer arrays, preorder and postorder where preorder is the preorder traversal of 
a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
If there exist multiple answers, you can return any of them.

Example 1:
Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
Example 2:
Input: preorder = [1], postorder = [1]
Output: [1]

Constraints:

1 <= preorder.length <= 30
1 <= preorder[i] <= preorder.length
All the values of preorder are unique.
postorder.length == preorder.length
1 <= postorder[i] <= postorder.length
All the values of postorder are unique.
It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
*/


class Solution {
    // recursively find the left 
    int preOrderIndex = 0, postOrderIndex = 0;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        TreeNode root = new TreeNode(preorder[preOrderIndex++]);
        // if the current root value is not match the postOrder arr
        // we knew it probably have its subTree, so check left first
        if (root.val != postorder[postOrderIndex]) {
            root.left = constructFromPrePost(preorder, postorder);
        }
        // we have already ruled out the leftside, the the unmatch found
        // for the right subTree
        // we need to construct for its rightSubTree
        if (root.val != postorder[postOrderIndex]) {
            root.right = constructFromPrePost(preorder, postorder);
        }
        postOrderIndex++;
        return root;
    }
}

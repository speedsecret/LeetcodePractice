/*
236. LowestCommonAncestorI.java
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
    
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants 
(where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1

Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.
*/

public class LowestCommonAncestorI {
    public static TreeNode findLCAI(TreeNode root, TreeNode one, TreeNode two) {
        // corner case
        if (root == null || root == one || root == two) {
            return root;
        }
        // Based on the info from the question, the LCA must exists.
        // check the left part and right part recursively
        // if both are not null, return root
        // if one is null, return the other one
        TreeNode leftNode = findLCAI(root.left, one, two);
        TreeNode rightNode = findLCAI(root.right, one, two);

        if (leftNode != null && rightNode != null) {
            return root;
        }
        // both p and q are in the leftNode
        // leftNode != null --> leftNode;
        // both p and q are in the rightNode
        // rightNode != null --> rightNode;
        return leftNode == null ? rightNode : leftNode;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(9);
        node.right = new TreeNode (3);
        node.right.left = new TreeNode(1);
        node.right.right = new TreeNode(10);
        System.out.println(findLCAI(node, node.left.left, node.right.right).key);
    }
}



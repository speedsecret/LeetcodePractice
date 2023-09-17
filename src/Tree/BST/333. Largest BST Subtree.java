/*
333. Largest BST Subtree.java
https://leetcode.com/problems/largest-bst-subtree/description/

Given the root of a binary tree, find the largest subtree
, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.

A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:

The left subtree values are less than the value of their parent (root) node's value.
The right subtree values are greater than the value of their parent (root) node's value.
Note: A subtree must include all of its descendants.

Example 1:
Input: root = [10,5,15,1,8,null,7]
Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size, which is 3.
Example 2:

Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
-104 <= Node.val <= 104
 

Follow up: Can you figure out ways to solve it with O(n) time complexity?
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

// Methodology
// Use a new object(class), store 4 elements:
// size, min, max, boolean isBST
// use DFS to update each level accordingly

class Result {
    int size;
    int min;
    int max;
    boolean isBST;
    public Result(int size, int min, int max, boolean isBST) {
        this.size = size;
        this.min = min;
        this.max = max;
        this.isBST = isBST;
    }
}

class Solution {
    public int largestBSTSubtree(TreeNode root) {
        return findLargestBST(root).size;
    }

    private Result findLargestBST(TreeNode root) {
        // base case
        if (root == null) {
            return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }

        // ask leftSubTree and rightSubTree
        Result left = findLargestBST(root.left);
        Result right = findLargestBST(root.right);

        // check if the current subTree is a BST
        boolean isBST = false;
        if (left.isBST && right.isBST && root.val > left.max && root.val < right.min) {
            isBST = true;
        }

        // calculate the current size
        int size = isBST ? left.size + right.size + 1 : Math.max(left.size, right.size);

        // update the current min and current max
        int minVal = isBST ? Math.min(left.min, root.val) : Integer.MIN_VALUE;
        int maxVal = isBST ? Math.max(right.max, root.val) : Integer.MAX_VALUE;

        return new Result(size, minVal, maxVal, isBST);
    }
}


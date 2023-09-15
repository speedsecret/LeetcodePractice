/*
270. Closest Binary Search Tree Value.java
https://leetcode.com/problems/closest-binary-search-tree-value/description/

Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target. 
If there are multiple answers, print the smallest.

Example 1:
Input: root = [4,2,5,1,3], target = 3.714286
Output: 4
Example 2:
Input: root = [1], target = 4.428571
Output: 1

Constraints:

The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 109
-109 <= target <= 109
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
    public int closestValue(TreeNode root, double target) {
       return closest(root, root.val, target); 
    }

    private int closest(TreeNode root, int closest, double target) {
        // base case
        if (root == null) {
            return closest;
        }
        // recursive rule
        // when to update closest?
        // case1: we find closer element
        // case2: we find the other element which has the same gap to target, but smaller
        if (Math.abs(target - root.val) < Math.abs(target - closest) ||
            (Math.abs(target - root.val) == Math.abs(target - closest) && root.val < closest)) {
                closest = root.val;
        }
        if (target < root.val) {
            return closest(root.left, closest, target);
        } else {
            return closest(root.right, closest, target);
        }
    }
}

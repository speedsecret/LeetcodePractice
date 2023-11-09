/*
145. Binary Tree PostOrder Traversal.java
https://leetcode.com/problems/binary-tree-postorder-traversal/description/

Given the root of a binary tree, return the postorder traversal of its nodes' values.
Example 1:
Input: root = [1,null,2,3]
Output: [3,2,1]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [1]
Output: [1]
 

Constraints:

The number of the nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 

Follow up: Recursive solution is trivial, could you do it iteratively?
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

// summary for pre-order, in-order and post-order traversal
// it is important to understand all we need to use is DataStructure stack.
// for inorder, not proactively push root into the stack, 2 while loops
// for preOrder, proactively push root into the stack, push cur.right into stack first
// for postOrder, use linkedList<Integer> res, use res.addFirst() API, proactively push root into the stack, push cur.left into stack first
class Solution {
    // Methodology:
    // Use a stack and a linkedList(output list)
    // whoever needs to be placed at the end of the list, this should be polled out first
    // anyone who needs to be polled first, in iterative loop, we need to push them into stack late.
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        if (root == null) {
            return output;
        }
        // initilization
        stack.offerFirst(root);

        while (!stack.isEmpty()) {
            root = stack.pollFirst();
            output.addFirst(root.val);
            if (root.left != null) {
                stack.offerFirst(root.left);
            }
            if (root.right != null) {
                stack.offerFirst(root.right);
            }
        }

        return output;
    }
}
  

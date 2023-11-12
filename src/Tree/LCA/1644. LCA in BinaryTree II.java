/*
1644. LCA in BinaryTree II.java
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/

Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q.
If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
  
Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.

Example 3:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
Output: null
Explanation: Node 10 does not exist in the tree, so return null.

Constraints:

The number of nodes in the tree is in the range [1, 104].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
Follow up: Can you find the LCA traversing the tree, without checking nodes existence?
*/

// Methodology
// The diff from No.236, is this question won't make sure that the nodes are in the tree, so in each subTree traversal
// we need to check if the node is exist ---> so a helper function needed
// after confirmed that they are all existed in the tree ---> then we can perform the classcial LCA.
// otherwise, return null;

// Solution1: This solution is on the top of the classical LCA(No.236).
// To firstly check if both p and q existed in the Tree and its subTree.
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        boolean isPExisted = findNode(root, p);
        boolean isQExisted = findNode(root, q);
        if (isPExisted && isQExisted) {
            return lcaI(root, p, q);
        }
        return null;
    }

    private boolean findNode(TreeNode root, TreeNode p) {
        if (root == null) {
            return false;
        }
        if (root == p) {
            return true;
        }
        boolean findNodeLeft = findNode(root.left, p);
        boolean findNodeRight = findNode(root.right, p);
        return findNodeLeft || findNodeRight;
    }

    private TreeNode lcaI(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode leftNode = lcaI(root.left, p, q);
        TreeNode rightNode = lcaI(root.right, p, q);

        if (leftNode != null && rightNode != null) {
            return root;
        }
        return leftNode != null ? leftNode : rightNode;
    }
}

// Solution2: use a count as an int variable to record the current existence status of P and Q.
class Solution {
	int count = 0;
	
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode LCA = LCA(root, p, q);
        return count == 2 ? LCA : null;
    }
    
    public TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        TreeNode left = LCA(root.left, p, q);     
        TreeNode right = LCA(root.right, p, q);
        // in the lower level,
        // found the one of them is in the subTree.
        // so increment the count.
        if (root == p || root == q) {
            count++;
            return root;
        }
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}

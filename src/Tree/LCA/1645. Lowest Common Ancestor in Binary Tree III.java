/*
1645. Lowest Common Ancestor in Binary Tree III.java
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/description/

Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
Example 3:
Input: root = [1,2], p = 1, q = 2
Output: 1
 
Constraints:

The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q exist in the tree.
*/

// Methodology
// It is similar to Leetcode 160.

class Solution {
    // similiar to the intersection of two linkedLists
    // leetcode 160
    // https://leetcode.com/problems/intersection-of-two-linked-lists/description/
    public Node lowestCommonAncestor(Node p, Node q) {
       Node copyP = p, copyQ = q;
       while (copyP != copyQ) {
           copyP = copyP == null ? q : copyP.parent;
           copyQ = copyQ == null ? p : copyQ.parent;
       }
       return copyP;
    }
}

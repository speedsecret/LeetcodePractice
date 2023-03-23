package Tree;


public class LowestCommonAncestorI {
    public static TreeNode findLCAI(TreeNode root, TreeNode one, TreeNode two) {
        // corner case
        if (root == null || root == one || root == two) {
            return root;
        }
        // we could assume the LCA is existed.
        // check the left part and right part recursively
        // if both are not null, return root
        // if one is null, return the other one
        TreeNode leftNode = findLCAI(root.left, one, two);
        TreeNode rightNode = findLCAI(root.right, one, two);

        if (leftNode != null && rightNode != null) {
            return root;
        }
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



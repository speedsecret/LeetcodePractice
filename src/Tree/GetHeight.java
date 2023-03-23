package Tree;

/*
Calculate the height of the tree
 */

public class GetHeight {
    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /*
         2
       /  \
       1   3
        \
         6
     */

//    private static int getTreeHeight(TreeNode root) {
//        // base case: 最后一层在做什么
//        if (root == null) {
//            return 0;
//        }
//        // subproblem： 比现在的问题小一号的问题
//        TreeNode leftNode = root.left;
//        TreeNode rightNode = root.right;
//
//        // recursive rule
//        return Math.max(getTreeHeight(leftNode), getTreeHeight(rightNode)) + 1;
//    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(6);
        System.out.println("The height of the tree is = " + getHeight(root));
    }
}

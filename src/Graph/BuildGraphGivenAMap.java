package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildGraphGivenAMap {
    public static Map<Integer, List<Integer>> buildAGraphGivenAMap(TreeNode root, TreeNode parent) {
        if (root == null) {
            return new HashMap<>();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        buildAGraph_I(map, root, parent);
        return map;
    }

    private static void buildAGraph_I(Map<Integer, List<Integer>> map, TreeNode root, TreeNode parent) {
        // base case
        if (root == null) {
            return;
        }

        // subproblem
        map.putIfAbsent(root.val, new ArrayList<>());

        // recursive rule
        if (parent != null) {
            map.get(root.val).add(parent.val);
        }
        if (root.left != null) {
            map.get(root.val).add(root.left.val);
            buildAGraph_I(map, root.left, root);
        }
        if (root.right != null) {
            map.get(root.val).add(root.right.val);
            buildAGraph_I(map, root.right, root);
        }
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}

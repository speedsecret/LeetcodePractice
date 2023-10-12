/*
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
// Method 1: DFS
// Calculate the order from root to left
// then reverse it.

class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        dfs(root, 0);
        Collections.reverse(res);
        return res;
    }

    private void dfs(TreeNode root, int level) {
        // base case:
        if (root == null) {
            return;
        }
        // initilization
        if (level == res.size()) {
            res.add(new ArrayList<>());
        }

        // find target list and add the current element into it
        res.get(level).add(root.val);

        if (root.left != null) {
            dfs(root.left, level + 1);
        }
        if (root.right != null) {
            dfs(root.right, level + 1);
        }
    }
}

// Method 2:
// BFS solution with LinkedList.addFirst()

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
       // Use a linkedList so we can use addFirst() API so to make sure
       // every level of elements would stay in the front of the result
       LinkedList<List<Integer>> res = new LinkedList<>();
       // BFS
       if (root == null) {
           return res;
       }
       Deque<TreeNode> queue = new ArrayDeque<>();
       queue.offerLast(root);
       while (!queue.isEmpty()) {
           int size = queue.size();
           List<Integer> sol = new ArrayList<>();
           while (size-- > 0) {
                TreeNode cur = queue.pollFirst();
                sol.add(cur.val);
                if (cur.left != null) {
                    queue.offerLast(cur.left);
                }
                if (cur.right != null) {
                    queue.offerLast(cur.right);
                }
           }
           res.addFirst(sol);
       }
       return res;
    }
}

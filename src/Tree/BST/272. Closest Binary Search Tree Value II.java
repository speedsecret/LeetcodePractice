/*
272. Closest Binary Search Tree Value II.java
https://leetcode.com/problems/closest-binary-search-tree-value-ii/description/

Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target. You may return the answer in any order.

You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example 1:
Input: root = [4,2,5,1,3], target = 3.714286, k = 2
Output: [4,3]
Example 2:
Input: root = [1], target = 0.000000, k = 1
Output: [1]
*/

// Abstraction:
// Find the Kth Cloeset Element
// Method1: sort
// Method2: head
// Method3: Deque, maintaining a double-sides queue

class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // Method1: Using a Collections.sort algorithm to set up a new List
        // whenever the gap is smaller, we pipe them up from left side all the way to rightside.
        //     List<Integer> res = new ArrayList<>();
        //     dfs(root, res);

        //     Collections.sort(res, (a, b) -> Double.compare(Math.abs(target - a), Math.abs(target - b)));
        //     return res.subList(0, k);
        // }

        
        // private void dfs(TreeNode root, List<Integer> res) {
        //     if (root == null) {
        //         return;
        //     }

        //     res.add(root.val);
        //     dfs(root.left, res);
        //     dfs(root.right, res);
        // }

        // T:O(n)
        // S:O(n + k)
        // Method3: Using a deque(Double-queue) to handle the element when in-order traverse the tree
        Deque<Integer> deque = new ArrayDeque<>();
        dfs(root, deque, k, target);
        return new ArrayList<>(deque);
    }

    private void dfs(TreeNode root, Deque<Integer> queue, int k, double target) {
        // base case
        if (root == null) {
            return;
        }
        dfs(root.left, queue, k, target);
        queue.offerLast(root.val);
        if (queue.size() > k) {
            if (Math.abs(target - queue.peekFirst()) < Math.abs(target - queue.peekLast())) {
                queue.removeLast();
                // we can do an early return because any element we would add would have further distance
                return;
            } else {
                queue.removeFirst();
            }
        }
        dfs(root.right, queue, k, target);
    }
}


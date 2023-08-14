/*
Leetcode 133: Clone Graph
https://leetcode.com/problems/clone-graph/ 

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        // use BFS with hashMap
        // attention please:
        // make sure to check if the visited hashMap contains the element
        // if not added them into the visited map as well as into the queue
        // Also, don't forget to update the visited map and its related map;
        if (node == null) {
            return node;
        }
        Map<Node, Node> visited = new HashMap<>();
        Deque<Node> queue = new ArrayDeque<>();
        visited.put(node, new Node(node.val, new ArrayList<Node>()));
        queue.addFirst(node);
        while (!queue.isEmpty()) {
            Node curNode = queue.pollLast();
            // check currentNode's neighbors
            for (Node neighbor: curNode.neighbors) {
                // what is the hashMap doesn't contain this one
                if (!visited.containsKey(neighbor)) {
                    queue.addFirst(neighbor);
                    // created new node and add the node into the hashMap
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<Node>()));
                }
                // add neighbor's list into the curNode neighbor's list
                visited.get(curNode).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }
}

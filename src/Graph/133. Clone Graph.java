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

// Methodology:
// Basicially we should copy the exactly same nodes
// and finally return it
// look at the constructor, we knew the node has its own neighbors

// ChatGPT: The algorithm uses a breadth-first search (BFS) approach to traverse the original graph and create a deep copy of it.

class Solution {
    public Node cloneGraph(Node node) {
        // Use BFS with a hashMap to set up the relationship between nodes
        if (node == null) {
            return node;
        }
        Deque<Node> queue = new ArrayDeque<>();
        Map<Node, Node> visited = new HashMap<>();
        queue.offerLast(node);
        // Firstly, we should initilizae the new node
        visited.put(node, new Node(node.val, new ArrayList<>()));
        // check if the queue is empty
        while (!queue.isEmpty()) {
            // poll the first element we saw out first
            Node curNode = queue.pollFirst();
            // check the neighbors as we need to deep copy
            for (Node nei : curNode.neighbors) {
                // check if the current neighbor has been visited before
                if (!visited.containsKey(nei)) {
                    // if not, we should add the reflection relationship
                    visited.put(nei, new Node(nei.val, new ArrayList<>()));
                    // put it to the queue, serve for checking its neighbors in the future
                    queue.offerLast(nei);
                }
                // adding the nei's clone node to add into curNode's neighbors.
                // Regardless of whether nei is visited or not, the code ensures that the neighbor in the cloned graph (visited.get(nei)) 
                // is added to the neighbors list of the current node in the cloned graph (visited.get(curNode)). This step essentially 
                // replicates the edges (connections) between nodes in the cloned graph, ensuring that the cloned graph mirrors the structure of the original graph.
                visited.get(curNode).neighbors.add(visited.get(nei));
            }
        }
        return visited.get(node);
    }
}

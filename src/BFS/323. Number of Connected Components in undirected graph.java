/*
You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
Return the number of connected components in the graph.

Example 1:
Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2

Example 2:
Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1 

Constraints:

1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
There are no repeated edges.
*/

class Solution {
    public int countComponents(int n, int[][] edges) {
        // Methodology
        // I thought I can just process the edges matrix and check how many indegree equals to 0 that we have, which is the answer, however it is incorrect.

        // Correct approach is we can actually construct an adj list
        // then process from [0, n) then to find how many independent graph we had
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            adj.get(u).add(v);
            // undirected map
            adj.get(v).add(u);
        }
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                DFS(i, visited, adj);
                res++;
            }
        }
        return res;
    }

    // this DFS would make sure to traverse elements that each node and its neighbor can reach.
    private void DFS(int node, Set<Integer> visited, List<List<Integer>> adj) {
        visited.add(node);
        for (int nei :  adj.get(node)) {
            if (visited.add(nei)) {
                DFS(nei, visited, adj);
            }
        }
    }
}

/*
BFS Solution:
class Solution {
    public int countComponents(int n, int[][] edges) {
        // Create an adjacency list representation of the graph.
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        
        // Populate the adjacency list based on the given edges.
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u); // Since it's an undirected graph, add the reverse edge.
        }
        
        boolean[] visited = new boolean[n];
        int components = 0;
        
        // Perform BFS to count the connected components.
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, adjList, visited);
                components++;
            }
        }
        
        return components;
    }
    
    private void bfs(int node, List<List<Integer>> adjList, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        visited[node] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : adjList.get(current)) {
                if (!visited[neighbor]) {
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
    }
}
*/


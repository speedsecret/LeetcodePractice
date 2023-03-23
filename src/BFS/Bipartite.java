package BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * public class GraphNode {
 *   public int key;
 *   public List<GraphNode> neighbors;
 *   public GraphNode(int key) {
 *     this.key = key;
 *     this.neighbors = new ArrayList<GraphNode>();
 *   }
 * }
 */

public class Bipartite {
  public static boolean isBipartite(List<GraphNode> graph) {
    if (graph == null || graph.size() == 0) {
      return true;
    }
    Map<GraphNode, Integer> visited = new HashMap<>();
    for (GraphNode node: graph) {
     // if we can find some other route to go to this same node
     // we should return false;
     // so the if condition should be true as it is not a bipartite
     if (!BFS(visited, node)) {
      return false;
     }
    }
    return true;
  }

  private static boolean BFS(Map<GraphNode, Integer> visited, GraphNode node) {
    // base case
    if (visited.containsKey(node)) {
     return true;
    }
    Deque<GraphNode> queue = new ArrayDeque<>();
    queue.offerFirst(node);
    visited.put(node, 1);
    while (!queue.isEmpty()) {
      GraphNode curNode = queue.pollLast();
      int curGroup = visited.get(curNode);
      int neiGroup = curGroup == 0 ? 1 : 0;
      // search all curNode neighbors
      for (GraphNode neiNode: curNode.neighbors) {
        if (!visited.containsKey(neiNode)) {
          queue.offerFirst(neiNode);
          visited.put(neiNode, neiGroup);
        } else {
          if (visited.get(neiNode) != neiGroup) {
            return false;
          }
        }
      }
    }
    return true;
  }
}


class GraphNode {
   public int key;
   public List<GraphNode> neighbors;
   public GraphNode(int key) {
     this.key = key;
     this.neighbors = new ArrayList<GraphNode>();
   }
}
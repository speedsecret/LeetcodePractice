/*
743. Network Delay Time
https://leetcode.com/problems/network-delay-time/description/

You are given a network of n nodes, labeled from 1 to n. 
You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it 
takes for a signal to travel from source to target.
We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

Example 1:
Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:
Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:
Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
 
Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
  */

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Adjacency list
        Map<Integer, List<int[]>> adj = new HashMap<>();

        // Build the adjacency list
        for (int[] time : times) {
            int src = time[0];
            int dest = time[1];
            int travelTime = time[2];
            // two steps combined together into 1
            adj.computeIfAbsent(src, key -> new ArrayList<>()).add(new int[]{dest, travelTime});
        }
        // prepare for the final output
        int[] signalReceivedAt = new int[n + 1];
        Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);

        // Priority queue with a lambda comparator
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        pq.add(new int[]{0, k}); // Start from node k

        signalReceivedAt[k] = 0; // Time for starting node is 0

        while (!pq.isEmpty()) {
            int[] topPair = pq.poll();

            int currNode = topPair[1];
            int currNodeTime = topPair[0];

            if (currNodeTime > signalReceivedAt[currNode]) {
                continue;
            }

            if (!adj.containsKey(currNode)) {
                continue;
            }

            // Broadcast the signal to adjacent nodes
            for (int[] edge : adj.get(currNode)) {
                int time = edge[1];
                int neighborNode = edge[0];

                // Fastest signal time for neighborNode so far
                // signalReceivedAt[currNode] + time :
                // time when signal reaches neighborNode
                if (signalReceivedAt[neighborNode] > currNodeTime + time) {
                    signalReceivedAt[neighborNode] = currNodeTime + time;
                    pq.add(new int[]{signalReceivedAt[neighborNode], neighborNode});
                }
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, signalReceivedAt[i]);
        }

        // Integer.MAX_VALUE signifies at least one node is unreachable
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}

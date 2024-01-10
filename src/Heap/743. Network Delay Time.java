/*
743. Network Delay Time.java
https://leetcode.com/problems/network-delay-time/description/

You are given a network of n nodes, labeled from 1 to n. You are also given times, 
a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, 
vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes 
to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

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

// Methodology
// Using MinHeap to keep track the current point, the travel time needed from start point to end point.
// Creating an adjMap to store the direct graph.

// it is important to combine the question with course schedule I and course schedule II question.
// also, compare this question to Leetcode 815 bus routes.
// https://leetcode.com/problems/bus-routes/description/

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // use a PriorityQueue with a BFS to traverse all its neighbors when we processing pq.
        // traverse the whole int[][] times, and keep them in a hashMap
        // hashMap is an adjMap: Map<Integer, List<Integer>>
        Map<Integer, List<int[]>> adjMap = new HashMap<>();
        for (int[] time : times) {
            int src = time[0];
            int dest = time[1];
            int travelTime = time[2];
            adjMap.computeIfAbsent(src, a -> new ArrayList<>()).add(new int[]{dest, travelTime});
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        minHeap.add(new int[]{k, 0});

        int[] signalArray = new int[n + 1];
        Arrays.fill(signalArray, Integer.MAX_VALUE);
        signalArray[k] = 0;

        while (!minHeap.isEmpty()) {
            int[] preNodeArr = minHeap.poll();
            int preNode = preNodeArr[0];
            int preTravelTime = preNodeArr[1];
            if (signalArray[preNode] < preTravelTime || !adjMap.containsKey(preNode)) {
                continue;
            }

            // it is essentially the same thing like up/down/left/right directions.
            for (int[] nei : adjMap.get(preNode)) {
                int neiNode = nei[0];
                int neiTime = nei[1];
                // we found a new solution by using the neighbor as a bridge to its curret neiNode
                if (neiTime + signalArray[preNode] < signalArray[neiNode]){
                    signalArray[neiNode] = neiTime + signalArray[preNode];
                    minHeap.add(new int[]{neiNode, signalArray[neiNode]});
                }
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(signalArray[i], answer);
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}

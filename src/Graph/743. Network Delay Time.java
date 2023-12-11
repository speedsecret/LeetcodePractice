/*
743. Network Delay Time
https://leetcode.com/problems/network-delay-time/description/

You are given a network of n nodes, labeled from 1 to n. 
You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where 
ui is the source node, 
vi is the target node, 
wi is the time it takes for a signal to travel from source to target.
We will send a signal from a given node k. 
Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

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
// This is really like the maze II, which also use a PriorityQueue with BFS + dijkstra.

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

        // use an int answer to validate all element
        // we are not allow any of the element in the signalReceivedAt array is Integer.MAX_VALUE.
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, signalReceivedAt[i]);
        }

        // Integer.MAX_VALUE signifies at least one node is unreachable
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}

/*
Dec. 10th 2023
// Methodology
// Using MinHeap to keep track the current point, the travel time needed from start point to end point.
// Creating an adjMap to store the direct graph.

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adjMap = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int[] time = times[i];
            int src = time[0];
            int dest = time[1];
            int travelTime = time[2];
            adjMap.computeIfAbsent(src, key -> new ArrayList<>()).add(new int[]{dest, travelTime});
        }      

        // create an array with size of (n + 1), why?
        // so we can use index i to present the info {src, dest, travelTime}
        // as we already know the starting point is the index itself, we could use an int[] array to represent it
        int[] signalArray = new int[n + 1];
        Arrays.fill(signalArray, Integer.MAX_VALUE);
        // it is always 0 travel time since we started from the starting point.
        signalArray[k] = 0;

        // create a minHeap to store each possible travel State
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        minHeap.add(new int[]{k, 0});
        while (!minHeap.isEmpty()) {
            int[] preState = minHeap.poll();
            int preNode = preState[0];
            int preTravelTime = preState[1];

            // if the signalArray[preNode] already smaller than preTravelTime
            // or there isn't exist such a node in the adjMap, we just skip it.
            if (signalArray[preNode] < preTravelTime || !adjMap.containsKey(preNode)) {
                continue;
            }

            // check the preNode's neighbor
            for (int[] nei : adjMap.get(preNode)) {
                int neiNode = nei[0];
                int neiTravelTime = nei[1];
                if (signalArray[preNode] + neiTravelTime < signalArray[neiNode]) {
                    signalArray[neiNode] = signalArray[preNode] + neiTravelTime;
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
*/

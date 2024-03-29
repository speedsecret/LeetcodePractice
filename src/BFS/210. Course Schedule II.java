/*
210. Course Schedule II.java
https://leetcode.com/problems/course-schedule-ii/

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid 
answers, return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So the correct course order is [0,1].

Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:
Input: numCourses = 1, prerequisites = []
Output: [0]
*/

// Methodology
// Instead of using inDegree array, BFS with a queue
// DFS to check if cycle is existed is important, if cycle found, we can return false directly.

// Steps break down:
// Step1: adjList to construct the relationship between courses
// Step2: create a new hashMap, which records the course status: 
// 0 -> not visited
// 1 -> processing
// 2 -> visited
// step3: starts with each course, check if there is cycle exists, construct the LinkedList when
// processing the course and its neighbors.
// step4: convert the linkedList to an int array.

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //Approach II
        //DFS find the final courses and find out if there exist a cycle or not;
        
        //create adjList
        //create a visited Map to store it
        // HashMap to record courses that have been visited
        // build up and initilize the visited map
        List<List<Integer>> adj = new ArrayList<>();
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
            visited.put(i, 0);
        }

        for (int[] preCourse : prerequisites) {
            int pre = preCourse[1];
            int cur = preCourse[0];
            adj.get(pre).add(cur);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            // if the cycle has been detected
            if (!topoLogicalSort(adj, i, visited, res)) {
                return new int[0];
            }
        }

        int[] arr = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    private boolean topoLogicalSort(List<List<Integer>> adj, int i, Map<Integer, Integer> visited, LinkedList<Integer> res) {
        // base cases:
        // 1. we got the course and it has been processed successfully, return true;
        // 2. we got the course and it is current under processing, so return false;
        if (visited.get(i) == 2) {
            return true;
        } else if (visited.get(i) == 1) {
            return false;
        }

        visited.put(i, 1);
        for (int j : adj.get(i)) {
            if (!topoLogicalSort(adj, j, visited, res)) {
                return false;
            }
        }
        // record the current node has been processed.
        visited.put(i, 2);
        res.addFirst(i);
        return true;
    }
}

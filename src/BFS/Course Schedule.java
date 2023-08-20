/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you
should also have finished course 1. So it is impossible.
*/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // step1: setting up adjacent list, from pre --> cur
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        // step2: set up an int array to note down courses that needed to be taken before hand.
        int[] indegree = new int[numCourses];
        // step3: build up the inDegree arr.
        // indegree recorded that whether a current course has the pre-requisite requirement.
        for (int[] preCourse : prerequisites) {
            int pre = preCourse[1];
            int cur = preCourse[0];
            adj.get(pre).add(cur);
            // attention please, we need to add one element in its corresponding indegree arr
            inDegree[cur]++;
        }
        // step4: Set up the queue and put all indegree equals to 0 ---> queue
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.addFirst(i);
            }
        }

        // step5: use visited == 0 to record how many courses we had visited so far.
        int visited = 0;
        // BFS
        while (!queue.isEmpty()) {
            int preCourse = queue.pollLast();
            visited++;

            for (int neighbor: adj.get(preCourse)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.addFirst(neighbor);
                }
            }
        }

        return visited == numCourses;
    }
}

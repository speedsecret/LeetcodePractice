/*
444. Sequence Reconstruction.java
https://leetcode.com/problems/sequence-reconstruction/

You are given an integer array nums of length n where nums is a permutation of the integers in the range [1, n]. 
You are also given a 2D integer array sequences where sequences[i] is a subsequence of nums.

Check if nums is the shortest possible and the only supersequence. The shortest supersequence is a sequence with the shortest length and has all sequences[i] as subsequences. There could be multiple valid supersequences for the given array sequences.

For example, for sequences = [[1,2],[1,3]], there are two shortest supersequences, [1,2,3] and [1,3,2].
While for sequences = [[1,2],[1,3],[1,2,3]], the only shortest supersequence possible is [1,2,3]. [1,2,3,4] is a possible supersequence but not the shortest.
Return true if nums is the only shortest supersequence for sequences, or false otherwise.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
Example 1:

Input: nums = [1,2,3], sequences = [[1,2],[1,3]]
Output: false
Explanation: There are two possible supersequences: [1,2,3] and [1,3,2].
The sequence [1,2] is a subsequence of both: [1,2,3] and [1,3,2].
The sequence [1,3] is a subsequence of both: [1,2,3] and [1,3,2].
Since nums is not the only shortest supersequence, we return false.
Example 2:

Input: nums = [1,2,3], sequences = [[1,2]]
Output: false
Explanation: The shortest possible supersequence is [1,2].
The sequence [1,2] is a subsequence of it: [1,2].
Since nums is not the shortest supersequence, we return false.
Example 3:

Input: nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
Output: true
Explanation: The shortest possible supersequence is [1,2,3].
The sequence [1,2] is a subsequence of it: [1,2,3].
The sequence [1,3] is a subsequence of it: [1,2,3].
The sequence [2,3] is a subsequence of it: [1,2,3].
Since nums is the only shortest supersequence, we return true.
 

Constraints:

n == nums.length
1 <= n <= 104
nums is a permutation of all the integers in the range [1, n].
1 <= sequences.length <= 104
1 <= sequences[i].length <= 104
1 <= sum(sequences[i].length) <= 105
1 <= sequences[i][j] <= n
All the arrays of sequences are unique.
sequences[i] is a subsequence of nums.
*/

class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> seqs) {
        // Create the graph to represent the sequences and in-degrees of each element
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        // build the graph and inDegree
        for (List<Integer> seq : seqs) {
            if (seq.size() == 1) {
                graph.putIfAbsent(seq.get(0), new HashSet<>());
                inDegree.putIfAbsent(seq.get(0), 0);
            }
            for (int i = 1; i < seq.size(); i++) {
                Integer u = seq.get(i - 1), v = seq.get(i);
                graph.putIfAbsent(u, new HashSet<>());
                inDegree.putIfAbsent(u, 0);
                graph.putIfAbsent(v, new HashSet<>());
                inDegree.putIfAbsent(v, 0);
                if (!graph.get(u).contains(v)) {
                    graph.get(u).add(v);
                    inDegree.put(v, inDegree.get(v) + 1);
                }
            }
        }

        // validation check
        // the size of graph and the length of nums must be the same otherwise return false
        if (graph.size() != nums.length) {
            return false;
        }

        // BFS + Topological sort
        Deque<Integer> queue = new ArrayDeque<>();
        for (Integer ele : inDegree.keySet()) {
            if (inDegree.get(ele) == 0) {
                queue.addLast(ele);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (queue.size() > 0) {
            // once we found the size of queue is larger than 1, we should return false;
            // because it represent that there are multiple supersubsequence
            if (queue.size() > 1) {
                return false;
            }
            Integer cur = queue.removeFirst();
            res.add(cur);
            for (Integer next : graph.get(cur)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    queue.addLast(next);
                }
            }
        }
        // must be the only shortest supersequence
        return Arrays.equals(res.stream().mapToInt(Integer::intValue).toArray(), nums);
    }
}

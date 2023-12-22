/*
Questions56: Merge Intervals
https://leetcode.com/problems/merge-intervals/description/
Given an array of intervals where intervals[i] = [starti, endi],
merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
*/

// Abstraction:
// Merge int[][] intervals.

// Methodology:
// Sort the intervals, sort by ascending order, according to the start element.
// Adding the first interval into the result
// Loop the interval from index = 1 to index = intervals.length - 1;

class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort the intervals based on the start time
        // LinkedList as the output ---> return res.toArray(new int[res.size()][]);
        // Condition check if res.getLast()[1] >= curInterval[0];
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        // add the first one into the result
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] curInterval = intervals[i];
            if (res.getLast()[1] >= curInterval[0]) {
                res.getLast()[1] = Math.max(res.getLast()[1], curInterval[1]);
            } else {
                res.add(curInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}

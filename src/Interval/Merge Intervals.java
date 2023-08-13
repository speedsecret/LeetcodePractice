/*
Leetcode 56:Merge Intervals
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

class Solution {
    public int[][] merge(int[][] intervals) {
        // so the algo is to use a linked List to compare previous end element
        // with current first element;
        // if preEnd >= curStart
        // make sure to use the larger one preEnd, curEnd
        // else ---> add the current one to the linkedList
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> list = new LinkedList<>();
        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] curInterval = intervals[i];
            int preLastEle = list.get(list.size() - 1)[1];
            if (list.getLast()[1] >= curInterval[0]) {
                list.getLast()[1] = Math.max(list.getLast()[1], curInterval[1]);
            } else {
                list.add(curInterval);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}

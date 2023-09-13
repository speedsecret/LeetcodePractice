/*
Question57: Insert Interval
https://leetcode.com/problems/insert-interval/description/

You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the 
end of the ith interval and intervals is sorted in ascending order by starti.
You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not
have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

Constraints:
0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105
*/

// Methodology:
// Using a counter to check if we had already processed the newInterval
// LinkedList<int[]> res as a middle layer output.
// case1: No overlap.
//    case1.1: curInterval[0] > newInterval[1] || newInterval[0] > curInterval[1]
//       case1.1.1: counter == 1 && curInterval[0] > newInterval[1] 
//       case1.1.2: add the curInterval
// case2: Yes, overlap --> then update newInterval without adding it into the output.

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> res = new LinkedList<>();
        if (intervals.length == 0) {
            res.add(newInterval);
            return res.toArray(new int[res.size()][]);
        }
        int counter = 1;
        for (int i = 0; i < intervals.length; i++) {
            int[] curInterval = intervals[i];
            // case1: No overlap wiz new interval
            if (curInterval[0] > newInterval[1] || newInterval[0] > curInterval[1]) {
                // case1.1 If we knew the newInterval[1] is smaller than the smallest interval start0, we should add the interval now.
                if (counter == 1 && curInterval[0] > newInterval[1]) {
                    res.add(newInterval);
                    counter++;
                }
                res.add(curInterval);
            } 
            // case2: Overlapped
            // update the newInterval
            else {
                newInterval[0] = Math.min(curInterval[0], newInterval[0]);
                newInterval[1] = Math.max(curInterval[1], newInterval[1]);
            }
        }
        // Don't forget to add the newInterval.
        if (counter == 1) {
            res.add(newInterval);
        }
        // paying attention to the return type.
        return res.toArray(new int[res.size()][]);
    }
}

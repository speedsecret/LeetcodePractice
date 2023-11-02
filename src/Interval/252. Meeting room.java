/*
252. Meeting room
https://leetcode.com/problems/meeting-rooms/description/ 

Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: true
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti < endi <= 106
*/

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        // sort the array first, then loop the array
        // if the array is not gonna work. We would need to return false;
        if (intervals.length <= 1) {
            return true;
        }
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        int[] preInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] curInterval = intervals[i];
            // it is necessary to sort out this logic
            // if the current startTime is earlier than the previous end time
            // we got return false. 
            if (curInterval[0] < preInterval[1]) {
                return false;
            } else {
                preInterval = curInterval;
            }
        }
        return true;
    }
}

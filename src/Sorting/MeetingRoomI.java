package Sorting;

import java.util.Arrays;

public class MeetingRoomI {
    public static boolean canAttendMeetings(int[][] intervals) {
        // only allow one intervals results.
        // sort the array first then we can just compare it
        // once we found Last endTime is larger than first startTime just return false
        // return true if possible
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return true;
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        if (intervals.length == 2) {
            return intervals[0][1] <= intervals[1][0];
        }
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {2, 4},
                {5, 8},
                {3, 9},
                {13, 15}
        };
        int[][] intervals2 = {
                {2, 10},
                {13, 15}
        };
        System.out.println(canAttendMeetings((intervals)));
        System.out.println(canAttendMeetings((intervals2)));
    }
}

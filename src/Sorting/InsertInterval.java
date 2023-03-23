package Sorting;

/*
Insert an interval to a sorted list of non-overlap intervals, the result list should
also be sorted and non-overlapped
 */

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    // Case1: No over-lap --->
    // case1.1 the current one is completely smaller than newInterval
    // case1.2 the current one is completely larger than newInterval
    // both needed to be added into current one into the final result;
    // case2: overlap --> merge Interval
    // use a boolean inserted to update the status of new interval
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<int[]> res = new ArrayList<>();
        boolean inserted = false;
        for (int[] cur : intervals) {
            // case 1.1
            if (cur[1] < newInterval[0]) {
                res.add(cur);
            }
            // case 1.2
            else if (cur[0] > newInterval[1]) { //[3,9], [1, 2]
                if (!inserted) {
                    res.add(newInterval);
                    inserted = true;
                }
                res.add(cur);
            }
            // case 2
            // there is an overlap exist but has not been inserted into the array yet
            else {
                newInterval[0] = Math.min(newInterval[0], cur[0]);
                newInterval[1] = Math.max(newInterval[1], cur[1]);
            }
        }
        if (!inserted) {
            res.add(newInterval);
        }
        return res.toArray(new int[res.size()][]);
    }
}

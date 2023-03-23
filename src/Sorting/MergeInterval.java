package Sorting;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MergeInterval {
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return null;
        }
        // sort the start time.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merge = new LinkedList<>();
        // loop the intervals
        merge.add(intervals[0]);
        // index started from 1
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (merge.isEmpty() || merge.getLast()[1] < interval[0]) {
                merge.add(interval);
            } else {
                merge.getLast()[1] = Math.max(merge.getLast()[1], interval[1]);
            }
        }
//        for (int[] interval : intervals) {
//            int lastIntervalEndValue = merge.getLast()[1];
//            if (merge.isEmpty() || lastIntervalEndValue < interval[0]) {
//                merge.add(interval);
//            } else {
//                merge.getLast()[1] = Math.max(lastIntervalEndValue, interval[1]);
//            }
//        }
        return merge.toArray(new int[merge.size()][]);
    }

    public static int length(List<Interval> intervalList) {
        if (intervalList == null || intervalList.size() == 0) {
            return 0;
        }
        Collections.sort(intervalList, (a, b) -> Integer.compare(a.start, b.start));
        int res = 0;
        Interval prev = intervalList.get(0);
        for (int i = 1; i < intervalList.size(); i++) {
            // case 1: there is an overlap, update the intervals
            Interval cur = intervalList.get(i);
            if (prev.end > cur.start) {
                prev.end = Math.max(prev.end, cur.end);
            } else {
                res += prev.end - prev.start;
                prev = cur;
            }
        }
        res += prev.end - prev.start;
        return res;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {2, 4},
                {3, 9},
                {5, 8},
                {13, 15}
        };
        Interval interval_1 = new Interval(2, 4);
        Interval interval_2 = new Interval(3, 9);
        Interval interval_3 = new Interval(5, 8);
        Interval interval_4 = new Interval(13, 15);
        List<Interval> intervalList = Arrays.asList(interval_1, interval_2, interval_3, interval_4);
        System.out.printf("Current length == " + length(intervalList) + "\n");
        int[][] intervalsResult = merge(intervals);
        for (int i = 0; i < intervalsResult.length; i++) {
            System.out.println(Arrays.toString(intervalsResult[i]) + "\n");
        }
    }

    static class Interval{
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

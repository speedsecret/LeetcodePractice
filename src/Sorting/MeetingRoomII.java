package Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

class MeetingRoomII {
    public static int minMeetingRooms(int[][] intervals) {
        // Leetcode No.56的变种
        // check how many meetings are required, is to check how many overlapped the meetings we had.
        // we could always to maintain a meeting queue
        // once meeting finished, we can just release this meeting room.
        // 通过画图解析 得需要使用一个minheap的数据结构
        // 比较的就是array[1] 的大小
        if (intervals.length == 1) {
            return 1;
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int numsOfMinRooms = 1;
        int globalMax = 1;
        minHeap.add(intervals[0]);
        for (int i = 1; i <= intervals.length - 1; i++) {
            int[] curInterval = minHeap.peek();
            // if current peek() end-time is later than the next start time
            // we need to add a new room
            if (curInterval[1] > intervals[i][0]) {
                numsOfMinRooms++;
            }
            globalMax = Math.max(globalMax, numsOfMinRooms);
            minHeap.add(intervals[i]);
        }
        return globalMax;
    }

    public static int minMeetingRooms_ArraysSort(int[][] intervals) {
        if (intervals == null || intervals[0].length == 0) {
            return 0;
        }
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int startIndex = 0, endIndex = 0, room = 0, res = 0;
        while (startIndex < intervals.length) {
            if (start[startIndex] < end[endIndex]) {
                room++;
                startIndex++;
                res = Math.max(res, room);
            } else {
                room--;
                endIndex++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15,20}
        };
        int[][] intervals2 = {
                {9,21},{15,30},
                {13,34},{21,44},
                {23,29}, {16,32},
                {17,38},{22,46},
                {5,11},{22,31},{13,24}
        };
        System.out.println(minMeetingRooms_ArraysSort((intervals2)));
    }
}

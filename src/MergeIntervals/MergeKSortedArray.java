package MergeIntervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Merge K sorted array into one big sorted array in ascending order.

Assumptions

The input arrayOfArrays is not null, none of the arrays is null either.
 */
public class MergeKSortedArray {
    // assume there is none empty array, neither there is array is null
    /*
    public static int[] mergeKSortArray(int[][] arrayOfArrays) {
        // Step 1: pick MinHeap as a core data structure
        // Step 2: Visited each head element in each array and put them into the minHeap
        // Step 3: minHeap.poll() and minHeap.offer() to adjust the process --> fulfill the final result
        PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                if (o1.value == o2.value) {
                    return 0;
                }
                return o1.value < o2.value ? -1 : 1;
            }
        });
        // prep for creating a result array
        int length = 0;
        for (int i = 0; i < arrayOfArrays.length; i++) {
            int[] array = arrayOfArrays[i];
            length += array.length;
            if (array.length != 0) {
                minHeap.offer(new Entry(i, 0, array[0]));
            }
        }
        int[] res = new int[length];
        int curIndex = 0;
        while (!minHeap.isEmpty()) {
            Entry curEntry = minHeap.poll();
            int curRow = curEntry.row;
            int curCol = curEntry.col;
            res[curIndex++] = curEntry.value;
            // check if we are standing at the end of this current array
            if (curCol + 1 < arrayOfArrays[curRow].length) {
                curCol++;
                minHeap.offer(new Entry(curRow, curCol, arrayOfArrays[curRow][curCol]));
            }
        }
        return res;
    }

    static class Entry{
        int row;
        int col;
        int value;
        public Entry(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
    */
    // Analysis:
    // Use a minHeap to put each head of array in the arrayOfArrays into it.
    // Creating a class
    // Use a while loop to poll the current minimum element, and add the next element in that array
    public static int[] merge(int[][] arrayOfArrays) {
        PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(11, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                if (o1.val == o2.val) {
                    return 0;
                }
                return o1.val < o2.val ? -1 : 1;
            }
        });
        int length = 0;
        for (int i = 0; i < arrayOfArrays.length; i++) {
            int[] curArray = arrayOfArrays[i];
            length += curArray.length;
            if (curArray.length > 0) {
                minHeap.offer(new Entry(i, 0, curArray[0]));
            }
        }
        // array should be created
        int[] res = new int[length];
        int index = 0;
        while (!minHeap.isEmpty()) {
            Entry curEntry = minHeap.poll();
            res[index++] = curEntry.val;
            if (curEntry.col + 1 < arrayOfArrays[curEntry.row].length) {
                curEntry.col++;
                minHeap.offer(new Entry(curEntry.row, curEntry.col, arrayOfArrays[curEntry.row][curEntry.col]));
            }
        }
        return res;
    }

    static class Entry {
        int row;
        int col;
        int val;
        public Entry(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[][] arrayOfArrays = {
                {3},
                {1, 2, 3, 4, 5}
        };
        System.out.println("Current sorted new array is: " + Arrays.toString(merge(arrayOfArrays)));
    }
    // T: O(nlogk)
    // S: O(k)
}

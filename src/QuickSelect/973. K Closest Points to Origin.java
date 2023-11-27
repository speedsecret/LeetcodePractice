/*

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 
Example 1:


Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 

Constraints:

1 <= k <= points.length <= 104
-104 <= xi, yi <= 104

https://leetcode.com/problems/k-closest-points-to-origin/
*/

// Methodology:
// Method1: Use maxHeap O(nlogk)
// Method2: Quick select O(n) --> pipe up larger or equals elements to right sides.

class Solution {
    // public int[][] kClosest(int[][] points, int k) {
    //     // use a maxHeap algorithm
    //     PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0] * b[0] + b[1] * b[1], a[0] * a[0] + a[1] * a[1]));
    //     for (int[] curPoint : points) {
    //         maxHeap.add(curPoint);
    //         if (maxHeap.size() > k) {
    //             maxHeap.poll();
    //         }
    //     }
    //     int[][] res = new int[k][2];
    //     for (int i = 0; i < k; i++) {
    //         int[] curPoint = maxHeap.poll();
    //         res[i][0] = curPoint[0];
    //         res[i][1] = curPoint[1];
    //     }
    //     return res;
    // }

    public int[][] kClosest(int[][] points, int k) {
        // use quickSelect T: O(n)
        // create a place holder 2-D array
        int[][] res = new int[k][2];
        quickSelect(points, 0, points.length - 1, k - 1);
        // copy and paste all sorted elements from index = 0 to index = k -1
        for (int i = 0; i < k; i++) {
            res[i][0] = points[i][0];
            res[i][1] = points[i][1];
        }
        // return the placeHolder 2-D array
        return res;
    }

    private void quickSelect(int[][] points, int left, int right, int target) {
        // partition is the crucial step here
        int pivotIndex = partition(points, left, right);
        // case 1:
        if (pivotIndex == target) {
            return;
        }
        // case 2:
        // we don't have enough element to fulfill the final res
        else if (pivotIndex < target) {
            quickSelect(points, pivotIndex + 1, right, target);
        } 
        // case 3:
        // we can safely discard the right part of the array
        else {
            quickSelect(points, left, pivotIndex - 1, target);
        }
    }

    // the goal is to having all k elements seat in index from 0 to k - 1;
    private int partition(int[][]points, int left, int right) {
        int pivot = dist(points[right]);
        int start = left;
        int end = right - 1;
        while (start <= end) {
            if (dist(points[start]) < pivot) {
                start++;
            } else if (dist(points[end]) > pivot) {
                end--;
            } else {
                swap(points, start++, end--);
            }
        }
        // swap the pivot element back
        swap(points, start, right);
        return start;
    }

    private int dist(int[] arr) {
        return arr[0] * arr[0] + arr[1] * arr[1];
    }
    
    private void swap(int[][] points, int left, int right) {
        int[] tempArr = points[left];
        points[left] = points[right];
        points[right] = tempArr;
    }
}

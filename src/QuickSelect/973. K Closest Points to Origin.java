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

    public int[][] kClosest(int[][] points, int K) {
        //method2 : use quickSelect:
        //only focusing on one element not focus on the whole array;
        //The quickSelect function is used to find the Kth smallest element. The K - 1 is used because array indices are zero-based, which means that the first element is at index 0, the second element at index 1, and so on.
        quickSelect(points, 0, points.length - 1, K - 1);
        int[][] ans = new int[K][];
        for (int i = 0; i < K; i++)
            ans[i] = points[i];
        return ans;
    }
   
    private void quickSelect(int[][] points, int i, int j, int target) {
        int mid = partition(points, i, j);
        if (mid == target) {
            return;
        } else if (mid > target) {
            quickSelect(points, i, mid - 1, target);
        } else {
            quickSelect(points, mid + 1, j, target);
        }
    }
   
    private int partition(int[][] points, int i, int j) {
        int[] pivotIndex = points[j];
        int pivot = dist(pivotIndex);
        int start = i;
        int end = j - 1;
        while (start <= end) {
            if (dist(points[start]) < pivot) {
                start++;
            } else if (dist(points[end]) >= pivot) {
                end--;
            } else {
                swap(points, start++, end--);
            }
        }
        swap(points, start, j);
        return start;
    }
   
    private void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }
   
    private int dist(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }
}




/*
986. Interval List Intersections
https://leetcode.com/problems/interval-list-intersections/description/

You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.

The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].

Example 1:


Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Example 2:

Input: firstList = [[1,3],[5,9]], secondList = []
Output: []
*/

// Abstraction:
// Get me with the interval intersections, if they only overlapped within a edge, create new int[]{edge, edge} and add it into the result.

// Methodology:
// Use two pointers, first and second;
// Loop the two int[][] at the same time
// Define the new leftBound and new rightBound, adding intersection if applicable;
// Move the left or right index

class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
       List<int[]> res = new ArrayList<>();
       if (firstList.length == 0 || secondList.length == 0) {
           return res.toArray(new int[0][]);
       }
       // use two pointers to represent the first matrix - > first
       // and the second matrix --> second
       int first = 0, second = 0;
       while (first < firstList.length && second < secondList.length) {
           // let's assume A and B would intersect with each other
           int low = Math.max(firstList[first][0], secondList[second][0]);
           int high = Math.min(firstList[first][1], secondList[second][1]);
           
           // low is reasonable smaller than high
           // add the int[] into the final res
           if (low <= high) {
               res.add(new int[]{low, high});
           }

           // check which one needs to be discard and the corresponding index would move forward 1 step.
           if (firstList[first][1] < secondList[second][1]) {
               first++;
           } else {
               second++;
           }
       }
       return res.toArray(new int[res.size()][]);
    }
}


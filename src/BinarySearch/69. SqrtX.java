/*
69. SqrtX.java
https://leetcode.com/problems/sqrtx/description/
  
Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
Example 1:

Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.
Example 2:

Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
*/

// Methodology
// Binary Search, left = 1, right = x;
// check the 'mid' and compare 'mid' with 'x / mid';
// return right at the end.

class Solution {
    public int mySqrt(int x) {
        // binary Search to find the smaller sqrt
        // however, it is important to check the lower bound and the upperBound.
        if (x <= 1) {
            return x;
        }
        int left = 1, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // The binary search continues until left is greater than right, at which point, 
        // right represents the largest integer whose square is less than or equal to x
        // which is the square root rounded down.
        return right;
    }
}

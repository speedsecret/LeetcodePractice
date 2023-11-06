/*
279. Perfect Square.java
https://leetcode.com/problems/perfect-squares/

Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/

// Methodology
// DP --> solve subproblem then solve bigger, then solve the whole problem.

class Solution {
    public int numSquares(int n) {
        // Mindset: DP
        // To calculate the value of numSquares(n)\text{numSquares}(n)numSquares(n), first we need to calculate all the values before nnn, i.e. numSquares(n−k)∀k∈{square numbers}\text{numSquares}(n-k) \qquad \forall{k} \in\{\text{square numbers}\}numSquares(n−k)∀k∈{square numbers}. If we have already kept the solution for the number n−kn-kn−k in somewhere, we then would not need to resort to the recursive calculation which prevents the stack overflow.
        // we need to find the minimum combination of square numbers
        // use iterative way to solve this
        // so the smaller element which is also the square number is
        // 1, 4, 9, 16, 25 .. 10,000
        // DFS something like the coins questions but you have to find the least number of combination and return the answer.
        // start with the bottom-up solution.
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        
        // we need to solve smaller questions firstly
        // then move to larger one.
        for (int target = 1; target <= n; target++) {
            for (int j = 1; j <= target; j++) {
                int square = j * j;
                if ((target - square) < 0) {
                    break;
                }
                dp[target] = Math.min(dp[target], 1 + dp[target - square]);
            }
        }
        return dp[n];
    }
}

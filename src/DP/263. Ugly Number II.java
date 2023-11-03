/*
263. Ugly Number II.java

An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.
Example 1:

Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
*/
// Methodology:
// Using DP to create a length n array ---> dp[i] = new int[n];
// dp[0] = 1;

// then we iterate from [1 to n)
class Solution {
    public int nthUglyNumber(int n) {
        // use dynamic programming
        // it would be too slow if we would like to use the No.263 method.
        // instead, we knew the sequence of ugly numberes are
        // 1,2,3,4,5 ---> times 2, 3, 5;
        // 6, 8, 9, 10, 12, 15, 16, 18, 20
        // 6 = 3 * 2;
        // 8 = 4 * 2;
        // 9 = 3 * 3;
        // 10 = 2 * 5;
        // 12 = 4 * 3;
        // 15 = 5 * 3;
        // 16 = 8 * 2;
        // 18 = 6 * 3;
        // 20 = 10 * 2;
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for (int i = 1; i < n; i++) {
            int nextUglyNumber = Math.min(factor2, Math.min(factor3, factor5));
            ugly[i] = nextUglyNumber;
            
            // check if duplication happened
            if (nextUglyNumber == factor2) {
                factor2 = 2 * ugly[++index2];
            }
            if (nextUglyNumber == factor3) {
                factor3 = 3 * ugly[++index3];
            }
            if (nextUglyNumber == factor5) {
                factor5 = 5 * ugly[++index5];
            }
        }
        return ugly[n - 1];
    }
}

/*
  
263. Ugly number.java
https://leetcode.com/problems/ugly-number/description/

*/

class Solution {
    public boolean isUgly(int n) {
        // edge cases:
        if (n <= 0) {
            return false;
        }
        List<Integer> primeFactorList = Arrays.asList(2, 3, 5);
        Set<Integer> set = new HashSet<>(primeFactorList);
        for (int factor : set) {
            n = helper(n, factor);
        }
        return n == 1;
    }

    private int helper(int n, int factor) {
        while (n % factor == 0) {
            n /= factor;
        }
        return n;
    }
}

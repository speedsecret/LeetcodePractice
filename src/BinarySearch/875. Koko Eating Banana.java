/*
875. Koko Eating Bananas
https://leetcode.com/problems/koko-eating-bananas/

Similar question:
1283. Find the smallest Divisor Given a threshold
https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas,
she eats all of them instead and will not eat any more bananas during this hour.
Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:
Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:
Input: piles = [30,11,23,4,20], h = 6
Output: 23
*/

// Methodology
// We may use o(N^2) to find the answer easily, but the problem is that Time limit exceeds.
// So we could actually use the binary Search
// Use binary Search and we should determine the lower bound and upper bound.

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // actually, it is focused on binary search
        int left = 1, right = (int)1e9;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            int sum = 0;
            for (int ele : piles) {
                sum += (ele + mid - 1) / mid;
            }
            if (sum > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

// Solution for Leetcode 1283
// https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = (int)1e6;
        while (left < right) {
            int sum = 0;
            int mid = left + (right - left) / 2;
            for (int ele : nums) {
                sum += (ele + mid - 1) / mid;
            }
            // case 1:
            // the current sum > threshold, which means the divisor is too small
            // we need to increase the divisor!!!
            // so the left move up 1 step by mid.
            if (sum > threshold) {
                left = mid + 1;
            }
            // case 2: 
            else {
                right = mid;
            }
        }
        return left;
    }
}

// Solution for Leetcode 1891:
// https://leetcode.com/problems/cutting-ribbons/
class Solution {
    public int maxLength(int[] ribbons, int k) {
        int left = 1;
        int right = 100001; // Equivalent to (int) 1e5 + 1
        while (left < right) {
            int mid = left + (right - left) / 2;

            // if the length of ribbon is mid
            // are we able to cut it into >= k pieces?
            // if not, shorten the right(then try with a shorten length)
            if (!isCutPossible(ribbons, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }

    private boolean isCutPossible(int[] ribbons, int mid, int k) {
        int count = 0;
        for (int ele : ribbons) {
            count += ele / mid;
        }
        return count >= k;
    }
}


/*
Leetcode 5: Longest Palindromic Substring
https://leetcode.com/problems/longest-palindromic-substring/description/

Given a string s, return the longest 
palindromic substring in s.

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
*/
// Method:
// 1.Two pointers
// 2.Expand from Center

// when maxLen is odd. In a 0-indexed array, we need to adjust the left index such that it points to the start of the palindrome. Here's why:
// When maxLen is odd, (maxLen - 1) / 2 calculates half of the length minus one. This is because, in a palindrome like "racecar," you need to move maxLen / 2 steps
// from the center to reach the start or end.
// By subtracting 1, you adjust for the 0-based index. In a 0-indexed array, the center i is located at the exact middle element of the palindrome.
// So, to move to the start of the palindrome, you subtract 1.
// When maxLen is even, take 
//     i i+1
// 0 1 2 3   4 
// c c e e   c
// maxLength = 4
// l = i - (maxLength - 1) / 2 --> l = 2 - (4 - 1)/2 = 1;
// r = i + maxLength / 2 --> 2 + 2 = 4;
// if without subtracting one, we would encounter left = 0, right = 4 which is incorrect.

class Solution {
    public String longestPalindrome(String s) {
        // Never know about if the length of String s is odd or even
        // so we need to expandFromCenter by applying two sets of indices
        int maxLen = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            // why we started from i and i
            // as well as from i and i + 1 ?
            // because they are adapting different usage case
            int len1 = expandFromCenter(s, i, i);
            int len2 = expandFromCenter(s, i, i + 1);
            maxLen = Math.max(len1, len2);
            // i = 1
            if (maxLen > right - left) {
                left = i - (maxLen - 1) / 2;
                right = i + maxLen / 2;
            }
        }
        return s.substring(left, right + 1);
    }

    private int expandFromCenter(String s, int left, int right) {
        // Question:
        // why we would return right - left - 1?
        // Because we have over adding the index left and right once.
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}

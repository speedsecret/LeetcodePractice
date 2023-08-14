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
Method:
1.Two pointers
2.Expand from Center

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

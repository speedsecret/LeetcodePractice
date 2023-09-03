/*
3. Longest Substring without repeating characters.

https://leetcode.com/problems/longest-substring-without-repeating-characters/
Given a string s, find the length of the longest 
substring without repeating characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // two pointers
        // hashMap to store Character, Integer
        if (s.length() == 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            char curChar = s.charAt(end);
            while (map.containsKey(curChar)) {
                // attention please
                // we need to remove the element in this while loop
                // increment 1 each time
                map.remove(s.charAt(start++));
            }
            // add the current char into the hashMap
            map.put(curChar, end++);
            maxLength = Math.max(maxLength, end - start);
        }
        return maxLength;
    }
}

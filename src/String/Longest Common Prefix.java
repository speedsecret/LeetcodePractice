/*
14. Longest Common Prefix
https://leetcode.com/problems/longest-common-prefix/

Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
*/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // Let's assume the first string as the current longest common prefix.
        String prefix = strs[0];
        /*
        In the context of this algorithm:

        If strs[i].indexOf(prefix) is not equal to 0, it means that the current prefix is not a valid common   prefix for the current string strs[i]. So, the algorithm enters the while loop and shortens the prefix by removing the last character.

        If strs[i].indexOf(prefix) is equal to 0, it means that the current prefix is indeed a valid common prefix for the current string strs[i]. In this case, the algorithm proceeds to the next string in the array without modifying the prefix.
        */
        for (int i = 1; i < strs.length; i++) {
            while(strs[i].indexOf(prefix) != 0) {
                // when the strs[i] not starts with current prefix
                // shorten the prefix to a (one element)smaller string
                // until it goes to an empty string or strs[i] can starts with prefix
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        return prefix;
    }
}

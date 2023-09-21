/*
131. Palindrome Partitioning.java
https://leetcode.com/problems/palindrome-partitioning/

Given a string s, partition s such that every 
substring
 of the partition is a 
palindrome
. Return all possible palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
*/

// Methodology:
// This should be very efficient as one doesn't need to worry about the string concatenation.

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, "", new ArrayList<>(), s, 0);
        return res;
    }

    private void dfs(List<List<String>> res, String current, List<String> list, String s, int startIndex) {
        // base case:
        if (startIndex == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }

        // recursive rule:
        for (int i = 1; i <= s.length(); i++) {
            if (startIndex + i <= s.length()) {
                String currentStr = s.substring(startIndex, startIndex + i);
                if (isValidPalindrome(currentStr)) {
                    list.add(currentStr);
                    dfs(res, current, list, s, startIndex + i);
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    private boolean isValidPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

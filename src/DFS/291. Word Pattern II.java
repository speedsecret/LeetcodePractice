/*
291. Word Pattern II.java
https://leetcode.com/problems/word-pattern-ii/description/

Given a pattern and a string s, return true if s matches the pattern.

A string s matches a pattern if there is some bijective mapping of single characters to non-empty strings 
such that if each character in pattern is replaced by the string it maps to, then the resulting string is s.
A bijective mapping means that no two characters map to the same string, and no character maps to 
two different strings.
Example 1:
Input: pattern = "abab", s = "redblueredblue"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "red"
'b' -> "blue"
Example 2:
Input: pattern = "aaaa", s = "asdasdasdasd"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "asd"
Example 3:
Input: pattern = "aabb", s = "xyzabcxzyabc"
Output: false

Constraints:
1 <= pattern.length, s.length <= 20
pattern and s consist of only lowercase English letters.
*/

// Methodology
// DFS + try all different kinds of matches, then validate them.
// Besides, we need to maintain a Set String to deduplications for String match already existed.

class Solution {
  public boolean wordPatternMatch(String pattern, String s) {
        // Create a char-string mapping hashMap
        Map<Character, String> mapChar = new HashMap<>();
        Set<String> set = new HashSet<>();
        // Call the recursive helper function to check if all the recursively subproblems are valid
        return match(mapChar, set, 0, pattern, 0, s);
    }

    private boolean match(Map<Character, String> mapChar, Set<String> strSet, int pi, String pat, int si, String str) {
        // base cases:
        if (pi == pat.length() && si == str.length()) {
            return true;
        }
        if (pi == pat.length() || si == str.length()) {
            return false;
        }

        char c = pat.charAt(pi);

        // the hashMap has the mapping relationships
        // case1: the mapChar does contain the current char
        if (mapChar.containsKey(c)) {
            // then validate if the value in mapChar is part of the substring of str
            String mappedStr = mapChar.get(c);
            if (!str.startsWith(mappedStr, si)) {
                return false;
            }

            return match(mapChar, strSet, pi + 1, pat, si + mappedStr.length(), str);
        }

        // If this char-String mapping relations have not seen before
        // case2: the mapChar doesn't seen this char before
        for (int k = si; k < str.length(); k++) {
            String newString = str.substring(si, k + 1);

            if (strSet.contains(newString)) {
                continue;
            }

            // Create or update the mapping
            mapChar.put(c, newString);
            strSet.add(newString);

            // continue to match the rest of pattern and string
            if (match(mapChar, strSet, pi + 1, pat, si + newString.length(), str)) {
                return true;
            }

            // backTracking remove the mapping and makr the string segment as un-used.
            mapChar.remove(c);
            strSet.remove(newString);
        }
        return false;
    }
}

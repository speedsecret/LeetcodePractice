/*
438. find all anagrams in a string.java
https://leetcode.com/problems/find-all-anagrams-in-a-string/description/

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.
*/

// Methodology:
// Use the hashMap to store the <key,value> entry instance for smaller/shorter string
// perform two pointers(left, right) and using an int variable match to check the current status of matching.
// Keep the return list and kept adding the left index into the list.

class Solution {
    // Methodology
    // Use a hashMap to store all the characters and its frequency
    // for String p, then use two pointers left and right
    // to maintaining a size p slinding windown so we can extract the starting indices if there are matches.
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int left = 0, right = 0;
        int match = 0;
        Map<Character, Integer> map = getMap(p);
        while (right < s.length()) {
            char c = s.charAt(right);
            Integer count = map.get(c);
            if (count != null) {
                map.put(c, count - 1);
                if (count == 1) {
                    match++;
                }
            }
            if (right >= p.length()) {
                c = s.charAt(left);
                count = map.get(c);
                if (count != null) {
                    map.put(c, count + 1);
                    if (count == 0) {
                        match--;
                    }
                }
                left++;
            }
            if (match == map.size()) {
                res.add(left);
            }
            right++;
        }
        return res;
    }

    private Map<Character, Integer> getMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}

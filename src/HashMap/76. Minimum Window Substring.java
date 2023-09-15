/*
76. Minimum Window Substring.java
https://leetcode.com/problems/minimum-window-substring/

Given two strings s and t of lengths m and n respectively, return the minimum window 
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.
Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.

Follow up: Could you find an algorithm that runs in O(m + n) time?
*/

// Abstraction:
// Find the shortest minimum substring in s, that contains all elements in t.

// Methodology:
// Two hashMap plus an int[] arr
// One hashMap to store the elements and their freq of t.
// The other hashMap to store the current processed start and end interval substring of s.
// int[] arr store the globalMinWindowLength, globalMinLeft, globalMinRight;

class Solution {
    public String minWindow(String s, String t) {
        // use one hashMap to store the string t
        // use the other hashMap to store the current string within the window
        // set up an array int[] distance = {length, left, right};
        if (t.length() > s.length()) {
            return "";
        }
        Map<Character, Integer> tMap = new HashMap<>();
        for (char ch : t.toCharArray()) {
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }
        Map<Character, Integer> windowMap = new HashMap<>();
        int targetSize = tMap.size();
        int formed = 0;
        // use a int array to represent the current windowLength, leftIndex and rightIndex
        int[] res = new int[]{-1, 0, 0};
        int start = 0;
        int end = 0;

        // process the string s
        while (end < s.length()) {
            char ch = s.charAt(end);
            int count = tMap.getOrDefault(ch, 0);
            // insert element to the current sliding windowMap
            windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);

            if (count > 0 && windowMap.get(ch).intValue() == count) {
                formed++;
            }

            // while the formed added to the size of targetSize, we need to 
            // enter the while loop, so to update windowMap and formed(if applicable)
            while (formed == targetSize && start <= end) {
                // updated the char ch by using the start index
                ch = s.charAt(start);
                // the window has not been initialized yet or we found a smaller window
                // then update the distance, update the leftIndex and rightIndex
                if (res[0] == -1 || end - start + 1 < res[0]) {
                    res[0] = end - start + 1;
                    res[1] = start;
                    res[2] = end;
                }
                
                // windowMap remove element
                windowMap.put(ch, windowMap.get(ch) - 1);

                // if the removed element is part of the result, we might double check if formed needs to be decreased.
                if (tMap.containsKey(ch) && windowMap.get(ch).intValue() < tMap.get(ch).intValue()) {
                    formed--;
                }

                // move the start pointer one step forward
                start++;
            }
            end++;
        }
        // if res[0] == -1 which means we don't find anything
        // or we can just return s.substring(left, right + 1);
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }
}

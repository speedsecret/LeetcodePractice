/*
49. Group Anagrams.java
https://leetcode.com/problems/group-anagrams/description/

Given an array of strings strs, group the anagrams together. You can return the answer in any order.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]
 

Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/

// Methodology
// 去String 化，将不标准的String(char的集合）convert成标准的String
// String s--> char[] arr = s.toCharArray() --> Arrays.sort(arr)
// Use HashMap<String, List<String>> map

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // standardize String str
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String newString = new String(arr);
          
            if (!map.containsKey(newString)) {
                map.put(newString, new ArrayList<>());
            }
            map.get(newString).add(str);
        }

        for (String str : map.keySet()) {
            res.add(map.get(str));
        }
        return res;
    }
}

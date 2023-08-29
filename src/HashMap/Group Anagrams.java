/*
Leetcode 49: 
https://leetcode.com/problems/group-anagrams/submissions/

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.

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

// Method1: transfer each str in strs arr, sort its char
// and convert to new String(create new String object)
// check the hashMap, if we haven't seen this before, added a new linkedlist
// then add the new original string into the map.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Use Arrays.sort API to sort String.toCharArray()
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (String str : strs) {
            char[] arrChar = str.toCharArray();
            Arrays.sort(arrChar);
            // This would create a new String object
            String newString = new String(arrChar);
            // System.out.println("what is the new string looks like: " + newString);
            // This would present a String representation of the array itself, not its contents
            // String newString2 = arrChar.toString();
            // System.out.println("what is the new string looks like: " + newString2);
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

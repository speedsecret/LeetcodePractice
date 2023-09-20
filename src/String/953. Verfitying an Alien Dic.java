/*
953. Verfitying an Alien Dic.java
https://leetcode.com/problems/verifying-an-alien-dictionary/

In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
Example 1:
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:
Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:
Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', 
where '∅' is defined as the blank character which is less than any other character (More info).

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
*/

// Abstraction
// Given an String order which means the new rule of the Alien Dictionary
// Also, given a String[] words, so we can check if the words followed the rule of Alien Dic.

// Methodology
// 1. Create an int[] arr to quantify the new rule order.
// 2. Compare with each word in String[] arr iteratively
//    char preChar = words[i - 1]; char curChar = words[i]
//    case1: they have the same value for the current char
//    case2: the current char follow the rule(early return, break)
//    case3: the current char don't follow the current rule

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        // Figuring out what the alien dictionary order is by looping the String order
        // and note it down while using an int array
        // then compare each adjacent one in words
        int[] pos = new int[order.length()];
        for (int i = 0; i < pos.length; i++) {
            pos[order.charAt(i) - 'a'] = i;
        }

        // loop the String[] words
        for (int i = 1; i < words.length; i++) {
            String prev = words[i - 1], cur = words[i];
            int len = Math.min(prev.length(), cur.length());
            int countEqual = 0;
            for (int j = 0; j < len; j++) {
                char ph = prev.charAt(j), ch = cur.charAt(j);
                // case1: increment 1 for countEqual as this variable record how many letters are exactly the same
                if (ph == ch) {
                    countEqual++;
                } 
                // case2.1: find mismatch:
                // the prev is larger than the cur
                else if (pos[ph - 'a'] > pos[ch - 'a']){
                    return false;
                }
                // case2.2: if "ph - 'a'" index > "ch - 'a'" --> just break it 
                // as it is correct now matter how, we don't bother to iterative every letter in this current word
                else {
                    break;
                } 
            }
            // countEqual is used for compare case "app" and "apple" exist at the same time.
            if (countEqual == len && prev.length() > cur.length()) {
                return false;
            }
        }
        return true;
    }
}

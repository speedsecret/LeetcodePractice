/*
Leetcode 345 Reverse Vowels of a String
https://leetcode.com/problems/reverse-vowels-of-a-string/description/

Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', 
and they can appear in both lower and upper cases, more than once.

Example 1:
Input: s = "hello"
Output: "holle"

Example 2:
Input: s = "leetcode"
Output: "leotcede"
*/

class Solution {
    // Attention please:
    // Previously, I am using two while loops with the conditions left + 1 < right and right - 1 > left. However, these conditions might cause the pointers to skip valid vowel positions.
    private static char[] arr = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
    private static Set<Character> set = new HashSet<>();

    public String reverseVowels(String s) {
        // Clarification:
        // what is the vowels: a e i o u
        // check if they are belongs to vowels
        // use two pointers to loop the string
        int left = 0, right = s.length() - 1;
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        char[] charArray = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            charArray[i] = s.charAt(i);
        }
        while (left < right) {
            if (!set.contains(charArray[left])) {
                left++;
                continue;
            }
            
            if (!set.contains(charArray[right])) {
                right--;
                continue;
            }
            swap(charArray, left++, right--);
        }
        return new String(charArray);
    }

    private void swap(char[] charArr, int left, int right) {
        char temp = charArr[left];
        charArr[left] = charArr[right];
        charArr[right] = temp;
    }
}

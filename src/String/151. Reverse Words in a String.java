/*
151. Reverse Words in a String.java

Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

Example 1:
Input: s = "the sky is blue"
Output: "blue is sky the"
Example 2:
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

Constraints:

1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.

Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
*/
// remove the leading and trailing space if any
// then reverse the whole String s first
// finally reverse each single string in the reversed String s

class Solution {
    public String reverseWords(String s) {
        s = reverse(s.trim());
        int start = 0, end = 0;
        // dlrow olleh
        // s
        //      e
        String newString = "";
        while (end < s.length()) {
            if (end == 0 || s.charAt(end - 1) == ' ') {
                while (end < s.length() && s.charAt(end) != ' ') {
                    end++;
                }
                // the small trick here that we can optimized is to 
                // remodel the reverse method, we can pass three parameters into it
                // reverseFunction(s, leftIndex, rightIndex)
                newString += reverse(s.substring(start, end)) + " ";
            } else {
                while (end < s.length() && s.charAt(end) == ' ') {
                    end++;
                }
                if (end < s.length() && s.charAt(end) != ' ') {
                    start = end;
                }
            }
        }
        return newString.trim();
    }

    private String reverse(String s) {
        int left = 0, right = s.length() - 1;
        char[] charArray = s.toCharArray();
        while (left < right) {
            swap(charArray, left++, right--);
        }
        return String.valueOf(charArray);
    }

    private void swap(char[] array, int left, int right) {
        char c = array[left];
        array[left] = array[right];
        array[right] = c;
    }
}

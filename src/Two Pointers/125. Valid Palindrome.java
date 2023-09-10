/*
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
 

Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
*/

// In summary:
// the question ask us to clean up the String dynamically then do the comparsion.
// When two characters need get compared, we need to use Character.toLowerCase() API to convert the upper case letters to lower cases.

// Methodology:
// 1. Two pointers.
// 2. Use the correct Character APIs: Character.toLowerCase()
//                                    Character.isLetterOrDigit()
// if the current character is space, or other symbols we should just jump it. 
// And it is the same with leftIndex and rightIndex, we would need to convert(make sure) two compared characters are lower-case letters.
// if it is different, we can return false in advance.

class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (left < right && Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
                return false;
            }
        }
        return true;
    }
}

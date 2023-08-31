/*
Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
*/

class Solution {
    public boolean isMatch(String text, String pattern) {
        // Create a dynamic programming table to store subproblem results
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];

        // Initialize the base case: empty text and pattern match
        dp[text.length()][pattern.length()] = true;

        // Iterate through the dynamic programming table in reverse order
        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                // Check if the current characters match or pattern character is '.'
                boolean first_match = (i < text.length() &&
                                       (pattern.charAt(j) == text.charAt(i) ||
                                        pattern.charAt(j) == '.'));

                // Check for the presence of '*' in the pattern
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    // Two cases: 1. Skip the current pattern character and '*' (j+2)
                    //            2. Match the current character and continue to next text character (i+1)
                    dp[i][j] = dp[i][j + 2] || (first_match && dp[i + 1][j]);
                } else {
                    // No '*' present, match the current characters and move to the next characters
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0]; // Return the result of matching the entire

    }
}
/*
Explanation:

We create a dynamic programming table dp with dimensions (text.length() + 1) x (pattern.length() + 1) to store subproblem results.
The base case is when both the text and pattern are empty, which means they match, so we set dp[text.length()][pattern.length()] to true.
We iterate through the dp table in reverse order, considering each possible subproblem.
For each subproblem, we check if the current characters match (first_match), and then we consider two cases:
If the next character in the pattern is '', we have two options: skip the current pattern character and '' (j+2), or match the current character and continue to the next text character (i+1).
If there is no '*', we simply match the current characters and move to the next characters.
Finally, the value in dp[0][0] indicates whether the entire text and pattern match.
This dynamic programming approach has a time complexity of O(m * n), where m is the length of the text and n is the length of the pattern, making it an efficient solution for regular expression matching.

In the context of the regular expression matching problem, when we say "For each subproblem," we mean that we are considering different combinations of characters from the text and pattern strings to determine if they match according to the regular expression rules.

Now, let's focus on the specific part you mentioned:

"For each subproblem, we check if the current characters match (first_match), and then we consider two cases:"

In each subproblem, we are looking at the current characters of the text and pattern strings that we are comparing. The variable first_match is a boolean that indicates whether the current characters match or not. It's determined by checking if the current character in the pattern (pattern.charAt(j)) is the same as the current character in the text (text.charAt(i)) or if the pattern character is a period '.' (which matches any character).

Based on whether the current characters match (first_match), we have two cases to consider:

Case 1: The next character in the pattern is '*' (indicated by pattern.charAt(j + 1) == '*'):
This means that the current pattern character (pattern.charAt(j)) is followed by a '*', which represents zero or more occurrences of the preceding character. In this case, we have two options:

Option A: Skip the current pattern character and the following '*' (move to j+2 in the pattern) and directly move to the subproblem with the remaining pattern.
Option B: Match the current character in the text with the current pattern character, and then continue to the next character in the text (i+1) while keeping the same pattern character. Essentially, this is equivalent to considering one more occurrence of the current character in the text.
Case 2: The next character in the pattern is not '*':
This means that the current pattern character does not have a '*' following it. In this case, we simply match the current characters in both the text and the pattern, and then move to the next characters in both the text (i+1) and the pattern (j+1).

The key idea here is that the '*' in the pattern allows us to consider different possibilities: we can either skip characters in the pattern or match more occurrences of characters in the text. By considering these two cases based on the presence of '*', we can systematically explore different combinations of characters to determine if the given text matches the pattern according to the regular expression rules.

*/

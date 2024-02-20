/*
Leetcode 267: Palindrome Permutation II

Given a string s, return all the palindromic permutations (without duplicates) of it.
You may return the answer in any order. If s has no palindromic permutation, return an empty list.
Example 1:
Input: s = "aabb"
Output: ["abba","baab"]
Example 2:
Input: s = "abc"
Output: []

Constraints:
1 <= s.length <= 16
s consists of only lowercase English letters.
*/

// Use DFS, combine with the finding permuation logic
// determine the base case, when totalUsed == curr.length;
// and the recursive rule, it depends on the actual value of the count

class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<>();

        int[] map = new int[26];
        // Count frequences
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        
        // Check how many single chars we haave
        int oneCount = 0;
        for (int val : map) if (val % 2 == 1) oneCount++;

        // If even there should not be a singe oneCount
        if (s.length() % 2 == 0 && oneCount > 0) {
            return result;
        }

        // If uneven we can only have one singular count
        if (s.length() % 2 != 0 && oneCount > 1) {
            return result;
        }
        
        // Call DFS
        dfs(result, map, 0, new char[s.length()], 0, s.length()-1);

        // return result
        return result;

    }

    public void dfs(List<String> result, int[] used, int totalUsed, char[] curr, int i, int j) {
        // If we used up all of our characters
        // That is our result
        if (totalUsed == curr.length) {
            result.add(new String(curr));
            return;
        }

        // Loop through all of our keys
        for (char c = 'a'; c <= 'z'; c++) {
            // If the current key has >= 2 chars.
            // It's eligible to be placed in the left and right positions of curr
            int count = used[c - 'a'];
            // no matter the count is odd or even, as long the repeated times larger or equal to 2, we should
            // place them accordingly.
            if (count >= 2) {
                // Places characters respectively
                curr[i] = c;
                curr[j] = c;

                // Mark values as used
                used[c - 'a'] = count - 2;
                // Recurse
                dfs(result, used, totalUsed + 2, curr, i + 1, j - 1);
                // unmark values as used
                used[c - 'a'] = count;
            } else if(count == 1) {
                // If the used key == 1
                // We know for a fact the only place we can use this character is in the middle.
                if (i == j) {
                    // Set the value in the middle
                    curr[i] = c;
                    // Mark as used
                    used[c - 'a']--;
                    // Recurse
                    dfs(result, used, totalUsed + 1, curr, i + 1, j - 1);
                    // Unmark and reset count
                    used[c - 'a'] = count;
                }
            }
        }
    }
}

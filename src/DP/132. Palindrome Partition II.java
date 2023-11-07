/*
132. Palindrome Partition II.java
https://leetcode.com/problems/palindrome-partitioning-ii/

Given a string s, partition s such that every 
substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1
 

Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters only.
*/

class Solution {
    // Methodology
    /*
    Algorithm:
    Step1: Bottom-up Dynamic Programming follows an iterative approach to solve the problem. We have to start by finding the minimum possible
    cuts in the smallest substring and move towards a larger substring.
    This can be implemented using a nested loop. The outer loop sets the upper bound for the substring index with variable end. 
    The inner loop takes each substring between start and end and calculates the minimum number of cuts for substring from index 0 to end.

    Step2: Build a one-dimensional array cutsDp to store the results of subproblems. cutsDp[i] stores the minimum number of cuts for a substring ending at index i.

    Step3: Calculating the minimum number of cuts is similar to the Memoization approach.

    Initially, the minimumCut will be equal to the maximum possible cuts for a substring. So for a substring ending at index end,
    the minimumCut would be equal to the value of index end.

    The minimum cut for s.substring(start, end) can be calculated as,

    minimum(minimumCut, Minimum cuts for substring s(start, end))
    Minimum cuts for substring s(start, end) = 1 + Minimum cuts for substring s(0, start - 1)
    Minimum cuts for substring s.substring(0, start - 1) is equivalent to finding the result for substring ending at index start - 1 which can be given by cutsDp[start - 1]. 
    So, we can say that,

    Minimum cuts for s.substring(start, end) = 1 + cutsDp[start - 1]
    In the end, we will store the results of the current calculation at cutsDp[end] as every chosen substring ends at index end.

    Step4: We are using a similar iterative approach to check if a substring is a palindrome or not. We will build the palindromeDp beforehand. 
    While finding the minimum cuts will refer to the stored values in palindromeDp and proceed only if the current substring is a palindrome. 
    Refer to Approach 2 in Palindrome Partitioning Solution.

    Step5: Return the minimum number of cuts for the original substring starting at index 0 and ending at n - 1 which will be given by cutsDp[n - 1].
    */

    private int[] cutsDp;
    private boolean[][] palindromeDp;

    public int minCut(String s) {
        cutsDp = new int[s.length()];
        palindromeDp = new boolean[s.length()][s.length()];

        buildPalindromeDp(s, s.length());

        for (int end = 0; end < s.length(); end++) {
            int minimumCut = end;
            for (int start = 0; start <= end; start++) {
                if (palindromeDp[start][end]) {
                    minimumCut = start == 0 ? 0 : Math.min(minimumCut, cutsDp[start - 1] + 1);
                }
            }
            cutsDp[end] = minimumCut;
        }
        return cutsDp[s.length() - 1];
    }

    private void buildPalindromeDp(String s, int n) {
        for (int end = 0; end < s.length(); end++) {
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || palindromeDp[start + 1][end - 1])) {
                    palindromeDp[start][end] = true;
                }
            }
        }
    }
}

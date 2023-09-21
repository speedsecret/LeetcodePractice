/*
93. Restore IP Address.java
https://leetcode.com/problems/restore-ip-addresses/

A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 
Constraints:

1 <= s.length <= 20
s consists of digits only.
*/

// Methodology
// DFS with two levels of terminal conditions
// use a helper function to validate if the segmentStr is valid.
// then dfs calling the subproblem

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(res, s, "", 0, 0);
        return res;
    }

    private void dfs(List<String> res, String s, String current, int startIndex, int segment) {
        // base cases:
        if (segment == 4) {
            if (startIndex == s.length()) {
                res.add(current);
            }
            return;
        }

        // try different segment lengths: 1, 2, or 3 characters.
        for (int i = 1; i <= 3; i++) {
            if (startIndex + i <= s.length()) {
                // i is used for determining the length of possible new current string
                String segmentStr = s.substring(startIndex, startIndex + i);

                // Check if the segment is valid (within [0, 255] and not having leading zero except for "0")
                if (isValid(segmentStr)) {
                    String newSegment = current.isEmpty() ? segmentStr : current + "." + segmentStr;
                    // recursively call the dfs function with the updated parameters
                    dfs(res, s, newSegment, startIndex + i, segment + 1);
                }
            }
        }
    }

    private boolean isValid(String segmentStr) {
        boolean valid1 = (segmentStr.length() > 1 && segmentStr.charAt(0) != '0') || segmentStr.length() == 1;
        boolean valid2 = Integer.parseInt(segmentStr) <= 255;
        return valid1 && valid2;
    }
}

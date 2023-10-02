/*
Leetcode 139 Word Break.
https://leetcode.com/problems/word-break/

Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
*/

class TrieNode {
    boolean isWord;
    Map<Character, TrieNode> children;
    TrieNode() {
        this.children = new HashMap<>();
    }
}

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // build up a TrieNode by using wordDict
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                // check if the hashMap contains char
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TrieNode());
                }
                cur = cur.children.get(c);
            }
            cur.isWord = true;
        }

        // then build up a dp array
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < dp.length; i++) {
            if (i == 0 || dp[i - 1]) {
                TrieNode cur = root;
                for (int j = i; j < dp.length; j++) {
                    char c = s.charAt(j);
                    if (!cur.children.containsKey(c)) {
                        break;
                    }
                    // then move to the next element
                    cur = cur.children.get(c);
                    if (cur.isWord) {
                        dp[j] = true;
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }
}

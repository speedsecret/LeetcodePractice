/*
126. Word Ladder II.java
https://leetcode.com/problems/word-ladder-ii/

A transformation sequence from word beginWord to word endWord using a dictionary wordList 
is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest 
transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. 
Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 5
endWord.length == beginWord.length
1 <= wordList.length <= 500
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
The sum of all shortest transformation sequences does not exceed 105.

*/

// Methodology
// BFS + DFS review it by 09.24.2023

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return new ArrayList<>();
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return new ArrayList<>();

        List<List<String>> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        HashMap<String, List<String>> map = new HashMap<>(); //{end -> start}
        boolean find = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            HashSet<String> set = new HashSet<>();
            while (size-- > 0) {
                String cur = queue.poll();
                if (cur.equals(endWord)) {
                    find = true;
                    continue;
                }

                HashSet<String> nextStrings = findNext(cur, dict);
                for (String next : nextStrings) {
                    if (set.add(next)) {
                        queue.offer(next);
                        map.putIfAbsent(next, new ArrayList<>());
                    }
                    map.get(next).add(cur);
                }
            }
            dict.removeAll(set);
            if (find) {
                List<String> temp = new LinkedList<>();
                temp.add(endWord);
                dfs(endWord, beginWord, map, temp, result);
                // result.add(temp);
                break;
            }
        }
        return result;
    }

    private HashSet<String> findNext(String word, HashSet<String> dict) {
        HashSet<String> res = new HashSet<>();
        char[] chs = word.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            char temp = chs[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == temp) continue;
                chs[i] = c;
                String newString = String.valueOf(chs);
                if (dict.contains(newString)) res.add(newString);
            }
            chs[i] = temp;
        }
        return res;
    }

    private void dfs(String start, String end, HashMap<String, List<String>> map, List<String> res, List<List<String>> ans) {
        if (start.equals(end)) {
            ans.add(new ArrayList<>(res));
            return;
        }

        for (String next : map.get(start)) {
            res.add(0, next);
            dfs(next, end, map, res, ans);
            res.remove(0);
        }
    }
}

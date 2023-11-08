/*
269. Alien-dictionary.java
https://leetcode.com/problems/alien-dictionary/

There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.
You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings in words are 
sorted lexicographically by the rules of this new language.
If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of letters, return "".

Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. 
If there are multiple solutions, return any of them.

Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.
*/

// Similar to course schedule I, but using a different type of dataType within the adjList
// Also, it is required to use the StringBuilder at the middle end part to construct a final String
// Adding the validation check is necessary.
// For words[i] only consists of lowercase english letter, int[] Degree = new int[26] would be sufficient.

class Solution {
    public String alienOrder(String[] words) {
        // Step1: Initilize the adjMap and built up the adjMap by traversing the String[] words
        // Step2: Use int[] inDegree to calculate how many character pointed to the current character
        // Step3: BFS queue: Deque<Character> queue

        // Step1: 
        Map<Character, List<Character>> adjMap = new HashMap<>();
        int[] inDegree = new int[26];

        for (String str : words) {
            for (char c : str.toCharArray()) {
                if (!adjMap.containsKey(c)) {
                    adjMap.put(c, new ArrayList<>());
                }
            }
        }

        for (int i = 1; i < words.length; i++) {
            String pre = words[i - 1], cur = words[i];
            // case 1:
            if (pre.length() > cur.length() && pre.startsWith(cur)) {
                return "";
            }
            // case 2:
            for (int j = 0; j < Math.min(pre.length(), cur.length()); j++) {
                char preChar = pre.charAt(j), curChar = cur.charAt(j);
                if (preChar != curChar) {
                    adjMap.get(preChar).add(curChar);
                    inDegree[curChar - 'a']++;
                    break;
                }
            }
        }

        // Step2: 
        Deque<Character> queue = new ArrayDeque<>();
        for (char ch : adjMap.keySet()) {
            if (inDegree[ch - 'a'] == 0) {
                queue.offerLast(ch);
            }
        }
        // Step3: 
        // Initilize a brand new StringBuilder
        // And process the queue
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char preChar = queue.pollFirst();
            // when we poll a character out of the queue
            // we should just append it to the end of stringbuilder
            sb.append(preChar);
            for (char neiChar : adjMap.get(preChar)) {
                inDegree[neiChar - 'a']--;
                if (inDegree[neiChar - 'a'] == 0) {
                    queue.offerLast(neiChar);
                }
            }
        }
        // Step4: focus on output.
        // validate if the StringBuilder sb is valid.
        if (sb.length() < adjMap.size()) {
            return "";
        }
        return sb.toString();
    }
}


/*
// Method2: DFS solution
class Solution {
    
    private Map<Character, List<Character>> reverseAdjList = new HashMap<>();
    private Map<Character, Boolean> seen = new HashMap<>();
    private StringBuilder output = new StringBuilder();
    
    public String alienOrder(String[] words) {
        
        // Step 0: Put all unique letters into reverseAdjList as keys.
        for (String word : words) {
            for (char c : word.toCharArray()) {
                reverseAdjList.putIfAbsent(c, new ArrayList<>());
            }
        }
        
        // Step 1: Find all edges and add reverse edges to reverseAdjList.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            // Check that word2 is not a prefix of word1.
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    reverseAdjList.get(word2.charAt(j)).add(word1.charAt(j));
                    break;
                }
            }
        }
        
        // Step 2: DFS to build up the output list.
        for (Character c : reverseAdjList.keySet()) {
            if (!dfs(c)) return "";
        }
        
        return output.toString();
    }
    
    // Return true iff no cycles detected.
    private boolean dfs(Character c) {
        if (seen.containsKey(c)) {
            return seen.get(c); // If this node was grey (false), a cycle was detected.
        }
        seen.put(c, false);
        for (Character next : reverseAdjList.get(c)) {
            boolean result = dfs(next);
            if (!result) return false;
        }
        seen.put(c, true);
        output.append(c);
        return true;
    }    
}
*/

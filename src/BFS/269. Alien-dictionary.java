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


class Solution {
    public String alienOrder(String[] words) {
        // Methodology:
        // Step1:
        // Extract the relationships
        // Step2:
        // Representing the relationships
        // Step3:
        // Build up one possible alienOrder
        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer> counts = new HashMap<>();

        // Step1:
        for (String word: words) {
            for (char c : word.toCharArray()) {
                counts.putIfAbsent(c, 0);
                adjList.putIfAbsent(c, new ArrayList<>());
            }
        }

        // Step2:
        // find all edges:
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i], word2 = words[i + 1];
            // prefix violation check
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            // find the first non-match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char pre = word1.charAt(j), cur = word2.charAt(j);
                if (pre != cur) {
                    adjList.get(pre).add(cur);
                    counts.put(cur, counts.get(cur) + 1);
                    break;
                }
                // if there is a match, we can just advance our character by 1 step
            }
        }

        // Step3: Build a valid output
        StringBuilder sb = new StringBuilder();
        Deque<Character> queue = new ArrayDeque<>();

        for (char c : counts.keySet()) {
            if (counts.get(c).equals(0)) {
                queue.offerLast(c);
            }
        }

        // BFS process the queue
        while (!queue.isEmpty()) {
            char c = queue.pollFirst();
            sb.append(c);

            for (char nei : adjList.get(c)) {
                counts.put(nei, counts.get(nei) - 1);
                // whne the current char's inDegree is equals to 0, we put it to queue
                if (counts.get(nei).equals(0)) {
                    queue.offerLast(nei);
                }
            }
        }

        // final check to see if we found a match
        if (sb.length() < counts.size()) {
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

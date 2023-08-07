/*
Leetcode: 245
link: https://leetcode.com/problems/shortest-word-distance-iii/description/
Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, 
return the shortest distance between the occurrence of these two words in the list.

Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.

Example 1:
Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
Output: 1

Example 2:
Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
Output: 3
*/

class Solution {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        // Use Pair<Integer, Integer> Class
        // to record which word belongs to which list
        // created and merge the two lists first
        // for loop the whole list and then make sure both key and value are the same
        List<Pair<Integer, Integer>> indices = new ArrayList<>();
        for (int i = 0; i < wordsDict.length; i++) {
            String str = wordsDict[i];
            if (str.equals(word1)) {
                indices.add(new Pair(i, 0));
            }
            if (str.equals(word2)) {
                indices.add(new Pair(i, 1));
            }
        }

        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < indices.size() - 1; i++) {
            if (!indices.get(i).getKey().equals(indices.get(i + 1).getKey()) &&
            !indices.get(i).getValue().equals(indices.get(i + 1).getValue())) {
                distance = Math.min(distance, indices.get(i + 1).getKey() - indices.get(i).getKey());
            }
        }
        return distance;
    }
}

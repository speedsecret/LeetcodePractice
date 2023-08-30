package BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // use BFS(a queue) with 2 hashSets to resolve this problem
        // one hashSet is use to store elements in wordList, to avoid duplication.
        // create a new hashSet, we would like to check if the new elements already in the hashSet
        // once we poll the next level elements from queue, the steps + 1
        // we would start from beginWord, replace every character from 'a' to 'z'
        // once we met the endWord, return the steps right away.
        // if we exhausted all options and the current queue is empty, return 0
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }
        // prep for the BFS
        int steps = 0;
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.addFirst(beginWord);
        visited.add(beginWord);
        // start the BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curWord = queue.pollLast();
                // check if met the endWord
                if (curWord.equals(endWord)) {
                    return steps + 1;
                }
                char[] curWordArr = curWord.toCharArray();
                for (int j = 0; j < curWordArr.length; j++) {
                    char c = curWordArr[j];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        curWordArr[j] = ch;
                        String newString = new String(curWordArr);
                        if (!visited.contains(newString) && set.contains(newString)) {
                            queue.addFirst(newString);
                            visited.add(newString);
                        }
                    }
                    curWordArr[j] = c;
                }
            }
            steps++;
        }
        return 0;
    }

public static void main(String[] args) {
    String startWord = "cclull";
    String endWord = "kfxhjj";
    List<String> wordList = Arrays.asList(new String[]{"izvcnt","hyjwgb","luzvff","illbjg","iehfzp","jjofku",
            "tmkrma","yrrvpq","ntijqd","lbruez","jtoimy","fgktfq","tmtibx","vujkns","fjdeds","cicrlt","lkupnp",
            "kbquoc","vaqnwd","sybbkk","voifyl","zycdrm","yxfkyg","tusvnf","bfffsq","oxqtaq","slynkf","eiamsy",
            "cxmvkt","xsmdmi","jckoeq","zxpjjf","ndjdtk","xvqomc","hmqrlq","nwmoyw","swomhn","tqrljp","ruwdbe",
            "hgliyu","cclull","fpeltr","kivdkq","puuqfh","kdjnrw","ceuvpm","axnoct","kfxhjj","mhvqjv","kmhlgy",
            "avgxno","jiqrjj","rkiyyt","pjvjuf","twlwjy","mdjlug","nqmteo","mbqith","unfgkn","snvcok","ytjezq","jzbgdm"});
    System.out.println("the length of shortest word ladder is: " + ladderLength(startWord, endWord, wordList));
}
}

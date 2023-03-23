package BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // use DFS to compare each possible char as the next String in the wordLadder chain
        Set<String> set = new HashSet<>(wordList);
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char[] curr = queue.poll().toCharArray();
                for (int j = 0; j < curr.length; j++) {
                    char tmp = curr[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        curr[j] = c;
                        String next = new String(curr);
                        // then what
                        // check if it belongs to wordList
                        if (set.contains(next)) {
                            if (next.equals(endWord)) {
                                return count + 1;
                            }
                            queue.add(next);
                            set.remove(next);
                        }
                    }
                    curr[j] = tmp;
                }
            }
            count++;
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

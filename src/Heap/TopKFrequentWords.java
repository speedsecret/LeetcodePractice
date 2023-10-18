package Heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
692. Top K frequent Words.java
Given a composition with different kinds of words, return a list of the top K most frequent words in the composition.

Assumptions

the composition is not null and is not guaranteed to be sorted
K >= 1 and K could be larger than the number of distinct words in the composition, in this case, just return all the distinct words
Return

a list of words ordered from most frequent one to least frequent one (the list could be of size K or smaller than K)
Examples

we should also assume the length of each string in the String array is a single length string

Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2 frequent words are [“b”, “c”]
Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 4 frequent words are [“b”, “c”, "a", "d"]
Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 5 frequent words are [“b”, “c”, "a", "d"]
 */
public class TopKFrequentWords {
    public static String[] topKFrequent(String[] combo, int k) {
        // use a minHeap
        // T: O(nlogk + n - k)
        // S: O(k)
        Map<Character, Integer> map = buildMap(combo);
        // use a minHeap
        PriorityQueue<Map.Entry<Character, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return 0;
                }
                return o1.getValue() < o2.getValue() ? -1 : 1;
            }
        });
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.add(entry);
            } else {
                // make sure the minHeap store the maximum k frequent elements
                if (minHeap.peek().getValue() < entry.getValue()) {
                    minHeap.poll();
                    minHeap.add(entry);
                }
            }
        }
        int size = minHeap.size();
        String[] res = new String[size];
        for (int i = size - 1; i >= 0; i--) {
            res[i] = String.valueOf(minHeap.poll().getKey());
        }
        return res;
    }

    /*
    class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> cntString = new HashMap<>();
            for (String str : words) {
                cntString.put(str, cntString.getOrDefault(str, 0) + 1);
            }
            // PriorityQueue by using Lambda expression
            // if their frequencies are the same, use the reverse order of lexicographical order as the standard. so we can expect to get a sorted lexicographical order result.
            PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> cntString.get(a).equals(cntString.get(b)) ? b.compareTo(a) : Integer.compare(cntString.get(a), cntString.get(b)));
            for (String str : cntString.keySet()) {
                minHeap.add(str);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
    
            // Prep for the output
            List<String> list = new ArrayList<>();
            while (!minHeap.isEmpty()) {
                list.add(minHeap.poll());
            }
            Collections.reverse(list);
            return list;
        }
        // [TODO]Method2: use BucketSort + Trie
    }
}
    */

    private static Map<Character, Integer> buildMap(String[] combo) {
        Map<Character, Integer> map = new HashMap<>();
        for (String str: combo) {
            char c = str.charAt(0);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}

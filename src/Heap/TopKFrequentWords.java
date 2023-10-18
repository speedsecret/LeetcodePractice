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
        // Method1: use MaxHeap
        Map<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>(
            (a, b) -> cnt.get(a).equals(cnt.get(b)) ? b.compareTo(a) : cnt.get(a) - cnt.get(b)
        );
        for (String str : cnt.keySet()) {
            pq.add(str);
            if (pq.size() > k) {
                pq.poll(); 
            }
        }

        // prep for the output
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        Collections.reverse(res);
        return res;
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

package HashMap;

import java.util.HashMap;
import java.util.Map;

public class NonFixedSizeSlidingWindow_I {
    public static String minWindow(String s, String t) {
        if (s == null || t == null || t.length() > s.length()) {
            return "";
        }
        Map<Character, Integer> map = buildMap(t);
        int slow = 0;
        int start = -1;
        int match = 0;
        int shortest = Integer.MAX_VALUE;
        for (int fast = 0; fast < s.length(); fast++) {
            // Step1: add fast
            char cur = s.charAt(fast);
            Integer count = map.get(cur);
            if (count != null) {
                if (count == 1) {
                    match++;
                }
                map.put(cur, count - 1);
            }
            // step2: move slow in a while loop
            // we need to move this as long as the size equals to map
            // attention pls: this is a situation that we use a while loop
            // instead of an if and else loop
            while (match == map.size()) {
                if (shortest > fast - slow + 1) {
                    shortest = fast - slow + 1;
                    start = slow;
                }
                // use the element which tied to index slow
                cur = s.charAt(slow);
                count = map.get(cur);
                if (count != null) {
                    if (count == 0) {
                        match--;
                    }
                    map.put(cur, count + 1);
                }
                slow++;
            }
        }
        return shortest == Integer.MAX_VALUE ? "" : s.substring(start, start + shortest);
    }

    /*
    Find the longest substring that contains at most k distinct elements
    Example1:
    Input: s = 'eceba' k = 2;
    output: 3;
       s
        f
    eceba
    int start = 0;
    int shortest = Integer.MAX_VALUE;
    int slow = 0;
    int fast = 0;
    Use a hashMap to store the current Longest string
     */
    private static int getLongestSubStringLengthWithKDistinctEle(String s, int k) {
        int slow = 0;
        int result = -1;
        Map<Character, Integer> map = new HashMap<>();
        for (int fast = 0; fast < s.length(); fast++) {
            // step1: add fast
            char cur = s.charAt(fast);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            // step2: remove the slow
            // while loop
            // so as long as the size of map is larger than k
            // we should execute the removal process
            while (map.size() > k) {
                cur = s.charAt(slow);
                Integer count = map.get(cur);
                if (count == 1) {
                    map.remove(cur);
                } else {
                    map.put(cur, count - 1);
                }
                slow++;
            }
            // step3: update the global
            result = Math.max(result, fast - slow + 1);
        }
        return result;
    }


    private static Map<Character, Integer> buildMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c: str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        String s = "eceab";
        int k = 2;
        System.out.printf("Currently, the length of the longest substring is: "
                + getLongestSubStringLengthWithKDistinctEle(s, k));
    }
}

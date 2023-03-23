package HashMap;
/*
Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.

Assumptions

sh is not null or empty.
lo is not null.
Examples

l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba").
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllAnagrams {
    // l represent the normal condition longer one, be cautious about the edge case the length of l is smaller than the shorter one
    // s represent the normal condition shorter one.
    public static List<Integer> getAllAnagramsIndices(String shortString, String longString) {
        List<Integer> result = new ArrayList<>();
        // handle the edge case
        if (shortString == null || longString == null || shortString.length() == 0 || longString.length() == 0) {
            return result;
        }
        if (shortString.length() > longString.length()) {
            return result;
        }
        /*
        To use hashMap to store the elements
        abc
        0
        asdflacb
           i
         */
        Map<Character, Integer> shortStringMap = getAllCharacters(shortString);
        int match = 0;
        int uniqueSizeOfShortString = shortStringMap.size();
        // Gonna need to consider the chance of retrieving a non int value
        for (int i = 0; i < longString.length(); i++) {
            //Maintaining a sliding window
            char curChar = longString.charAt(i);
            Integer count = shortStringMap.get(curChar);
            if (count != null) {
                if (count == 1) {
                    match++;
                }
                shortStringMap.put(curChar, count - 1);
            }
            if (i >= shortString.length()) {
                curChar = longString.charAt(i - shortString.length());
                count = shortStringMap.get(curChar);
                if (count != null) {
                    if (count == 0) {
                        match--;
                    }
                    shortStringMap.put(curChar, count + 1);
                }
            }
            if (match == uniqueSizeOfShortString) {
                result.add(i - shortString.length() + 1);
            }
        }
        return result;
    }

    private static Map<Character, Integer> getAllCharacters(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (!map.containsKey(curChar)) {
                map.put(curChar, 1);
            } else {
                map.put(curChar, map.get(curChar) + 1);
            }
        }
        return map;
    }

    public static List<Integer> allAnagrams(String sh, String lo) {
        // Write your solution here
        List<Integer> res = new ArrayList<>();
        if (lo.length() == 0) {
            return res;
        }
        if (sh.length() > lo.length()) {
            return res;
        }
        // use a hashMap to store the sh
        // Map<Character, Integer>
        // use an int variable to store the number of each character in sh.

    /*
       aab
       ababac
         f
    */
        Map<Character, Integer> map = getShortStringMap(sh);
        int match = 0;
        for (int fast = 0; fast < lo.length(); fast++) {
            char curChar = lo.charAt(fast);
            Integer count = map.get(curChar);
            if (count != null) {
                if (count == 1) {
                    match++;
                }
                map.put(curChar, count - 1);
            }
            if (fast >= sh.length()) {
                curChar = lo.charAt(fast - sh.length());
                count = map.get(curChar);
                if (count != null) {
                    if (count == 0) {
                        match--;
                    }
                    map.put(curChar, count + 1);
                }
            }
            if (fast >= sh.length() - 1) {
                if (match == map.size()) {
                    res.add(fast - sh.length() + 1);
                }
            }
        }
        return res;
    }

    private static Map<Character, Integer> getShortStringMap(String sh) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch: sh.toCharArray()) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        String s = "ab";
        String l = "bcabcbbbbac";
        String l2 = "abcbac";
        System.out.printf("What is the current indices list: " + allAnagrams(s, l2));
    }
}

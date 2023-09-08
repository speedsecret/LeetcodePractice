package BinarySearch;

/*
702. SearchInUnknownSizedList
https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/

Given an integer dictionary A of unknown size, where the numbers in the dictionary are sorted in ascending order,
determine if a given target integer T is in the dictionary. Return the index of T in A, return -1 if T is not in A.

Assumptions

dictionary A is not null
dictionary.get(i) will return null(Java)/INT_MIN(C++)/None(Python) if index i is out of bounds
Examples

A = {1, 2, 5, 9, ......}, T = 5, return 2
A = {1, 2, 5, 9, 12, ......}, T = 7, return -1
 */

/*
Let's assume this dictionary is a linkedlist
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchInUnknownSizedList {
    public static int searchInUnknownSizedList(List<Integer> dict, int target) {
        // corner case check
        if (dict.isEmpty() || dict.size() <= 0) {
            return -1;
        }
        // started making boundary
        int left = 0;
        int right = 1;
        while (right < dict.size() && dict.get(right) != null && dict.get(right) < target) {
            left = right;
            right = 2 * right;
        }
        return classicalBinarySearch(dict, target, left, right);
    }

    private static int classicalBinarySearch(List<Integer> dict, int target, int left, int right) {
        // what worth double-checking is the query target should still do a null check
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (dict.get(mid) != null && dict.get(mid) < target) {
                left = mid + 1;
            } else if (dict.get(mid) != null && dict.get(mid) > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> dict = Arrays.asList(1, 2, 5, 6, 10, 12, 19, 20, 24, 28, 29, 30, 33, 35);
        int target = 31;
        int target2 = 32;
        int target3 = 33;
        System.out.printf("The ideal target index is: " + searchInUnknownSizedList(dict, target) + "\n");
        System.out.printf("The ideal target index is: " + searchInUnknownSizedList(dict, target2) + "\n");
        System.out.printf("The ideal target index is: " + searchInUnknownSizedList(dict, target3) + "\n");
    }
}

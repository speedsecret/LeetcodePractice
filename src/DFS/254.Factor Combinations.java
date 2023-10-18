/*
Leetcode 254: Factor Combinations
https://leetcode.com/problems/factor-combinations/

Numbers can be regarded as the product of their factors.

For example, 8 = 2 x 2 x 2 = 2 x 4.
Given an integer n, return all possible combinations of its factors.
You may return the answer in any order.

Note that the factors should be in the range [2, n - 1].

Example 1:
Input: n = 1
Output: []

Example 2:
Input: n = 12
Output: [[2,6],[3,4],[2,2,3]]

Example 3:
Input: n = 37
Output: []
*/

class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new LinkedList<>();
        // Initilized a list which only contains the initial factor.
        getAllPossibleFactors(res, new LinkedList<>(Arrays.asList(n)));
        return res;
    }

    private void getAllPossibleFactors(List<List<Integer>> res, LinkedList<Integer> list) {
        // base case
        // as long as the list has a size over 1, which means
        // we found a valid factor combinations
        if (list.size() > 1) {
            res.add(new LinkedList<>(list));
        }
        // the lastFactor here will become the new elements that can be multiplied together.
        int lastFactor = list.removeLast();
        // the below for loop would help to avoid duplicates
        // the current factor i can from 2(if the list is empty) up to the square root of the last element.
        // lastFactor / i represent the current possible factor, but the lastFact % i represent whether there is a remainder.
        for (int i = list.isEmpty() ? 2 : list.peekLast(); i <= lastFactor / i; i++) {
            if (lastFactor % i == 0) {
                list.add(i);
                list.add(lastFactor / i);
                getAllPossibleFactors(res, list);
                list.removeLast();
                list.removeLast();
            }
        }
        // add lastFactor into the list, backtracking so to consider all possible combination are considered.
        list.add(lastFactor);
    }
}


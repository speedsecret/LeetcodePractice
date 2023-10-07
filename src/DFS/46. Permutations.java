package DFS;

import java.util.ArrayList;
import java.util.List;

import static Utils.Swap.swapChar;
/*
46. Permutations.java
https://leetcode.com/problems/permutations/

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:
Input: nums = [1]
Output: [[1]]
*/

/*
Adding nums[index]: In the for loop, we add nums[index] to the list because we are building permutations of the input array nums.
The index parameter represents the current position we are trying to fill in the permutation. 
By adding nums[index] to the list, we ensure that at each step of the recursion, we are considering a different element from the input array nums. 
This is essential for generating distinct permutations.

index + 1 in the Recursive Call: The index parameter in the permutation method represents the position in the nums array that
we are currently trying to fill with an element. When we make the recursive call, we want to move on to the next position in the permutation, 
which is why we pass index + 1. If we passed i + 1 instead, it would not correctly represent the position we are trying to fill in the permutation. 
The variable i is used for iterating through the array in the for loop, and it doesn't necessarily correspond to the current position in the permutation we are building.

These choices ensure that we correctly generate all possible permutations of the input array nums.
 */

public class AllPermutations {
    public static List<String> allPermutations(String input) {
        List<String> res = new ArrayList<>();
        if (input == null) {
            return res;
        }
        char[] array = input.toCharArray();
        helper(res, array, 0);
        return res;
    }

    private static void helper(List<String> res, char[] array, int index) {
        // base case
        if (index == array.length) {
            res.add(new String(array));
            return;
        }
        // recursive rule
        // started from each index so it will generate non-duplicated permutation result
        for (int i = index; i < array.length; i++) {
            swapChar(array, i, index);
            helper(res, array, index + 1);
            swapChar(array, i, index);
        }
    }

    public static void main(String[] args) {
        String input = "abc";
        List<String> result = allPermutations(input);
        while (result.size() > 0) {
            System.out.println(result.get(result.size() - 1) + " ");
            result.remove(result.size() - 1);
        }
    }
}

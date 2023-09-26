/*
78.AllSubsets.java
https://leetcode.com/problems/subsets/description/


Given an integer array nums of unique elements, return all possible 
subsets(the power set). The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
*/

public class AllSubsets {
    public static List<String> allSubsets(String str) {
        if (str == null) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] set = str.toCharArray();
        buildAllPossibleSubsets(result, sb, set, 0);
        return result;
    }

    private static void buildAllPossibleSubsets(List<String> result,
                                                StringBuilder sb, char[] set, int index) {
        // base case:
        if (index == set.length)  {
            result.add(sb.toString());
            return;
        }
        // not pick the character at currentIndex
        buildAllPossibleSubsets(result, sb, set, index + 1);
        // pick up the character at currentIndex
        buildAllPossibleSubsets(result, sb.append(set[index]), set, index + 1);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        String input = "abc";
        List<String> result = allSubsets(input);
        while (result.size() > 0) {
            System.out.println(result.get(result.size() - 1) + " ");
            result.remove(result.size() - 1);
        }
    }
}

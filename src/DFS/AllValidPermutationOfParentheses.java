package DFS;

import java.util.ArrayList;
import java.util.List;

public class AllValidPermutationOfParentheses {
    /*
    [10-30-2022] 考虑做一下吗？ 有答案
    https://app.laicode.io/app/problem/179
     */
    public static List<String> allValidPermutationOfParentheses(int n) {
        List<String> result = new ArrayList<>();
        char[] newChar = new char[2 * n];
        // helper function here is good for a DFS recursive call
        helper(result, newChar, n, n, 0);
        return result;
    }

    private static void helper(List<String> res, char[] curChar, int left, int right, int index) {
        // base case
        if (left == 0 && right == 0) {
            res.add(new String(curChar));
            return;
        }
        // recursive rule
        if (left > 0) {
            curChar[index] = '(';
            helper(res, curChar, left - 1, right, index + 1);
        }
        if (right > left) {
            curChar[index] = ')';
            helper(res, curChar, left, right - 1, index + 1);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        List<String> res = allValidPermutationOfParentheses(n);
        while (!res.isEmpty()) {
            System.out.printf("Current String is: " + res.remove(res.size() - 1) + '\n');
        }
    }
}

package DFS;

/*
Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.

Assumptions

l, m, n >= 0
l + m + n > 0
Examples

l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AllValidPermutationOfParenthesesII {
    /*
    You had to make sure you are using the correct the DataStructure, the name does not matter but the class you
    are utilizing matters.
     */
    private static final char[] curChar = {'(', ')', '<', '>', '{', '}'};
    public static List<String> getAllValidPermutationOfParentheseII(int l, int m, int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int[] remain = {l, l, m, m, n, n};
        Deque<Character> stack = new ArrayDeque<>();
        int targetLength = 2 * (l + m + n);
        DFS(res, sb, remain, stack, targetLength);
        return res;
    }

    private static void DFS(List<String> res, StringBuilder sb, int[] remain, Deque<Character> stack,
                            int targetLength) {
        // base case
        if (sb.length() == targetLength) {
            res.add(sb.toString());
            return;
        }
        // recursive rule
        for (int i = 0; i < remain.length; i++) {
            if (i % 2 == 0) {
                if (remain[i] > 0) {
                    sb.append(curChar[i]);
                    stack.offerFirst(curChar[i]);
                    remain[i]--;
                    DFS(res, sb, remain, stack, targetLength);
                    sb.deleteCharAt(sb.length() - 1);
                    stack.pollFirst();
                    remain[i]++;
                }
            } else {
                if (!stack.isEmpty() && stack.peekFirst() == curChar[i - 1]) {
                    sb.append(curChar[i]);
                    stack.pollFirst();
                    remain[i]--;
                    DFS(res, sb, remain, stack, targetLength);
                    sb.deleteCharAt(sb.length() - 1);
                    stack.offerFirst(curChar[i - 1]);
                    remain[i]++;
                }
            }
        }
    }

    private static void helperII(List<String> result, StringBuilder cur, int[] remain, Deque<Character> stack, int targetLen, int index){
        //base case
        if(index == targetLen){
            result.add(cur.toString());
            return;
        }
        //main logic
        for(int i = 0; i < remain.length; i++){
            //handle it when the number is odd;
            //which means the left bracket, we just put it into it.
            if(i % 2 == 0){
                if(remain[i] > 0){
                    cur.append(curChar[i]);
                    stack.offerFirst(curChar[i]);
                    remain[i]--;
                    helperII(result, cur, remain, stack, targetLen, index + 1);
                    cur.deleteCharAt(cur.length() - 1);
                    stack.pollFirst();
                    remain[i]++;
                }
            } else {
                if (!stack.isEmpty() && stack.peekFirst() == curChar[i - 1]){
                    cur.append(curChar[i]);
                    stack.pollFirst();
                    remain[i]--;
                    helperII(result, cur, remain, stack, targetLen,index + 1);
                    cur.deleteCharAt(cur.length() - 1);
                    stack.offerFirst(curChar[i - 1]);
                    remain[i]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int l = 1;
        int m = 0;
        int n = 1;
        List<String> res = getAllValidPermutationOfParentheseII(l, m, n);
        System.out.printf("Current list of all permutation string is" + res);
    }
}

package DFS;

import java.util.ArrayList;
import java.util.List;

import static Utils.Swap.swapChar;

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

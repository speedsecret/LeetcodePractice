package DFS;

import java.util.ArrayList;
import java.util.List;

import static Utils.Swap.swapChar;

public class AllPermutationOfSubsets {
    public static List<String> allPermutationsOfSubsets(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) {
            return result;
        }
        char[] array = set.toCharArray();
        helper(array, result, 0);
        return result;
    }

    private static void helper(char[] array, List<String> res, int index) {
        res.add(new String(array, 0, index));
        // base case
        if (index == array.length) {
            return;
        }

        for (int i = index; i < array.length; i++) {
            swapChar(array, i, index);
            helper(array, res, index + 1);
            swapChar(array, i, index);
        }
    }

    public static void main(String[] args) {
        String input = "abc";
        List<String> result = allPermutationsOfSubsets(input);
        while (result.size() > 0) {
            System.out.println(result.get(result.size() - 1) + " ");
            result.remove(result.size() - 1);
        }
    }
}

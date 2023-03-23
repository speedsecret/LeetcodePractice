package DFS;

import java.util.ArrayList;
import java.util.List;


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

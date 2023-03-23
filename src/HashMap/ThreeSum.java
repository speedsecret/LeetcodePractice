package HashMap;

// Find three element triple so to add up into 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> findThreeSum(int[] array) {
        List<List<Integer>> result = new ArrayList<>();
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        }
        // use two pointers
        // also, we need to sort from lower number to higher number
        Arrays.sort(array);
        for (int i = 0; i < array.length && array[i] <= 0; i++) {
            // remove duplication from beginning index
            if (i == 0 || (i > 0 && array[i] == array[i - 1])) {
                int start = i + 1;
                int end = array.length - 1;
                int cur = array[i];
                while (start < end) {
                    int sum = cur + array[start] + array[end];
                    if (sum == 0) {
                        result.add(Arrays.asList(cur, start, end));
                        //remove duplication from left hand side
                        while (start < end && array[start] == array[start + 1]) {
                            start++;
                        }
                        //remove duplication from right hand side
                        while (start < end && array[end] == array[end - 1]) {
                            end--;
                        }
                        start++;
                        end--;
                    } else if (sum > 0) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
            int[] array = new int[] {3, -1, 0, 4, 1, 100};
            System.out.printf("What is the pair array looks like" + Arrays.toString(new List[]{findThreeSum(array)}));
    }
}

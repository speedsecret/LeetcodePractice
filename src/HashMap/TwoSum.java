package HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
*  Given an array, and a target number
*  How to find one pair of element within the array can be added up to this targetNumber?
* */
public class TwoSum {
    public static int[] findTwoSum(int[] array, int target) {
        // Solution one, brutal force, you can traverse the whole array by using two pointers
        // Fix one and move one util loop the array n times.
        if (array == null || array.length <= 1) {
            return new int[0];
        }
        // Solution 2: We would use a hashMap to store the element that we had already visited.
        // Also, in this case we just need to loop the array once and we shall get the right pair
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int cur = array[i];
            if (visited.containsKey(target - cur)) {
                return new int[]{i, visited.get(target - cur)};
            }
            visited.put(cur, i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] array = new int[] {3, 2, 0, 4, 1, 100};
        int target = 104;
        System.out.printf("What is the pair array looks like" + Arrays.toString(findTwoSum(array, target)));
    }
}

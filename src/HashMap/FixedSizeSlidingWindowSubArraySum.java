package HashMap;

/*
Find the maximum total weight that a deliverer could take such that every parcel he takes is in a different weight.
 */

import java.util.HashSet;
import java.util.Set;

public class FixedSizeSlidingWindowSubArraySum {
    public static int fixedSizeSlidingWindowSubArraySum_Method_1(int[] array, int k) {
        // Method:
        // maintaining a hashset, as the maximum size == k
        // truncate the hashSet if non-distinct element found
        // use a sum to record current sum
        // use a global max to record max
        if (array == null || array.length <= 0 || k <=0 ) {
            return Integer.MIN_VALUE;
        }
        int sum = 0;
        int max = 0;
        int slow = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            int curVal = array[i];
            while (set.contains(curVal) || (i - slow >= k)) {
                sum -= array[slow];
                set.remove(array[slow++]);
            }
            sum += curVal;
            set.add(curVal);
            if (i + 1 - slow == k) {
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 10, 9, 8, 9, 19};
        int k = 3;
        System.out.printf("This is not a distinct array = " + fixedSizeSlidingWindowSubArraySum_Method_1(array, k));
    }
}

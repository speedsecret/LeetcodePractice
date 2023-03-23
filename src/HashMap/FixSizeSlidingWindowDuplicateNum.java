package HashMap;

/*
Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that
nums[i] = nums[j] and abs[i - j] <=k
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FixSizeSlidingWindowDuplicateNum {
    // method 0:
    // we can stand at the beginning and check every k from the current index
    // T: O(k * n)
    // S: O(1) --> as not required to store element

    // method 1:
    // we should use a hashMap to store the element val and its index
    // and always store the latest index

    // method 2:
    // as we could fix k elements in the hashMap, we could use this as a sliding window
    // actively add and remove

    public static boolean fixSizeSlidingWindowDuplicateNum(int[] array, int k) {
        if (array == null || array.length == 0) {
            return true;
        }
        boolean isNotDistinctArrayMethod1 = fixSizeSlidingWindowDuplicateNum_method_1(array, k);
        boolean isNotDistinctArrayMethod2 = fixSizeSlidingWindowDuplicateNum_method_2(array, k);
        return isNotDistinctArrayMethod1;
    }

    public static boolean fixSizeSlidingWindowDuplicateNum_method_1(int[] array, int k) {
        // use a hashMap
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int curVal = array[i];
            if (map.containsKey(curVal)) {
                if (Math.abs(i - map.get(curVal)) <= k) {
                    return true;
                }
            }
            map.put(curVal, i);
        }
        return false;
    }

    public static boolean fixSizeSlidingWindowDuplicateNum_method_2(int[] array, int k) {
        // maintain a size <=k sliding window set
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            int curVal = array[i];
            if (!set.add(curVal)) {
                return true;
            }
            if (i >= k) {
                set.remove(array[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 4, 10, 11, 9, 20, 2};
        int k = 100;
        System.out.printf("Is this an not distinct array = " + fixSizeSlidingWindowDuplicateNum_method_1(array, k));
    }
}

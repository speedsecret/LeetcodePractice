package HashMap;

/*
Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that
abs(nums[i] - nums[j]) <= d and abs[i - j] <=k
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

public class FixSizeSlidingWindowDuplicateNum_II {
    // method1:
    // using a fixed size sliding window, as the size is k
    // we just need to use a TreeSet.
    // detailed logic:
    // we could check the ceiling to compare if ceiling() - ele <= d
    // or the floor to compare if element - floor <= d

    // T: O(nlog(k)) --> follow up question, could we update this time complexity? If so, how to do it?
    // S: O(k)
    public static boolean fixSizeSlidingWindowDuplicateNum_II_Method_1(int[] array, int k, int d) {
        if (array == null || array.length <= 0 || k < 0 || d < 0) {
            return true;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < array.length; i++) {
            int curEle = array[i];
            // check floor and ceiling
            Integer floor = set.floor(curEle);
            Integer ceiling = set.ceiling(curEle);
            if (curEle - floor <= d || ceiling - curEle <= d) {
                return true;
            }
            // else, we add this element into the treeSet
            set.add(curEle);
            // we should already maintain the size of k
            if (i >= k) {
                set.remove(array[i - k]);
            }
        }
        return false;
    }

    // Method 2, store elements in bucket but not using a TreeMap directly
    // for each value range, we only allow one element reside
    // so we just need to check left bucket and the right bucket and to know if we had abs(nums[i] - nums[j]) <= d) ele
    // eg:
    // #1 bucket [0 - 4] #2 bucket [5 - 9] #3 bucket [10 - 14] #4 bucket [15 - 19]
    // if d = 5, we could only check the 3 buckets (self && leftBucket && rightBucket)
    // setup the bucket based on the number of d

    // T: O(n)
    // S: O(n) --> worst case we need to create n bucket to store n elements for the entire array
    public static boolean fixSizeSlidingWindowDuplicateNum_II_Method_2(int[] array, int k, int d) {
        if (array == null || array.length <= 0 || k < 0 || d < 0) {
            return true;
        }
        // use a hashMap to store size k elements
        // <index, value> --> index of bucket, value is the element in the array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int curVal = array[i];
            int index = getBucketIndex(array, d, i);
            // first to check with current element
            if (map.put(index, curVal) != null) {
                return true;
            }
            Integer floor = map.get(index - 1);
            Integer ceiling = map.get(index + 1);
            if (floor != null && curVal - floor <= d
                    || ceiling != null && ceiling - curVal <= d) {
                return true;
            }
            map.put(index, curVal);
            if (i >= k) {
                map.remove(getBucketIndex(array, d, i - k));
            }
        }
        return false;
    }

    private static int getBucketIndex(int[] array, int d, int i) {
        return array[i] < 0 ? (array[i] / (d + 1) - 1) : (array[i] / (d + 1));
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 10, 9, 8, 7, 19};
        int k = 3;
        int d = 2;
        System.out.printf("This is not a distinct array = " + fixSizeSlidingWindowDuplicateNum_II_Method_2(array, k, d));
    }
}

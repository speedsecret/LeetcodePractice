package HashMap;

/*
Given an integer array nums, return true if any value appears at least twice in the array,
and return false if every element is distinct.
 */

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/*
Method:
Loop the array via using a hashSet, if we were not able to add the element into the hashSet we can just return true;


 */
public class FixedSizeSlidingWindow {
    //Method:
    // T: O(n)
    // S: O(n)
    public static boolean findFixedSizeSlidingWindow_hashSet(int[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (!set.add(array[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 9, 10, 2};
        Instant instant = Instant.now();
        long startTime = instant.getNano() * 1000;
        System.out.printf("Starting time= " + startTime + "\n");
        System.out.printf("Is this not a distinct array=" + findFixedSizeSlidingWindow_hashSet(array) + "\n");
        instant = Instant.now();
        long endTime = instant.getNano() * 1000;
        System.out.printf("end time= " + endTime + "\n");
        System.out.printf("Working speed = " + (endTime - startTime) + "\n");
    }
}

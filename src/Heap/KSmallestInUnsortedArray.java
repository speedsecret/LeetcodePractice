package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KSmallestInUnsortedArray{
    public static int[] findKSmallestElements(int[] array, int k) {
        // corner case check
        if (array == null || array.length <= 1) {
            return array;
        }
        // as we pass the array from left to right, we could push the element in a maxHeap, henceforth, we could pop that out later.
        // once the size of maxHeap reaches out to k, we need to add a filter, which compares the top of the heap and if the current one is smaller than the top one, we can poll it out and push the current on to the maxHeap.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2.intValue() - o1.intValue());
        for (int i = 0; i < array.length; i++) {
            if (i < k) {
                maxHeap.offer(array[i]);
            } else {
                if (maxHeap.peek() > array[i]) {
                    maxHeap.poll();
                    maxHeap.offer(array[i]);
                }
            }
        }
        // create an empty array so we can insert elements from end to the beginning. And in this case we would expect to see it shows up in an ascending order.
        int[] result = new int[k];
        for (int i = 0; i < k; i--) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 82, 1};
        int k = 2;
        System.out.println(Arrays.toString(findKSmallestElements(array, k)));
    }
}

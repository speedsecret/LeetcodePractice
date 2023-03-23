package HashMap;

/*
You are given an array of integers numbers, there is a sliding window of size k which is moving form the very left
of the array to the very right, you can only see the k numbers in the window. Each time the sliding window mvoes right by
one position
Return the max sliding window --> return an array
 */

import Heap.MaxHeap;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class FindMaxElementInSizeKSlidingWindow {
    public static int[] findMaxElementInSizeKSlidingWindow_Method_1(int[] array, int k) {
        // Method 1
        // just maintaining a size k array as the output result
        // each time we would check the array
        // T:O(nk)
        // S:O(1) --> don't count as the maintaining array
        if (array == null || array.length == 0 || k <= 0) {
            return new int[0];
        }
        int[] result = new int[array.length - k + 1];
        int max = 0;
        // 0 1 2 3 4 5 6 7
        // 2 8 9 2 1 9 8 5
        // s
        //   f
        for (int fast = k - 1; fast < array.length; fast++) {
            int slow = 0;
            int curMax = array[fast];
            for (slow = 0; slow < fast; slow++) {
                curMax = Math.max(max, result[fast - slow + 1]);
            }
            result[fast - slow + 1] = curMax;
        }
        return result;
    }

    public static int[] findMaxElementInSizeKSlidingWindow_Method_2(int[] array, int k) {
        // method 2
        // use a maxHeap
        // T: O(nk)
        // S: O(k) --> maintaining an maxHeap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int[] result = new int[array.length - k + 1];
        for (int i = 0; i < array.length; i++) {
            maxHeap.offer(array[i]);
            if (i >= k) {
                // time complexity dominate factor as this is O(K) instead of O(logk)
                maxHeap.remove(array[i - k + 1]);
            }
            if (i >= k - 1) {
                result[i - k + 1] = maxHeap.peek();
            }
        }
        return result;
    }

    public static int[] findMaxElementInSizeKSlidingWindow_Method_3(int[] array, int k) {
        // Method 3
        // Use a TreeSet to store the key and value
        // But we have to define an element class
        // T: O(nlogk)
        // S: O(k)
        if (array == null || array.length <= 0 || k > array.length) {
            return new int[0];
        }
        TreeSet<Element> set = new TreeSet<Element>();
        int[] result = new int[array.length - k + 1];
        for (int i = 0; i < array.length; i++) {
            set.add(new Element(array[i], i));
            // remove slow
            if (i >= k) {
                set.remove(new Element(array[i - k], i - k));
            }
            // update the result
            if (i >= k - 1) {
                result[i - k + 1] = set.last().value;
            }
        }
        return result;
    }


    static class Element implements Comparable<Element>{
        int index;
        int value;
        public Element(int v, int i) {
            this.value = v;
            this.index = i;
        }
        @Override
        public int compareTo(Element other) {
            int result = Integer.compare(this.value, other.value);
            if (result == 0) {
                return Integer.compare(this.index, other.index);
            }
            return result;
        }
    }

    public static int[] findMaxElementInSizeKSlidingWindow_Method_4(int[] array, int k) {
        // method 4
        // use array deque and always maintaining the monotonically decreasing order
        // T: O(n)
        // S: O(k)
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[array.length - k + 1];
        // we need to put index into the deque but not the element
        for (int fast = 0; fast < array.length; fast++) {
            while (!deque.isEmpty() && array[fast] >= deque.peekLast()) {
                deque.pollLast();
            }
            deque.offerLast(fast);
            if (fast >= k) {
                if (!deque.isEmpty() && deque.peekFirst() == fast - k) {
                    deque.pollFirst();
                }
            }
            if (fast >= k - 1) {
                result[fast - k + 1] = array[deque.peekFirst()];
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] array = {1, 4, 3, 1, 2, 2, 3};
        int k = 3;
        System.out.println("Maximum sliding window = "
                + Arrays.toString(findMaxElementInSizeKSlidingWindow_Method_3(array, k)));
    }
}
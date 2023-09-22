/*
480. Find Median from K elements in a stream
https://leetcode.com/problems/sliding-window-median/description/

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
So the median is the mean of the two middle values.

For examples, if arr = [2,3,4], the median is 3.
For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
You are given an integer array nums and an integer k. 
There is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
Explanation: 
Window position                Median
---------------                -----
[1  3  -1] -3  5  3  6  7        1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7        3
 1  3  -1  -3 [5  3  6] 7        5
 1  3  -1  -3  5 [3  6  7]       6
Example 2:

Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 

Constraints:

1 <= k <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
*/

// Methodology:
// Use two hashMaps with several helper functions(lazyRemove, add, tryRemove, remove, rebalance)
// Handle the k == 1 case seperately
// Handle the i = [0 ~ k) cases
// Handle the i = [k ~ n) cases

class Solution {
    HashMap<Integer, Integer> deleted;
    PriorityQueue<Integer> minHeap, maxHeap;
    // counts for elements in minHeao and maxHeap
    int minHS, maxHS;
    // Array to store results
    double[] res;

    public double[] medianSlidingWindow(int[] nums, int k) {
        // Initilize data structures
        deleted = new HashMap<>();
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        res = new double[nums.length - k + 1];

        if (k == 1) {
            for (int i = 0; i < nums.length; i++) {
                res[i] = 0.0 + nums[i];
            }
            return res;
        }

        // Initialize the sliding window
        for (int i = 0; i < k; i++) {
            add(nums[i]);
        }
        res[0] = getMedian();

        // sliding window and calculate medians for each window
        for (int i = k; i < nums.length; i++) {
            lazyRemove(nums[i - k]);
            add(nums[i]);
            res[i - k + 1] = getMedian();
        }
        return res;
    }

    // put elements which could be removed into a hashMap(used as a buffer), will got removed later
    private void lazyRemove(int a) {
        // check if 'a' is in minHeap and maxHeap, decrement counts accordingly
        if (!minHeap.isEmpty() && a >= minHeap.peek()) {
            minHS--;
        } else if (!maxHeap.isEmpty() && a <= maxHeap.peek()) {
            maxHS--;
        }
        deleted.put(a, deleted.getOrDefault(a, 0) + 1);
    }

    private void add(int a) {
        if (minHeap.isEmpty() || a > minHeap.peek()) {
            minHeap.offer(a);
            minHS++;
        } else {
            maxHeap.offer(a);
            maxHS++;
        }

        rebalance();
    }

    // remove an element from the deleted Map
    private void remove(int r) {
        deleted.put(r, deleted.get(r) - 1);
        if (deleted.get(r) == 0) {
            deleted.remove(r);
        }
    }

    // remove deleted elements from the heaps
    private void tryRemove() {
        while (!minHeap.isEmpty() && deleted.containsKey(minHeap.peek())) {
            remove(minHeap.poll());
        }
        while (!maxHeap.isEmpty() && deleted.containsKey(maxHeap.peek())) {
            remove(maxHeap.poll());
        }
    }

    private double getMedian() {
        tryRemove();

        if (minHS == maxHS) {
            return (double)minHeap.peek() / 2.0 + (double)maxHeap.peek() / 2.0;
        } else if (minHS > maxHS) {
            return (double)minHeap.peek();
        } else {
            return (double)maxHeap.peek();
        }
    }

    // rebalance the heaps to maintain their sizes
    private void rebalance() {
        tryRemove();
        if (minHS - maxHS > 1) {
            maxHeap.offer(minHeap.poll());
            minHS--;
            maxHS++;
        } else if (maxHS - minHS > 1) {
            minHeap.offer(maxHeap.poll());
            minHS++;
            maxHS--;
        }
    }
}

// ***** the method below would lead to TLE ******

// **Important**
// Small problem here is that we find the two heaps method would encounter TLE.
// "Testcases passed, but took too long."

// considering using the maxHeap and minHeap
// Step1: creating the heaps
// Step2: addingNumber for the first k elements in nums
// Step3: dealing with median elements --> considering using a boolean 
// Step4: check the elements that is almost out of the window
// Step5: dealing with the last sliding window

/*

public double[] medianSlidingWindow(int[] nums, int k) {
        // Initialize two heaps: minHeap for the larger half, maxHeap for the smaller half
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int n = nums.length;
        double[] medians = new double[n - k + 1];

        for (int i = 0; i < n; i++) {
            // Add the current element to the maxHeap if it's smaller than the max element in minHeap
            maxHeap.offer(nums[i]);
            minHeap.offer(maxHeap.poll());

            // Balance the heaps if necessary
            while (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
            while (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }

            // If the window size is reached, calculate the median
            if (i >= k - 1) {
                // If k is even, calculate the average of the top elements from both heaps
                if (k % 2 == 0) {
                    medians[i - k + 1] = (double) maxHeap.peek() / 2 + (double) minHeap.peek() / 2;
                } else {
                    medians[i - k + 1] = (double) maxHeap.peek();
                }

                // Remove the element that is no longer in the window
                int removed = nums[i - k + 1];
                if (removed <= maxHeap.peek()) {
                    maxHeap.remove(removed);
                } else {
                    minHeap.remove(removed);
                }
            }
        }

        return medians;
    }
}
*/

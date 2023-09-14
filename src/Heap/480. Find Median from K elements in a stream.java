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

// **Important**
// Small problem here is that we find the two heaps method would encounter TLE.
// "Testcases passed, but took too long."

// considering using the maxHeap and minHeap
// Step1: creating the heaps
// Step2: addingNumber for the first k elements in nums
// Step3: dealing with median elements --> considering using a boolean 
// Step4: check the elements that is almost out of the window
// Step5: dealing with the last sliding window

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

/*
Leetcode 239 https://leetcode.com/problems/sliding-window-maximum/description/
You are given an array of integers nums, there is a sliding window of size k which is
moving from the very left of the array to the very right. You can only see the k numbers in the window. 
Each time the sliding window moves right by one position.

Return the max sliding window.
Example 1:
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 
Example 2:
Input: nums = [1], k = 1
Output: [1]
*/

// Use monotonic queue.
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] arr = new int[nums.length - k + 1];
        int start = 0;

        // keep the dp as a strictly decreasing order queue
        // monotonic Deque
        for (int i = 0; i < k; i++) {
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        arr[start++] = nums[dq.peekFirst()];

        // track the element from index = k to index = n - 1;
        // sliding window needs running, we need to remove element out of the queue
        for (int i = k; i < nums.length; i++) {
            if (dq.peekFirst() == i - k) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }

            dq.offerLast(i);
            arr[start++] = nums[dq.peekFirst()];
        }

        return arr;
    }
}

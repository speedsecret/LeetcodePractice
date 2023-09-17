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
// two stages:
// 1st stage: elements' index less than k, we should just adding or poll elements' index into it
// 2st stage: check the peekFirst() element's index, if it equals to i - k, remove it, repeat stage 1 while loop, record the current max.
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] arr = new int[nums.length - k + 1];
        int start = 0;

        // keep the dp as a strictly decreasing order queue
        // monotonic Deque
        // in another word, the peekFirst() ele must be the largest in the dq --> is currently the returnable max.
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
            // if it is current out of the scope, we should poll it out
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

/*
 public int[] maxSlidingWindow(int[] nums, int k) {
        // Method 1: use PriorityQueue to store the max element(maxHeap) and two pointer --> TTL due to remove time is really time consuming
        // Method 2: using a treeSet to store k elements all the time but the time complexity is also huge --> O(nlogk)
        // Method 3: using a deque, to only keep the useful elements in the deque and always keep a monotonic order queue
        // Additionally, only store the index in the queue
        Deque<Integer> dq = new ArrayDeque<>();
        int[] arr = new int[nums.length - k + 1];
        int start = 0;
        // loop the nums once
        // case1: for index < k
        for (int i = 0; i < k; i++) {
            int ele = nums[i];
            // always maintain a increasing queue.
            while (!dq.isEmpty() && ele >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        } 
        arr[start++] = nums[dq.peekFirst()];
        // case2: for index >= k
        //      thread2.1 for the first element in deque --> pollElement out
        //      thread2.2 for check if the current element is larger or equals to smallest one in the queue(we should keep a fresh sliding window) --> keep adding element and poll element out if find larger or equal element
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
 */
}

/*
215.Top K Largest Element in an array
https://leetcode.com/problems/kth-largest-element-in-an-array/description/

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?

 

Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Method1: use a minHeap, remember to check with lambda expression to refresh the memory on how to use it.
        // keep an int variable minEle
        // logic: maintaining a hashSet, keep the size as of k
        // adding the number into the set for two reasons:
        // 1. the size of set is smaller than k
        // 2. the minEle is smaller than the num, remove the minEle
        // 3. maintain a minHeap--> so we peek the hashMap then replace minEle
        
        /*
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            if (!minHeap.isEmpty() && minHeap.size() >= k) {
                int ele = minHeap.peek();
                if (ele >= num) {
                    continue;
                } else {
                    minHeap.poll();
                }
            }
            minHeap.add(num);
        }
        return minHeap.poll();
        */

        // Method 2: quickSelect
        // Methodology:
        // Using quickSelect, each time to randomly choose a index, then loop the element in current list
        // construct with 3 lists, larger, same, and smaller
        // Put larger elements to larger list, 
        // put equal element to same list
        // put smaller elements to smaller list
        // Method 2: Use QuickSelect
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = partition(nums, left, right);
            if (mid == k - 1) {
                // return the specific number
                return nums[mid];
            } else if (mid > k - 1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return nums[k - 1];
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left + 1, j = right;
        while (i <= j) {
            // methodology
            // move all larger or equals element to the front of the nums array
            // so i should be move one step forward.

            // for right handside element, it shouldn't larger or equals to pivot, right?
            // for left handside element, it shouldn't smaller or equals to the pivot too.
            // so for the above cases, we need to swap them.
            if (nums[j] >= pivot) {
                swap(nums, i++, j);
            } else if (nums[i] <= pivot) {
                swap(nums, i, j--);
            } else {
                i++;
                j--;
            }
        }
        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}  

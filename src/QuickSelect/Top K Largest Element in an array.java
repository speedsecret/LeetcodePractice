/*
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
        // Method 2: Ues quick Select
        // recursively call the quick select function and confidently drop impossible parts, decrease the length
        // step1: pick a pivot randomly
        // step2: create three lists and loop the original list
        // step3: determine which quickselect sub-call needs to be called in and return it accordingly.
        List<Integer> list = new ArrayList<>();
        for (int num :  nums) {
            list.add(num);
        }
        return quickSelect(list, k);
    }

    private int quickSelect(List<Integer> list, int k) {
        int pivotIndex = new Random().nextInt(list.size());
        int pivot = list.get(pivotIndex);

        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int num : list) {
            if (num > pivot) {
                left.add(num);
            } else if (num < pivot) {
                right.add(num);
            } else {
                mid.add(num);
            }
        }

        if (left.size() >= k) {
            return quickSelect(left, k);
        } else if (mid.size() + left.size() < k) {
            return quickSelect(right, k - mid.size() - left.size());
        }
        return pivot;
    }
}

  

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
        List<Integer> list = Arrays.stream(nums)
                                    .boxed()
                                    .collect(Collectors.toList());
        return quickSelect(list, k);
    }

    private int quickSelect(List<Integer> list, int k) {
        int pivotIndex = new Random().nextInt(list.size());
        int pivot = list.get(pivotIndex);
        
        // construct 3 lists
        List<Integer> smallerList = new ArrayList<>();
        List<Integer> sameList = new ArrayList<>();
        List<Integer> largerList = new ArrayList<>();

        for (int ele : list) {
            if (ele < pivot) {
                smallerList.add(ele);
            } else if (ele > pivot) {
                largerList.add(ele);
            } else {
                sameList.add(ele);
            }
        }

        */
        // recursive rule:
        // focus on largerList then sameList then smallerList, because we would like to find kth Largest element.
        // Recursion:
        // If the left list's size is greater than or equal to k, it means that the kth largest element must be in the left list because all elements in it are larger than 
        // the pivot. 
        // So, it recursively calls quickSelect(left, k) to find the kth largest element in the left list.
        // If the left list's size plus the mid list's size is less than k, it means that the kth largest element is in the right list. 
        // We adjust k by subtracting the sizes of left and mid lists from it because we have already determined that these elements are not part of the kth largest. 
        // So, we recursively call quickSelect(right, k - (left.size() + mid.size())) to find the kth largest element in the right list.
        // If neither of the above conditions is met, it means that k falls within the mid list. In this case, we return the pivot because we've found the kth largest element, 
        // which is the pivot itself.
        */
        // case1:
        if (largerList.size() >= k) {
            return quickSelect(largerList, k);
        }
        // case 2:
        if (largerList.size() + sameList.size() < k) {
            return quickSelect(smallerList, k - largerList.size() - sameList.size());
        } 
        // case 3:
        else {
            return sameList.get(0);
        }
    }
}  

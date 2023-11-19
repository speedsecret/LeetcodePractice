/*
347. Top K freq elements
https://leetcode.com/problems/top-k-frequent-elements/

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:
Input: nums = [1], k = 1
Output: [1]
 
Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.
 
Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

// You are more than welcome to use the minHeap to compare
// but I guess the therotical time complexity is slower --> nlogk

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // As it required to return the most k frequent
        // It is critical to make a sorted int[] array from largest to the smallest
        // Applied this approach into the helper function.
        Map<Integer, Integer> freqMap = getMap(nums);
        int[] elements = freqMap.keySet().stream().mapToInt(Integer::intValue).toArray();
        int left = 0, right = elements.length - 1;
        while (left < right) {
            int mid = partition(freqMap, elements, left, right);
            if (mid == k - 1) {
                break;
            } else if (mid < k - 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Arrays.copyOfRange(elements, 0, k);
    }

    private int partition(Map<Integer, Integer> freqMap, int[] elements, int left, int right) {
        int pivot = freqMap.get(elements[left]);
        int i = left + 1, j = right;
        while (i <= j) {
            // if the larger or equal element in the right hand side,
            // remove it from the right to the front.
            if (freqMap.get(elements[j]) >= pivot) {
                swap(elements, i++, j);
            } 
            // if the smaller or equal element in the left hand side,
            // remove it from the front to the end.
            else if (freqMap.get(elements[i]) <= pivot) {
                swap(elements, i, j--);
            } 
            // otherwise, either leftIndex and rightIndex are well stand in their pos.
            else {
                i++;
                j--;
            }
        }
        swap(elements, left, j);
        return j;
    }

    private Map<Integer, Integer> getMap(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int ele : nums) {
            freqMap.put(ele, freqMap.getOrDefault(ele, 0) + 1);
        }
        return freqMap;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

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
class Solution {
    Map<Integer, Integer> freqMap;
    public int[] topKFrequent(int[] nums, int k) {
        // Use quickSelect
        // Create a hashMap, find the frequencies for each element in nums
        // We would construct an array, which have all unique elements
        // using the elements as index to retrive frequencies from map
        // for each parition, we would sort larger elements into the right side
        // so from the range of [targetIndex, targetIndex + k), is the result we want to returned.
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        if (k >= nums.length) {
            return nums;
        }
        freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        // mapToInt(Integer::intValue): Here, we use the mapToInt method to map each key (which is of type Integer) to its primitive int value. This step is essentially converting the Stream of Integer objects into a Stream of int primitives. It's equivalent to calling .intValue() on each Integer object.
        int[] elements = freqMap.keySet().stream().mapToInt(Integer::intValue).toArray();

        int left = 0, right = elements.length - 1, targetIndex = elements.length - k;
        while (left <= right) {
            int pivotIndex = partition(elements, left, right);

            if (pivotIndex == targetIndex) {
                break;
            } else if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return Arrays.copyOfRange(elements, targetIndex, targetIndex + k);
    }

    private int partition(int[] elements, int left, int right) {
        int pivotFreq = freqMap.get(elements[right]);
        int pivotIndex = left;

        for (int i = left; i < right; i++) {
            if (freqMap.get(elements[i]) < pivotFreq) {
                swap(elements, pivotIndex, i);
                pivotIndex++;
            }
        }
        swap(elements, pivotIndex, right);
        return pivotIndex;
    }

    private void swap(int[] elements, int left, int right) {
        int temp = elements[left];
        elements[left] = elements[right];
        elements[right] = temp;
    }
}

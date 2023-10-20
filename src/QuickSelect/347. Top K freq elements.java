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
        Map<Integer, Integer> freqMap = getFreqMap(nums);
        // construct a distinct int array
        int[] elements = freqMap.keySet().stream().mapToInt(Integer::intValue).toArray();
        // use quickSelect
        int left = 0, right = elements.length - 1;
        while (left < right) {
            int mid = partition(elements, freqMap, left, right);
            if (mid > k - 1 ) {
                right = mid - 1;
            } else if (mid < k - 1){
                left = mid + 1;
            } else {
                break;
            }
        }
        return Arrays.copyOfRange(elements, 0, k);
    }

    private int partition(int[] elements, Map<Integer, Integer> map, int left, int right) {
        int pivotIndex = elements[left];
        int pivot = map.get(pivotIndex);
        int i = left + 1, j = right;
        while (i <= j) {
            if (map.get(elements[j]) >= pivot) {
                swap(elements, i++, j);
            } else if (map.get(elements[j]) <= pivot) {
                swap(elements, i, j--);
            } else {
                i++;
                j--;
            }
        }
        swap(elements, left, j);
        return j;
    }

    private Map<Integer, Integer> getFreqMap(int[] nums) {
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

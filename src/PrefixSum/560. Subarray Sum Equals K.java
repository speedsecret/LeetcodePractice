/*
Leetcode 560. Subarray Sum Equals K
https://leetcode.com/problems/subarray-sum-equals-k/

Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 

Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
*/

// Method: PrefixSum
// if sum[i] - sum[j] == 0
// Represents the elements in between i and j are zero
// if sum[i] - sum[j] == k
// Represents the elements in between i and j are k

// Use two int variables: counter and prefixSum
// Use a hashMap to store all prefixSum and its duplicated times in it
// update the prefixSum when we traversing the arr, also, checking if the *prefixSum - k* has been stored 
// in the hashMap
// if so, adding the corresponding duplicated times into the counter as the final return result.
// keep adding/updating the current prefixSum into the hashMap

class Solution {
    public int subarraySum(int[] nums, int k) {
        // use this feature to build up an hashMap
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefixSum = 0;
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (map.containsKey(prefixSum - k)) {
                counter += map.get(prefixSum - k);
            }

            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return counter;
    }
}

/*
// Feb.07.2024
// My hashMap method approach, while I believe the highlight one is better from clean code perspective.
// Mindset:
// Use a hashMap to store a entry as <Integer, Integer>
// and named it as counts;

// Use a linear time complexity to process the nums array
// maintain an int globalRes
// to check how many subArray we already have is equals to (sum - k)
// if the result is larger than 0
// add it into a global variable globalRes

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        // we know there is always the sum of the subarray equals to 0.
        count.put(0, 1);
        int globalRes = 0, prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            Integer curRes = count.get(prefixSum - k);
            if (curRes != null) {
                globalRes += curRes;
            }
            count.put(prefixSum, count.getOrDefault(prefixSum, 0) + 1);
        }
        return globalRes;
    }
}
*/



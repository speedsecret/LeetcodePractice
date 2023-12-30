/*
4. Median of Two sorted Arrays.java
https://leetcode.com/problems/median-of-two-sorted-arrays/description/

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106

*/

// Methodology
// Step1: define which one is smaller
// Step2: define the leftIndex and rightIndex
// Step3: partitionLeftHalfNums1, partitionRightHalfNums1, partitionLeftHalfNums2, partitionRightHalfNums2;
// Step4: condition check
// Step5: repeat step3 - step5
// Step6: return 0.0 if not find it.


class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
         
        int m = nums1.length;
        int n = nums2.length;
        int leftIndex = 0, rightIndex = m;

        // binary Search and the time complexity is O(log(min(m, n)));
        while (leftIndex <= rightIndex) {
            int partitionNums1 = (leftIndex + rightIndex) / 2;
            int partitionNums2 = (m + n + 1) / 2 - partitionNums1;

            // this is the first half of nums1, maxLeftNums1 means the largest element in the leftPartition
            int maxLeftNums1 = (partitionNums1 == 0) ? Integer.MIN_VALUE : nums1[partitionNums1 - 1];
            // this is the right half of nums1, maxRightNums1 means the smallest element in the rightPartition
            int minRightNums1 = (partitionNums1 == m) ? Integer.MAX_VALUE : nums1[partitionNums1];
            int maxLeftNums2 = (partitionNums2 == 0) ? Integer.MIN_VALUE : nums2[partitionNums2 - 1];
            int minRightNums2 = (partitionNums2 == n) ? Integer.MAX_VALUE : nums2[partitionNums2];

            // the maxLeftElement in nums1 smaller or equals to minRightElement in nums2
            // as well as
            // the maxLeftElement in nums2 smaller or equals to minRightElement in nums1
            if (maxLeftNums1 <= minRightNums2 && maxLeftNums2 <= minRightNums1) {
                // the total length of two arrays is even number
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftNums1, maxLeftNums2) + Math.min(minRightNums1, minRightNums2)) / 2.0;
                } 
                // odd number
                else {
                    return Math.max(maxLeftNums1, maxLeftNums2);
                }
            } 
            // the partitionNums1 is larger than required, so we should shorten it.
            else if (maxLeftNums1 > minRightNums2) {
                rightIndex = partitionNums1 - 1;
            } else {
                leftIndex = partitionNums1 + 1;
            }
        }
        return 0.0;
    }
}
	

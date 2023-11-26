/*
42. TrapMaxRain.java
https://leetcode.com/problems/trapping-rain-water/description/
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:
n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/    

// Methodology:
// Use two pointers: curLeft, curRight to check from left, right to middle
// Use two int variables leftMax, rightMax, and updated the leftMax, rightMax on the fly
// when curLeft < curRight, check the curLeft with LeftMax, vice verse when curLeft > curRight

public class TrapMaxRain {
   public int trap(int[] height) {
        // edge case check:
        if (height.length < 3) {
            return 0;
        }
        int leftMax = 0, rightMax = 0, leftIndex = 0, rightIndex = height.length - 1, res = 0;
        // this terminal condition must be leftIndex < rightIndex
        // once they are the same, there is no chance of adding more water into it
        while (leftIndex < rightIndex) {
            if (height[leftIndex] <= height[rightIndex]) {
                if (height[leftIndex] > leftMax) {
                    leftMax = height[leftIndex];
                } else {
                    res += leftMax - height[leftIndex];
                }
                leftIndex++;
            } else {
                if (height[rightIndex] > rightMax) {
                    // then we need to update the rightMax index
                    rightMax = height[rightIndex];
                } else {
                    res += rightMax - height[rightIndex];
                }
                rightIndex--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] input = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        //index: 0 1 2 3 4 5 6 7 8 9 10 11
        //      {0,1,0,2,1,0,1,3,2,1,2, 1}
        //
        System.out.printf("The max rain is about = " + trap(input));
    }
}

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
    public static int trap(int[] height) {
        // use two pointers so we can check the height slot by slot.
        // then maintaining two variables to record the max raining level
        if (height == null || height.length <= 2) {
            return 0;
        }
        int leftMax = 0;
        int rightMax = 0;
        int curLeft = 0;
        int curRight = height.length - 1;
        int result = 0;
        while (curLeft < curRight) {
            // case 1:
            if (height[curLeft] < height[curRight]) {
                if (height[curLeft] > leftMax) {
                    leftMax = height[curLeft];
                } else {
                    result += leftMax - height[curLeft];
                }
                curLeft++;
            }
            // case 2:
            else {
                if (height[curRight] > rightMax) {
                    rightMax = height[curRight];
                } else {
                    result += rightMax - height[curRight];
                }
                curRight--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        //index: 0 1 2 3 4 5 6 7 8 9 10 11
        //      {0,1,0,2,1,0,1,3,2,1,2, 1}
        //
        System.out.printf("The max rain is about = " + trap(input));
    }
}

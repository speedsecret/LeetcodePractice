package Array;

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

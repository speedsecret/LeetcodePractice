/*
Description
Given n items with size m, which represent the maximum we can put into the bagback,
How full you can fill this backpack?
(Each item can only be selected once and the size of the item is a positive integer)

Example: 
array = [3,4,8,5]
backpack size = 10
Output: 9
*/

import java.util.ArrayList;
import java.util.List;

public class BackpackFiller {
    public int fillBackpack(int[] items, int backpackSize) {
        int n = items.length;
        int[][] dp = new int[n + 1][backpackSize + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= backpackSize; j++) {
                if (items[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i - 1]] + items[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[n][backpackSize];
    }
    
    public static void main(String[] args) {
        int[] items = {3, 4, 8, 5};
        int backpackSize = 10;
        
        BackpackFiller backpackFiller = new BackpackFiller();
        int result = backpackFiller.fillBackpack(items, backpackSize);
        
        System.out.println("Maximum value that can fill the backpack: " + result);
    }
}

/*
Analysis:
Using DP as we don't know the length of elements that we may used.
Row Starts from 0 -> n + 1;
Col Starts from 0 -> size + 1;
DP[i][j] represent the maximum sum we can put into the backpack after looping through all elements from 0 - current i;

Items/Backpack  
            0  1  2  3  4  5  6  7  8  9 10
           --------------------------------
        0 | 0  0  0  0  0  0  0  0  0  0  0
        3 | 0  0  0  3  3  3  3  3  3  3  3
        4 | 0  0  0  3  4  4  4  7  7  7  7
        8 | 0  0  0  3  4  4  4  7  8  8  8
        5 | 0  0  0  3  4  5  5  7  8  9  9
*/

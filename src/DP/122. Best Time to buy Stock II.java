/*
122. Best Time to buy Stock II.java
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
However, you can buy it then immediately sell it on the same day.
Find and return the maximum profit you can achieve.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 

Constraints:
1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
*/
// Methodology
// If the current prices is high than cost --> we should take the gain
// For each day, we would update the newly cost --> 
// 1. for lower one, we want to update it to get a lower prices.
// 2. for higher one, we must to sale it and re-purchase it at the same day.

class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int cost = prices[0];
        int profits = 0;
        for (int i = 1; i < prices.length; i++) {
            // we are making money now when the current prices is larger than cost
            if (prices[i] > cost) {
                profits += prices[i] - cost;
            }
            cost = prices[i];
        }
        return profits;
    }
}

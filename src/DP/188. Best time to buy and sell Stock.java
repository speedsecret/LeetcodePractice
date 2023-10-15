/*
188. Best time to buy and sell Stock.java
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/

You are given an integer array prices where prices[i] is the price of a given stock on the ith day, 
and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).


Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

*/

// Methodology
// We are calculating the maxProfit by reinvest money into the 2nd to kth transaction
// Taking the previous Profit into the transaction calculation.
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int[] profits = new int[k + 1];
        int[] costs = new int[k + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < k; j++) {
                costs[j + 1] = Math.min(costs[j + 1], prices[i] - profits[j]);
                profits[j + 1] = Math.max(profits[j + 1], prices[i] - costs[j + 1]);
            }
        }
        return profits[k];
    }
}

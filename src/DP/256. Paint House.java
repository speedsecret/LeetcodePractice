/*
256. Paint House.java
https://leetcode.com/problems/paint-house/

There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.

For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost
of painting house 1 with color green, and so on...Return the minimum cost to paint all houses.

Example 1:

Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
Minimum cost: 2 + 5 + 3 = 10.
Example 2:
Input: costs = [[7,6,2]]
Output: 2
 
Constraints:

costs.length == n
costs[i].length == 3
1 <= n <= 100
1 <= costs[i][j] <= 20
*/

class Solution {
    public int minCost(int[][] costs) {
        // clarification
        // return: minimum cost to paint all houses
        // requirement: no two adj houses are the same color
        // color: red, blue, green
        // cost[0][0]: 0th house in red = 17;
        // cost[0][1]: 0th house in blue = 2;
        // cost[0][2]: 0th house in green = 17;
        // [[17,2,17],[16,16,5],[14,3,19]]

        // use DP and create a matrix model
        int m = costs.length;
        // methodology
        // start to look at the second last row of the costs
        // then to update the costs iteratively, for each color, there are 2 options to paint and update the costs in its current row
        // until finish paint all house
        // return the smallest among red, blue and green
        for (int i = m - 2; i >= 0; i--) {
            costs[i][0] = costs[i][0] + Math.min(costs[i + 1][1], costs[i + 1][2]);
            costs[i][1] = costs[i][1] + Math.min(costs[i + 1][0], costs[i + 1][2]);
            costs[i][2] = costs[i][2] + Math.min(costs[i + 1][1], costs[i + 1][0]);
        }

        return Math.min(costs[0][0], Math.min(costs[0][1], costs[0][2]));
    }
}

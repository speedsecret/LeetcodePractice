/*
265. Paint House II.java
https://leetcode.com/problems/paint-house-ii/description/

There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by an n x k cost matrix costs.

For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
Return the minimum cost to paint all houses.

 

Example 1:

Input: costs = [[1,5,3],[2,9,4]]
Output: 5
Explanation:
Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
Example 2:

Input: costs = [[1,3],[2,4]]
Output: 5
 

Constraints:

costs.length == n
costs[i].length == k
1 <= n <= 100
2 <= k <= 20
1 <= costs[i][j] <= 20
 

Please solve it in O(nk) runtime with O(1) space complexity --> need to update the input on the fly.

*/

// Method2:
// Use two int variables minColor and secondMinColor(created them when each nested loop starts)
// for each row, it would update the minColor and secondMinColor if applicable
// would need to finally to loop the bottom row and found the minimum cost.

class Solution {
    public int minCostII(int[][] costs) {
        // use dynamica programming
        int n = costs.length, k = costs[0].length;
        for (int house = 1; house < n; house++) {
            int minCostColor = -1, secondMinCostColor = -1;
            for (int color = 0; color < k; color++) {
                int cost = costs[house - 1][color];
                // the first visited minCostColor
                // or find a cheaper color, even cheap than minCostColor
                if (minCostColor == -1 || cost < costs[house - 1][minCostColor]) {
                    secondMinCostColor = minCostColor;
                    minCostColor = color;
                } 
                // the first time visited secondMinCostColor
                // or find a cheaper color, is expansive then costs[house - 1][minCostColor]
                // but cheaper than costs[house - 1][secondMinCostColor]
                else if (secondMinCostColor == -1 || cost < costs[house - 1][secondMinCostColor]) {
                    secondMinCostColor = color;
                }
            }

            // then update the current layer of painting cost
            for (int color = 0; color < k; color++) {
                if (color == minCostColor) {
                    costs[house][color] += costs[house - 1][secondMinCostColor];
                } else {
                    costs[house][color] += costs[house - 1][minCostColor];
                }
            }
        }

        // loop the costs array
        int ans = Integer.MAX_VALUE;
        for (int ele : costs[n - 1]) {
            ans = Math.min(ele, ans);
        }
        return ans;
    }
}

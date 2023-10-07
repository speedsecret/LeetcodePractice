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
        if (costs.length == 0) {
            return 0;
        }
        int k = costs[0].length, n = costs.length;
        for (int house = 1; house < n; house++) {
            // find the minColor and the secondMinColor in the previous row
            int minColor = -1, secondMinColor = -1;
            for (int color = 0; color < k; color++) {
                int cost = costs[house - 1][color];
                // ****** use cost to compare with costs[house - 1][minColor] and costs[house - 1][color]
                if (minColor == -1 || cost < costs[house - 1][minColor]) {
                    secondMinColor = minColor;
                    minColor = color;
                } else if (secondMinColor == -1 || cost < costs[house - 1][secondMinColor]) {
                    secondMinColor = color;
                }
            }

            // calculate the new costs for the current row
            for (int color = 0; color < k; color++) {
                if (color == minColor) {
                    costs[house][color] += costs[house - 1][secondMinColor];
                } else {
                    costs[house][color] += costs[house - 1][minColor];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        // check each cell in the bottom row
        // return min
        for (int c : costs[n - 1]) {
            min = Math.min(min, c);
        }
        return min;
    }
}

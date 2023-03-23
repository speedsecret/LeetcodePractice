package DFS;
/*
Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), get all the possible ways to pay a target number of cents.

Arguments
coins - an array of positive integers representing the different denominations of coins, there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
target - a non-negative integer representing the target number of cents, eg. 99
Assumptions

coins is not null and is not empty, all the numbers in coins are positive
target >= 0
You have infinite number of coins for each of the denominations, you can pick any number of the coins.
Return

a list of ways of combinations of coins to sum up to be target.
each way of combinations is represented by list of integer, the number at each index means the number of coins used for the denomination at corresponding index.
Examples

coins = {2, 1}, target = 4, the return should be

[

  [0, 4],   (4 cents can be conducted by 0 * 2 cents + 4 * 1 cents)

  [1, 2],   (4 cents can be conducted by 1 * 2 cents + 2 * 1 cents)

  [2, 0]    (4 cents can be conducted by 2 * 2 cents + 0 * 1 cents)

]
 */

import java.util.ArrayList;
import java.util.List;

public class CombinationsOfCoins {
    public static List<List<Integer>> getAllCoins(int target, int[] coins) {
        // corner case check
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        DFS(result, sol, 0, target, coins);
        return result;
    }

    private static void DFS(List<List<Integer>> result, List<Integer> sol, int index, int target, int[] coins) {
        // base case
        if (index == coins.length - 1) {
            if (target % coins[index] == 0) {
                sol.add(target / coins[index]);
                result.add(new ArrayList<>(sol));
                sol.remove(sol.size() - 1);
            }
            return;
        }

        // recursive rule
        // calculate what is the max element is for a use
        int max = target / coins[index];
        for (int i = 0; i <= max; i++) {
            sol.add(i);
            DFS(result, sol, index + 1, target - i * coins[index], coins);
            sol.remove(sol.size() - 1);
        }
    }

    public static void main(String[] args) {
        int target = 10;
        int[] coins = {34,31,29,16,2};
        List<List<Integer>> res = getAllCoins(target, coins);
        while (res.size() > 0) {
            List<Integer> cur = res.get(0);
            res.remove(cur);
            cur.forEach((ele) -> System.out.printf("Current element is" +
                    " " + ele + "\n"));
        }
    }
}

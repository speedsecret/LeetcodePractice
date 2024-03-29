/*
815. Bus Routes
https://leetcode.com/problems/bus-routes/description/

You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus 
repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence
1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the 
bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target.
Return -1 if it is not possible.

Example 1:
Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, 
then take the second bus to the bus stop 6.
Example 2:
Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1
 
Constraints:
1 <= routes.length <= 500.
1 <= routes[i].length <= 105
All the values of routes[i] are unique.
sum(routes[i].length) <= 105
0 <= routes[i][j] < 106
0 <= source, target < 106
*/

// Methodology
// Basically, setup an array of List<Integer> each index represent every station
// and each station represent all different routes

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // check the edge case, the srouce is the target
        // so the passenger doesn't even need to onboard the bus
        if (source == target) {
            return 0;
        }

        // Redundancy optimization 
        int maxStation = 0;
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                maxStation = Math.max(maxStation, routes[i][j]);
            }
        }

        List<Integer>[] stationToRoutes = new ArrayList[maxStation + 1];

        // built up the stationToRoutes array
        // this is can also be replaced by the hashMap
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int curStation = routes[i][j];
                if (stationToRoutes[curStation] == null) {
                    stationToRoutes[curStation] = new ArrayList<>();
                }
                stationToRoutes[curStation].add(i);
            }
        }

        // create an output int[] array for storing results.
        int[] result = new int[maxStation + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        queue.offerLast(source);
        while (!queue.isEmpty()) {
            int curStation = queue.pollFirst();
            for (int route : stationToRoutes[curStation]) {
                // if the route never check before
                if (!visited.contains(route)) {
                    for (int neiStation : routes[route]) {
                        // case1: neiStation is curStation
                        if (neiStation == curStation) {
                            continue;
                        }
                        // case2: keep iterating, there isn't any routes available in the current neiStation
                        if (result[neiStation] == 0) {
                            result[neiStation] = result[curStation] + 1;
                            queue.offerLast(neiStation);
                        }
                        // case3: the neiStation equals to target
                        // we found the destination
                        if (neiStation == target) {
                            return result[target];
                        }
                    }
                }
                visited.add(busRoute);
            }
        }
        return -1;
    }
}

/*
Version 2:
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // 来看看有多少个Station
        int maxStation = 0;
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                maxStation = Math.max(maxStation, routes[i][j]);
            }
        }

        // 建立从station 到 路线的一个映射关系
        // 注意这个语法的规范性
        List<Integer>[] stationToRoutes = new ArrayList[maxStation + 1];
        
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                int curStation = routes[i][j];
                if (stationToRoutes[curStation] == null) {
                    stationToRoutes[curStation] = new ArrayList<>();
                }
                stationToRoutes[curStation].add(i);
            }
        }

        // Initilize the BFS queue
        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        int[] result = new int[maxStation + 1];
        queue.offerLast(source);

        while (!queue.isEmpty()) {
            int curStation = queue.pollFirst();
            for (int busRoute : stationToRoutes[curStation]) {
                // check if this busRoute has been visited before
                // if so we just don't need to revisit it
                if (!visited.contains(busRoute)) {
                    for (int station : routes[busRoute]) {
                        if (station == curStation) {
                            continue;
                        }
                        if (result[station] == 0) {
                            result[station] = result[curStation] + 1;
                            queue.offerLast(station);
                        }
                        if (station == target) {
                            return result[target];
                        }
                    }
                }
                visited.add(busRoute);
            }
        }
        return -1;
    }
}
*/

/*
841. Keys and Rooms.java
https://leetcode.com/problems/keys-and-rooms/

There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.

 

Example 1:

Input: rooms = [[1],[2],[3],[]]
Output: true
Explanation: 
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.
Example 2:

Input: rooms = [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
 

Constraints:

n == rooms.length
2 <= n <= 1000
0 <= rooms[i].length <= 1000
1 <= sum(rooms[i].length) <= 3000
0 <= rooms[i][j] < n
All the values of rooms[i] are unique.
*/

class Solution {
    private int visitedNumber;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
       // Just use a DFS to recursively traverse elements in its neighbor
       int n = rooms.size();
       boolean[] visited = new boolean[n];
       DFS(rooms, visited, 0);
       // loop the boolean arr once
       for (int i = 0; i < n; i++) {
           if (visited[i]) {
               visitedNumber++;
           }
       }
       return visitedNumber == n ? true : false;
    }

    private void DFS(List<List<Integer>> rooms, boolean[] visited, int index) {
        if (visited[index]) {
            return;
        }
        // to mark the current one has been visited.
        visited[index] = true;

        // get the related list
        for (int ele : rooms.get(index)) {
            DFS(rooms, visited, ele);
        }
    }
}

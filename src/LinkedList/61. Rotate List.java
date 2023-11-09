/*
61. Rotate List.java
https://leetcode.com/problems/rotate-list/description/

Given the head of a linked list, rotate the list to the right by k places.
Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Example 2:
Input: head = [0,1,2], k = 4
Output: [2,0,1]

Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
Seen this question in a real interview before?
1/4
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // scan twice
        // the first scan, we can find how many nodes it have, let's say n
        // then we use int rotateIndex = k % n;
        // then the second scan, we can record the n - (rotateNumber) index
        // then perform the rotation.
        if (head == null || k == 0) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int length = 0;
        ListNode copyNode = head;
        while (copyNode != null) {
            copyNode = copyNode.next;
            length++;
        }
        int rotateIndex = k % length;
        if (rotateIndex == 0) {
            return head;
        }

        int breakIndex = length - rotateIndex;
        copyNode = head;
        //                     cN
        //
        // 1 -> 2 -> 3 -> 4 -> 5
        while (breakIndex > 1) {
            breakIndex--;
            copyNode = copyNode.next;
        }
        cur.next = copyNode.next;
        cur = cur.next;
        copyNode.next = null;
        while (cur != null && cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        return dummy.next;
    }
}

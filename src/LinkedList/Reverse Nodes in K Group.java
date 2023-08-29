/*
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes
is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.

Eg1:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
Example 2:

Eg2:
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]


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
    public ListNode reverseKGroup(ListNode head, int k) {
        // edge case check
        if (k == 1) {
            return head;
        }
        
        ListNode dummyNode = new ListNode(0, head);
        ListNode prevGroupEnd = dummyNode;
        ListNode current = head;

        // calculate how many nodes we have in the linkedList
        int totalNodes = 0;
        for (ListNode temp = head; temp != null; temp = temp.next) {
            totalNodes++;
        }

        // for each k group, we do reverse process and then connect to the next group from the last group
        for (int j = 0; j < totalNodes / k; j++) {
            ListNode start = current;
            ListNode prev = prevGroupEnd;

            // reverse the k-th group
            for (int i = 0; i < k; i++) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            // connect the reversed k-group to the previous and the next groups
            // step1: lastNode end coonect to the current node head;
            prevGroupEnd.next = prev;
            // setp2: connect to the newNodeHead
            start.next = current;

            // Update pointers for the next reverse iteration
            prevGroupEnd = start;
        }
        return dummyNode.next;
    }
}

/*
Leetcode 92: ReverseLinkedList II
https://leetcode.com/problems/reverse-linked-list-ii/

Given the head of a singly linked list and two integers left and right where left <= right, 
reverse the nodes of the list from position left to position right, and return the reversed list.
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // Use iterative way to solve this problem
        ListNode dummy = new ListNode(0, head);
        ListNode before = dummy;

        // find the before, which is the one node before the starting node that needs to be reversed.
        for (int i = 1; i < left; i++) {
            before = before.next;
        }

        ListNode prev = before;
        ListNode curr = before.next;

        for (int i = left; i <= right; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Step1: tail(for example 1: Node2) pointed to the next element(for example 1:node 5)
        // Step2: have the beforeNode pointed to the final preNode(for example 1:node 4)
        before.next.next = curr;
        before.next = prev;

        return dummy.next;
    }
}


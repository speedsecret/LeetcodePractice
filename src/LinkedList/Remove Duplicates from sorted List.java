/*
Leetcode 83: Remove-duplicates-from-sorted-list
https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/

Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.
eg1:
Input: head = [1,1,2]
Output: [1,2]

eg2:
Input: head = [1,1,2,3,3]
Output: [1,2,3]
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
  // it is obvious that we know the headNode should be reserved
  // then we probably don't need to use the dummyNode as a helper
  // so we can set up two nodes: first is the currNode to copy head node
  // secondly, we need to set up a new node which is just one steps further than the head.
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head;
        ListNode postNode = head.next;
        while (curr != null && postNode != null) {
            if (curr.val != postNode.val) {
                curr.next = postNode;
                curr = curr.next;
            }
            postNode = postNode.next;
        }
        curr.next = postNode;
        return head;
    }
}

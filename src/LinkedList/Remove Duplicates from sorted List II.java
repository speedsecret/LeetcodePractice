/*
Given the head of a sorted linked list, delete all nodes that have duplicate numbers, 
leaving only distinct numbers from the original list. Return the linked list sorted as well.

eg1:
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]

eg2:
Input: head = [1,1,1,2,3]
Output: [2,3]
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
    public ListNode deleteDuplicates(ListNode head) {
        // intuition:
        // step1: use dummyNode
        // setp2: use with other two pointers node, currNode, headNode.
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode currNode = dummy;

        while (head != null) {
            // case1: find the first element is equals to the next elements,
            // keep checking the next elements to find if they are the same
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // skips all duplicates
                currNode.next = head.next;
            }
            // case2: connect the current element if necessary 
            else {
                currNode = currNode.next;
            }
            // always processing forward.
            head = head.next;
        }
        
        return dummy.next;
    }
}

  

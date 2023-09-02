/*
Leetcode 234. Palindrome LinkedList
https://leetcode.com/problems/palindrome-linked-list/

Given the head of a singly linked list, return true if it is a palindrome or false otherwise. 

Example 1:
Input: head = [1,2,2,1]
Output: true

Example 2:
Input: head = [1,2]
Output: false
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

// find the middle
// break and reverse the second half
// use each point in each note, verify if they are the same
class Solution {
    public boolean isPalindrome(ListNode head) {
      if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = findMiddle(head);
        ListNode secondHalf = mid.next;
        mid.next = null;
        ListNode newNode = reverseList(secondHalf);
        return isPalindrom(head, newNode);
    }

    private ListNode findMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }

    private boolean isPalindrom(ListNode one, ListNode two) {
        while (one != null && two != null) {
            if (one.val != two.val) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return true;
    }
}

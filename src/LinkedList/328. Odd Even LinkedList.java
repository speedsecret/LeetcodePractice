/*
328. Odd Even LinkedList
https://leetcode.com/problems/odd-even-linked-list/

Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes
with even indices, and return the reordered list. The first node is considered odd, and the second node is even, and so on.
Note that the relative order inside both the even and odd groups should remain as it was in the input.
You must solve the problem in O(1) extra space complexity and O(n) time complexity.

Example 1:
Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
Example 2:
Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]
Constraints:

The number of nodes in the linked list is in the range [0, 104].
-106 <= Node.val <= 106
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

// Methodology:
// create two dummyNodes, odd and even
// then we can adjust the head linkedList from left to right
// use a int variable counter, once we process one linkedList, then increment one more levels

// thoughts:
// like the question 86 partition list
// https://leetcode.com/problems/partition-list/
// use two dummyNodes and their copies
// the only diff is we might need to use an int variable counter
// to determine whether it is odd or even.

class Solution {
    public ListNode oddEvenList(ListNode head) {
        // To loop head twice as I guess
        // then use a new reference to point to the new head;
        if (head == null || head.next == null) {
            return head;
        }
        ListNode even = new ListNode(0);
        ListNode odd = new ListNode(0);
        ListNode oddHead = odd;
        ListNode evenHead = even;
        int counter = 1;
        while (head != null) {
            if (counter % 2 == 1) {
                odd.next = head;
                odd = odd.next;
            } else {
                even.next = head;
                even = even.next;
            }
            counter++;
            head = head.next;
        }
        even.next = null;
        // evenHead.next is the first adjusted head of linkedList Node
        odd.next = evenHead.next;
        return oddHead.next;
    }
}

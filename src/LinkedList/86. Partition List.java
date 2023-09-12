/*
86. Partition List
https://leetcode.com/problems/partition-list/description/

Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example 1:
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:
Input: head = [2,1], x = 2
Output: [1,2]

Constraints:

The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200
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
    public ListNode partition(ListNode head, int x) {
       // the dummyNode intuition is correct, but just don't have a clear mindset on how to implement it
       // the educational answer provide me with the solution
       // we need to craete 4 dummyNode:
       // smallerNode, smallerNodeHead
       // largerOrEqualNode, largerOrEqualNodeHead;
       ListNode smallerNode = new ListNode(0);
       ListNode smallerNodeHead = smallerNode;
       ListNode largerOrEqualNode = new ListNode(0);
       ListNode largerOrEqualNodeHead = largerOrEqualNode;

       while (head != null) {
           // case 1: if the current node is smaller than x
           // then we chain the current one with the smallerNode part
           if (head.val < x) {
               smallerNode.next = new ListNode(head.val);
               smallerNode = smallerNode.next;
           } 
           // case 2: if the current Node is larger or equals to x
           // then we chain the current one with the larger one part
           else {
               largerOrEqualNode.next = new ListNode(head.val);
               largerOrEqualNode = largerOrEqualNode.next;
           }
           // move forward one steps no matter under what circumstances.
           head = head.next;
       }

       // to make sure there is nothing behind the largerOrEqualNode list
       largerOrEqualNode.next = null;
       
       // connect the smaller one to the larger one.
       smallerNode.next = largerOrEqualNodeHead.next;
       return smallerNodeHead.next;
    }
}

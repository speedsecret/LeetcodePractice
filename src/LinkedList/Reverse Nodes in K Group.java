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

        // Method 1:
        // step1: create a list, and add every K group listNode head into the list
        // step2: reverse each list seperately, and taking care of the last list(as the last list may not have enough elements, so we just continue so to leave it as it is)
        // step3: connect with elements together then return the first head of the list
        List<ListNode> groupHeads = new ArrayList<>();
        int flag = 1;

        // step1:
        while (head != null) {
            groupHeads.add(head);

            // for the kth group, we need to mark the flag == 0
            for (int i = 0; i < k - 1; i++) {
                if (head.next != null) {
                    head = head.next;
                } else {
                    flag = 0;
                }
            }

            ListNode next = head.next;
            head.next = null;
            head = next;
        }

        // step2: reverse k group of list
        for (int i = 0; i < groupHeads.size(); i++) {
            // for the kth group
            // case 1: if size of kth group is equals to k, then we would also need to reverse it and reset the head
            // case 2: if size of kth group is smaller then k, then we don't need to reverse the element, neither should we need to reset it.
            if (i != groupHeads.size() - 1 || flag != 0) {
                groupHeads.set(i, reverseLinkedList(groupHeads.get(i)));
            }
        }

        // step3: connect the reversed group
        for (int i = 0; i < groupHeads.size() - 1; i++) {
            ListNode node = groupHeads.get(i);
            while (node.next != null) {
                node = node.next;
            }
            // we only need to connect the k - 1 times.
            node.next = groupHeads.get(i + 1);
        }

        return groupHeads.get(0);
    }

    // classcial way of reversing the linkedList
    private ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    // Method 2: Not using a List to store all heads of the linkedList Nodes
    public ListNode reverseKGroup_v2(ListNode head, int k) {
        if(head == null||head.next == null) 
            return head;
        ListNode temp = head;
        int length = 0;
        while(temp != null)
        {
            length++;
            temp=temp.next;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur;
        ListNode nex;
        while(length >= k) {
            cur = pre.next;
            nex = cur.next;
            for(int i=1;i<k;i++) {
                cur.next = nex.next;
                nex.next = pre.next;
                pre.next = nex;
                nex = cur.next;
            }
            pre = cur;
            length = length - k;
        }
        return dummyHead.next;
    }
}

package LinkedList;

/*
If linkedList has a circle, then fast would catch up slow instead.
 */

import static LinkedList.MiddleNodeLinkedList.getTestListNode;

public class CheckIfLinkedListHasCircle {
    public static boolean linkedListHasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        // use two pointers too.
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            // case 1: No cycle
            // it would jump out of the while loop for not be able to fulfill the first two condition check
            // case 2: Cycle found
            // it would fail at the slow != fast condition check
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = getTestListNode();
        ListNode hasCycleHead = getTestListNode();
        hasCycleHead.next.next.next.next = hasCycleHead.next.next;
        System.out.printf("This linkedList has a cycle = " + linkedListHasCycle(head) + "\n");
        System.out.printf("This linkedList has a cycle = " + linkedListHasCycle(hasCycleHead));
    }
}

package LinkedList;

/*
Goal:
find the middle node of the linked list
L = 1 -> 2 -> null, return 1
L = 1 -> 2 -> 3 -> 4 -> null, return 2
 */

public class MiddleNodeLinkedList {
    public static ListNode findMiddleNode(ListNode head) {
        // use two pointer
        // slowNode and fastNode
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        /*
                      s
                                 f
        L = 1 -> 2 -> 3 -> 4 -> null, return 2
         */
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = getTestListNode();
        System.out.printf("Current middle node is:" + findMiddleNode(head).value);
    }

    public static final ListNode getTestListNode() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        return head;
    }
}

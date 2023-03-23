package LinkedList;

/*
Merge two sorted lists into one large sorted list.

Examples

L1 =
                         Node1
        1 -> 4 -> 6 -> null,

L2 =
                  Node2
        2 -> 5 -> null,

Dummy     newNode
newNode -> 1 -> 2 -> 4 -> 5


Use a dummy node, put two sorted list together, move whoever small to the new listNode

merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null



 */

public class MergeTwoSortedLinkedList {
    public static ListNode merge(ListNode one, ListNode two) {
        // check corner case
        // understand we may need a while loop
        // pause condition is either one of two ListNodes reached to null
        if (one == null) {
            return two;
        }
        if (two == null) {
            return one;
        }
        ListNode dummy = new ListNode(0);
        ListNode newNode = dummy;
        while (one != null && two != null) {
            if (one.value < two.value) {
                newNode.next = one;
                one = one.next;
            } else {
                newNode.next = two;
                two = two.next;
            }
            newNode = newNode.next;
        }
        if (one == null) {
            newNode.next = two;
        } else if (two == null) {
            newNode.next = one;
        }
        return dummy.next;
    }

    public static void main(String[] arg) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(6);
        ListNode head2 = new ListNode(3);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(7);
        head2.next.next.next = new ListNode(10);
        ListNode newNode = merge(head, head2);
        while (newNode != null) {
            System.out.println(newNode.value + "\n");
            newNode = newNode.next;
        }
    }
}

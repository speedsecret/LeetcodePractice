package LinkedList;

/*
Reverse a singly-linked list iteratively.

Examples

L = null, return null
L = 1 -> null, return 1 -> null
L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null

    head
prv
    1 --> 2 --> 3 --> null
          n

               p
               h
    <--1<--3<--4 null
                n
 */
public class ReverseLinkedList {
    public static ListNode reverseLinkedListIteratively(ListNode head) {
        // the solution is to use a prev node
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /*
          h
    1 --> 2 --> 3 <-- 4 --> null
          n
                      newHead


     */

    public static ListNode reverseLinkedListRecursively(ListNode head) {
        //base case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseLinkedListRecursively(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] arg) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode newNode = reverseLinkedListIteratively(head);
        System.out.println("Start print the linkedList iteratively");
        while (newNode != null) {
            System.out.println(newNode.value + "\n");
            newNode = newNode.next;
        }
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        System.out.println("Start print the linkedList recursively");
        ListNode newHead = reverseLinkedListRecursively(head1);
        while (newHead != null) {
            System.out.println(newHead.value + "\n");
            newHead = newHead.next;
        }
    }
}

class ListNode {
    public ListNode next;
    public int value;
    public ListNode(int value) {
        this.value = value;
        next = null;
    }
}





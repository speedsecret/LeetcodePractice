package LinkedList;

/*
 Being Given two linked Lists, we could add them up together to generate a new number
 */

import static LinkedList.MiddleNodeLinkedList.getTestListNode;
import static LinkedList.ReverseLinkedList.reverseLinkedListRecursively;

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode one, ListNode two) {
        // Never know what the newListNode looks like
        // use a dummyNode
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int val = 0;
        while (one != null || two != null || val != 0) {
            if (one != null) {
                val += one.value;
                one = one.next;
            }
            if (two != null) {
                val += two.value;
                two = two.next;
            }
            cur.next = new ListNode(val % 10);
            val /= 10;
            // don't forget this one as it is critical!
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode one = getTestListNode();
        ListNode two = reverseLinkedListRecursively(one);
        System.out.printf("The new number is: " + addTwoNumbers(one, two).value);
    }
}

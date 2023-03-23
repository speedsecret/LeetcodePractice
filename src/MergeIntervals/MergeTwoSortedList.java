package MergeIntervals;

/*
Merge two sorted lists into one large sorted list.

Examples

L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
L1 = null, L2 = null, merge L1 and L2 to null

Use a dummy node
 */

public class MergeTwoSortedList {
    public static ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (one != null && two != null) {
            if (one.value <= two.value) {
                cur.next = one;
                one = one.next;
            }
            else {
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;
        }
        if (one != null) {
            cur.next = one;
        }
        else if (two != null) {
            cur.next = two;
        }
        return dummy.next;
    }

    static class ListNode{
        int value;
        ListNode next;
        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        a.next = new ListNode(3);
        ListNode b = new ListNode(8);
        b.next = new ListNode(10);

    }
}



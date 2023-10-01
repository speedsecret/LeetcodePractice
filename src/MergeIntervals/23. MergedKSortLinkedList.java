/*
23. MergedKSortLinkedList.java
https://leetcode.com/problems/merge-k-sorted-lists/description/

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 
Constraints:

k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.
*/
// Methodology
// Use a priorityQueue and put every first ListNode of the list into the queue
// As long as the priorityQueue is not empty
// we would keep constructing the linkedList
    
public class MergedKSortLinkedList {
    // similarly to the MergeKSortedArray
    // Step1: We still using a minHeap, with a dummy node
    // Step2: instead of creating a new Class, we can take the advantage of linkedList
    // Step3: put every head of the list into the minHeap, create a size k minHeap
    // Step4: iteratively adding the smallest element into the tail of the new sorted list
    public static ListNode mergeKSortLinkedList(List<ListNode> listOfLists) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (int i = 0; i < listOfLists.size(); i++) {
            ListNode curNode = listOfLists.get(i);
            minHeap.offer(curNode);
        }
        // we can start creating the new List
        while (!minHeap.isEmpty()) {
            ListNode curNode = minHeap.poll();
            // check if we reached the end of the list
            cur.next = curNode;
            if (cur.next.next != null) {
                minHeap.offer(curNode.next);
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    // T: O(Nlogk)
    // S: O(n + k) O(n) means creating a new linked list costs O(n) space.

    public static void main(String[] args) {
        List<ListNode> res = new ArrayList<>();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(0);
        ListNode listNode3 = new ListNode(10);
        listNode2.next = new ListNode(100);
        listNode3.next = new ListNode(11);
        res = Arrays.asList(listNode1, listNode2, listNode3);
        ListNode outputList = mergeKSortLinkedList(res);

        while (outputList.next != null) {
            System.out.println(outputList.val + "\n");
            outputList = outputList.next;
        }
        System.out.println(outputList.val);
    }
}

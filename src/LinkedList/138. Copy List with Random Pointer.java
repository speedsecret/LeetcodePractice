/*
Leetcode 138: Copy List with Random Pointer
https://leetcode.com/problems/copy-list-with-random-pointer/description/ 

A linked list of length n is given such that each node contains an additional random pointer, 
which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, 
then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as 
a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, 
or null if it does not point to any node. Your code will only be given the head of the original linked list.

Example 1:
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:
Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
Example 3:
Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        // three passes
        // firstly, construct a long list on the top of the current linkedlist, which double the previous size
        // secondly, construct the new random list;
        // third(finally), split the long list to two lists, one is original list, the second is the new list
        if (head == null) {
            return head;
        }

        // the first pass
        // the goal is to finish the preparation for the new linkedList construction
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.val);
            newNode.next = current.next;
            current.next = newNode;
            // so this steps skip two nodes at a time.
            current = newNode.next;
        }

        // the second pass
        // the goal is to complete the random pointer for the copyed new list.
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        // the third pass
        // the goal is to split the current linked list into one list and then return the copyed one.
        current = head;
        Node newHead = current.next;
        Node resultHead = newHead;
        while (current != null) {
            current.next = current.next.next;
            // need to consider the status when the newHead is the last one element in the pre-split list
            if (newHead.next != null) {
                newHead.next = newHead.next.next;
                newHead = newHead.next;
            }
            current = current.next;
        }
        return resultHead;
    }

    // Dec 25th, 2023
    /*
    class Solution {
    public Node copyRandomList(Node head) {
        // !edge case check!
        if (head == null) {
            return head;
        }
        // we need to create a new set of nodes
        // which could started with the newNode

        // then we can basically copy whatever the original nodes could have
        // then split the linkedList into two, re-chain them and then to concatenate them
        
        // then to return the copied node(linkedList)
        
        // step1: create the copyNode, which copy the value of the orginal nodes, leave the random nodes aside.
        Node copyNode = head;
        while (copyNode != null) {
            Node newNode = new Node(copyNode.val);
            newNode.next = copyNode.next;
            copyNode.next = newNode;
            copyNode = newNode.next;
        }

        // step2: create and copy the relationship of the random nodes
        copyNode = head;
        while (copyNode != null) {
            if (copyNode.random != null) {
                copyNode.next.random = copyNode.random.next;
            }
            copyNode = copyNode.next.next;
        }

        // step3: break the adjusted chain nodes
        copyNode = head;
        Node newNode = copyNode.next;
        Node result = newNode;
        while (copyNode != null) {
            copyNode.next = copyNode.next.next;
            if (newNode.next != null) {
                newNode.next = newNode.next.next;
                newNode = newNode.next;
            }
            copyNode = copyNode.next;
        }
        return result;
     }
    }
    */

    // Method1: Intuition:
    // Use the HashMap to store Node, creating nodes while looping the LinkedList head
    // we will create a clone of linked List Nodes.
    /*
    private Node copyRandomListByHashMap(Node node) {
        Map<Node, Node> nodeMap = new HashMap<>();
        Node current = head;

        // first pass to create the relationship
        while (current != null) {
            nodeMap.put(current, new Node(current.val));
            current = current.next;
        } 

        // second pass so we can clone the next pointer and random pointer
        current = head;
        while (current != null) {
            Node newNode = nodeMap.get(current);
            newNode.next = nodeMap.get(current.next);
            newNode.random = nodeMap.get(current.random);
            current = current.next;
        }

        return nodeMap.get(head);
    }
    */
}

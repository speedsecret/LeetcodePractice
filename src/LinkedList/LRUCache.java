package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    // use a double linked list, so we can make sure O(1) time complexity
    // with a hashMap so we can setup an 1--> ListNode relationship
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    class DLinkedNode {
        DLinkedNode prev;
        DLinkedNode next;
        int key;
        int value;
    }

    // constructor
    public LRUCache (int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = null;
        tail.prev = null;
    }

    // get() API
    // get Node from cache then implement a non-null check
    public int get(int key) {
        DLinkedNode curNode = cache.get(key);
        // there is no instance exist in the cache yet
        if (curNode == null) {
            return -1;
        }
        // once the node existed, we should reset the node to the front
        // of the doublyLinkedList
        moveToHead(curNode);
        return curNode.value;
    }

    // put() API
    public void put(int key, int value) {
        DLinkedNode curNode = cache.get(key);
        if (curNode == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            size++;
            addNode(newNode);
            cache.put(key, newNode);
            // we need to pop element if capacity limit has been breached
            if (size > capacity) {
                DLinkedNode popNode = popTail();
                removeNode(popNode);
                cache.remove(popNode.key);
                size--;
            }
        } else {
            curNode.value = value;
            cache.put(key, curNode);
            moveToHead(curNode);
        }
    }

    // Firstly should consider the node been attached to its prev and next
    private void addNode(DLinkedNode node) {
        // add the node in the front of the Doubly LinkedList
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private void removeNode(DLinkedNode node) {
        /*
             n

        1        8 - 10
          -------->
        <----------
        */
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private DLinkedNode popTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }


    public static void main(String[] args) {
        LRUCache currentCache = new LRUCache(3);
        currentCache.put(3, 5);
        currentCache.put(2, 10);
        currentCache.put(15, 11);
        currentCache.put(1, 9);
        System.out.println("Current element is " + currentCache.get(2));
        System.out.println("Current element is " + currentCache.get(3));
        System.out.println("Current element is " + currentCache.get(15));
    }
}

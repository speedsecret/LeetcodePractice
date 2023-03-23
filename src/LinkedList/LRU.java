package LinkedList;

// In this API design, we would like to implement put() and get()
// The key to solve this issue is to use a doubleLinkedList to store same keys.
// Meanwhile, we would use HashMap<Integer, DoubleLinkedListNode> to store all elements.

import sun.misc.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    private Map<Integer, DlinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DlinkedNode head, tail;

    class DlinkedNode {
        DlinkedNode prev;
        DlinkedNode next;
        int key;
        int value;
    }

    public LRU(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        // create presodu head and tail
        head = new DlinkedNode();
        tail = new DlinkedNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DlinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        // take a look to see if they already exist
        DlinkedNode node = cache.get(key);
        if (node == null) {
            DlinkedNode newNode = new DlinkedNode();

            cache.put(key, newNode);
            addNode(newNode);

            size++;
            // check capacity
            if (size > capacity) {
                //pop the tail
                DlinkedNode tail = popTail();
                cache.remove(tail.key);
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    public void moveToHead(DlinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private void removeNode(DlinkedNode node) {
        DlinkedNode prev = node.prev;
        DlinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    public DlinkedNode popTail() {
        DlinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    public void addNode(DlinkedNode node) {
        /*Always add node right after head*/
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }
}

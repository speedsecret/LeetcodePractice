// No.146 LRU Cache
// https://leetcode.com/problems/lru-cache/

// Summary:
// 1.Doubled-linked list and HashMap are critical 
// 2.Adding node into the list or remove node from the list, also need to add node to hashMap(cache), and remove it from cache
// 3.Make sure the constructor written correctly
// In Java, when defining a class, the constructor should be written as a method with the same name as the class. The correct way to define a constructor for the Node class is to use the public keyword followed by the class name, and then specify any parameters needed. In this case, the constructor should take key and value as parameters and set them accordingly.

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {

    private Map<Integer, Node> cache = new HashMap<>();
    int size;
    int capacity;
    Node head, tail;


    public LRUCache(int capacity) {
        this.capacity = capacity;

        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node curNode = cache.get(key);
        if (curNode == null) {
            return -1;
        }
        moveToHead(curNode);
        return curNode.value;
    }
    
    public void put(int key, int value) {
        Node curNode = cache.get(key);
        if (curNode == null) {
            Node newNode = new Node(key, value);

            cache.put(key, newNode);
            addNode(newNode);
            size++;

            if (size > capacity) {
                Node popNode = popTail();
                size--;
                cache.remove(popNode.key);
            }
        } else { 
            curNode.value = value;
            cache.put(key, curNode);
            moveToHead(curNode);
        }
    }

    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private Node popTail() {
        Node popNode = tail.prev;
        removeNode(popNode);
        return popNode;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

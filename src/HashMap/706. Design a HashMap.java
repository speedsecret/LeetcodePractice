/*
706. Design a hashMap
https://leetcode.com/problems/design-hashmap/

Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 

Example 1:

Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
*/

// Methodology:
// We will use a ListNode class
// There are int key, value and ListNode available in this class
// create a listNode[] array, with size(~10^4)
// critical function is to define the hash(key) ---> (int)((long)key * multi % size);

class ListNode{
    int key;
    int value;
    ListNode next;
    // the constructor define the relationship between current node with next node
    public ListNode(int key, int value, ListNode next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}

class MyHashMap {
    static int size = 19997;
    static int multi = 12582917;
    ListNode[] data;

    public MyHashMap() {
        // determine the size of the hashMap, which means
        // the ListNode array can contain size length of listNodes.
        data = new ListNode[size];
    }

    public void put(int key, int value) {
        remove(key);
        int h = hash(key);
        // always creating a new ListNode
        // and chaining it to the head of the ListNode
        ListNode newNode = new ListNode(key, value, data[h]);
        data[h] = newNode;
    }
    
    public int get(int key) {
        int h = hash(key);
        ListNode node = data[h];
        while (node != null) {
            if (node.key == key) {
                return node.value;
            }
            node = node.next;
        }
        // can not find the node
        return -1;
    }
    
    public void remove(int key) {
        int h = hash(key);
        ListNode node = data[h];
        if (node == null) {
            return;
        }
        if (node.key == key) {
            // instead of keep the node in data[h]
            // we would need to update the date[h] by
            // using node.next;
            data[h] = node.next;
        } else {
            while (node.next != null) {
                if (node.next.key == key) {
                    node.next = node.next.next;
                    return;
                }
                node = node.next;
            }
        }
    }

    private int hash(int key) {
        return (int)((long) key * multi % size);
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */




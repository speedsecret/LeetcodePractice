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

class MyHashMap {
    // Got to resolve the conflicts by using an ListNode array
    // Then to calculate the hash by leveraging a hash function
    class ListNode {
        int key;
        int value;
        ListNode next;
        public ListNode(int key, int value, ListNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // Just the prime number which is reasonable
    static final int size = 19997;
    static final int mult = 12582917;
    ListNode[] data;

    public MyHashMap() {
        this.data = new ListNode[size];
    }
    
    public void put(int key, int value) {
        remove(key);
        int h = hash(key);
        ListNode node = new ListNode(key, value, data[h]);
        data[h] = node;
    }
    
    public int get(int key) {
        int h = hash(key);
        ListNode node = data[h];
        for (; node != null; node = node.next) {
            if (node.key == key) {
                return node.value;
            }
        }
        return -1;
    }
    
    public void remove(int key) {
        int h = hash(key);
        ListNode node = data[h];
        if (node == null) {
            return;
        }
        if (node.key == key) {
            data[h] = node.next;
        } else {
            // if not able to locate the current element
            // check the next element.
            for (; node.next != null; node = node.next) {
                if (node.next.key == key) {
                    node.next = node.next.next;
                    return;
                }
            }
        }
    }

    private int hash(int key) {
        return (int)((long)key * mult % size);
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */




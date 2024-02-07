/*
Leetcode 716.Max Stack
Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.

Implement the MaxStack class:

MaxStack() Initializes the stack object.
void push(int x) Pushes element x onto the stack.
int pop() Removes the element on top of the stack and returns it.
int top() Gets the element on the top of the stack without removing it.
int peekMax() Retrieves the maximum element in the stack without removing it.
int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.

 

Example 1:

Input
["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
[[], [5], [1], [5], [], [], [], [], [], []]
Output
[null, null, null, null, 5, 5, 1, 5, 1, 5]

Explanation
MaxStack stk = new MaxStack();
stk.push(5);   // [5] the top of the stack and the maximum number is 5.
stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
stk.top();     // return 5, [5, 1, 5] the stack did not change.
stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
stk.top();     // return 1, [5, 1] the stack did not change.
stk.peekMax(); // return 5, [5, 1] the stack did not change.
stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
stk.top();     // return 5, [5] the stack did not change.
*/

// Mindset:
// Key Concepts: TreeMap, DoublyLinkedListNode
// So we need to use a TreeMap to leverage the natural ordering attribute, represent as a maxStack.
// Besides, instead of using integer to store the element
// Introducing a new class which is called DoublyLinkedListNode
// which store the value and its ordering status, represent as a stack.

class DoublyLinkedListNode {
    int value;
    DoublyLinkedListNode prev;
    DoublyLinkedListNode next;
    public DoublyLinkedListNode(int value) {
        this.value = value;
    }
}

class MaxStack {
    TreeMap<Integer, List<DoublyLinkedListNode>> treeMap = new TreeMap<>();
    DoublyLinkedListNode head;
    DoublyLinkedListNode tail;

    public MaxStack() {
        head = new DoublyLinkedListNode(0);
        tail = new DoublyLinkedListNode(0);
        head.next = tail;
        tail.prev = head;
    }
    
    public void push(int x) {
        DoublyLinkedListNode node = new DoublyLinkedListNode(x);
        addNode(node);

        treeMap.computeIfAbsent(x, key -> new ArrayList<>()).add(node);
        // if (!treeMap.containsKey(x)) {
        //     treeMap.put(x, new ArrayList<>());
        // }
        // treeMap.get(x).add(node);
    }
    
    public int pop() {
        int popKey = head.next.value;
        DoublyLinkedListNode node = head.next;

        // removeNode from the DoublyLinkedListNode
        head.next.next.prev = head;
        head.next = node.next;

        // remove node in TreeMap
        List<DoublyLinkedListNode> list = treeMap.get(popKey);
        list.remove(list.size() - 1);
        if (list.isEmpty()) {
            treeMap.remove(popKey);
        }
        return popKey;
    }
    
    public int top() {
        return head.next.value;
    }
    
    public int peekMax() {
        return treeMap.lastKey();
    }
    
    public int popMax() {
        int max = treeMap.lastKey();
        List<DoublyLinkedListNode> list = treeMap.get(max);
        DoublyLinkedListNode node = list.remove(list.size() - 1);
        if (list.isEmpty()) {
            treeMap.remove(max);
        }

        // remove the node
        node.next.prev = node.prev;
        node.prev.next = node.next;
        return max;
    }

    private void addNode(DoublyLinkedListNode node) {
        // add the node just after the head
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

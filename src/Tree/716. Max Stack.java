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

class MaxStack {
    DoubleLinkedList head;
    DoubleLinkedList tail;
    TreeMap<Integer, List<DoubleLinkedList>> treeMap;

    public MaxStack() {
        head = new DoubleLinkedList(0);
        tail = new DoubleLinkedList(0);
        tail.prev = head;
        head.next = tail;
        treeMap = new TreeMap<>();
    }

    public void push(int x) {
        DoubleLinkedList newNode = new DoubleLinkedList(x);
        
        addNode(newNode);

        // check if x has already showed up before
        if (!treeMap.containsKey(x)) {
            treeMap.put(x, new ArrayList<DoubleLinkedList>());
        }
        // add the element into the end of the linkedList within the treeMap
        treeMap.get(x).add(newNode);
    }

    public int pop() {
        int res = head.next.value;
        List<DoubleLinkedList> targetList = treeMap.get(res);
        // remove the element in the TreeMap
        DoubleLinkedList node = targetList.remove(targetList.size() - 1);
        if (targetList.isEmpty()) {
            treeMap.remove(res);
        }

        // remove the node from the head
        head.next.next.prev = head;
        head.next = head.next.next;
        return res;
    }

    public int top() {
        int res = head.next.value;
        return res;
    }

    public int peekMax() {
        return treeMap.lastKey();
    }

    public int popMax() {
        int max = treeMap.lastKey();
        List<DoubleLinkedList> targetList = treeMap.get(max);
        // remove the element in the treeMap
        DoubleLinkedList node = targetList.remove(targetList.size() - 1);
        if (targetList.isEmpty()) {
            treeMap.remove(max);
        }

        // remove the node from where the popMax node located
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return max;
    }

    // addNode process
    private void addNode(DoubleLinkedList newNode) {
        head.next.prev = newNode;
        newNode.prev = head;
        newNode.next = head.next;
        head.next = newNode;
    }
}

class DoubleLinkedList {
    // there doesn't exist a key
    int value;
    DoubleLinkedList next;
    DoubleLinkedList prev;
    public DoubleLinkedList(int value) {
        this.value = value;
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

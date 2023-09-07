/*
155. Min Stack
https://leetcode.com/problems/min-stack/

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:
MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

Example 1:
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation: 

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2 

Constraints:
-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.
*/
class MinStack {
    // Don't need to push Bigger elements than the top one in the minStack, instead push the smallest again.
    // use two stack --> constructed by ArrayDeque
    // pay attention to if the stack would overflow
    // minStack
    // curStack
    Deque<Integer> minStack;
    Deque<Integer> curStack;

    public MinStack() {
        minStack = new ArrayDeque<>();
        curStack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        curStack.offerFirst(val);
        if (minStack.isEmpty() || minStack.peekFirst() > val) {
            minStack.offerFirst(val);
        } else {
            minStack.offerFirst(minStack.peekFirst());
        }
    }
    
    // Given there is a constraint here:
    // Methods pop, top and getMin operations will always be called on non-empty stacks.
    // so we don't need to have empty() check in these API calls.
    public void pop() {
        // if (curStack.isEmpty()) {
        //     return;
        // }
        curStack.pollFirst();
        minStack.pollFirst();
    }
    
    public int top() {
        // if (curStack.isEmpty()) {
        //     return 0;
        // }
        return curStack.peekFirst();
    }
    
    public int getMin() {
        return minStack.peekFirst(); 
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

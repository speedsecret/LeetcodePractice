package Heap;

/*
Implement the process of building a minHeap
the basic part is using an array with an initiated size

there are several core main public functions
1.poll()
2.add()
3.given a series of data to build up a minHeap

As the array is 0 based
if the index of parent is i; then the left subTree index is 2*i + 1 the right subTree index is 2*i + 2;
if the index of kid is i; then the parent index is (i - 1) / 2;

Summary:
0. use a dummy index == 0, assign the Integer.MIN_VALUE
1. heapify from size/2
2. adding elements and right after check all the way up
3. enabling exception thrown out or just sprint out current data structure is full
 */

import java.util.LinkedList;

public class MinHeap<Item extends Comparable> {
    // create a class
    // 1.Initialization
    private int[] data;
    private int size;
    private int maxSize;

    public MinHeap() {
        this(16);
    }

    public MinHeap(int maxSize) {
        // store as a whistler hence we don't need to check index > 0 everytime.
        // as there shouldn't be any value smaller than Integer.MIN_VALUE
        // hence the real index can start from 1
        data = new int[maxSize + 1];
        data[0] = Integer.MIN_VALUE;
        this.size = 0;
        this.maxSize = maxSize;
    }

    // 2.Insert an element
    public boolean add(int value) {
        // size up
        int index = size + 1;
        size++;
        // check if already full
        if (size == maxSize) {
            System.out.printf("The current heap is full, we were not able to process this request");
            return false;
        }
        // else, we would add this element to right most child point
        // then do a recursive swap
        while (value < data[index / 2]) {
            // swap
            data[index] = data[index / 2];
            index = index / 2;
        }
        // when everything settle in the last step, we can fix this value in the tree
        data[index] = value;
        return true;
    }

    // 3. Delete Min Value
    public int poll() {
        // check size
        // got to assume there shouldn't have any target == -1;
        if (size == 0) {
            System.out.printf("This is an empty heap!");
            return Integer.MIN_VALUE;
        }
        if (size == 1) {
            size = 0;
            return data[1];
        }
        int delValue = data[1];
        int lastValue = data[size];
        size--;
        data[1] = lastValue;
        shiftDown(1, data[1]);
//        for (int child = index * 2; child <= size; child = child * 2) {
//            if (child < size && data[child] >= data[child + 1]) {
//                child = child + 1;
//            }
//            if (lastValue < data[child]) {
//                break;
//            } else {
//                data[index] = data[child];
//                index = child;
//            }
//        }
        return delValue;
    }

    // 4.Build up a minHeap
    // 4.1 Build up a new contructor
    public MinHeap(int[] initData) {
        if (initData == null || initData.length < 0) {
            System.out.println("The input data is not correct!");
            return;
        }
        data = new int[initData.length + 1];
        data[0] = Integer.MIN_VALUE;
        // deep copy
        for (int i = 0; i < initData.length; i++) {
            data[i + 1] = initData[i];
        }
        this.maxSize = initData.length;
        this.size = initData.length;
        heapify();
    }

    // 4.2 buildMinHeap() --> Heapify()
    private void heapify() {
        if (size == 1) {
            return;
        }
        // start from the last non-leaf point
        for (int parent = size / 2; parent > 0; parent--) {
            int tmp = data[parent];
            shiftDown(parent, tmp);
        }
    }

    // 4.3 findMinHead(int index)
    private void shiftDown(int root, int tmp) {
        int index = root;
        for (int child = index * 2; child < size; child = child * 2) {
            // compare left child and with right child
            if (child < size && data[child] >= data[child + 1]) {
                child = child + 1;
            }
            if (tmp < data[child]) {
                break;
            } else {
                // move the down element up
                data[index] = data[child];
                index = child;
            }
        }
        data[index] = tmp;
    }

    // other useful public api
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return data[1];
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>(16);

        System.out.println("Current min element is: " + minHeap.peek());
        minHeap.add(9);
        minHeap.add(8);
        minHeap.add(99);
        minHeap.add(1);
        minHeap.add(39);
        System.out.println("Current min element is: " + minHeap.peek());
        System.out.println("Current min element is: " + minHeap.poll());
        System.out.println("Current min element is: " + minHeap.poll());
        minHeap.add(19);
        minHeap.add(29);
        System.out.println("Current min element is: " + minHeap.poll());
        System.out.println("Current min element is: " + minHeap.poll());
        System.out.println("Current min element is: " + minHeap.getSize());
        System.out.printf("Expected number should be: -2147483648, 1, 1, 8, 9, 19, 3" + "\n");
    }
}

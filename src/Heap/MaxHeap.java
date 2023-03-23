package Heap;

public class MaxHeap<Item extends Comparable> {
    // There are three main function we need to implement
    // 1. add()
    // 2. poll()
    // 3. maxHeap()

    // started from the initilization and move forward with TODO in details.
    private int[] data; //using an array to store the heap
    private int size;
    private int maxSize;

    public MaxHeap () {
        this(16);
    }

    public MaxHeap(int maxSize) {
        data = new int[maxSize + 1];
        data[0] = Integer.MAX_VALUE;
        this.size = 0;
        this.maxSize = maxSize;
    }

    public boolean add(int ele) {
        //TODO
        // check size as the first thing
        int index = size + 1;
        size++;
        if (size == maxSize) {
            System.out.printf("The current heap is full, unable to add val");
            return false;
        }
        // add at the bottom right-most
        while (ele > data[index / 2]) {
            // swap
            data[index] = data[index / 2];
            index = index / 2;
        }
        data[index] = ele;
        return true;
    }

    public int poll() {
        //TODO
        if (size == 0) {
            System.out.printf("This is an empty Heap!");
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
        // call shiftDown method so we could make sure
        return delValue;
    }

    public void maxHeap(int[] initData) {
        //TODO
        if (initData == null || initData.length == 0) {
            System.out.printf("The input data is not correct!");
            return;
        }
        // created a new array which satisifed the length of initData array
        data = new int[initData.length + 1];
        data[0] = Integer.MAX_VALUE;
        for (int i = 0; i < initData.length; i++) {
            data[i + 1] = initData[i];
        }
        // define the size and the maxSize
        this.maxSize = initData.length;
        this.size = initData.length;
        heapify();
    }

    private void heapify() {
        // edge case check
        if (size == 1) {
            return;
        }
        // start from the last non-leaf treeNode
        for (int parent = size / 2; parent > 0; parent--) {
            int temp = data[parent];
            shiftDown(parent, temp);
        }
    }

    private void shiftDown(int root, int val) {
        int index = root;
        for (int child = index * 2; child < size; child = child * 2) {
            // compare left child and with right child
            if (child < size && data[child] < data[child + 1]) {
                child = child + 1;
            }
            if (val > data[child]) {
                break;
            } else {
                data[index] = data[child];
                index = child;
            }
        }
        data[index] = val;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        //TODO
        MaxHeap<Integer> maxHeap = new MaxHeap<>(16);
        maxHeap.add(99);
        maxHeap.add(1);
        System.out.println("Current min element is: " + maxHeap.poll());
        maxHeap.add(9);
        maxHeap.add(8);
        maxHeap.add(39);
        System.out.println("Current min element is: " + maxHeap.poll());
        maxHeap.add(19);
        maxHeap.add(29);
        System.out.println("Current min element is: " + maxHeap.poll());
        System.out.println("Current min element is: " + maxHeap.poll());
        System.out.println("Current min element is: " + maxHeap.poll());
        System.out.printf("Current size is " + maxHeap.size() + "\n");
        System.out.printf("Expected number should be: 99, 39, 29, 19, 9, 2");
    }
}

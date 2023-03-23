package Iterator;

/*
Design an iterator that supports the peek operation on an existing iterator in addition to the
hasNext()
next()
operation

Implement the peekingIterator class:
1.PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator
2.int next() returns the next elements in the array and move the iterator pointer to the next ele
3.boolean hasNext() returns true if there are still elements in the array;
4.int peek() returns the next element in the array without moving the pointer.
 */

import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {
    private Integer peekedEle;
    private Iterator<Integer> iterator;
    private boolean hasPeeked;
    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    // implement function peek()
    public Integer peek() {
        // case 1 has peeked before?
        // case1.1 --> return the peekedEle
        // case1.2 --> get the next() and update the  element and boolean hasPeeked
        if (!hasPeeked) {
            peekedEle = iterator.next();
            hasPeeked = true;
        }
        return peekedEle;
    }

    @Override
    public boolean hasNext() {
        // has peeked || iterator hasNext()?
        return hasPeeked || iterator.hasNext();
    }

    @Override
    public Integer next() {
        // case1 hasNext()?
        // case1.1 yes --> check other case
        // case1.2 no --> return null;
        // case2 hasPeeked?
        // case2.1 yes --> return the old value, updated the hasPeeked and peekedEle
        // case2.2 no --> return the the next;
        if (!hasNext()) {
            return null;
        }
        if (!hasPeeked) {
            return iterator.next();
        }
        Integer returnVal = peekedEle;
        hasPeeked = false;
        peekedEle = null;
        return returnVal;
    }
}

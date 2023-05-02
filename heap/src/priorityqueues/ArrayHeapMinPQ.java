package priorityqueues;

import java.util.ArrayList;
import java.util.List;

/**
 * @see ExtrinsicMinPQ
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    static final int START_INDEX = 1;
    List<PriorityNode<T>> items;
    // TODO: add fields as necessary

    /* check runtime of arraylist methods before using them!!!
    ArrayList Method Runtimes:
        add(): O(1)
        add(element, index): O(n)
        get(): O(1)
        remove(): O(n)
     */


    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        // TODO: add code as necessary
    }

    // Here's a method stub that may be useful. Feel free to change or remove it, if you wish.
    // You'll probably want to add more helper methods like this one to make your code easier to read.

    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int a, int b) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    // Adds an item with the given priority value.
    // Must run in O(log n) time not including rare resize operation.
    @Override
    public void add(T item, double priority) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    // Returns true if the PQ contains the given item; false otherwise.
    // Must run in O(log n)
    @Override
    public boolean contains(T item) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    // Returns the item with least-valued priority.
    // Must run in O(log n) time
    @Override
    public T peekMin() {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    // Removes and returns the item with least-valued priority.
    // Must run in O(log n) time not including rare resize operation.
    @Override
    public T removeMin() {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    // Changes the priority of the given item.
    // Must run in O(log n) time
    @Override
    public void changePriority(T item, double priority) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    // 	Returns the number of items in the PQ.
    // Must run in O(log n) time
    @Override
    public int size() {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}

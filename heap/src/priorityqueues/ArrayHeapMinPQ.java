package priorityqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Map;
import java.util.HashMap;
/**
 * @see ExtrinsicMinPQ
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    static final int START_INDEX = 1;
    List<PriorityNode<T>> items;
    Map<T, Integer> locations;

    /* check runtime of arraylist methods before using them!!!
    ArrayList Method Runtimes:
        add(): O(1)
        add(element, index): O(n)
        get(): O(1)
        remove(): O(n)
     */

    /* FINDING CHILDREN/PARENTS
    leftChild(i) = 2i
    rightChild(i) = 2i + 1
    parent(i) = (i / 2)
     */


    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        locations = new HashMap<>();
        // this works with empty starting arraylist
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
    // PQ can only contain unique items!! 2 items can be assigned same priority value
    // but there cannot be duplicate items.
    @Override
    public void add(T item, double priority) {
        // if priority is less than the item with the lowest priority:
        // this value is the new beginning of the arraylist
        // otherwise :
        if (item == null || !contains(item)) {
            throw new IllegalArgumentException();
        }

    }

    // Returns true if the PQ contains the given item; false otherwise.
    // Must run in O(log n)
    @Override
    public boolean contains(T item) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    // Returns the item with least-valued priority.
    // Throws NoSuchElementException if PQ is empty
    // Must run in O(log n) time
    @Override
    public T peekMin() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.get(1).getItem();
    }

    // Removes and returns the item with least-valued priority.
    // Must run in O(log n) time not including rare resize operation.
    @Override
    public T removeMin() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    // Changes the priority of the given item.
    // Must run in O(log n) time
    @Override
    public void changePriority(T item, double priority) {
        // find node to set
        //.setPriority(priority);
    }

    // 	Returns the number of items in the PQ.
    // Must run in O(log n) time
    @Override
    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}

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
    static final int START_INDEX = 0;
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

    // NEED A PERCOLATE UP AND PERCOLATE DOWN METHOD
    // could call swap method in both and switch parameters depending on whether it is
    // percolating up or down
    private void swap(int a, int b) {
        // swap indexes
        // update HashMap keys and values for those items
        PriorityNode<T> itemA = items.get(a);
        PriorityNode<T> itemB = items.get(b);
        items.set(a, itemB);
        items.set(b, itemA);
        locations.put(itemA.getItem(), b);
        locations.put(itemB.getItem(), a);
    }

    private boolean checkPriority(int a, int b) {
        return items.get(a).getPriority() < items.get(b).getPriority();
    }

    //fix parent, left, and right so only one node and can update the rest
    private void percolateUp(int parent, int child) {
        while (!checkPriority(parent, child)) {
            swap(parent, child);
            child = parent;
            if (parent == 0) {
                break;
            }
            parent = (parent - 1) / 2;
        }
    }

    private void percolateDown(int parent, int left, int right) {
        while (left <= items.size() - 1 && size() > 1) {
            if (right <= items.size() - 1) { // if right exists
                if (!checkPriority(left, right)) { // left is bigger than right
                    if (!checkPriority(parent, right)) { // parent is bigger than right
                        swap(parent, right); // swap
                        parent = right;
                        left = (parent * 2) + 1;
                        right = (parent + 1) * 2;
                    }
                } else { // left is smaller than right
                    if (!checkPriority(parent, left)) { // parent is bigger than left
                        swap(parent, left); // swap
                        parent = left;
                        left = (parent * 2) + 1;
                        right = (parent + 1) * 2;
                    }
                }
            }
            //parent <= items.size() - 1?
            if (left <= items.size() - 1) {
                // parent or left could be out of bounds here??
                if (!checkPriority(parent, left)) {
                    swap(parent, left);
                    // change all your indices and try percDown again
                    parent = left;
                    left = (parent * 2) + 1;
                    right = (parent + 1) * 2;
                } else { // parent is smaller than left so Im going to stop
                    break;
                }
            }
        }
    }

    // Adds an item with the given priority value.
    // Must run in O(log n) time not including rare resize operation.
    // PQ can only contain unique items!! 2 items can be assigned same priority value
    // but there cannot be duplicate items.
    @Override
    public void add(T item, double priority) {
        if (item == null || contains(item)) {
            throw new IllegalArgumentException();
        }
        // add to next available spot
        PriorityNode<T> element = new PriorityNode<>(item, priority);
        items.add(element);
        // element's index is now size - 1
        locations.put(item, size() - 1);
        int indexB = size() - 1;
        int indexA = (indexB - 1) / 2;
        percolateUp(indexA, indexB);
    }

    // Returns true if the PQ contains the given item; false otherwise.
    // Must run in O(log n)
    @Override
    public boolean contains(T item) {
        return locations.containsKey(item);
    }

    // Returns the item with least-valued priority.
    // Throws NoSuchElementException if PQ is empty
    // Must run in O(log n) time
    @Override
    public T peekMin() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return items.get(START_INDEX).getItem();
    }

    // Removes and returns the item with least-valued priority.
    // Must run in O(log n) time not including rare resize operation.
    @Override
    public T removeMin() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T removedItem = items.get(START_INDEX).getItem();

        swap(0, size() - 1);
        items.remove(size() - 1);
        locations.remove(removedItem);

        int parent = 0;
        int left = (parent * 2) + 1;
        int right = (parent + 1) * 2;
        percolateDown(parent, left, right);

        return removedItem;
    }

    // Changes the priority of the given item.
    // Must run in O(log n) time
    @Override
    public void changePriority(T item, double priority) {
        // find node to set
        // .setPriority(priority);
        // THROWS NO SUCH ELEMENT EXCEPTION IF ITEM NOT PRESENT IN THE PQ
        // I worked on changePriority
        // !contains(item)
        // locations.get(item) == null
        if (locations.get(item) == null) {
            throw new NoSuchElementException();
        }
        int index = locations.get(item);
        items.get(index).setPriority(priority);
        percolateUp((index - 1) / 2, index);
        percolateDown(index, (index * 2) + 1, (index + 1) * 2);
    }

    //UPDATE items.size() TO SIZE()

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

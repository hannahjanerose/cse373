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
        locations.put(item, items.size() - 1);
        int indexB = items.size() - 1;
        int indexA = (indexB - 1) / 2;
        while (items.get(indexB).getPriority() < items.get(indexA).getPriority()) {
            swap(indexA, indexB);
            indexB = indexA;
            if (indexA == 0) {
                break;
            }
            indexA = (indexA - 1) / 2;
        }
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
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.get(0).getItem();
        // items.remove(items.get(items.size())); ?? removing item at the end
    }

    // Removes and returns the item with least-valued priority.
    // Must run in O(log n) time not including rare resize operation.
    @Override
    public T removeMin() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        T removedItem = items.get(0).getItem();
        locations.remove(removedItem);
        items.set(0, items.get(items.size() - 1));
        // does this remove from last index?
        items.remove(items.size() - 1);
        locations.put(items.get(0).getItem(), 0);

        //PERCOLATE DOWN
        int parent = 0;
        int left = (parent * 2) + 1;
        int right = (parent + 1) * 2;
        while (left <= items.size() - 1 && size() > 1) {
            if (right <= items.size() - 1) {
                if (checkPriority(left, right) && checkPriority(left, parent)) {
                    swap(parent, left);
                    parent = left;
                    left = (parent * 2) + 1;
                    right = (parent + 1) * 2;
                } else if (checkPriority(right, left) && checkPriority(right, parent)){
                    // check priority, if size > 1
                    swap(parent, right);
                    parent = right;
                    left = (parent * 2) + 1;
                    right = (parent + 1) * 2;
                }
            }
            // do i check if priority of parent here is still less than child?
            // does this percolate have to go all the way down?
            swap(parent, left);
            parent = left;
            left = (parent * 2) + 1;
            right = (parent + 1) * 2;
        }
        return removedItem;
    }

    // Changes the priority of the given item.
    // Must run in O(log n) time
    @Override
    public void changePriority(T item, double priority) {
        // find node to set
        //.setPriority(priority);
        //THROWS NO SUCH ELEMENT EXCEPTION IF ITEM NOT PRESENT IN THE PQ
        // I worked on changePriority
        // !contains(item)
        if (locations.get(item) == null) {
            throw new NoSuchElementException();
        }
        int index = locations.get(item);
        items.get(index).setPriority(priority);
        //percolate down or up compared to its parent
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

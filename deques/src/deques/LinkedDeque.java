package deques;

/**
 * @see Deque
 */
public class LinkedDeque<T> extends AbstractDeque<T> {
    private int size;
    // IMPORTANT: Do not rename these fields or change their visibility.
    // We access these during grading to test your code.
    Node<T> front;
    Node<T> back;
    // Feel free to add any additional fields you may need, though.

    public LinkedDeque() {
        size = 0;
        // initialize front and back nodes
        // check constructors for the node class to figure out how to initialize the fields
        front.prev = null;
        front.next = back;
        back.next = null;
        back.prev = front;
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node<T>(item);
        if (size == 0) {
            front.next = newNode;
            newNode.prev = front;
            newNode.next = back;
            back.prev = newNode;
        } else {
            newNode.next = front.next;
            front.next = newNode;
            newNode.prev = front;
        }
        size += 1;
    }

    public void addLast(T item) {
        size += 1;
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int size() {
        return size;
    }
}

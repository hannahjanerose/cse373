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
        front = new Node<>(null);
        back = new Node<>(null);
        front.next = back;
        back.prev = front;
        // initialize front and back nodes
        // check constructors for the node class to figure out how to initialize the fields
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
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
        if (size == 0) {
            addFirst(item);
        } else {
            Node<T> newNode = new Node<>(item);
            newNode.prev = back.prev;
            back.prev = newNode;
            newNode.next = back;
        }
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;

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

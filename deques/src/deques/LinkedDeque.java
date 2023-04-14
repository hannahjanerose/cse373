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
        newNode.next = front.next;
        front.next = newNode;
        newNode.prev = front;
        newNode.next.prev = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.prev = back.prev;
        back.prev = newNode;
        newNode.next = back;
        newNode.prev.next = newNode;
        size += 1;
    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node<T> first = front.next;
        front.next = first.next;
        first.next.prev = front;
        first.next = null;
        first.prev = null;

        return first.value;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node<T> last = back.prev;
        back.prev = back.prev.prev;
        last.prev.next = last.next;
        last.next = null;
        last.prev = null;
        return last.value;
    }

    public T get(int index) {
        if ((index >= size) || (index < 0)) {
            return null;
        }
        if (index <= size / 2) {
            Node<T> current = front.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        } else {
            Node<T> current = back.prev;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current.value;
        }
    }

    public int size() {
        return size;
    }
}

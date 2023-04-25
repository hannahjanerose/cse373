package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ArrayMap<K, V> extends AbstractIterableMap<K, V> {
    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.
     */
    SimpleEntry<K, V>[] entries;

    // You may add extra fields or helper methods though!

    /**
     * Constructs a new ArrayMap with default initial capacity.
     */
    public ArrayMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Constructs a new ArrayMap with the given initial capacity (i.e., the initial
     * size of the internal array).
     *
     * @param initialCapacity the initial capacity of the ArrayMap. Must be > 0.
     */
    public ArrayMap(int initialCapacity) {
        this.entries = this.createArrayOfEntries(initialCapacity);
    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * {@code Entry<K, V>} objects.
     *
     * Note that each element in the array will initially be null.
     *
     * Note: You do not need to modify this method.
     */
    @SuppressWarnings("unchecked")
    private SimpleEntry<K, V>[] createArrayOfEntries(int arraySize) {
        /*
        It turns out that creating arrays of generic objects in Java is complicated due to something
        known as "type erasure."

        We've given you this helper method to help simplify this part of your assignment. Use this
        helper method as appropriate when implementing the rest of this class.

        You are not required to understand how this method works, what type erasure is, or how
        arrays and generics interact.
        */
        return (SimpleEntry<K, V>[]) (new SimpleEntry[arraySize]);
    }

    @Override
    public V get(Object key) {
        Iterator<Entry<K, V>> itr = this.iterator();
        while (itr.hasNext()) {
            Entry<K, V> entry = itr.next();
            if (entry != null) {
                K thisKey = entry.getKey();
                if (Objects.equals(thisKey, key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Iterator<Entry<K, V>> itr = this.iterator();
        while (itr.hasNext()) {
            Entry<K, V> entry = itr.next();
            if (entry != null) {
                if (Objects.equals(entry.getKey(), key)) {
                    V oldValue = entry.getValue();
                    if (oldValue != value) {
                        entry.setValue(value);
                    }
                    return oldValue;
                }
            }
        }
        if (size == entries.length) {
            SimpleEntry<K, V>[] newEntries = createArrayOfEntries(size * 2);
            for (int i = 0; i < size; i++) {
                newEntries[i] = entries[i];
            }
            entries = newEntries;
        }
        SimpleEntry<K, V> newEntry = new SimpleEntry<>(key, value);
        this.entries[size] = newEntry;
        size++;

        return null;
    }

    @Override
    public V remove(Object key) {
        for (int i = 0; i < size; i++) {
            if (entries[i] != null) { //this is what i changed
                if (Objects.equals(entries[i].getKey(), key)) {
                    SimpleEntry<K, V> entryRemoved = entries[i];
                    if (size > 1) {
                        SimpleEntry<K, V> lastEntry = entries[size - 1];
                        entries[i] = lastEntry;
                        entries[size - 1] = null;
                    } else {
                        entries[i] = null;
                    }
                    size--;
                    return entryRemoved.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void clear() {
        SimpleEntry<K, V>[] newEntries = this.createArrayOfEntries(size);
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Iterator<Entry<K, V>> itr = this.iterator();
        while (itr.hasNext()) {
            Entry<K, V> entry = itr.next();
            if (entry != null) {
                if (Objects.equals(entry.getKey(), key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        // Note: You may or may not need to change this method, depending on whether you
        // add any parameters to the ArrayMapIterator constructor.
        return new ArrayMapIterator<>(this.entries);
    }

    private static class ArrayMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private final SimpleEntry<K, V>[] entries;
        private int position;


        public ArrayMapIterator(SimpleEntry<K, V>[] entries) {
            this.entries = entries;
            this.position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < entries.length && this.entries[position] != null;
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Map.Entry<K, V> entry = entries[position];
            position++;
            return entry;
        }
    }
}

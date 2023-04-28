package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ChainedHashMap<K, V> extends AbstractIterableMap<K, V> {
    private static final double DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD = 0.75; // size / chains.length
    private static final int DEFAULT_INITIAL_CHAIN_COUNT = 10;
    private static final int DEFAULT_INITIAL_CHAIN_CAPACITY = 5;

    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.
     */
    AbstractIterableMap<K, V>[] chains;
    private int size; // number of buckets being used
    private double resizingLoadFactorThreshold;
    private int chainInitialCapacity;


    // You're encouraged to add extra fields (and helper methods) though!

    /**
     * Constructs a new ChainedHashMap with default resizing load factor threshold,
     * default initial chain count, and default initial chain capacity.
     */
    public ChainedHashMap() {
        this(DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD, DEFAULT_INITIAL_CHAIN_COUNT, DEFAULT_INITIAL_CHAIN_CAPACITY);
    }

    /**
     * Constructs a new ChainedHashMap with the given parameters.
     *
     * @param resizingLoadFactorThreshold the load factor threshold for resizing. When the load factor
     *                                    exceeds this value, the hash table resizes. Must be > 0.
     * @param initialChainCount the initial number of chains for your hash table. Must be > 0.
     * @param chainInitialCapacity the initial capacity of each ArrayMap chain created by the map.
     *                             Must be > 0.
     */
    public ChainedHashMap(double resizingLoadFactorThreshold, int initialChainCount, int chainInitialCapacity) {
        this.resizingLoadFactorThreshold = resizingLoadFactorThreshold;
        this.chains = createArrayOfChains(initialChainCount);
        this.chainInitialCapacity = chainInitialCapacity;
        this.size = 0;

    }

    /**
     * This method will return a new, empty array of the given size that can contain
     * {@code AbstractIterableMap<K, V>} objects.
     *
     * Note that each element in the array will initially be null.
     *
     * Note: You do not need to modify this method.
     * @see ArrayMap createArrayOfEntries method for more background on why we need this method
     */
    @SuppressWarnings("unchecked")
    private AbstractIterableMap<K, V>[] createArrayOfChains(int arraySize) {
        return (AbstractIterableMap<K, V>[]) new AbstractIterableMap[arraySize];
    }

    /**
     * Returns a new chain.
     *
     * This method will be overridden by the grader so that your ChainedHashMap implementation
     * is graded using our solution ArrayMaps.
     *
     * Note: You do not need to modify this method.
     */
    protected AbstractIterableMap<K, V> createChain(int initialSize) {
        return new ArrayMap<>(initialSize);
    }

    @Override
    public V get(Object key) {

        if (!containsKey(key)) {
            return null;
        }
        int hashCode = 0;
        if (key != null) {
            hashCode = Math.abs(key.hashCode() % chains.length);
        }

        return chains[hashCode].get(key);
    }

    @Override
    public V put(K key, V value) {
        // if key received is null, hashcode == 0
        if ((double) (size / chains.length) >= resizingLoadFactorThreshold) {
            // resize!!! AND REHASH
            this.chains = hashHelper(); //rehashing all values to chains
        }
        int hashCode = 0;
        if (key != null) {
            hashCode = Math.abs(key.hashCode() % chains.length);
        }
        if (chains[hashCode] == null) {
            chains[hashCode] = createChain(chainInitialCapacity);

        }
        V result = chains[hashCode].put(key, value);
        if (result == null) {
            size++;
        }

        return result;
    }

    private AbstractIterableMap<K, V>[] hashHelper() {
        /*
        for each key
        get new hashcode
        hash to that location in the new hashmap
        return the new hashmap
         */
        AbstractIterableMap<K, V>[] result = createArrayOfChains(chains.length * 2);
        for (Map.Entry<K, V> entry : this) {
            K key = entry.getKey();
            V value = entry.getValue();
            int hashCode = 0;
            if (key != null) {
                hashCode = Math.abs(key.hashCode() % result.length);
            }
            if (result[hashCode] == null) {
                result[hashCode] = createChain(chainInitialCapacity);

            }
            result[hashCode].put(key, value);
        }
        return result;

    }

    @Override
    public V remove(Object key) {
        int hashCode = Math.abs(key.hashCode() % chains.length);
        V value = null;
        if (chains[hashCode] != null) {
            value = chains[hashCode].remove(key);
            if (chains[hashCode].size() == 0) {
                chains[hashCode] = null;
                size--;
            }
        }
        // when chain has no more entries, index there becomes null
        return value;
    }

    @Override
    public void clear() {
        chains = createArrayOfChains(chains.length);
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (chains == null) {
            return false;
        }
        int hashCode = 0;
        if (key != null) {
            hashCode = Math.abs(key.hashCode() % chains.length);
        }
        if (chains[hashCode] != null) {
            return chains[hashCode].containsKey(key);
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        // Note: you won't need to change this method (unless you add more constructor parameters)
        return new ChainedHashMapIterator<>(this.chains);
    }


    /*
    See the assignment webpage for tips and restrictions on implementing this iterator.
     */
    private static class ChainedHashMapIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        private AbstractIterableMap<K, V>[] chains; // the hash table itself
        private int index; // keeps track of the bin we are in, traverse hash table
        private Iterator<Map.Entry<K, V>> iterator;

        public ChainedHashMapIterator(AbstractIterableMap<K, V>[] chains) {
            this.chains = chains;
            this.index = 0;
            iterator = null;
        }

        // Returns true if the iteration has more elements.
        // (In other words, returns true if next() would return an
        // element rather than throwing an exception.)
        @Override
        public boolean hasNext() {
            // check the next bin
            if (chains == null) {
                return false;

            } else if (index == chains.length) {
                return false;
            }

            if (iterator == null) {
                while (chains[index] == null && index < chains.length - 1) {
                    index++;
                }
                if (index == chains.length) {
                    return false;
                }
                return chains[index] != null;

            }
            if (!iterator.hasNext()) {
                iterator = null;
                index++;
                return hasNext();
            }

            return true;
        }

        // 	Returns the next element in the iteration.
        // 	Throw a NoSuchElementException if you are out of elements.
        @Override
        public Map.Entry<K, V> next() {
            // we want to be checking the next entry in the hash table

            // if there is no next array map then throw an exception
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // if the iterator is pointing at nothing
            // set the iterator to the index of chains
            if (iterator == null) {
                iterator = chains[index].iterator();
            }
            return iterator.next();


        }
        // INVARIANTS:
        // Each index in the array of chains is null if and only if that chain has no entries.
        // index HAS to change to null when it is cleared of all entries
        // The index field of the iterator always references the current chain being iterated through
        // (the chain which contains the next entry that next will return).
        // The index field is null after the iterator has been exhausted of all entries.
    }
}

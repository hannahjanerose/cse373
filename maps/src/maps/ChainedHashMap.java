package maps;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @see AbstractIterableMap
 * @see Map
 */
public class ChainedHashMap<K, V> extends AbstractIterableMap<K, V> {
    private static final double DEFAULT_RESIZING_LOAD_FACTOR_THRESHOLD = 0.75; // chainsInUse / chainCount;
    private static final int DEFAULT_INITIAL_CHAIN_COUNT = 10; // in parameters, it says this must be > 0.
    /*
    I think "initial chain count" means the size of the hash map, so maybe we should have it be like 10.
    even if chainCount is 10, the references can be null, so chainCount is just the capacity of the hashMap
     */
    private static final int DEFAULT_INITIAL_CHAIN_CAPACITY = 5; // changed this to be slightly bigger

    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.
     */
    AbstractIterableMap<K, V>[] chains;
    private int size; // number of K,V pairs in the Map
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
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public V put(K key, V value) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public V remove(Object key) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void clear() {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public int size() {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
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
            // check if there is another element
            // two situations
            // first check over the hash table
            // then check over the array map

            // if we are at the last bin and its empty then there is nothing next
            if (chains == null || index == chains.length) {
                return false;
            }
            // in here we know that we are not at the end of the hash table
            // we know that there is something in the hash table ??
            // traversing thru all null values to get to the next non-null bucket
            itrHelper();
            // at this point we have reached a non-empty bucket (will start here if we were already in one)
            iterator = chains[index].iterator();
            if (!iterator.hasNext()) {
                if (!itrHelper()) {
                    return false;
                }
            }
            return true;
        }

        private boolean itrHelper() {
            while (chains[index] == null) {
                index++;
                if (index == chains.length) {
                    return false;
                }
            }
            return true;
        }

        // Returns the next element in the iteration.
        // Throw a NoSuchElementException if you are out of elements.
        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            //check if its null first
            Iterator<Entry<K, V>> itr = chains[index].iterator();
            if (itr.hasNext()) {

            }
            Map.Entry<K, V> entry = itr.next();
            if (index < chains.length - 1) {
                if (chains[index] == null) {
                    index++;
                } else {
                    itr.next();
                }
            }
            return entry;
            // return next element in iteration
        }
        // Each index in the array of chains is null if and only if that chain has no entries.
        // index HAS to change to null when it is cleared of all entries
        // The index field of the iterator always references the current chain being iterated through
        // (the chain which contains the next entry that next will return).
        // The index field is null after the iterator has been exhausted of all entries.
    }
}

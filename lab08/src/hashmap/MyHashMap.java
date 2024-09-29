package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int N; // number of nodes in buckets
    private int initialCapacity;
    private double loadFactor;

    private final int INITIALCAPACITY = 16;
    private final double LOADFACTOR = 0.75;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        initialCapacity = INITIALCAPACITY;
        this.loadFactor = LOADFACTOR;
        N = 0;
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        loadFactor = LOADFACTOR;
        N = 0;
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        N = 0;
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new ArrayList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        int i = Math.floorMod(key.hashCode(), initialCapacity);
        boolean flag = false;
        for (Node n : buckets[i]) {
            if (n.key.equals(key)) {
                n.value = value;
                flag = true;
            }
        }
        if (flag == false) {
            buckets[i].add(new Node(key, value));
            N += 1;
        }
        if (((double) N) / initialCapacity > loadFactor) {
            initialCapacity = 2 * initialCapacity;
            N = 0;
            Collection<Node>[] bucketsTemp = buckets;
            buckets = new Collection[initialCapacity];
            for (int j = 0; j < initialCapacity; j++) {
                buckets[j] = createBucket();
            }
            for (Collection<Node> c : bucketsTemp){
                for (Node n : c) {
                    put(n.key, n.value);
                }
            }
        }

    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int i = Math.floorMod(key.hashCode(), initialCapacity);
        if (buckets[i] == null) {
            return null;
        }
        for (Node n : buckets[i]) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
//        return get(key) != null;
        int i = Math.floorMod(key.hashCode(), initialCapacity);
        if (buckets[i] == null) {
            return false;
        }
        for (Node n : buckets[i]) {
            if (n.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return N;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        N = 0;
        for (Collection<Node> c : buckets) {
            c.clear();
        }
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        Set<K> setRes = new HashSet<>();
        for (Collection<Node> c : buckets) {
            for (Node n : c) {
                setRes.add(n.key);
            }
        }
        return  setRes;
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        int i = Math.floorMod(key.hashCode(), initialCapacity);
        V value= get(key);
        if (value == null) {
            return null;
        }
        N -= 1;
        Iterator<Node> iter = buckets[i].iterator();
        while (iter.hasNext()) {
            Node n = iter.next();
            if (n.key == key) {
//                buckets[i].remove(iter);
                iter.remove();
                break;
            }
        }
        //**** other way to achieve remove use MyHashMapNodeIterator *****
//        Iterator<Node> iter = this.iteratorNode();
//        while (iter.hasNext()) {
//            Node n = iter.next();
//            if (n.key == key) {
//                iter.remove();
//                break;
//            }
//        }
        //****
        return value;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {

//        private Set<K> s ;
//        private Iterator<K> iter;
        private Iterator<Node> iter;
        private int p = 0;

        public MyHashMapIterator() {
//            s = MyHashMap.this.keySet();
//            iter  = s.iterator();
            iter = buckets[p].iterator();
        }

        @Override
        public boolean hasNext() {
            if (iter.hasNext() && p < initialCapacity) {
                return true;
            } else if (p < initialCapacity) {
                p++;
                iter = buckets[p].iterator();
                return iter.hasNext();
            }
            return false;
        }

        @Override
        public K next() {
            return iter.next().key;
        }

    }


    public Iterator<Node> iteratorNode() {
        return new MyHashMap.MyHashMapNodeIterator();
    }

    private class MyHashMapNodeIterator implements Iterator<Node> {

        private Iterator<Node> iter;
        private Node rn;
        private int p = 0;

        public MyHashMapNodeIterator() {
            iter = buckets[p].iterator();
        }

        @Override
        public boolean hasNext() {
            if (iter.hasNext() && p < initialCapacity) {
                return true;
            } else if (p < initialCapacity) {
                p++;
                iter = buckets[p].iterator();
                return iter.hasNext();
            }
            return false;
        }

        @Override
        public Node next() {
            rn = iter.next();
            return rn;
        }

        @Override
        public void remove() {
            buckets[p].remove(rn);
        }
    }
}

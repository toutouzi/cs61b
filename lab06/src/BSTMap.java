import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    private int size = 0;

    private Node bstmap;

    private class Node {
        K key;
        V val;
        Node left;
        Node right;

        Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.val = value;
            this.left = left;
            this.right = right;
        }

        Node() { }

        Node lookup(K k) {
            if (k == null || bstmap == null) return null;
            if (k.equals(key) || this.left == null && this.right == null) {
                return this;
            }
            if (k.compareTo(this.key) < 0) {
                if (left == null) return this;
                return left.lookup(k);
            } else {
                if (right == null) return this;
                return right.lookup(k);
            }
        }
    }
    @Override
    public void put(K key, V value) {
        if (key == null) return;
        if (bstmap == null) {
            bstmap = new Node(key, value, null, null);
            size += 1;
        } else {
            Node parent = bstmap.lookup(key);
            if (parent.key.compareTo(key) < 0) {
                parent.right = new Node(key, value, null, null);
                size += 1;
            } else if (parent.key.equals(key)) {
                parent.val = value;
            } else {
                parent.left = new Node(key, value, null, null);
                size += 1;
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
        if (bstmap == null) return null;
        Node n = bstmap.lookup(key);
        if (n == null || !n.key.equals(key)) {
            return null;
        }
        return n.val;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (bstmap == null || key == null) return false;
        return bstmap.lookup(key).key.equals(key) ;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        size = 0;
        bstmap = null;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new TreeSet<>();
        keySetHelper(bstmap, set);
        return set;
    }

    private void keySetHelper(Node n, Set<K> set) {
        if(n == null) {
            return;
        }
        if (n.left != null) keySetHelper(n.left, set);
        set.add(n.key);
        if (n.right != null) keySetHelper(n.right, set);
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        if (!bstmap.lookup(key).key.equals(key)) return null;
        size -= 1;
        V res = get(key);
        /** cur && pre of the key */
        Node cur = bstmap;
        Node pre = null;
        while (!key.equals(cur.key)) {
            pre = cur;
            if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        /** replace cur with pre, and modify tree strcture */
        Node next = cur;
        Node nextpre = null;
        if (next.right != null) {
            nextpre = next;
            next = next.right;
            while(next.left != null) {
                nextpre = next;
                next = next.left;
            }
            cur.key = next.key;
            cur.val = next.val;
            if (nextpre.equals(cur)) {
                nextpre.right = next.right;
            } else {
                nextpre.left = next.right;
            }
        } else {
            if (pre != null) {
                pre.right = cur.left;
            } else {
                bstmap = cur.left;
            }
        }

        return res;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new BSTMapIter();
    }

    private class BSTMapIter implements Iterator<K> {
        private Stack<Node> st;
//        private Set<K> set;
//        private Iterator<K> iter;

        public BSTMapIter() {
//            set = BSTMap.this.keySet();
//            iter = set.iterator();
            st = new Stack<>();
            pushLeft(bstmap);
        }

        private void pushLeft(Node root) {
            while (root != null) {
                st.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
//           return iter.hasNext();
            return st.empty();
        }

        @Override
        public K next() {
//            return iter.next();
            Node cur = st.pop();
            pushLeft(cur.right);
            return cur.key;
        }
    }
}

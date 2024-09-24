package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {

    protected int size;
    private int capacity;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B() {
        size = 0;
        capacity = 8;
        nextFirst = 4;
        nextLast = 5;
        items = (T[]) new Object[capacity];
    }

    public ArrayDeque61B(int c) {
        size = 0;
        capacity = c;
        nextFirst = 0;
        nextLast = Math.floorMod(nextFirst + 1, capacity);
        items = (T[]) new Object[capacity];
    }

    @Override
    public void addFirst(T x) {
//        if (x == null) return;
        if (capacity == size) {
            resize_up();
        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, capacity);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (capacity == size) {
            resize_up();
        }
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, capacity);
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> l = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            l.addLast(this.get(i));
        }
        return l;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size <= 0 ) return null;
        T res = get(0);
        nextFirst = Math.floorMod(nextFirst + 1, capacity);
        size -= 1;
        if (size < 0.25 * capacity) {
            resize_down();
        }
        return res;

    }

    @Override
    public T removeLast() {
        if (size <= 0) return null;
        T res = get(size - 1);
        nextLast = Math.floorMod(nextLast - 1, capacity);
        size -= 1;
        if (size < 0.25 * capacity) {
            resize_down();
        }
        return res;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) return null;
        int x = Math.floorMod(nextFirst + index + 1, capacity);
        return items[x];
    }

    @Override
    public T getRecursive(int index) {
//        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
        if (index < 0 || index > size) return null;
        int firstIndex = Math.floorMod(nextFirst + 1, capacity);
        return _getRecursiveHelper(index, firstIndex);
    }

    private T _getRecursiveHelper(int count, int curIndex) {
        if (count == 0) return items[curIndex];
        curIndex = Math.floorMod(curIndex + 1, capacity);
        return _getRecursiveHelper(count - 1, curIndex);
    }

    private void resize_up() {
        int capacity_new = capacity * 2;
        T[] items_new = (T[]) new Object[capacity_new];
        for (int i = 0; i < size; i++) {
            items_new[i] = this.get(i);
        }

        this.items = items_new;
        this.capacity = capacity_new;
        this.nextFirst = capacity - 1;
        this.nextLast  = size;
    }

    private void resize_down() {
        int capacity_new = capacity / 2;
        T[] items_new = (T[]) new Object[capacity_new];
        for (int i = 0; i < size; i++) {
            items_new[i] = this.get(i);
        }

        this.items = items_new;
        this.capacity = capacity_new;
        this.nextFirst = capacity - 1;
        this.nextLast  = size;
    }

    public int getCapacity() {
        return capacity;
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T> {

        private int pos;

        public ArrayDeque61BIterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T res = get(pos);
            pos += 1;
            return res;
        }
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (other instanceof ArrayDeque61B<?> o) {
            if (this.size != o.size) { return false; }
            Iterator<T> tIer = this.iterator();
            Iterator<?> oIer = o.iterator();

            while (tIer.hasNext()) {
                if (tIer.next() != oIer.next()) { return false; }
            }
            return true;
        }
        return false;
    }
}
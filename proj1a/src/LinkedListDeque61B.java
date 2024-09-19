import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private Node sentinel;
    private int size;

    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public LinkedListDeque61B() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.prev = sentinel;
        this.sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        this.sentinel.next = new Node(this.sentinel, x, this.sentinel.next);
        this.sentinel.next.next.prev = this.sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        this.sentinel.prev = new Node(this.sentinel.prev, x, this.sentinel);
        this.sentinel.prev.prev.next = this.sentinel.prev;
        size += 1;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = new Node(null, null, sentinel);
        while (p.next.next != sentinel) {
            p.next = p.next.next;
            returnList.addLast(p.next.item);
        }
        return returnList;
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
        if (size < 1) return null;
        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = first.prev;
        first.prev = null;
        first. next = null;
        size -= 1;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (size < 1) return null;
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = last.next;
        last.prev = null;
        last.next = null;
        size -= 1;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            Node p = sentinel;
            while (index >= 0) {
                p = p.next;
                index -= 1;
            }
            return p.item;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size) return null;
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node curNode) {
        if (index == 0) {
            return curNode.item;
        }
        return getRecursiveHelper(index - 1, curNode.next);
    }
}

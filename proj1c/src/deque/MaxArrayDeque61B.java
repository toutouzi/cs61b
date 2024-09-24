package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{

    private Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (this.size <= 0) { return null; }
        T res_max = get(0);

        for (T n : this) {
            if (c.compare(n, res_max) > 0) {
                res_max = n;
            }
        }
        return res_max;
    }
}

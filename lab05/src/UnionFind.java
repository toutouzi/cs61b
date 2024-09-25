import com.puppycrawl.tools.checkstyle.checks.blocks.NeedBracesCheck;

public class UnionFind {
    // TODO: Instance variables
    int[] DisjiontSet;
    int size;
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        DisjiontSet = new int[N];
        size = N;
        for (int i = 0; i < N; i++) {
            DisjiontSet[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        if (v < 0 || v >= size) {
            throw new IllegalArgumentException("Need positive input");
        }
        int root = find(v);
        return Math.abs(DisjiontSet[root]);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        if (v < 0 || v >= size) {
            throw new IllegalArgumentException("Need positive input");
        }
        return DisjiontSet[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 < 0 || v1 >= size || v2 < 0 || v2 >= size) {
            throw new IllegalArgumentException("Need positive input");
        }
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v < 0 || v >= size) {
            throw new IllegalArgumentException("Need positive input");
        }
        /** 递归解决 */
        if (DisjiontSet[v] < 0) return v;
        DisjiontSet[v] = find(DisjiontSet[v]);
        return DisjiontSet[v];
//        int root;
//        int n = v;
//        while (DisjiontSet[v] >= 0) {
//            v = DisjiontSet[v];
//        }
//        root = v;
//        while (DisjiontSet[n] >= 0) {
//            int temp = n;
//            n = DisjiontSet[n];
//            DisjiontSet[temp] = root;
//        }
//        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 < 0 || v1 >= size || v2 < 0 || v2 >= size) {
            throw new IllegalArgumentException("Need positive input");
        }
        int temp = find(v1);
        int temp2 = find(v2);
        if (temp == temp2) return;
        int sizeV1 = DisjiontSet[temp];
        int sizeV2 = DisjiontSet[temp2];
        if (sizeV2 <= sizeV1) {
            DisjiontSet[temp2] = sizeV1 + sizeV2;
            DisjiontSet[temp] = temp2;
        } else {
            DisjiontSet[temp] = sizeV1 + sizeV2;
            DisjiontSet[temp2] = temp;
        }
    }

}

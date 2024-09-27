import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private int size;
    private int n;
    private int virtualTop;
    private int virtualDown;
    private boolean[][] board;
    private boolean isPercolation;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N < 0) {
            throw new IllegalArgumentException("input N > 0");
        }
        size = 0;
        n = N;
        virtualTop = n * n;
        virtualDown = n * n + 1;
        isPercolation = false;
        uf = new WeightedQuickUnionUF(N * N + 2);
        board = new boolean[N][N];
        for (boolean[] row : board) {
            for (int i = 0; i < N; i++) {
                row[i] = false;
            }
        }
    }

    private int xyTo1D(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= n) {
//            throw new ArrayIndexOutOfBoundsException("x = " + x + " y = " + y  + "should be in range N");
            return -1;
        }
        return n * x + y;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        int curUF = xyTo1D(row, col);
        size++;
        board[row][col] = true;

        if (row == 0) {
            uf.union(virtualTop, curUF);
        }

//        if (row == n - 1) {
//            uf.union(virtualDown, curUF);
//        }

        int upUF = xyTo1D(row - 1, col);
        if (upUF != -1 && isOpen(row - 1, col)) {
            uf.union(upUF, curUF);
        }
        int downUF = xyTo1D(row + 1, col);
        if (downUF != -1 && isOpen(row + 1, col)) {
            uf.union(downUF, curUF);
        }
        int leftUF = xyTo1D(row, col - 1);
        if (leftUF != -1 && isOpen(row, col - 1)) {
            uf.union(leftUF, curUF);
        }
        int rightUF = xyTo1D(row, col + 1);
        if (rightUF != -1 && isOpen(row, col + 1)) {
            uf.union(rightUF, curUF);
        }

        if (row == n - 1 && isFull(row, col)) {
            isPercolation = true;
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if(row<0 || row>=board.length || col<0 || col>=board.length)
            throw new IndexOutOfBoundsException();
        return board[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return  uf.connected(xyTo1D(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return size;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return isPercolation;
    }

}

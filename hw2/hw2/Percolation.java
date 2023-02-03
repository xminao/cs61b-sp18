package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import javax.imageio.plugins.tiff.TIFFDirectory;

public class Percolation {

    private int N;
    private int virtualTop;
    private int virtualBottom;
    private boolean[][] grid; // (0, 0) is the upper-left site.
    private int numberOpened;
    private WeightedQuickUnionUF unionSet;
    private WeightedQuickUnionUF unionSetWithB;
    private boolean percolate;

    /** Create N-by-N grid, with all sites initialization.*/
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        numberOpened = 0;
        percolate = false;
        grid = new boolean[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                grid[i][j] = false;
            }
        }
        // N * N is virtual top site.
        unionSet = new WeightedQuickUnionUF(N * N + 1);
        unionSetWithB = new WeightedQuickUnionUF(N * N + 2);
    }

    /** Convert 2D position to 1D.
     *  for instance, (2, 4) converted to 2 * N + 4.
     *   0  1  2
     *   3  4  5
     *   6  7  8 */
    private int rcTo1D(int row, int col) {
        return row * N + col;
    }

    /** Returns the site (row, col) is valid. */
    private boolean validSite(int row, int col) {
        return (row >= 0 && row < N && col >= 0 && col < N);
    }

    /** Open the site (row, col) if it is not open.*/
    public void open(int row, int col) {
        if (!validSite(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int index = rcTo1D(row, col);
        grid[row][col] = true;
        numberOpened += 1;
        // left, right, top, bottom neighbor.
        if (validSite(row, col - 1) && isOpen(row, col - 1)) {
            unionSet.union(index, rcTo1D(row, col - 1));
            unionSetWithB.union(index, rcTo1D(row, col - 1));
        }
        if (validSite(row, col + 1) && isOpen(row, col + 1)) {
            unionSet.union(index, rcTo1D(row, col + 1));
            unionSetWithB.union(index, rcTo1D(row, col + 1));
        }
        if (validSite(row - 1, col) && isOpen(row - 1, col)) {
            unionSet.union(index, rcTo1D(row - 1, col));
            unionSetWithB.union(index, rcTo1D(row - 1, col));
        }
        if (validSite(row + 1, col) && isOpen(row + 1, col)) {
            unionSet.union(index, rcTo1D(row + 1, col));
            unionSetWithB.union(index, rcTo1D(row + 1, col));
        }
        // is connected to top or bottom.
        if (row == 0) {
            unionSet.union(index, virtualTop);
            unionSetWithB.union(index, virtualTop);
        } else if (row == N - 1) {
            unionSetWithB.union(index, virtualBottom);
        }
    }

    /** Is the site (row, col) open. */
    public boolean isOpen(int row, int col) {
        if (!validSite(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    /** Is the site (row, col) full. */
    public boolean isFull(int row, int col) {
        if (!validSite(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return unionSet.connected(rcTo1D(row, col), virtualTop);
    }

    /** Number of open sites. */
    public int numberOfOpenSites() {
        return numberOpened;
    }

    /** Does the system percolate. */
    public boolean percolates() {
        return unionSetWithB.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {

    }
}

package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board implements WorldState {
    private static final int BLANK = 0;
    private int[][] _initial;
    private int[][] _goal;
    private int N;
    public Board(int[][] tiles) {
        N = tiles.length;
        _initial = new int[N][N];
        _goal = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            System.arraycopy(tiles[i], 0, _initial[i], 0, N);
        }
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                _goal[i][j] = i * N + j + 1;
            }
        }
        _goal[N - 1][N - 1] = BLANK;
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        return _initial[i][j];
    }

    public int size() {
        return N;
    }

    /**
     * Returns neighbors of this board.
     * Reference: http://joshh.ug/neighbors.html
     * Comment: have to replace variables names with readable words.
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int wrong = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                // ignore the blank position (N - 1, N - 1).
                if (i != N - 1 || j != N - 1) {
                    if (_initial[i][j] != _goal[i][j]) {
                        wrong += 1;
                    }
                }
            }
        }
        return wrong;
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (_initial[i][j] != BLANK) {
                    int goalRow = (_initial[i][j] - 1) / N;
                    int goalCol = _initial[i][j] - goalRow * N - 1;
                    sum += Math.abs(goalRow - i);
                    sum += Math.abs(goalCol - j);
                }
            }
        }
        return sum;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        if (this == y) {
            return true;
        }
        Board b = Board.class.cast(y);
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (this._initial[i][j] != b._initial[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(_initial);
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}

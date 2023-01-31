package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private boolean cycle = false;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
    }

    @Override
    public void solve() {
        dfs(s, -1);
    }

    // Helper methods go here
    private void dfs(int curr, int prev) {
        marked[curr] = true;
        announce();

        if (cycle) {
            return;
        }

        for (int w : maze.adj(curr)) {
            if (!marked[w]) {
                dfs(w, curr);
            } else if (w != prev) {
                edgeTo[w] = curr;
                announce();
                cycle = true;
                return;
            }
        }
    }
}


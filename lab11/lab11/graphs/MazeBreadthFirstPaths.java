package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        marked[s] = true;
        announce();

        while (!queue.isEmpty()) {
            int poll = queue.poll();

            if (poll == t) {
                targetFound = true;
                return;
            }

            for (int w : maze.adj(poll)) {
                if (!marked[w]) {
                    queue.add(w);
                    edgeTo[w] = poll;
                    announce();
                    distTo[w] = distTo[poll] + 1;
                    announce();
                    marked[w] = true;
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}


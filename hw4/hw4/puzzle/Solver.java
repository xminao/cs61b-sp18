package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    /** Priority queue of search nodes. */
    private MinPQ<SearchNode> queue;
    private SearchNode _initial;
    private SearchNode _goal;

    /** Each SearchNode represents one "move sequence". */
    private class SearchNode {
        private WorldState state;
        private int numberMoves;
        private SearchNode prevNode;

        public SearchNode(WorldState w, int m, SearchNode prev) {
            state = w;
            numberMoves = m;
            prevNode = prev;
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode n1, SearchNode n2) {
            int priority1 = n1.numberMoves + n1.state.estimatedDistanceToGoal();
            int priority2 = n2.numberMoves + n2.state.estimatedDistanceToGoal();
            return priority1 - priority2;
        }
    }

    /** Constructor */
    public Solver(WorldState initial) {
        queue = new MinPQ<>(new SearchNodeComparator());
        _initial = new SearchNode(initial, 0, null);
        queue.insert(_initial);
        search();
    }

    private void search() {
        SearchNode node = queue.delMin();
        if (node.state.isGoal()) {
            _goal = node;
            return;
        }
        SearchNode neighbor;
        for (WorldState w : node.state.neighbors()) {
            neighbor = new SearchNode(w, node.numberMoves + 1, node);
            // neighbor is the previous Word.
            if (node.prevNode != null) {
                if (!w.equals(node.prevNode.state)) {
                    queue.insert(neighbor);
                }
            } else {
                queue.insert(neighbor);
            }
        }
        search();
    }

    /** Returns the minimum number of moves to solve
     *  the puzzle starting at the initial WorldState. */
    public int moves() {
        return _initial.state.estimatedDistanceToGoal();
    }

    /** Returns a sequence of WorldStates from the initial
     *  WorldState to the solution. */
    public Iterable<WorldState> solution() {
        List<WorldState> list = new LinkedList<>();
        SearchNode p = _goal;
        while (p != null) {
            list.add(0, p.state);
            p = p.prevNode;
        }
        return list;
    }

}

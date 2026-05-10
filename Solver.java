import java.util.*;

public class Solver {
    Board board;

    public Solver(Board board) {
        this.board = board;
    }

    public SearchResult bfs() {
        long startTime = System.currentTimeMillis();

        Queue<Node> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        State startState = new State(board.startRow, board.startCol, 0);
        Node startNode = new Node(startState, null, "", 0);

        queue.add(startNode);
        visited.add(startState);

        int iterations = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            iterations++;

            if (board.isGoal(current.state)) {
                long endTime = System.currentTimeMillis();
                return buildResult(current, iterations, endTime - startTime);
            }

            for (Direction dir : Direction.values()) {
                SlideResult result = board.slide(current.state, dir);

                if (result == null) {
                    continue;
                }

                State nextState = result.state;

                if (!visited.contains(nextState)) {
                    visited.add(nextState);

                    Node nextNode = new Node(
                            nextState,
                            current,
                            dir.symbol,
                            current.cost + result.moveCost
                    );

                    queue.add(nextNode);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        return new SearchResult(new ArrayList<>(), new ArrayList<>(), -1, iterations, endTime - startTime);
    }

    private SearchResult buildResult(Node goalNode, int iterations, long executionTime) {
        List<String> moves = new ArrayList<>();
        List<State> states = new ArrayList<>();

        Node current = goalNode;

        while (current != null) {
            states.add(current.state);

            if (current.parent != null) {
                moves.add(current.move);
            }

            current = current.parent;
        }

        Collections.reverse(states);
        Collections.reverse(moves);

        return new SearchResult(moves, states, goalNode.cost, iterations, executionTime);
    }
}
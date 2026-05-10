<<<<<<< HEAD
import java.util.*;

public class Solver {
    Board board;

    public Solver(Board board) {
        this.board = board;
    }

    SearchResult solve(String algorithm, String heuristicName) {
        if (algorithm.equals("BFS")) {
            return bfs();
        } else {
            return informedSearch(algorithm, heuristicName);
        }
    }

    SearchResult bfs() {
        long startTime = System.currentTimeMillis();

        Queue<Node> queue = new LinkedList<>();
        HashSet<State> visited = new HashSet<>();

        State start = new State(board.startRow, board.startCol, 0);
        Node startNode = new Node(start, null, "", 0, 0);

        queue.add(startNode);
        visited.add(start);

        int iterations = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            iterations++;

            if (board.isGoal(current.state)) {
                long endTime = System.currentTimeMillis();
                return makeResult(current, iterations, endTime - startTime);
            }

            for (Direction dir : Direction.values()) {
                SlideResult sr = board.slide(current.state, dir);

                if (sr != null && !visited.contains(sr.state)) {
                    visited.add(sr.state);
                    Node next = new Node(sr.state, current, dir.symbol, current.cost + sr.moveCost, 0);
                    queue.add(next);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        return gagal(iterations, endTime - startTime);
    }

    SearchResult informedSearch(String algorithm, String heuristicName) {
        long startTime = System.currentTimeMillis();

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return Double.compare(a.priority, b.priority);
            }
        });

        HashMap<State, Integer> bestCost = new HashMap<>();

        State start = new State(board.startRow, board.startCol, 0);
        Node startNode = new Node(start, null, "", 0, 0);

        pq.add(startNode);
        bestCost.put(start, 0);

        int iterations = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            iterations++;

            if (board.isGoal(current.state)) {
                long endTime = System.currentTimeMillis();
                return makeResult(current, iterations, endTime - startTime);
            }

            for (Direction dir : Direction.values()) {
                SlideResult sr = board.slide(current.state, dir);

                if (sr == null) continue;

                int newCost = current.cost + sr.moveCost;

                if (!bestCost.containsKey(sr.state) || newCost < bestCost.get(sr.state)) {
                    bestCost.put(sr.state, newCost);

                    double h = heuristic(sr.state, heuristicName);
                    double priority;

                    if (algorithm.equals("UCS")) {
                        priority = newCost;
                    } else if (algorithm.equals("GBFS")) {
                        priority = h;
                    } else {
                        priority = newCost + h;
                    }

                    Node next = new Node(sr.state, current, dir.symbol, newCost, priority);
                    pq.add(next);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        return gagal(iterations, endTime - startTime);
    }

    double heuristic(State s, String heuristicName) {
        int dr = Math.abs(s.row - board.goalRow);
        int dc = Math.abs(s.col - board.goalCol);

        if (heuristicName.equals("H2")) {
            return Math.sqrt(dr * dr + dc * dc);
        } else if (heuristicName.equals("H3")) {
            return Math.max(dr, dc);
        } else {
            return dr + dc;
        }
    }

    SearchResult makeResult(Node goal, int iterations, long time) {
        ArrayList<String> moves = new ArrayList<>();
        ArrayList<State> states = new ArrayList<>();

        Node temp = goal;

        while (temp != null) {
            states.add(temp.state);

            if (temp.parent != null) {
                moves.add(temp.move);
            }

            temp = temp.parent;
        }

        Collections.reverse(states);
        Collections.reverse(moves);

        return new SearchResult(moves, states, goal.cost, iterations, time);
    }

    SearchResult gagal(int iterations, long time) {
        return new SearchResult(new ArrayList<>(), new ArrayList<>(), -1, iterations, time);
    }
}
=======
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
>>>>>>> 9ef2c15eb393d366f5acec4b095247fbde6a67ff

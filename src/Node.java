public class Node {
    State state;
    Node parent;
    String move;
    int cost;
    double priority;

    public Node(State state, Node parent, String move, int cost, double priority) {
        this.state = state;
        this.parent = parent;
        this.move = move;
        this.cost = cost;
        this.priority = priority;
    }
}
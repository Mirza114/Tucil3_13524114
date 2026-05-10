public class Node {
    State state;
    Node parent;
    String move;
    int cost;

    public Node(State state, Node parent, String move, int cost) {
        this.state = state;
        this.parent = parent;
        this.move = move;
        this.cost = cost;
    }
}

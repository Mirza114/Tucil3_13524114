<<<<<<< HEAD
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
=======
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
>>>>>>> 9ef2c15eb393d366f5acec4b095247fbde6a67ff

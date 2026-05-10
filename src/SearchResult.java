<<<<<<< HEAD
import java.util.ArrayList;

public class SearchResult {
    ArrayList<String> moves;
    ArrayList<State> states;
    int totalCost;
    int iterations;
    long time;

    public SearchResult(ArrayList<String> moves, ArrayList<State> states, int totalCost, int iterations, long time) {
        this.moves = moves;
        this.states = states;
        this.totalCost = totalCost;
        this.iterations = iterations;
        this.time = time;
    }
}
=======
import java.util.List;

public class SearchResult {
    List<String> moves;
    List<State> states;
    int totalCost;
    int iterations;
    long executionTime;

    public SearchResult(List<String> moves, List<State> states, int totalCost, int iterations, long executionTime) {
        this.moves = moves;
        this.states = states;
        this.totalCost = totalCost;
        this.iterations = iterations;
        this.executionTime = executionTime;
    }
}
>>>>>>> 9ef2c15eb393d366f5acec4b095247fbde6a67ff

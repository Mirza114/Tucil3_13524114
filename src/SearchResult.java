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
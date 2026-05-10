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
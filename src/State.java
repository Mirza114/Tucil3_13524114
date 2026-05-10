import java.util.Objects;

public class State {
    int row, col;
    int nextNumber;

    public State(int row, int col, int nextNumber) {
        this.row = row;
        this.col = col;
        this.nextNumber = nextNumber;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof State)) return false;
        State other = (State) obj;
        return row == other.row && col == other.col && nextNumber == other.nextNumber;
    }

    public int hashCode() {
        return Objects.hash(row, col, nextNumber);
    }
}

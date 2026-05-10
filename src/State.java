import java.util.Objects;

public class State {
    int row;
    int col;
    int nextNumber;

    public State(int row, int col, int nextNumber) {
        this.row = row;
        this.col = col;
        this.nextNumber = nextNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof State)) return false;
        State other = (State) o;
        return row == other.row && col == other.col && nextNumber == other.nextNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, nextNumber);
    }
}

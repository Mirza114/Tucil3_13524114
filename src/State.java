<<<<<<< HEAD
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
=======
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
>>>>>>> 9ef2c15eb393d366f5acec4b095247fbde6a67ff

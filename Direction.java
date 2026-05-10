public enum Direction {
    U(-1, 0, "U"),
    D(1, 0, "D"),
    L(0, -1, "L"),
    R(0, 1, "R");

    public final int dx;
    public final int dy;
    public final String symbol;

    Direction(int dx, int dy, String symbol) {
        this.dx = dx;
        this.dy = dy;
        this.symbol = symbol;
    }
}
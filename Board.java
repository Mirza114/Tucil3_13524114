import java.io.*;

public class Board {
    int n, m;
    char[][] grid;
    int[][] cost;
    int startRow, startCol;
    int goalRow, goalCol;
    int maxNumber = -1;

    public Board(String filename) throws IOException {
        readFile(filename);
    }

    private void readFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String[] size = br.readLine().trim().split("\\s+");

            n = Integer.parseInt(size[0]);
            m = Integer.parseInt(size[1]);

            grid = new char[n][m];
            cost = new int[n][m];

            boolean foundStart = false;
            boolean foundGoal = false;

            for (int i = 0; i < n; i++) {
                String line = br.readLine().trim();

                if (line.length() != m) {
                    throw new IllegalArgumentException("Panjang baris map tidak sesuai ukuran M.");
                }

                for (int j = 0; j < m; j++) {
                    char c = line.charAt(j);
                    grid[i][j] = c;

                    if (c == 'Z') {
                        startRow = i;
                        startCol = j;
                        foundStart = true;
                    } else if (c == 'O') {
                        goalRow = i;
                        goalCol = j;
                        foundGoal = true;
                    } else if (Character.isDigit(c)) {
                        maxNumber = Math.max(maxNumber, c - '0');
                    } else if (c != '*' && c != 'X' && c != 'L') {
                        throw new IllegalArgumentException("Simbol tidak valid: " + c);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                String[] parts = br.readLine().trim().split("\\s+");

                if (parts.length != m) {
                    throw new IllegalArgumentException("Jumlah cost tidak sesuai ukuran M.");
                }

                for (int j = 0; j < m; j++) {
                    cost[i][j] = Integer.parseInt(parts[j]);
                }
            }

            if (!foundStart || !foundGoal) {
                throw new IllegalArgumentException("Map harus memiliki Z dan O.");
            }
        }
    }

    public boolean isGoal(State s) {
        return s.row == goalRow && s.col == goalCol && s.nextNumber > maxNumber;
    }

    public SlideResult slide(State state, Direction dir) {
        int r = state.row;
        int c = state.col;
        int nextNumber = state.nextNumber;
        int totalMoveCost = 0;

        while (true) {
            int nr = r + dir.dx;
            int nc = c + dir.dy;

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                return null;
            }

            if (grid[nr][nc] == 'X') {
                break;
            }

            if (grid[nr][nc] == 'L') {
                return null;
            }

            r = nr;
            c = nc;
            totalMoveCost += cost[r][c];

            char cell = grid[r][c];

            if (Character.isDigit(cell)) {
                int value = cell - '0';

                if (value == nextNumber) {
                    nextNumber++;
                } else if (value > nextNumber) {
                    return null;
                }
            }
        }

        if (r == state.row && c == state.col) {
            return null;
        }

        return new SlideResult(new State(r, c, nextNumber), totalMoveCost);
    }

    public void printBoard(State state) {
        char[][] temp = new char[n][m];

        for (int i = 0; i < n; i++) {
            temp[i] = grid[i].clone();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 'Z') {
                    temp[i][j] = '*';
                }
            }
        }

        temp[state.row][state.col] = 'Z';

        for (int i = 0; i < n; i++) {
            System.out.println(new String(temp[i]));
        }
    }
}

class SlideResult {
    State state;
    int moveCost;

    public SlideResult(State state, int moveCost) {
        this.state = state;
        this.moveCost = moveCost;
    }
}
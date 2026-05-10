import java.io.*;
import java.util.*;

public class Board {
    int n, m;
    char[][] grid;
    int[][] cost;

    int startRow, startCol;
    int goalRow, goalCol;
    int maxNumber = -1;

    public Board(String filename) throws Exception {
        readFile(filename);
    }

    void readFile(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));

        n = sc.nextInt();
        m = sc.nextInt();

        grid = new char[n][m];
        cost = new int[n][m];

        boolean adaStart = false;
        boolean adaGoal = false;

        for (int i = 0; i < n; i++) {
            String line = sc.next();

            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);
                grid[i][j] = c;

                if (c == 'Z') {
                    startRow = i;
                    startCol = j;
                    adaStart = true;
                } else if (c == 'O') {
                    goalRow = i;
                    goalCol = j;
                    adaGoal = true;
                } else if (Character.isDigit(c)) {
                    int angka = c - '0';
                    if (angka > maxNumber) {
                        maxNumber = angka;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        sc.close();

        if (!adaStart || !adaGoal) {
            throw new Exception("Input tidak valid, harus ada Z dan O");
        }
    }

    boolean isGoal(State s) {
        return s.row == goalRow && s.col == goalCol && s.nextNumber > maxNumber;
    }

    SlideResult slide(State state, Direction dir) {
        int r = state.row;
        int c = state.col;
        int nextNumber = state.nextNumber;
        int moveCost = 0;

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
            moveCost += cost[r][c];

            char isi = grid[r][c];

            if (Character.isDigit(isi)) {
                int angka = isi - '0';

                if (angka == nextNumber) {
                    nextNumber++;
                } else if (angka > nextNumber) {
                    return null;
                }
            }
        }

        if (r == state.row && c == state.col) {
            return null;
        }

        State newState = new State(r, c, nextNumber);
        return new SlideResult(newState, moveCost);
    }

    void printBoard(State state) {
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
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("=== Ice Sliding Puzzle Solver ===");
            System.out.print("Masukkan path file input: ");
            String filename = scanner.nextLine();

            try {
                Board board = new Board(filename);
                Solver solver = new Solver(board);

                System.out.println("Algoritma yang digunakan: BFS");
                SearchResult result = solver.bfs();

                if (result.totalCost == -1) {
                    System.out.println("Solusi tidak ditemukan.");
                    System.out.println("Banyak iterasi: " + result.iterations);
                    System.out.println("Waktu eksekusi: " + result.executionTime + " ms");
                    return;
                }

                System.out.println("\nSolusi Yang Ditemukan: " + String.join("", result.moves));
                System.out.println("Cost dari Solusi: " + result.totalCost);
                System.out.println("Waktu eksekusi: " + result.executionTime + " ms");
                System.out.println("Banyak iterasi: " + result.iterations);

                System.out.println("\nInitial");
                board.printBoard(result.states.get(0));

                for (int i = 1; i < result.states.size(); i++) {
                    System.out.println("\nStep " + i + ": " + result.moves.get(i - 1));
                    board.printBoard(result.states.get(i));
                }

                System.out.print("\nApakah ingin menyimpan solusi? (Ya/Tidak): ");
                String save = scanner.nextLine();

                if (save.equalsIgnoreCase("Ya")) {
                    saveSolution("solusi_bfs.txt", board, result);
                    System.out.println("Solusi disimpan ke solusi_bfs.txt");
                }

            } catch (Exception e) {
                System.out.println("Terjadi error: " + e.getMessage());
            }
        }
    }

    public static void saveSolution(String filename, Board board, SearchResult result) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            writer.println("Algoritma: BFS");
            writer.println("Solusi Yang Ditemukan: " + String.join("", result.moves));
            writer.println("Cost dari Solusi: " + result.totalCost);
            writer.println("Waktu eksekusi: " + result.executionTime + " ms");
            writer.println("Banyak iterasi: " + result.iterations);
            writer.println();

            for (int i = 0; i < result.states.size(); i++) {
                if (i == 0) {
                    writer.println("Initial");
                } else {
                    writer.println("Step " + i + ": " + result.moves.get(i - 1));
                }

                char[][] temp = new char[board.n][board.m];

                for (int r = 0; r < board.n; r++) {
                    temp[r] = board.grid[r].clone();
                }

                for (int r = 0; r < board.n; r++) {
                    for (int c = 0; c < board.m; c++) {
                        if (temp[r][c] == 'Z') {
                            temp[r][c] = '*';
                        }
                    }
                }

                State state = result.states.get(i);
                temp[state.row][state.col] = 'Z';

                for (int r = 0; r < board.n; r++) {
                    writer.println(new String(temp[r]));
                }

                writer.println();
            }
        }
    }
}

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
        Scanner input = new Scanner(System.in);

        System.out.println("Ice Sliding Puzzle Solver");
        System.out.print("file input: ");
        String filename = input.nextLine().trim();

        try {
            Board board = new Board(filename);
            Solver solver = new Solver(board);

            System.out.print("pilih algoritma (UCS/GBFS/A*/BFS): ");
            String algorithm = input.nextLine().toUpperCase();

            if (algorithm.equals("astar")) {
                algorithm = "A*";
            }

            String heuristic = "H1";

            if (algorithm.equals("GBFS") || algorithm.equals("A*")) {
                System.out.print("pilih heuristic (H1/H2/H3): ");
                heuristic = input.nextLine().toUpperCase();
            }

            SearchResult result = solver.solve(algorithm, heuristic);

            if (result.totalCost == -1) {
                System.out.println("\nsolusi tidak ditemukan.");
                System.out.println("waktu eksekusi: " + result.time + " ms");
                System.out.println("iterasi: " + result.iterations);
                input.close();
                return;
            }

            System.out.println("\nsolusi yang ditemukan: " + String.join("", result.moves));
            System.out.println("cost solusi: " + result.totalCost);
            System.out.println("waktu eksekusi: " + result.time + " ms");
            System.out.println("iterasi: " + result.iterations);

            System.out.println("\ninitial");
            board.printBoard(result.states.get(0));

            for (int i = 1; i < result.states.size(); i++) {
                System.out.println("\nstep " + i + ": " + result.moves.get(i - 1));
                board.printBoard(result.states.get(i));
            }

            System.out.print("\nplayback? (ya/tidak): ");
            String playback = input.nextLine();

            if (playback.equalsIgnoreCase("ya")) {
                while (true) {
                    System.out.print("step, atau -1 untuk keluar: ");
                    int step = input.nextInt();

                    if (step == -1) break;

                    if (step >= 0 && step < result.states.size()) {
                        if (step == 0) {
                            System.out.println("\ninitial");
                        } else {
                            System.out.println("\nstep " + step + ": " + result.moves.get(step - 1));
                        }
                        board.printBoard(result.states.get(step));
                    } else {
                        System.out.println("step tidak tersedia.");
                    }
                }

                input.nextLine();
            }

            System.out.print("\nsimpan solusi? (ya/tidak): ");
            String save = input.nextLine();

            if (save.equalsIgnoreCase("ya")) {
                saveSolution("solusi.txt", board, result, algorithm, heuristic);
                System.out.println("solusi disimpan ke solusi.txt");
            }

        } catch (Exception e) {
            System.out.println("terjadi error: " + e.getMessage());
        }

        input.close();
    }

    static void saveSolution(String filename, Board board, SearchResult result, String algorithm, String heuristic) throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter(filename));

        out.println("algoritma: " + algorithm);
        if (algorithm.equals("GBFS") || algorithm.equals("A*")) {
            out.println("heuristic: " + heuristic);
        }

        out.println("solusi yang ditemukan: " + String.join("", result.moves));
        out.println("cost solusi: " + result.totalCost);
        out.println("waktu eksekusi: " + result.time + " ms");
        out.println("iterasi: " + result.iterations);
        out.println();

        for (int i = 0; i < result.states.size(); i++) {
            if (i == 0) {
                out.println("initial");
            } else {
                out.println("step " + i + ": " + result.moves.get(i - 1));
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

            State s = result.states.get(i);
            temp[s.row][s.col] = 'z';

            for (int r = 0; r < board.n; r++) {
                out.println(new String(temp[r]));
            }

            out.println();
        }

        out.close();
    }
}
=======
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
>>>>>>> 9ef2c15eb393d366f5acec4b095247fbde6a67ff

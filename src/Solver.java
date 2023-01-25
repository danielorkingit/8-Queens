import java.util.Random;

public class Solver {
    private final int SIZE;
    private int[] queens;
    private Random rand = new Random();
    
    public Solver(int n) {
    	this.SIZE = n;
        queens = new int[SIZE];
        initBoard();
    }
    
    private void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            int col = rand.nextInt(SIZE);
            queens[i] = col;
        }
    }
    
    private void printBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char c = (queens[row] == col) ? '\u2655' : '*';
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
    
    private int countConflicts(int row, int col) {
        int conflicts = 0;
        for (int i = 0; i < SIZE; i++) {
            if (i != row) {
                if (queens[i] == col) {
                    conflicts++;
                } else if (Math.abs(i - row) == Math.abs(queens[i] - col)) {
                    conflicts++;
                }
            }
        }
        return conflicts;
    }
    
    private boolean isSolution() {
        for (int row = 0; row < SIZE; row++) {
            if (countConflicts(row, queens[row]) > 0) {
                return false;
            }
        }
        return true;
    }
    
    public void solve() {
        while (!isSolution()) {
            int row = rand.nextInt(SIZE);
            int minConflicts = Integer.MAX_VALUE;
            int minCol = -1;

            for (int col = 0; col < SIZE; col++) {
                if (col == queens[row]) {
                    continue;
                }
                int conflicts = countConflicts(row, col);
                if (conflicts < minConflicts) {
                    minConflicts = conflicts;
                    minCol = col;
                }
            }

            queens[row] = minCol;
        }
        printBoard();
    }

    public static void main(String[] args) {
        Solver solver = new Solver(5);
        solver.solve();
    }
}
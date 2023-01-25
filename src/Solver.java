/**
 * 
 * @author Daniel Orkin
 * 
 * @version 25.01.2023
 * 
 */

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
    
    /**
     * 
     * @param queens: Index -> Zeile | Value -> Spalte
     * 
     * Setzt je eine der N-Vielen Damen in eine Zeile.
     * 
     */
    
    private void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            queens[i] = rand.nextInt(SIZE);
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
    
    /**
     * 
     * @param row: Zeile der untersuchten Stelle
     * @param col: Spalte der untersuchten Stelle
     * @return Anzahl der auftretenden "Konflikte"
     * 
     */
    
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
    
    /**
     * 
     * Untersucht, ob bereits alle Damen richtig gesetzt werden und
     * es somit keine "Konflikte" an {queens.index, queens[index]}
     * mehr gibt.
     * 
     */
    
    private boolean isSolution() {
        for (int row = 0; row < SIZE; row++) {
            if (countConflicts(row, queens[row]) > 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * Auswahl einer zufälligen Dame (Zeile).
     * Setzen der Dame auf das Feld mit den geringsten "Konflikten".
     * 
     * Beim Iterieren durch mögliche Spalten der Dame.
     * 
     * @param queen: Zeile der Dame
     * 
     */
    
    public void solve() {
        while (!isSolution()) {
            int queen = rand.nextInt(SIZE);
            int minConflicts = Integer.MAX_VALUE;
            int minCol = -1;

            for (int col = 0; col < SIZE; col++) {
                if (col == queens[queen]) {
                    continue;
                }
                int conflicts = countConflicts(queen, col);
                if (conflicts < minConflicts) {
                    minConflicts = conflicts;
                    minCol = col;
                }
            }

            queens[queen] = minCol;
        }
        printBoard();
    }

    public static void main(String[] args) {
        Solver solver = new Solver(40);
        solver.solve();
    }
}
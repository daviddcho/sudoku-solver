import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuSolver {

    static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public static boolean isValid(int[][] board, int row, int col, int num) {
        int i, j;
        // checks row
        for (i = 0; i < board.length; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // checks col
        for (i = 0; i < board.length; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        int boxRowStart = row - (row % 3);
        int boxColStart = col - (col % 3);
        // checks the corresponding box its in
        for (i = boxRowStart; i < boxRowStart + 3; i++) {
            for (j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean solveSudoku(int[][] board, int row, int col) {
        boolean isFilled = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // if cell is not filled
                if (board[i][j] == 0) {
                    // records the empty cell
                    row = i;
                    col = j;
                    isFilled = false;
                    break;
                }
            }
        }
        // BASE CASE:
        if (isFilled) {
            return true;
        }
        // checks thru all the possible numbers
        for (int i = 1; i <= 9; i++) {
            if (isValid(board, row, col, i)) {
                board[row][col] = i;
                if (solveSudoku(board, row, col)) {
                    return true;
                }
            }
        }
        board[row][col] = 0; // clears the cell
        return false;
    }


    public static void main(String[] args) throws FileNotFoundException {

        int[][] board = new int[9][9];
        String fileName = "board1.txt";
        Scanner sc = new Scanner(new File(fileName));

        int i = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            for (int j = 0; j < line.length(); j++) {
                char d = line.charAt(j);
                board[i][j] = Integer.parseInt(String.valueOf(d));
            }
            i++;
        }
        sc.close();

        printBoard(board);
        solveSudoku(board, 0, 0);
        System.out.println("SOLVED: ");
        printBoard(board);
    }
}


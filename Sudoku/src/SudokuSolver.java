import java.util.Scanner;

public class SudokuSolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numRows = 9;
        int numCols = 9;

        int[][] sudokuGrid = new int[numRows][numCols];

        System.out.println("Enter the Sudoku matrix (9x9):");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                sudokuGrid[i][j] = scanner.nextInt();
            }
        }

        if (solveSudoku(sudokuGrid)) {
            System.out.println("\n\nSolved Sudoku:\n");
            printSudoku(sudokuGrid);
        } else {
            System.out.println("\n\nNo solution exists.\n");
        }

        scanner.close();
    }

    // Function to solve Sudoku using backtracking
    public static boolean solveSudoku(int[][] grid) {
        int[] emptyCell = findEmptyCell(grid);
        int row = emptyCell[0];
        int col = emptyCell[1];

        // If there are no empty cells, the Sudoku is solved
        if (row == -1) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidMove(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku(grid)) {
                    return true;
                }

                // If the current configuration does not lead to a solution, backtrack
                grid[row][col] = 0;
            }
        }
        return false;
    }

    // Function to find the next empty cell
    public static int[] findEmptyCell(int[][] grid) {
        int[] emptyCell = {-1, -1};
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 0) {
                    emptyCell[0] = row;
                    emptyCell[1] = col;
                    return emptyCell;
                }
            }
        }
        return emptyCell;
    }

    // Function to check if a number can be placed in a cell
    public static boolean isValidMove(int[][] grid, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Function to print the Sudoku grid
    public static void printSudoku(int[][] grid) {
        for (int[] ints : grid) {
            for (int col = 0; col < grid[0].length; col++) {
                System.out.print(ints[col] + " ");
            }
            System.out.println();
        }
    }
}

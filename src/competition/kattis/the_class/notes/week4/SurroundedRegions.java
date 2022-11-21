package competition.kattis.the_class.notes.week4;

public class SurroundedRegions {

    static void printGrid(char[][] grid) {
        for(char[] row : grid) {
            for (char i : row) {
                System.out.print(i);
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void searchAndFill(char[][] grid, int i, int j, char value) {
        int rows = grid.length;
        int columns = grid[0].length;

        // Check if position is connected to the 'O' region
        //   Can't be off the edge of the grid
        if(i < 0 || i >= rows ||  j < 0 || j >= columns || grid[i][j] != 'O') {
            return;
        }

        // Mark the region as connected
        grid[i][j] = value;

        // Search the connected cells
        searchAndFill(grid, i - 1, j, value);
        searchAndFill(grid, i + 1, j, value);
        searchAndFill(grid, i, j - 1, value);
        searchAndFill(grid, i, j + 1, value);
    }

    public static void captureRegions(char[][] board) {
        int rows = board.length;
        int columns = board[0].length;

        printGrid(board);
        for (int j = 0; j < columns; j++) {
            // Search across the top row
            if(board[0][j] == 'O') {
                searchAndFill(board, 0, j, 'V');
            }
            // Search across the bottom row
            if(board[rows-1][j] == 'O') {
                searchAndFill(board, rows-1, j, 'V');
            }
        }
        for (int i = 0; i < rows; i++) {
            // Search across the left column
            if (board[i][0] == 'O') {
                searchAndFill(board, i, 0, 'V');
            }
            if (board[i][columns-1] == 'O') {
                searchAndFill(board, i, columns-1, 'V');
            }
        }

        printGrid(board);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Capture unmarked cells
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                // Restore marked cells
                if (board[i][j] == 'V') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        captureRegions(board);
        printGrid(board);
    }
}

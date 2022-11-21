package competition.kattis.the_class.notes.week6;

public class CoastLine {

    public static void searchAndFill(int[][] grid, int i, int j) {
        int rows = grid.length;
        int columns = grid[0].length;

        // Check if position is connected to the 'O' region
        //   Can't be off the edge of the grid
        if(i < 0 || i >= rows ||  j < 0 || j >= columns || grid[i][j] != 0) {
            return;
        }

        // Mark the region as connected
        grid[i][j] = -1;

        // Search the connected cells
        searchAndFill(grid, i - 1, j);
        searchAndFill(grid, i + 1, j);
        searchAndFill(grid, i, j - 1);
        searchAndFill(grid, i, j + 1);
    }

    public static int coastLength(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;

        // Create a new grid surrounded by water
        int newRows = rows+2;
        int newColumns = columns+2;
        int[][] newGrid = new int[newRows][newColumns];
        for(int i = 0; i < newRows; i++) {
            newGrid[0][i] = 0;
            newGrid[newRows-1][i] = 0;
        }
        for(int j = 0; j < newColumns; j++) {
            newGrid[0][j] = 0;
            newGrid[0][newColumns-1] = 0;
        }
        for(int i = 0; i < rows; i++) {
            System.arraycopy(grid[i], 0, newGrid[i + 1], 1, columns);
        }

        // Flood fill the water point 0,0 is guaranteed to be water
        //  Convert the surrounded grid into a graph
        //  Use negative numbers to represent an edge since positive numbers
        //  are used for indicating land
        searchAndFill(newGrid, 0, 0);

        // Every non-marked location contributes to the coastline
        //  NOTE: start at index 1 and end at (newRows/newColumns)-1 since
        //        we know the newGrid is surrounded by 0
        int coast = 0;
        for(int i = 1; i < newRows-1; i++) {
            for(int j = 1; j < newColumns-1; j++) {

                // Search for an island mark
                // NOTE: water was marked as '-1' in the search and flood
                if(newGrid[i][j] == -1) {
                    continue;
                }

                // Found an island segment check if it is bordered by water
                //   If so it is on the coast
                if(newGrid[i-1][j] == -1) {
                    coast++;
                }
                if(newGrid[i+1][j] == -1) {
                    coast++;
                }
                if(newGrid[i][j-1] == -1) {
                    coast++;
                }
                if(newGrid[i][j+1] == -1) {
                    coast++;
                }
            }
        }
        return coast;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1,1,1,1,0},
                        {0,1,0,1,1,0},
                        {1,1,1,0,0,0},
                        {0,0,0,0,1,0},
                        {0,0,0,0,0,0}};
        System.out.println(coastLength(grid));
    }
}

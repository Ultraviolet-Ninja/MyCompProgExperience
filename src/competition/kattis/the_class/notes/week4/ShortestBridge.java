package competition.kattis.the_class.notes.week4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {

    static void printGrid(int[][] grid) {
        for(int[] row : grid) {
            for (int i : row) {
                System.out.print(i);
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void searchAndFill(int[][] grid, int i, int j, Queue<int[]> openList) {
        int rows = grid.length;
        int columns = grid[0].length;

        // Check if position is connected to the 'O' region
        //   Can't be off the edge of the grid
        if (i < 0 || i >= rows || j < 0 || j >= columns || grid[i][j] != 1) {
            return;
        }

        // Mark the region as connected
        grid[i][j] = -1;

        // Add grid location to the open list
        openList.add(new int[]{i, j});

        // Search the connected cells
        searchAndFill(grid, i - 1, j, openList);
        searchAndFill(grid, i + 1, j, openList);
        searchAndFill(grid, i, j - 1, openList);
        searchAndFill(grid, i, j + 1, openList);
    }

    public static int shortestBridge(int[][] grid) {
        boolean foundIsland = false;
        int rows = grid.length;
        int columns = grid[0].length;

        // Search for and fill the first island
        Queue<int[]> openList = new LinkedList<>();
        for (int i = 0; i < rows && !foundIsland; i++) {
            for (int j = 0; j < columns && !foundIsland; j++) {
                if (grid[i][j] == 1) {
                    // Island found, fill the island and build open list for
                    //    breadth first search
                    searchAndFill(grid, i, j, openList);
                    foundIsland = true;
                }
            }
        }

        // TRICK - using an offset array can
        //    reduce the amount of if statements needed
        // Here there are 4 neighbor cells with offset in row and column
        int[] offsetRow = {-1, 0, 1, 0};
        int[] offsetColumn = {0, 1, 0, -1};
        int directionCount = 4;

        printGrid(grid);

        // Starting at the island found, search for the other island
        while (!openList.isEmpty()) {

            // Pull an element off the search list
            int[] curr = openList.remove();
            int row = curr[0];
            int column = curr[1];

            System.out.println(Arrays.toString(curr));

            // Check to see if neighbors have been set yet
            // If not, set the step count and add to open list for search
            //   NOTE: negative numbers used to indicate step counts
            //         since islands are marked with positive numbers
            for(int i = 0; i < directionCount; i++) {
                // Calculate the neighbor coordinate
                int neighborRow = row + offsetRow[i];
                int neighborColumn = column + offsetColumn[i];

                // Check if neighbor is in the grid
                if(0 <= neighborRow && neighborRow < rows &&
                        0 <= neighborColumn && neighborColumn < columns) {

                    // Neighbor is in the grid, check if it is water ('0')
                    //   and mark the offset distance
                    if (grid[neighborRow][neighborColumn] == 0) {
                        grid[neighborRow][neighborColumn] = grid[row][column] - 1;
                        openList.add(new int[]{neighborRow, neighborColumn});
                    }
                    // Check of neighbors is an island, then we've found the shortest
                    //   distance, return it
                    else if (grid[neighborRow][neighborColumn] == 1) {
                        return (-1 * grid[row][column]) - 1;
                    }
                }
            }

            printGrid(grid);
        }

        // Empty open list, must not have found the other island
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0},
                        {1,1,1,1,0,0,0,1,0,0},
                        {1,0,0,1,0,0,0,1,0,1},
                        {1,0,0,1,0,0,0,1,1,1},
                        {0,0,0,1,0,0,0,0,1,0},
                        {0,0,0,1,0,0,1,1,1,0},
                        {0,0,0,0,0,0,1,1,0,0},
                        {0,0,0,0,0,0,1,0,0,0},
                        {0,0,0,0,0,0,1,0,0,0}};
        System.out.println(shortestBridge(grid));
    }
}

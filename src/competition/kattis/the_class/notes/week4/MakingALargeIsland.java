package competition.kattis.the_class.notes.week4;

import java.util.ArrayList;
import java.util.HashSet;

public class MakingALargeIsland {
    public static int searchAndFill(int[][] grid, int i, int j, int value) {
        int rows = grid.length;
        int columns = grid[0].length;

        // Check if position is part of an island
        //   Can't be off the edge of the grid
        //   Islands are 1's not 0's
        if(i < 0 || i >= rows ||  j < 0 || j >= columns || grid[i][j] != 1) {
            return 0;
        }

        // Found an island segment, mark it with the value
        grid[i][j] = value;

        // Search neighbor cells
        return 1 + searchAndFill(grid, i - 1, j, value) + searchAndFill(grid, i + 1, j, value)
                 + searchAndFill(grid, i, j - 1, value) + searchAndFill(grid, i, j + 1, value);
    }

    public static int largestIsland(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        ArrayList<Integer> sizes = new ArrayList<>();

        final int startingId = 2;
        int id = startingId;

        // Build list of island sizes
        for (int i = 0;  i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(grid[i][j] == 1) {
                    sizes.add(searchAndFill(grid, i, j, id++));
                }
            }
        }

        // Didn't find any islands
        if (id == startingId) {
            return 1;
        }
        // All grid cells == 1? i.e. one big island
        else if(sizes.get(0) == rows * columns) {
            return sizes.get(0);
        }

        int maxSize = 1;
        // Search for a '0' (not an island)
        //   That could be flipped, find its neighbors and add the size
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {

                // Found a 0 grid location find the neighbor islands
                if(grid[i][j] == 0) {
                    HashSet<Integer> neighbors = new HashSet<>();
                    if(i > 0 && grid[i-1][j] != 0) {
                        neighbors.add(grid[i-1][j] - startingId);
                    }
                    if(i < rows-1 && grid[i+1][j] != 0) {
                        neighbors.add(grid[i+1][j] - startingId);
                    }
                    if(j > 0 && grid[i][j-1] != 0) {
                        neighbors.add(grid[i][j-1] - startingId);
                    }
                    if(j < columns-1 && grid[i][j+1] != 0) {
                        neighbors.add(grid[i][j+1] - startingId);
                    }

                    // For each neighbor add the size
                    //   NOTE: the size starts at 1 since the 0 will be flipped to a 1
                    int sum = 1;
                    for(Integer neighbor : neighbors) {
                        sum += sizes.get(neighbor);
                    }

                    // Record the greatest area
                    if(sum > maxSize) {
                        maxSize = sum;
                    }
                }
            }
        }
        return maxSize;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(largestIsland(grid));
    }
}

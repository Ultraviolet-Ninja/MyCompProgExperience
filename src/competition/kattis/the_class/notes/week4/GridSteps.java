package competition.kattis.the_class.notes.week4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GridSteps {

    public static int[][] gridSteps(int n, int x, int y) {

        // Initialize the search grid
        int[][] grid = new int[n][n];
        for(int[] row : grid) {
            Arrays.fill(row, -1);
        }
        grid[x][y] = 0;

        // Initialize the grid to the starting location
        Queue<int[]> openList = new LinkedList<>();
        openList.add(new int[] {x, y});

        while (!openList.isEmpty()) {

            // Pull an element off the search list
            int[] curr = openList.remove();
            int row = curr[0];
            int column = curr[1];

            // Check to see if neighbors have been set yet
            // If not, set the step count and add to open list for search
            if(row > 0 && grid[row-1][column] == -1) {
                grid[row-1][column] = grid[row][column] + 1;
                openList.add(new int[] {row-1, column});
            }
            if(row < n-1 && grid[row+1][column] == -1) {
                grid[row+1][column] = grid[row][column] + 1;
                openList.add(new int[] {row+1, column});
            }
            if(column > 0 && grid[row][column-1] == -1) {
                grid[row][column-1] = grid[row][column] + 1;
                openList.add(new int[] {row, column-1});
            }
            if(column < n-1 && grid[row][column+1] == -1) {
                grid[row][column+1] = grid[row][column] + 1;
                openList.add(new int[] {row, column+1});
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        int[][] grid = gridSteps(5, 1, 2);
        System.out.println(Arrays.deepToString(grid));
    }
}

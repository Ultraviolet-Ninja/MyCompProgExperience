package competition.kattis.the_class.notes.week3;

public class MaxAreaOfIslands {

    public static int search(int[][] grid, int i, int j) {
        int rows = grid.length;
        int columns = grid[0].length;

        // Check if position is part of an island
        //   Can't be off the edge of the grid
        //   Islands are 1's not 0's
        if(i < 0 || i >= rows ||  j < 0 || j >= columns || grid[i][j] != 1) {
            return 0;
        }

        // Found an island segment, mark it as viewed
        grid[i][j] = 2;

        // Search neighbor cells
        return 1 + search(grid, i - 1, j) + search(grid, i + 1, j)
                 + search(grid, i, j - 1) + search(grid, i, j + 1);
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int maxIsland = 0;

        // Search the grid for islands
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                // Island found, find the area
                if (grid[i][j] == 1) {
                    int islandArea = search(grid, i, j);
                    if(islandArea > maxIsland) {
                        maxIsland = islandArea;
                    }
                }
            }
        }
        return maxIsland;
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
        System.out.println(maxAreaOfIsland(grid));
    }
}



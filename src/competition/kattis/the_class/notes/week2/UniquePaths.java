package competition.kattis.the_class.notes.week2;

public class UniquePaths {
    public static void main(String[] args) {

        // 5 x 5 grid
        // Start at position 0, 0
        // End at position 4, 4
        // Obstacle in row 1, column 3
        // Obstacle in row 2, column 2

        // Initialize the counts
        int rows = 5;
        int columns = 5;
        int[][] count = new int[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                count[i][j] = -1;  // Haven't determined the count yet
            }
        }

        // Across the first row there is one way to get to each position: move to the right
        for(int i = 0; i < columns; i++) {
            count[0][i] = 1;
        }
        // Down the first column there is one way to get to each position: move to down
        for(int i = 0; i < rows; i++) {
            count[i][0] = 1;
        }

        // It is not possible to get to a position with an obstacle
        count[1][3] = 0;
        count[2][1] = 0;

        // Across the rows and down the columns find the count for each cell
        for(int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if(count[i][j] == -1) {
                    count[i][j] = count[i-1][j] + count[i][j-1];
                }
            }
        }

        // Count in the lower right corner
        System.out.println(count[rows-1][columns-1]);
    }
}

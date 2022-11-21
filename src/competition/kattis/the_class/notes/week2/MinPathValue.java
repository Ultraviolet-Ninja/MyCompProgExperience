package competition.kattis.the_class.notes.week2;

import java.util.Arrays;

public class MinPathValue {
    public static void main(String[] args) {

        // Initialize matrix information
        int[][] matrix = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1},
        };
        int m = matrix.length;
        int n = matrix[0].length;

        // Initialize the path costs
        int[][] pathCosts = new int[m][n];

        // Path from the upper left to the upper left is the same as the value of the matrix
        pathCosts[0][0] = matrix[0][0];

        // Going down the first column, the only way to go is down
        for(int i = 1; i < m; i++) {
            pathCosts[i][0] = pathCosts[i-1][0] + matrix[i][0];
        }

        // Going across the first row, the only way to go is right
        for(int i = 1; i < n; i++) {
            pathCosts[0][i] = pathCosts[0][i-1] + matrix[0][i];
        }

        // For every other element, a choice can be made to go down or across
        //   Chose the minimum for each step
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                pathCosts[i][j] = Math.min(pathCosts[i][j - 1], pathCosts[i - 1][j]) + matrix[i][j];
            }
        }

        // The result is the value at the lower left of the pathCosts
        System.out.println(Arrays.deepToString(pathCosts));
        System.out.println(pathCosts[m-1][n-1]);
    }
}

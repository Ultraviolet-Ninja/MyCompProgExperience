package competition.kattis.jazz.lab4;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Done(url = "https://open.kattis.com/problems/hidingplaces")
public class hidingplaces {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);
    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    //All possible moves a Knight can take
    private static final int[][] MOVEMENT_VECTORS = new int[][]{new int[]{2,1}, new int[]{-2,1}, new int[]{2,-1},
            new int[]{-2,-1},new int[]{1,2}, new int[]{-1,2}, new int[]{1,-2},new int[]{-1,-2}};

    private static int[][] grid = new int[8][8];

    public static void main(String[] args) throws IOException {
        //Parsing the first line of input
        int numTests = Integer.parseInt(IN.readLine());
        int[][] startingPoints = new int[numTests][];

        //Gathering successive lines
        for (int i = 0; i < numTests; i++) {
            startingPoints[i] = toNumericCoords(IN.readLine());
        }

        Queue<int[]> openList = new LinkedList<>();

        for (int[] startingPoint : startingPoints) {
            openList.add(new int[] {startingPoint[0], startingPoint[1]});

            //The start to the BFS
            while (!openList.isEmpty()) {
                int[] curr = openList.remove();
                int row = curr[0];
                int column = curr[1];

                //Moving through all possible for a given set of coordinates
                for (int[] vector : MOVEMENT_VECTORS) {
                    int newRow = row + vector[0];
                    int newColumn = column + vector[1];

                    //Boundary checking and seeing if the new location has not been visited yet
                    if (newRow >= 0 && newRow < 8 && newColumn >= 0 && newColumn < 8 && grid[newRow][newColumn] == 0) {
                        grid[newRow][newColumn] = grid[row][column] + 1;
                        openList.add(new int[]{newRow, newColumn});
                    }
                }
            }
            //Finding the max number in the grid
            int max = findMax();
            STRING_BUILDER.append(max).append(" ");

            //Then getting all of the maximum locations
            for (String coord : getMaxLocations(max)) {
                STRING_BUILDER.append(coord).append(" ");
            }

            STRING_BUILDER.append("\n");

            grid = new int[8][8];
        }

        //Final output of the string
        OUT.write(STRING_BUILDER.toString().getBytes());
        OUT.flush();
    }

    private static int[] toNumericCoords(String chessNotation) {
        return new int[]{chessNotation.charAt(0)-'a', chessNotation.charAt(1)-'1'};
    }

    private static int findMax() {
        int max = 0;
        for (int[] row : grid) {
            for (int i : row) {
                if (i > max) {
                    max = i;
                }
            }
        }
        return max;
    }

    private static List<String> getMaxLocations(int max) {
        List<String> result = new ArrayList<>();

        for (int y = 7; y >= 0; y--) {
        for (int x = 0; x < 8; x++) {
                if (grid[x][y] == max)
                    result.add(toChessNotation(x, y));
            }
        }

        return result;
    }

    private static String toChessNotation(int x, int y) {
        return String.valueOf((char) ('a' + x)) +
                (char) ('1' + y);
    }
}

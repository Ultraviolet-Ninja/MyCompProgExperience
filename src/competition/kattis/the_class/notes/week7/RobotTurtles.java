package competition.kattis.the_class.notes.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class RobotTurtles {

    // Direction of the turtle
    //    0 -> up, 1 -> right, 2 -> down, 3 -> left
    // Helper arrays for the offset to the next row or column
    //    based on current turtle direction
    // up (value of 0) reduces the row by 1 but leave the column the same
    // right (value of 1) leaves the row the same but increases the column by 1
    // down (value of 2) increases the row by 1 but leaves the column the same
    // left (value of 3) leaves the row the same but reduces the column by 1
    public static final int[] NEXT_ROW = { -1, 0, 1, 0};
    public static final int[] NEXT_COLUMN = { 0, 1, 0, -1};

    /**
     * State class - stores the state of the turtle
     *     on the board
     */
    private static class State implements Comparable<State> {
        public int row;       // Current row for the turtle
        public int column;    // Current column for the turtle
        public int direction; // Current Orientation of the turtle
        public int distance;  // Distance from the starting location
        public String moves;  // Moves used to reach this location

        public State(int i, int j, int dir, int dst, String m) {
            row = i;
            column = j;
            direction = dir;
            distance = dst;
            moves = m;
        }

        public int compareTo(State other) {
            return distance - other.distance;
        }
    }

    public static String robotTurtles(char[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;

        // Initialize the distance array
        //   First index -> turtle orientation
        //      0 -> up, 1 -> right, 2 -> down, 3 -> left
        //   Second index is row
        //   Third index is column
        //   Element distances are initially infinity
        int[][][] distances = new int[4][8][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    distances[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        // Set turtle's initial location
        //    bottom row - 7
        //    left column - 0
        //    facing right - 1
        distances[1][7][0] = 0;
        PriorityQueue<State> openList = new PriorityQueue<>();
        openList.add(new State(7, 0, 1, 0, ""));

        // Run Dijkstra's to search for the end square 'D'
        while(!openList.isEmpty()) {
            State cur = openList.poll();

            // Continue if we've already visited this location
            if (cur.distance != distances[cur.direction][cur.row][cur.column]) {
                continue;
            }
            // If we found the end, return the moves
            if (grid[cur.row][cur.column] == 'D') {
                return cur.moves;
            }

            // Check possible new locations to search

            // move forward
            int newRow = cur.row + NEXT_ROW[cur.direction];
            int newColumn = cur.column + NEXT_COLUMN[cur.direction];
            boolean inGrid = newRow >= 0 && newRow < rows && newColumn >=0 && newColumn < columns;

            // New location must be in the grid and a space that the turtle can occupy
            if(inGrid && (grid[newRow][newColumn] == '.' || grid[newRow][newColumn] == 'D')) {

                // Is the move worth it in terms of distance?
                if(distances[cur.direction][cur.row][cur.column] + 1 < distances[cur.direction][newRow][newColumn]) {

                    // Set the new distance
                    distances[cur.direction][newRow][newColumn] = distances[cur.direction][cur.row][cur.column] + 1;

                    // Add the target to the openlist
                    openList.add(new State(
                        newRow,
                        newColumn,
                        cur.direction,
                        distances[cur.direction][newRow][newColumn],
                        cur.moves + "F"
                    ));
                }
            }

            // melt ice castle and move forward
            if(inGrid && grid[newRow][newColumn] == 'I') {

                // Is the move worth it in terms of distance?
                if(distances[cur.direction][cur.row][cur.column] + 2 < distances[cur.direction][newRow][newColumn]) {

                    // Set the new distance
                    distances[cur.direction][newRow][newColumn] = distances[cur.direction][cur.row][cur.column] + 2;

                    // Add the target to the openlist
                    openList.add(new State(
                            newRow,
                            newColumn,
                            cur.direction,
                            distances[cur.direction][newRow][newColumn],
                            cur.moves + "XF"
                    ));
                }
            }

            // turn left or right
            for(int turn : new int[] {-1, 1}) {
                int newDirection = (cur.direction + turn + 4) % 4;
                String newMove = (turn == -1 ? "L" : "R");

                // Is the move worth it in terms of distance?
                if(distances[cur.direction][cur.row][cur.column] + 1 < distances[newDirection][cur.row][cur.column]) {

                    // Set the new distance
                    distances[newDirection][cur.row][cur.column] = distances[cur.direction][cur.row][cur.column] + 1;

                    // Add the target to the openlist
                    openList.add(new State(
                            cur.row,
                            cur.column,
                            newDirection,
                            distances[newDirection][cur.row][cur.column],
                            cur.moves + newMove
                    ));
                }
            }
        }

        // Search is done if we get here then there must not be a solution
        return "no solution";
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[][] grid = new char[8][8];
        for(int i = 0; i < 8; i++) {
            String line = reader.readLine();
            for(int j = 0; j < 8; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        System.out.println(robotTurtles(grid));
    }
}

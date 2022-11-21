package competition.kattis.the_class.notes.week6;

import java.util.*;

public class EightPuzzle {

    private static final int SIZE = 3;

    static class PuzzleState {
        ArrayList<ArrayList<Integer>> puzzleConfiguration;
        String moves;

        PuzzleState(ArrayList<ArrayList<Integer>> pc, String m) {
            puzzleConfiguration = pc;
            moves = m;
        }

        @Override
        public int hashCode() {
            return puzzleConfiguration.hashCode();
        }
    }

    /**
     * Create a puzzle as 2D array list from a 2D array
     * @param grid - input grid
     * @return - the puzzle as a 2D array list
     */
    private static ArrayList<ArrayList<Integer>> createPuzzle(int[][] grid) {
        ArrayList<ArrayList<Integer>> rv = new ArrayList<>();
        for(int i = 0; i < SIZE; i++) {
            rv.add(new ArrayList<>());
            for(int j = 0; j < SIZE; j++) {
                rv.get(i).add(grid[i][j]);
            }
        }
        return rv;
    }

    /**
     * Create a deep copy of a puzzle
     * @param other - the puzzle to copy
     * @return - the copied puzzle
     */
    private static ArrayList<ArrayList<Integer>> copyPuzzle(ArrayList<ArrayList<Integer>> other) {
        ArrayList<ArrayList<Integer>> rv = new ArrayList<>();
        for(int i = 0; i < SIZE; i++) {
            rv.add(new ArrayList<>());
            for(int j = 0; j < SIZE; j++) {
                rv.get(i).add(other.get(i).get(j));
            }
        }
        return rv;
    }

    /**
     * Find the empty cell within a puzzle
     * @param puzzle - the puzzle to search
     * @return - the row/column of the empty cell
     */
    private static int[] getEmptyCell(ArrayList<ArrayList<Integer>> puzzle) {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(puzzle.get(i).get(j) == 0) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    /**
     * Makes a move in the given puzzle.  There are 4 options: up, left, down, and right
     * @param puzzle - the puzzle to make the moves to
     * @param rowMove - the specific move to make for the row (up or down)
     * @param columnMove - the specific move to make for the column (left or right)
     * @param emptyCell - the location of the empty cell
     * @return - a new puzzle with the move made
     */
    private static ArrayList<ArrayList<Integer>> makeMove(ArrayList<ArrayList<Integer>> puzzle, int rowMove, int columnMove, int[] emptyCell) {
        int emptyRow = emptyCell[0];
        int emptyColumn = emptyCell[1];

        // Find the new location for the empty cell
        //   Check that the move is still on the board
        int newRow = emptyRow + rowMove;
        int newColumn = emptyColumn + columnMove;
        if(newRow < 0 || newRow >= SIZE || newColumn < 0 || newColumn >= SIZE) {
            return null;
        }

        // Make the move copying the old puzzle and swap the empty cell with the new location
        ArrayList<ArrayList<Integer>> newPuzzle = copyPuzzle(puzzle);
        int temp = newPuzzle.get(emptyRow).get(emptyColumn);
        newPuzzle.get(emptyRow).set(emptyColumn, newPuzzle.get(newRow).get(newColumn));
        newPuzzle.get(newRow).set(newColumn, temp);

        return newPuzzle;
    }

    /**
     * Find the targets for the given puzzle configuration
     * @param puzzle - the starting puzzle configuration
     * @return - A list of puzzles that can be reached from this puzzle
     */
    private static List<PuzzleState> getEdgeTargets(PuzzleState puzzle) {
        int[] emptyCell = getEmptyCell(puzzle.puzzleConfiguration);
        if(emptyCell == null) {
            return new LinkedList<>();
        }

        // Possible moves are up down left or right
        List<PuzzleState> rv = new LinkedList<>();
        ArrayList<ArrayList<Integer>> up = makeMove(puzzle.puzzleConfiguration, -1, 0, emptyCell);
        if(up != null) {
            rv.add(new PuzzleState(up, puzzle.moves + "U"));
        }
        ArrayList<ArrayList<Integer>> left = makeMove(puzzle.puzzleConfiguration,0, -1, emptyCell);
        if(left != null) {
            rv.add(new PuzzleState(left, puzzle.moves + "L"));
        }
        ArrayList<ArrayList<Integer>> down = makeMove(puzzle.puzzleConfiguration,1, 0, emptyCell);
        if(down != null) {
            rv.add(new PuzzleState(down, puzzle.moves + "D"));
        }
        ArrayList<ArrayList<Integer>> right = makeMove(puzzle.puzzleConfiguration,0, 1, emptyCell);
        if(right != null) {
            rv.add(new PuzzleState(right, puzzle.moves + "R"));
        }

        return rv;
    }

    /**
     * Solve a puzzle given by the grid
     * @param p - the puzzle to solve
     * @return - the moves needed to solve the puzzle or 'impossible' if not possible
     */
    public static String solvePuzzle(int[][] p) {

        // Set the goal
        ArrayList<ArrayList<Integer>> goal = createPuzzle(new int[][] {{1, 2, 3},
                                                                       {4, 5, 6},
                                                                       {7, 8, 0}});

        // Create a puzzle from the input grid
        ArrayList<ArrayList<Integer>> start = createPuzzle(p);

        // Initialize the distance counting map to count the number of moves
        Map<ArrayList<ArrayList<Integer>>, Integer> distances = new HashMap<>();
        distances.put(start, 0);  // Takes 0 moves to start

        // Create the open list and start with the current location
        Deque<PuzzleState> openList = new LinkedList<>();
        openList.add(new PuzzleState(start, ""));

        // Search for the target - goal configuration
        while(!openList.isEmpty()) {
            PuzzleState curr = openList.remove();

            // If the goal is found, return the number of steps
            if(curr.puzzleConfiguration.equals(goal)) {
                return curr.moves;
            }

            for(PuzzleState target : getEdgeTargets(curr)) {

                // Ignore this configuration if it's already been seen
                if(distances.containsKey(target.puzzleConfiguration)) {
                    continue;
                }

                // Compute the distance to this configuration and add to the open list
                distances.put(target.puzzleConfiguration, distances.get(curr.puzzleConfiguration) + 1);
                openList.add(target);
            }
        }

        // Finished searching for a path, couldn't find one, return unsolvable
        return "impossible";
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 8, 6},
                        {7, 1, 4},
                        {2, 5, 3}};

        System.out.println(solvePuzzle(grid));
    }
}

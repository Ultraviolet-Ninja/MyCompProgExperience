package kattis.jazz.lab6;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class countingtriangles {
    private static final byte[] NEWLINE = "\n".getBytes();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int lineCount = Integer.parseInt(IN.readLine());
        List<Line[]> lineSetsList = new ArrayList<>();
        Line[] lines = new Line[lineCount];

        //Gathering all inputs
        while (lineCount != 0) {
            for (int i = 0; i < lineCount; i++) {
                lines[i] = new Line(IN.readLine().split(" "));
            }
            lineCount = Integer.parseInt(IN.readLine());
            lineSetsList.add(lines);
            lines = new Line[lineCount];
        }


        //Iterating through each set of lines
        for (var lineSet : lineSetsList) {
            int counter = 0;
            //Calculating the indexes for combinations
            List<int[]> combinationIndexes = generateCombinationIndexes(lineSet.length);

            //Iterating all combinations to see if those lines intersect with each other to form a triangle
            for (int[] combo : combinationIndexes) {
                if (lineSet[combo[0]].doesIntersect(lineSet[combo[1]]) &&
                        lineSet[combo[0]].doesIntersect(lineSet[combo[2]]) &&
                        lineSet[combo[2]].doesIntersect(lineSet[combo[1]])) {
                    counter++;
                }
            }
            OUT.write(String.valueOf(counter).getBytes());
            OUT.write(NEWLINE);
        }

        OUT.flush();
    }

    /**
     * Code cited here: <a href="https://www.baeldung.com/java-combinations-algorithm">...</a>
     * Generates the index combinations for a set unique elements
     * The Combination equation uses 2 parameters, but since r is always 3, n is the only variable
     * that needs to be specified
     *
     * @param n The length of array used to produce combinations
     * @return The List of arrays, all with a length of 3
     */
    private static List<int[]> generateCombinationIndexes(int n) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[3];

        // initialize with the lowest lexicographic combination
        for (int i = 0; i < 3; i++) {
            combination[i] = i;
        }

        while (combination[2] < n) {
            combinations.add(combination.clone());

            // generate next combination in lexicographic order
            int t = 2;
            while (t != 0 && combination[t] == n - 3 + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < 3; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }

        return combinations;
    }

    /**
     * A Line is a pair of (x,y) coordinates that correlate to the ends of the line
     */
    private static class Line {
        private final double x1, y1, x2, y2;

        public Line(String[] array) {
            x1 = Double.parseDouble(array[0]);
            y1 = Double.parseDouble(array[1]);
            x2 = Double.parseDouble(array[2]);
            y2 = Double.parseDouble(array[3]);
        }

        /**
         * Code cited here: <a href="https://bryceboe.com/2006/10/23/line-segment-intersection-algorithm/">...</a>
         *
         * @param value1 The first x coordinate
         * @param value2 The first y coordinate
         * @param value3 The second x coordinate
         * @param value4 The second y coordinate
         * @param value5 The third x coordinate
         * @param value6 The third y coordinate
         * @return True to show that a point intersects a line
         */
        private static boolean ccw(double value1, double value2, double value3,
                                   double value4, double value5, double value6) {
            return (value6 - value2) * (value3 - value1) > (value4 - value2) * (value5 - value1);
        }

        /**
         * Code cited here: <a href="https://bryceboe.com/2006/10/23/line-segment-intersection-algorithm/">...</a>
         *
         * @param other The other line to check an intersection between
         * @return True if there's an intersection between the 2 lines
         */
        public boolean doesIntersect(Line other) {
            return ccw(this.x1, this.y1, other.x1, other.y1, other.x2, other.y2) !=
                    ccw(this.x2, this.y2, other.x1, other.y1, other.x2, other.y2) &&
                    ccw(this.x1, this.y1, this.x2, this.y2, other.x1, other.y1) !=
                            ccw(this.x1, this.y1, this.x2, this.y2, other.x2, other.y2);
        }
    }
}

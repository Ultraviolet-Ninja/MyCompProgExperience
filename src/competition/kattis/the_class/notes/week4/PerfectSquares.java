package competition.kattis.the_class.notes.week4;

import java.util.LinkedList;
import java.util.Queue;

public class PerfectSquares {

    public static int numSquares(int n) {
        // Counts of perfect squares needed to reach a number
        //   index represents the number
        //   value at the index represents the perfect square count
        int[] counts = new int[n + 1];
        counts[0] = 1;

        // Initialize the open list
        Queue<Integer> openList = new LinkedList<>();
        openList.add(0);

        while (!openList.isEmpty()) {

            // Remove the value from the open list
            //   Build children for the search space by adding perfect squares
            //   Stop building children after value is > n
            int curr = openList.remove();
            for (int i = 1; curr + i*i <= n; i++) {

                // Only add the new value if it hasn't been seen yet
                if (counts[curr + i*i] == 0) {
                    counts[curr + i*i] = counts[curr] + 1;
                    openList.add(curr + i*i);
                }

                // Found the value we are looking for?
                //   Return the count of perfect squares
                if (counts[n] != 0) {
                    return counts[n] - 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }
}

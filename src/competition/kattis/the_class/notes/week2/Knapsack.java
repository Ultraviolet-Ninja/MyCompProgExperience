package competition.kattis.the_class.notes.week2;

import java.util.Arrays;

public class Knapsack {

    public static int[][] solve(int capacity, int itemCount, int[] values, int[] weights) {
        int[][] maxValues = new int[itemCount+1][capacity+1];

        for(int item = 1; item <= itemCount; item++) {
            for(int weight = 1; weight <= capacity; weight++) {

                // At each iteration of inner loop the available capacity of the knapsack in creases
                //    Decide whether to include a new item in the knapsack

                // Value without including the item
                int maxValWithoutCurr = maxValues[item - 1][weight];

                // Value with the item - assume zero for now
                int maxValWithCurr = 0;

                // Get the weight of the item
                int weightOfCurr = weights[item - 1];   // NOTE: the item weights are zero based but the
                                                        //       loop counters are 1 based

                // Does the item fit?
                if (weight >= weightOfCurr) {

                    // Ok so the item fits so get the total value if its added
                    maxValWithCurr = values[item - 1];  // NOTE: the item values are zero based but the
                                                        //       loop counters are 1 based

                    // Adding the item reduces the capacity of the knapsack
                    int remainingCapacity = weight - weightOfCurr;

                    //.The value after adding the item consists of the value of the item
                    //    plus the value if the knapsack had the reduced capacity
                    maxValWithCurr += maxValues[item - 1][remainingCapacity];
                }

                // Item fits but is it worth adding?
                //   Pick the largest of the values with or without adding the item
                maxValues[item][weight] = Math.max(maxValWithoutCurr, maxValWithCurr);
            }
        }
        return maxValues;
    }

    public static void recoverItems(int capacity, int itemCount, int[] values, int[] weights, int[][] maxValues) {

        // Backtrack from the solution to determine which items were added
        int remainingValue = maxValues[itemCount][capacity];
        int remainingWeight = capacity;

        for (int item = itemCount; item > 0 && remainingValue > 0; item--) {

            // Does removing the item affect the value of the row without the item
            if (remainingValue != maxValues[item - 1][remainingWeight]) {

                // Yep, this item must be in the knapsack
                System.out.println(item - 1);

                // Reduce the remaining value and capacity and look again
                remainingValue = remainingValue - values[item - 1];
                remainingWeight = remainingWeight - weights[item - 1];
            }
        }
    }

    public static void main(String[] args) {

        int capacity = 10;
        int itemCount = 4;

        int[] values = {10, 40, 30, 50};
        int[] weights = {5, 4, 6, 3};

        int[][] maxValues = solve(capacity, itemCount, values, weights);

        System.out.println(Arrays.deepToString(maxValues));

        System.out.println(maxValues[itemCount][capacity]);
        recoverItems(capacity, itemCount, values, weights, maxValues);
    }
}

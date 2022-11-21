package competition.kattis.the_class.notes.week2;

import java.util.Arrays;

public class ClimbingStairs {

    public static int minClimbingCost(int[] cost) {

        // Base Cases
        if(cost.length == 1) {
            return cost[0];
        }
        if(cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }

        // Set total cost starting on first stair
        //    or skipping the first stair
        int[] total = new int[cost.length];
        total[0] = cost[0];
        total[1] = cost[1];

        // The rest of the stairs going up
        //    Cost is based on current position + the minimum of skipping or not
        for(int i = 2; i < cost.length; i++) {
            total[i] = cost[i] + Math.min(total[i-1], total[i-2]);
        }

        // Cost of going up all the stairs is the value
        //   at the end of the array

        System.out.println(Arrays.toString(total));
        return total[cost.length-1];
    }

    public static void main(String[] args) {
        int[] cost = { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 };

        System.out.println(minClimbingCost(cost));
    }
}

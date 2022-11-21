package competition.kattis.the_class.notes.week7;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class FlightDiscount {

    private static final int FALSE = 0;
    private static final int TRUE = 1;

    private static class Flight implements Comparable<Flight> {
        public long cost;
        public int identifier;
        public int discountUsed;

        public Flight(long c, int id) {
            cost = c;
            identifier = id;
            discountUsed = 0;
        }

        public Flight(long c, int id, int u) {
            cost = c;
            identifier = id;
            discountUsed = u;
        }

        @Override
        public int compareTo(Flight other) {
            long dx = cost - other.cost;
            if(dx == 0) {
                return identifier - other.identifier;
            }
            return (int)dx;
        }
    }

    public static long flightDiscount(int cities, int[][] flights) {

        // Build graph as adjacency list
        ArrayList<ArrayList<Flight>> edges = new ArrayList<>(cities);
        for(int i = 0; i < cities; i++) {
            edges.add(new ArrayList<>());
        }
        // Add flights as edges
        //  NOTE: vertices are 0 based but flights are 1 based
        for (int[] flight : flights) {
            edges.get(flight[0]-1).add(new Flight(flight[2], flight[1]-1));
        }

        // Initialize the counting arrays
        long[][] cost = new long[cities][2];
        for (int i = 0; i < cities; i++) {
            cost[i][0] = Long.MAX_VALUE;
            cost[i][1] = Long.MAX_VALUE;
        }
        // Cost to the first city is 0 (no flights)
        cost[0][FALSE] = 0;
        cost[0][TRUE] = 0;

        // Run Dijkstra's keeping track of counts along the way
        PriorityQueue<Flight> openList = new PriorityQueue<>();
        openList.add(new Flight(0, 0));

        while(!openList.isEmpty()) {
            Flight curr = openList.poll();

            // if the node is already visited, ignore it
            if (cost[curr.identifier][curr.discountUsed] != curr.cost) {
                continue;
            }

            // Break out when we find the target node
            if (curr.identifier == cities - 1) {
                break;
            }

            for(Flight target : edges.get(curr.identifier)) {

                // Haven't used the discount, try it now for all targets
                if(curr.discountUsed == FALSE) {
                    long discountCost = curr.cost + target.cost / 2;  // Integer division takes the floor
                    if(discountCost < cost[target.identifier][1]) {
                        cost[target.identifier][1] = discountCost;
                        openList.add(new Flight(discountCost, target.identifier, TRUE));
                    }
                }

                // Check the cost without using the discount
                if(curr.cost + target.cost < cost[target.identifier][curr.discountUsed]) {
                    cost[target.identifier][curr.discountUsed] = curr.cost + target.cost;
                    openList.add(new Flight(curr.cost + target.cost, target.identifier, curr.discountUsed));
                }
            }
        }

        return cost[cities-1][TRUE];
    }

    public static void main(String[] args) {
        int cities = 3;
        int[][] flights = {{1, 2, 3},
                           {1, 2, 4},
                           {1, 3, 7},
                           {2, 3, 1},
                           {2, 1, 5}};
        System.out.println(flightDiscount(cities, flights));
    }
}

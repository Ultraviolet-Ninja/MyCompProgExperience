package competition.kattis.the_class.notes.week7;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class FlightInfo {

    private static final int MOD = 1000000007;

    private static class Flight implements Comparable<Flight> {
        public long distance;
        public int identifier;

        public Flight(long dist, int id) {
            distance = dist;
            identifier = id;
        }

        @Override
        public int compareTo(Flight other) {
            long dx = distance - other.distance;
            if(dx == 0) {
                return identifier - other.identifier;
            }
            return (int)dx;
        }
    }

    public static void flightInfo(int cities, int[][] flights) {

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
        long[] distance = new long[cities];
        long[] count = new long[cities];
        int[] maxFlights = new int[cities];
        int[] minFlights = new int[cities];
        for (int i = 0; i < cities; i++) {
            distance[i] = Long.MAX_VALUE;
            count[i] = 1;
            maxFlights[i] = 0;
            minFlights[i] = 0;
        }
        // Distance to the first city is 0 (no flights)
        distance[0] = 0;

        // Run Dijkstra's keeping track of counts along the way
        PriorityQueue<Flight> openList = new PriorityQueue<>();
        openList.add(new Flight(0, 0));

        while(!openList.isEmpty()) {
            Flight curr = openList.poll();

            // if the node is already visited, ignore it
            if (distance[curr.identifier] != curr.distance)
                continue;

            for (Flight target : edges.get(curr.identifier)) {
                count[target.identifier] %= MOD;
                // if found shorter path, set the distance and reset counts
                if(target.distance + distance[curr.identifier] < distance[target.identifier]) {
                    distance[target.identifier] = target.distance + distance[curr.identifier];
                    count[target.identifier] = count[curr.identifier];
                    maxFlights[target.identifier] = maxFlights[curr.identifier] + 1;
                    minFlights[target.identifier] = minFlights[curr.identifier] + 1;

                    openList.add(new Flight(distance[target.identifier], target.identifier));
                }
                // with equivalent distance, update counts
                else if(target.distance + distance[curr.identifier] == distance[target.identifier]) {
                    count[target.identifier] += count[curr.identifier];
                    maxFlights[target.identifier] = Integer.max(maxFlights[target.identifier], maxFlights[curr.identifier] + 1);
                    minFlights[target.identifier] = Integer.min(minFlights[target.identifier], minFlights[curr.identifier] + 1);
                }
            }
        }

        System.out.println(distance[cities-1] + " " + count[cities-1] + " " + minFlights[cities-1] + " " + maxFlights[cities-1]);
    }

    public static void main(String[] args) {
        int cities = 4;
        int[][] flights = {{1, 4, 5},
                           {1, 2, 4},
                           {2, 4, 5},
                           {1, 3, 2},
                           {3, 4, 3}};
        flightInfo(cities, flights);
    }
}

package competition.kattis.the_class.notes.week6;

import java.util.*;

public class Dijkstra {

    static class Edge implements Comparable<Edge>{
        public int source;
        public int target;
        public int weight;

        public Edge(int s, int t, int w) {
            source = s;
            target = t;
            weight = w;
        }

        @Override
        public int compareTo(Edge other) {
            return weight - other.weight;
        }
    }

    public static int dijkstra(int vertices, int[][] edgeArray, int source, int target) {

        // Best cost to reach each vertex
        int[] distances = new int[vertices];
        boolean[] visited = new boolean[vertices];

        // Build adjacency list of edges
        ArrayList<PriorityQueue<Edge>> edges = new ArrayList<>();
        for(int i = 0; i < vertices; i++) {
            edges.add(new PriorityQueue<>());
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        // NOTE edges are 0 based but cities are 1 based
        for (int[] edge : edgeArray) {
            edges.get(edge[0]-1).add(new Edge(edge[0]-1, edge[1]-1, edge[2]));
        }

        // Build an open list starting from the source
        Queue<Integer> openList = new LinkedList<>();
        openList.add(source-1);

        distances[source-1] = 0;  // distance to source node is zero

        while(!openList.isEmpty()) {
            int curr = openList.poll();

            // Optimization:
            // Can stop if we found the target
            //   if we are only concerned about distance from source -> target
            /*
            if(curr == target-1) {
                break;
            }
             */

            // Skip the node if we've already visited it
            if(visited[curr]) {
                continue;
            }
            visited[curr] = true;

            for(Edge edge : edges.get(curr)) {
                if(distances[edge.target] > distances[curr] + edge.weight) {
                    distances[edge.target] = distances[curr] + edge.weight;
                }
                openList.add(edge.target);
            }
        }

        return distances[target-1];
    }

    public static void main(String[] args) {
        int vertices = 6;
        int[][] edges = {
                {1,2,1},
                {1,5,5},
                {2,3,5},
                {2,5,3},
                {3,4,1},
                {4,5,5},
                {4,6,2},
        };
        System.out.println(dijkstra(vertices, edges, 1, 6));
    }
}

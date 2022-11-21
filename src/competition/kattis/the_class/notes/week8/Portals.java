package competition.kattis.the_class.notes.week8;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Portals {
    private static class Edge implements Comparable<Edge> {
        public int src;
        public int tgt;
        public int weight;

        public Edge(int s, int t, int w) {
            src = s;
            tgt = t;
            weight = w;
        }

        @Override
        public int compareTo(Edge other) {
            return weight - other.weight;
        }

        @Override
        public String toString() {
            return "[" + src + "->" + tgt + " : " + weight + "]";
        }
    }

    private static int portals(int vertices, int[][] portals) {

        int maxPortals = 2*vertices;

        // Best cost to reach each vertex
        int[] costs = new int[maxPortals];

        // Build adjacency list of edges
        ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
        for(int i = 0; i < maxPortals; i++) {
            edges.add(new ArrayList<>());
            costs[i] = Integer.MAX_VALUE;
        }
        for (int[] portal : portals) {
            int cost = portal[0];
            int up = portal[1]-1;
            int down = portal[2]-1;
            int left = portal[3]-1;
            int right = portal[4]-1;

            // an edge connecting up and down or connecting left and right has cost 0
            edges.get(up).add(new Edge (up, down, 0));
            edges.get(down).add(new Edge (down, up, 0));
            edges.get(left).add(new Edge (left, right, 0));
            edges.get(right).add(new Edge (right, left, 0));

            // Add the cost to reorder
            //   Since up,down and left,right are tied together only one change
            //   of direction is required
            edges.get(up).add(new Edge(up, left, cost));
            edges.get(left).add(new Edge(left, up, cost));
        }

        // Array list to hold all the edges in the minimal spanning tree
        ArrayList<Edge> mst = new ArrayList<>();

        // Initialize priority queue and cost list
        costs[0] = 0;
        PriorityQueue<Edge> openList = new PriorityQueue<>();
        for(Edge edge : edges.get(0)) {
            openList.add(edge);
        }

        // Prim's algorithm based on BFS
        while(!openList.isEmpty()) {
            Edge curr = openList.poll();
            if(costs[curr.tgt] != 0) {
                mst.add(curr);
                costs[curr.tgt] = 0;
                for(Edge edge : edges.get(curr.tgt)) {
                    if(costs[edge.tgt] > edge.weight) {
                        openList.add(edge);
                        costs[edge.tgt] = edge.weight;
                    }
                }
            }
        }

        int cost = 0;
        for(Edge edge : mst) {
            cost += edge.weight;
        }

        return cost;
    }

    public static void main(String[] args) {
        int[][] portals = {
                {10, 1, 4, 8, 9},
                {11, 1, 2, 5, 6},
                {12, 9, 10, 2, 3},
                {3, 4, 3, 6, 7},
                {15, 10, 8, 7, 5}
        };
        System.out.println(portals(5, portals));
    }
}

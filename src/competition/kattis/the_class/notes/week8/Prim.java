package competition.kattis.the_class.notes.week8;

import java.util.*;

public class Prim {

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

    private static ArrayList<Edge> prim(int vertices, int[][] edgeArray) {

        // Best cost to reach each vertex
        int[] costs = new int[vertices];

        // Build adjacency list of edges
        ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
        for(int i = 0; i < vertices; i++) {
            edges.add(new ArrayList<>());
            costs[i] = Integer.MAX_VALUE;
        }
        for (int[] edge : edgeArray) {
            edges.get(edge[0]).add(new Edge(edge[0], edge[1], edge[2]));
            edges.get(edge[1]).add(new Edge(edge[1], edge[0], edge[2]));
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

        return mst;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1, 4},
                {1, 3, 2},
                {1, 4, 5},
                {2, 4, 3},
                {2, 5, 6},
                {3, 4, 1},
                {3, 6, 8},
                {4, 5, 10},
                {4, 7, 9},
                {4, 8, 2},
                {6, 7, 7},
                {7, 8, 5}
        };
        System.out.println(prim(9, edges).toString());
    }
}

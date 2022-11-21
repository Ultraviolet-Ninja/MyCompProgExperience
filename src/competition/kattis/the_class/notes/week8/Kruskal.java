package competition.kattis.the_class.notes.week8;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {

    private static class UnionFind {
        private final int[] parent;
        private final int[] sizes;
        private int count;

        public UnionFind(int size) {
            parent = new int[size];
            sizes = new int[size];
            count = size; // number of sets

            // Initially all items are in their own set
            for(int i = 0; i < size; i++) {
                parent[i] = i;
                sizes[i] = 1;
            }
        }

        /**
         * Find an item in the set - perform path compression
         * @param item - the item to find
         * @return - the set (root item)
         */
        public int find(int item) {
            if(parent[item] == item) {
                return item;
            }
            parent[item] = find(parent[item]); // Search and path compression
            return parent[item];
        }

        /**
         * Union - join two sets
         * @param item1 - an item in the first set
         * @param item2 - an item in the second set
         */
        public void union(int item1, int item2) {

            // Find the sets
            int set1 = find(item1);
            int set2 = find(item2);

            // Sets must be disjoint
            if(set1 == set2) {
                return;
            }

            // Merge by setting the parent
            //   Smaller set becomes child of larger set
            if(sizes[set1] > sizes[set2]) {
                parent[set1] = set2;
                sizes[set2] += sizes[set1];
            } else {
                parent[set2] = set1;
                sizes[set1] += sizes[set2];
            }

            count--;
        }
    }

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

    private static ArrayList<Edge> kruskal(int vertices, int[][] edgeArray) {
        // Build an array of edges
        Edge[] edges = new Edge[edgeArray.length];
        for(int i = 0; i < edgeArray.length; i++) {
            edges[i] = new Edge(edgeArray[i][0], edgeArray[i][1], edgeArray[i][2]);
        }

        // Sort the array by weight
        Arrays.sort(edges);

        // Array list to hold all the edges in the minimal spanning tree
        ArrayList<Edge> mst = new ArrayList<>();

        // Union find structure for storing vertex sets
        UnionFind nodes = new UnionFind(vertices+1);

        // Search all the edges
        for(Edge edge : edges) {

            // Find the vertex set
            int x = nodes.find(edge.src);
            int y = nodes.find(edge.tgt);

            // Don't connect the edge if it doesn't connect components
            if(x == y) {
                continue;
            }

            // Add the edge to the MST
            mst.add(edge);

            // Join the sets
            nodes.union(x, y);

            // Done when a single set of vertices exists
            if(nodes.count == 1) {
                break;
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
        System.out.println(kruskal(9, edges).toString());
    }
}

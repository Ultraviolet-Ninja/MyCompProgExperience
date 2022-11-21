package competition.kattis.the_class.notes.week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ArcticNetwork {
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

    private static class Point {
        int x;
        int y;

        public Point(int i, int j) {
            x = i;
            y = j;
        }
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
    }

    private static class Edge implements Comparable<Edge> {
        public int src;
        public int tgt;
        public double weight;

        public Edge(int s, int t, double w) {
            src = s;
            tgt = t;
            weight = w;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(weight, other.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(reader.readLine());

        for(int k = 0; k < tests; k++) {
            StringTokenizer params = new StringTokenizer(reader.readLine(), " ");
            int satelliteCount = Integer.parseInt(params.nextToken());
            int outpostCount = Integer.parseInt(params.nextToken());

            // Zero distance needed if satellites equals outputs
            if(satelliteCount == outpostCount) {
                System.out.println("0.00");
                continue;
            }

            // Build a list of outputs
            Point[] outposts = new Point[outpostCount];
            for(int i = 0; i < outpostCount; i++) {
                StringTokenizer outpostToken = new StringTokenizer(reader.readLine(), " ");
                outposts[i] = new Point(Integer.parseInt(outpostToken.nextToken()), Integer.parseInt(outpostToken.nextToken()));
            }

            // Build an array of edges - distance between outputs indicates edge weight
            ArrayList<Edge> edges = new ArrayList<>();
            for(int i = 0; i < outpostCount-1; i++) {
                for(int j = i+1; j < outpostCount; j++) {
                    edges.add(new Edge(i, j, distance(outposts[i], outposts[j])));
                }
            }

            // Run Kruskal's to find minimal spanning tree
            Collections.sort(edges);

            // Array list to hold all the edges in the minimal spanning tree
            LinkedList<Edge> mst = new LinkedList<>();

            UnionFind nodes = new UnionFind(outpostCount);

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

            // Remove edges from MST for each satellite
            //  NOTE: removing an edge counts for 2 nodes
            //        so start at 1 to avoid removing too many
            for(int i = 1; i < satelliteCount; i++) {
                mst.removeLast();
            }

            // Print out the distance
            System.out.printf("%.2f\n", mst.removeLast().weight);
        }
    }
}

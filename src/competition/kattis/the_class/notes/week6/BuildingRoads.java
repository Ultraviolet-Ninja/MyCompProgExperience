package competition.kattis.the_class.notes.week6;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildingRoads {

    public static void searchAndMark(int vertex, boolean[] visited, ArrayList<ArrayList<Integer>> edges) {
        // Mark the vertex as visited
        visited[vertex] = true;

        // Search for neighbor vertices
        for (int neighbor : edges.get(vertex)) {
            if (!visited[neighbor]) {
                searchAndMark(neighbor, visited, edges);
            }
        }
    }

    public static int[][] buildRoads(int cities, int[][] roads) {

        // Build graph as adjacency list
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>(cities);
        boolean[] visited = new boolean[cities];
        for(int i = 0; i < cities; i++) {
            edges.add(new ArrayList<>());
            visited[i] = false;
        }
        // Add roads as edges
        //  NOTE: vertices are 0 based but cities are 1 based
        for (int[] road : roads) {
            // Roads are bidirectional
            edges.get(road[0]-1).add(road[1]-1);
            edges.get(road[1]-1).add(road[0]-1);
        }

        // Find all the connected components
        ArrayList<Integer> components = new ArrayList<>();
        for(int i = 0; i < cities; i++) {
            if(!visited[i]) {
                components.add(i);
                searchAndMark(i, visited, edges);
            }
        }

        // All cities connected, then no roads are needed
        if(components.size() == 1) {
            return null;
        }

        // Some roads need to be build, return the array of roads to connect the components
        //   NOTE edges are 0 based but cities are 1 based
        int[][] newRoads = new int[components.size()-1][2];
        for(int i = 0; i < components.size()-1; i++) {
            newRoads[i] = new int[] { components.get(i)+1, components.get(i+1)+1 };
        }

        return newRoads;
    }

    public static void main(String[] args) {
        int cities = 4;
        int[][] roads = {{1, 2},
                         {3, 4}};
        System.out.println(Arrays.deepToString(buildRoads(cities, roads)));
    }
}

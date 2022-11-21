package competition.kattis.the_class.notes.week6;

import java.util.*;

public class MessageRoutes {
    public static int[] searchAndMark(int vertex, ArrayList<ArrayList<Integer>> edges) {

        // Initialize tracking arrays
        int nodeCount = edges.size();
        int[] parents = new int[nodeCount];
        boolean[] visited = new boolean[nodeCount];
        for(int i = 0; i < nodeCount; i++) {
            parents[i] = -1;
            visited[i] = false;
        }

        // Initialize the open list
        Queue<Integer> openList = new LinkedList<>();
        openList.add(vertex);

        // Breadth first search through the nodes marking the parent tree
        while(!openList.isEmpty()) {
            int curr = openList.remove();
            for(int target : edges.get(curr)) {
                if(!visited[target]) {
                    visited[target] = true;
                    parents[target] = curr;
                    openList.add(target);
                }
            }
        }

        // Done with search return parent tree
        return parents;
    }

    public static Queue<Integer> messageRoutes(int computers, int[][] connections) {

        // Build graph as adjacency list
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>(computers);
        for(int i = 0; i < computers; i++) {
            edges.add(new ArrayList<>());
        }
        // Add connections as edges
        //  NOTE: vertices are 0 based but computers are 1 based
        for (int[] connection : connections) {
            // connections are bidirectional
            edges.get(connection[0]-1).add(connection[1]-1);
            edges.get(connection[1]-1).add(connection[0]-1);
        }

        int[] parents = searchAndMark(1, edges);

        // Couldn't find the end computer
        //   Path not possible
        if(parents[computers-1] == -1) {
            return null;
        }

        // Build the path
        Deque<Integer> path = new LinkedList<>();
        int curr = computers-1;
        while(curr != 0) {
            path.addFirst(curr+1);
            curr = parents[curr];
        }
        path.addFirst(1);

        return path;
    }

    public static void main(String[] args) {
        int computers = 5;
        int[][] connections = {{1, 2},
                               {1, 3},
                               {1, 4},
                               {2, 3},
                               {5, 4}};
        System.out.println(messageRoutes(computers, connections));
    }
}

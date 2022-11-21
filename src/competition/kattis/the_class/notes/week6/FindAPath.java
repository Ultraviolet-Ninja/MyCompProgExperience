package competition.kattis.the_class.notes.week6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FindAPath {

    public static boolean dfs(int curr, int tgt, ArrayList<ArrayList<Integer>> edges, boolean[] visited) {

        // Skip the node if it has already been visited
        if(visited[curr]) {
            return false;
        }

        // If the target was found, then a path exists
        if(curr == tgt) {
            return true;
        }

        // Mark the node visited and continue searching all targets
        visited[curr] = true;
        for(int target : edges.get(curr)) {
            if(dfs(target, tgt, edges, visited)) {
                return true;
            }
        }
        return false;
    }

    public static boolean findAPathDFS(int src, int tgt, int V, int[][] E) {

        // Build graph as adjacency list
        boolean[] visited = new boolean[V];
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            edges.add(new ArrayList<>());
            visited[i] = false;
        }
        // NOTE: vertices are 1 based but arrays are 0 based
        for (int[] e : E) {
            edges.get(e[0]-1).add(e[1]-1);
        }

        return dfs(src-1, tgt-1, edges, visited);
    }

    public static boolean findAPathBFS(int src, int tgt, int V, int[][] E) {

        // Build graph as adjacency list
        boolean[] visited = new boolean[V];
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            edges.add(new ArrayList<>());
            visited[i] = false;
        }
        // NOTE: vertices are 1 based but arrays are 0 based
        for (int[] e : E) {
            edges.get(e[0]-1).add(e[1]-1);
        }

        // Initialize the open list to the source node
        Queue<Integer> openList = new LinkedList<>();
        openList.add(src-1);

        // Search for the target
        while(!openList.isEmpty()) {
            int curr = openList.remove();

            // Skip the node if it has already been visited
            if(visited[curr]) {
                continue;
            }

            // If the target was found, then a path exists
            if(curr == tgt-1) {
                return true;
            }

            // Mark the node visited and continue searching all targets
            visited[curr] = true;
            for(int target : edges.get(curr)) {
                openList.add(target);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int vertices = 6;
        int[][] edges = {
                {1,2},
                {1,5},
                {2,3},
                {2,5},
                {3,4},
                {4,5},
                {4,6},
        };
        System.out.println(findAPathBFS(1, 6, vertices, edges));
        System.out.println(findAPathDFS(1, 6, vertices, edges));
    }
}

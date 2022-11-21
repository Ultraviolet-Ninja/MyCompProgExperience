package competition.kattis.the_class.notes.week6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FindAPathEvenOdd {

    private static final int EVEN = 0;
    private static final int ODD = 1;

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

    public static boolean findAPathDFS(int src, int tgt, int even_odd, int V, int[][] E) {

        // Build graph as adjacency list
        // Augmented graph has twice the vertices
        boolean[] visited = new boolean[V*2];
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < V*2; i++) {
            edges.add(new ArrayList<>());
            visited[i] = false;
        }
        // NOTE: vertices are 1 based but arrays are 0 based
        //   Connect even to odd vertices
        //   Connect odd to even vertices
        for (int[] e : E) {
            int src_even =  e[0]-1;
            int src_odd = e[0]+V-1;
            int tgt_even =  e[1]-1;
            int tgt_odd = e[1]+V-1;
            edges.get(src_even).add(tgt_odd);
            edges.get(src_odd).add(tgt_even);
        }

        // Determine the target (even or odd) based on query type
        int tgt_even = tgt-1;
        int tgt_odd = tgt+V-1;
        int real_target = even_odd == ODD ? tgt_odd : tgt_even;

        return dfs(src-1, real_target, edges, visited);
    }

    public static boolean findAPathBFS(int src, int tgt, int even_odd, int V, int[][] E) {

        // Build graph as adjacency list
        // Augmented graph has twice the vertices
        boolean[] visited = new boolean[V*2];
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < V*2; i++) {
            edges.add(new ArrayList<>());
            visited[i] = false;
        }
        // NOTE: vertices are 1 based but arrays are 0 based
        //   Connect even to odd vertices
        //   Connect odd to even vertices
        for (int[] e : E) {
            int src_even =  e[0]-1;
            int src_odd = e[0]+V-1;
            int tgt_even =  e[1]-1;
            int tgt_odd = e[1]+V-1;
            edges.get(src_even).add(tgt_odd);
            edges.get(src_odd).add(tgt_even);
        }

        // Initialize the open list to the source node
        Queue<Integer> openList = new LinkedList<>();
        openList.add(src-1);

        // Determine the target (even or odd) based on query type
        int tgt_even = tgt-1;
        int tgt_odd = tgt+V-1;
        int real_target = even_odd == ODD ? tgt_odd : tgt_even;

        // Search for the target
        while(!openList.isEmpty()) {
            int curr = openList.remove();

            // Skip the node if it has already been visited
            if(visited[curr]) {
                continue;
            }

            // If the target was found, then a path exists
            if(curr == real_target) {
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
        System.out.println(findAPathBFS(1, 6, ODD, vertices, edges));
        System.out.println(findAPathDFS(1, 6, EVEN, vertices, edges));
    }
}

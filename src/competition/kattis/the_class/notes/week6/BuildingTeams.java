package competition.kattis.the_class.notes.week6;

import java.util.*;

public class BuildingTeams {

    public static int[] buildTeams(int students, int[][] friendships) {

        // Build graph as adjacency list
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>(students);
        boolean[] visited = new boolean[students];
        int[] groups = new int[students];
        for(int i = 0; i < students; i++) {
            edges.add(new ArrayList<>());
            visited[i] = false;
            groups[i] = 0;
        }
        // Add relationships as edges
        //  NOTE: vertices are 0 based but friendships are 1 based
        for (int[] friendship : friendships) {
            // Friendships are bidirectional
            edges.get(friendship[0]-1).add(friendship[1]-1);
            edges.get(friendship[1]-1).add(friendship[0]-1);
        }

        for(int i = 0; i < students; i++) {
            if(!visited[i]) {
                Queue<Integer> openList = new LinkedList<>();
                openList.add(i);
                groups[i] = 1;

                while(!openList.isEmpty()) {
                    int curr = openList.poll();
                    for(int target : edges.get(curr)) {
                        if(groups[target] == groups[curr]) {
                            return null;
                        }
                        if(!visited[target]) {
                            visited[target] = true;
                            groups[target] = groups[curr] == 1 ? 2 : 1;
                            openList.add(target);
                        }
                    }
                }
            }
        }

        return groups;
    }

    public static void main(String[] args) {
        int students = 5;
        int[][] relationships = {
                {1, 2},
                {1, 3},
                {4, 5}
        };
        System.out.println(Arrays.toString(buildTeams(students, relationships)));
    }
}


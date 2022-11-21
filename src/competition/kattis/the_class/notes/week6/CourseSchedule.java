package competition.kattis.the_class.notes.week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {

    public static int[] courseSchedule(int courseCount, int[][] prereqs) {
        int[] courseOrder = new int[courseCount];

        // Build graph as adjacency list
        int[] edgeCount = new int[courseCount];
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < courseCount; i++) {
            edges.add(new ArrayList<>());
            edgeCount[i] = 0;
        }
        // Add prereqs as edges
        for (int[] prereq : prereqs) {
            edges.get(prereq[1]).add(prereq[0]);

            // Record the edge count to later use to add nodes to the openlist
            edgeCount[prereq[0]]++;
        }

        // Search for topological ordering

        // Initialize the open list to all vertices
        //   with no incoming edges
        Queue<Integer> openList = new LinkedList<>();
        for(int i = 0; i < courseCount; i++) {
            if(edgeCount[i] == 0) {
                openList.add(i);
            }
        }

        // Search
        //   Pull off a course from the open list
        //   Add it to the schedule
        //   Remove the edges from that course to children (e.g., reduce the degree)
        //   Add courses to the open list with no incoming edges
        int courseOrderLength = 0;
        while(!openList.isEmpty()) {
            int course = openList.remove();
            courseOrder[courseOrderLength++] = course;
            for(int targetCourse : edges.get(course)) {
                edgeCount[targetCourse]--;
                if(edgeCount[targetCourse] == 0) {
                    openList.add(targetCourse);
                }
            }
        }

        // Search is done, if all courses added to the list then possible
        //   otherwise not possible -> return empty array
        if(courseOrderLength == courseCount) {
            return courseOrder;
        } else {
            return new int[0];
        }
    }

    public static void main(String[] args) {
        int courseCount = 4;
        int [][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(courseSchedule(courseCount, prerequisites)));
    }
}

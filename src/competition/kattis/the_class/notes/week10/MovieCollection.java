package competition.kattis.the_class.notes.week10;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MovieCollection {
    private static class FenwickTree {
        private final int[] tree;

        public FenwickTree(int[] array) {
            tree = new int[array.length + 1];
            Arrays.fill(tree, 0);

            for (int i = 0; i < array.length; i++) {
                update(i+1, array[i]);
            }
        }

        public void update(int index, int adjustment) {
            for (int x = index; x < tree.length; x += (x & -x)) {
                tree[x] += adjustment;
            }
        }

        public int query(int index) {
            int sum = 0;
            for (int x = index; x > 0; x -= (x & -x)) {
                sum += tree[x];
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);

        int numTests = Integer.parseInt(reader.readLine());

        for(int t = 0; t < numTests; t++) {
            StringTokenizer params = new StringTokenizer(reader.readLine(), " ");
            int movies = Integer.parseInt(params.nextToken());
            int requests = Integer.parseInt(params.nextToken());

            // Initially all movies are on the stack
            //   Leave empty spaces to put movies on top after each request
            int[] treeValues = new int[requests + movies];
            Arrays.fill(treeValues, 0);
            for(int i = requests; i < requests + movies; i++) {
                treeValues[i] = 1;
            }
            FenwickTree tree = new FenwickTree(treeValues);

            // Initially movies are in order from 1 to m with 1 on top
            int[] indices = new int[movies+1];
            for(int i = 0; i <= movies; i++) {
                indices[i] = requests + i;
            }

            StringTokenizer requestMovies = new StringTokenizer(reader.readLine(), " ");
            for(int i = 0; i < requests; i++) {
                int movie = Integer.parseInt(requestMovies.nextToken());
                int moves = tree.query(indices[movie]) - 1;

                // Remove the movie from the stack
                tree.update(indices[movie], -1);

                // Put it on top of the stack
                indices[movie] = requests - i;

                // Add it to the stack
                tree.update(indices[movie], 1);

                out.print(moves + " ");
            }
            out.println();
        }
        out.flush();
    }
}

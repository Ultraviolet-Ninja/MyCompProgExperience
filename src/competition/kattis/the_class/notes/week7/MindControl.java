package competition.kattis.the_class.notes.week7;
// https://open.kattis.com/problems/control

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class MindControl {

    private static class UnionFind {
        private final int[] parent;
        private final int[] sizes;

        public UnionFind(int size) {
            parent = new int[size];
            sizes = new int[size];

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
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Union find to store where all the ingredients are
        UnionFind items = new UnionFind(500001);

        int recipes = Integer.parseInt(reader.readLine());
        int answer = 0;

        // Loop over all recipes
        for (int i = 0; i < recipes; i++) {

            StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
            int ingredientsCount = Integer.parseInt(tk.nextToken());
            int currentSize = 0;

            // Find all the cauldrons that contain the ingredients
            //   and add up the size
            HashSet<Integer> cauldron = new HashSet<>();
            for(int j = 0; j < ingredientsCount; j++) {
                int ingredient = Integer.parseInt(tk.nextToken());

                // What set "cauldron" is the ingredient in
                int set = items.find(ingredient);

                // If the cauldron is already in our current mix, skip it
                if(cauldron.contains(set)) {
                    continue;
                }

                // Add the cauldron to the mix
                cauldron.add(set);

                // Add up the total size
                currentSize += items.sizes[set];
            }

            // Does our mix match the current recipe size?
            //   If no throw it away and move on to the next ingredient
            if(currentSize != ingredientsCount) {
                continue;
            }

            // Found a potion we can make
            //   "mix" i.e. union the cauldrons together
            answer++;
            int prevSet = -1;
            for(int set : cauldron) {
                if(prevSet == -1) {
                    prevSet = set;
                }
                items.union(set, prevSet);
            }
        }

        System.out.println(answer);
    }

}

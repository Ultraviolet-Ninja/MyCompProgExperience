package competition.kattis.the_class.notes.week7;

public class UnionFind {
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
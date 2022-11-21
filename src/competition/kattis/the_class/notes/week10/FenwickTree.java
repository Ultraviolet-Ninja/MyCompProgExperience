package competition.kattis.the_class.notes.week10;

import java.util.Arrays;

public class FenwickTree {
    private final int[] tree;

    /**
     * Build a Fenwick tree from the given array
     * @param array the array of values to store
     */
    public FenwickTree(int[] array) {

        // Initialize the tree to empty
        tree = new int[array.length+1];
        Arrays.fill(tree, 0);

        // Add each element to the tree
        for(int i = 0; i < array.length; i++) {
            update(i+1, array[i]);
        }
    }

    /**
     * Updates a value in the tree by the given adjustment
     * @param index - the index of the value to update
     * @param adjustment - the adjustment amount
     */
    public void update(int index, int adjustment) {
        for(int x = index; x < tree.length; x += (x & -x)) {
            tree[x] += adjustment;
        }
    }

    /**
     * Sum query retrieves the sum from element 0 to index
     * @param index - the index of the last element to sum
     * @return - the sum from element 0 to index (inclusive)
     */
    public int query(int index) {
        int sum = 0;
        for(int x = index; x > 0; x -= (x & -x)) {
            sum += tree[x];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] values = {1,6,3,3,5,2,11,0};
        FenwickTree myTree = new FenwickTree(values);

        System.out.println(myTree.query(6));
        myTree.update(4, -2);
        System.out.println(myTree.query(6));
    }
}

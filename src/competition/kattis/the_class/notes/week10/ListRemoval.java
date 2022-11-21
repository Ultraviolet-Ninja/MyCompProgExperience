package competition.kattis.the_class.notes.week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ListRemoval {

    private static class SegmentTreeArray {

        private final int[] tree;
        private final int offsetToValues;

        private int nextPowerOf2(int x) {
            double tmp = Math.ceil(Math.log(x) / Math.log(2));
            return (int)Math.pow(2, tmp);
        }

        public SegmentTreeArray(int[] values) {

            offsetToValues = nextPowerOf2(values.length);

            tree = new int[offsetToValues * 2];

            // Add leaf nodes
            for (int i = 0; i < values.length; i++) {
                tree[offsetToValues + i] = values[i];
            }

            // build the tree by calculating
            // parents
            for (int i = offsetToValues - 1; i > 0; i--) {
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
            }
        }

        public void update(int index, int value) {

            int tempIndex = index + offsetToValues;

            // set value at position
            tree[tempIndex] = value;

            // move upward and update parents
            for (int i = tempIndex; i > 1; i /= 2) {
                // Add left and right children to get parent value
                if (i % 2 == 0) {
                    tree[i / 2] = tree[i] + tree[i + 1];
                } else {
                    tree[i / 2] = tree[i] + tree[i - 1];
                }
            }
        }

        public int query(int position) {

            // Start at the root
            int currPos = 1;
            int tgtPosition = position;

            // While not at a leaf node
            while (currPos < offsetToValues) {

                // Find indices of left and right children
                int leftChild = 2 * currPos;
                int rightChild = 2 * currPos + 1;

                // Decide if need to traverse the left or right
                if(tree[leftChild] >= tgtPosition) {
                    currPos = leftChild;
                } else {
                    tgtPosition -= tree[leftChild];
                    currPos = rightChild;
                }
            }

            // End position found, subtract offset to values to get the correct index
            return currPos - offsetToValues;
        }
    }

    public static int[] listRemoval(int[] list, int[] removals) {
        int[] initialIndices = new int[list.length];
        Arrays.fill(initialIndices, 1);

        // Initialize segment tree to record all items in their initial location
        SegmentTreeArray tree = new SegmentTreeArray(initialIndices);

        int[] removedItems = new int[removals.length];
        for(int i = 0; i < removals.length; i++) {
            int index = tree.query(removals[i]);
            removedItems[i] = list[index];
            tree.update(index, 0);
        }

        return removedItems;
    }

    public static void main(String[] args) {
        int[] list = {2, 6, 1, 4, 2};
        int[] removals = {3, 1, 3, 1 ,1};
        System.out.println(Arrays.toString(listRemoval(list, removals)));
    }
}

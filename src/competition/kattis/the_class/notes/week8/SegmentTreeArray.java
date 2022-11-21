package competition.kattis.the_class.notes.week8;

import java.util.Arrays;

public class SegmentTreeArray {

    private final int[] tree;
    private final int valueCount;

    public SegmentTreeArray(int[] values) {

        tree = new int[values.length * 2];
        valueCount = values.length;

        // Add leaf nodes
        System.arraycopy(values, 0, tree, valueCount, valueCount);

        // build the tree by calculating
        // parents
        for (int i = valueCount - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public void update(int index, int value) {

        int tempIndex = index + valueCount;

        // set value at position
        tree[tempIndex] = value;

        // move upward and update parents
        for (int i = tempIndex; i > 1; i /= 2) {
            // Add left and right children to get parent value
            if(i % 2 == 0) {
                tree[i / 2] = tree[i] + tree[i + 1];
            } else {
                tree[i / 2] = tree[i] + tree[i - 1];
            }
        }
    }

    public int query(int l, int r)
    {
        int sum = 0;

        // Start at the leaf nodes and find the highest parent
        int tempLeft = l + valueCount;
        int tempRight = r + valueCount+1;

        while(tempLeft < tempRight) {
            // If node is not subsumed by parent
            //    include its value in the sum
            if (tempLeft % 2 == 1) {
                sum += tree[tempLeft++];
            }
            if (tempRight % 2 == 1) {
                sum += tree[--tempRight];
            }

            tempLeft /= 2;
            tempRight /= 2;
        }

        return sum;
    }

    public void print() {
        System.out.println(Arrays.toString(tree));
    }

    public static void main(String[] args) {
        int[] values = {1,6,3,3,5,2,11,0};

        long start = System.nanoTime();
        SegmentTreeArray thisTree = new SegmentTreeArray(values);
        thisTree.print();
        System.out.println(thisTree.query(2, 5));

        thisTree.update(5, 5);
        thisTree.print();
        System.out.println(thisTree.query(2, 5));
        long end = System.nanoTime();

        System.out.println("TIME: " + (end - start));
    }
}

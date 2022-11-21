package competition.kattis.the_class.notes.week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Potentiometer {

    private static class SegmentTreeArray {

        private final int[] tree;
        private final int valueCount;

        public SegmentTreeArray(int[] values) {

            tree = new int[values.length * 2];
            valueCount = values.length;

            // Add leaf nodes
            for (int i = 0; i < valueCount; i++) {
                tree[valueCount + i] = values[i];
            }

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
                if (i % 2 == 0) {
                    tree[i / 2] = tree[i] + tree[i + 1];
                } else {
                    tree[i / 2] = tree[i] + tree[i - 1];
                }
            }
        }

        public int query(int l, int r) {
            int sum = 0;

            // Start at the leaf nodes and find the highest parent
            int tempLeft = l + valueCount;
            int tempRight = r + valueCount + 1;

            while (tempLeft < tempRight) {
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
    }

    // Sample Input:
    //    3
    //    100
    //    100
    //    100
    //    6
    //    M 1 1
    //    M 1 3
    //    S 2 200
    //    M 1 2
    //    S 3 0
    //    M 2 3
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int potentiometerCount = Integer.parseInt(reader.readLine());
        int[] potentiometers = new int[potentiometerCount];

        for(int i = 0; i < potentiometerCount; i++) {
            potentiometers[i] = Integer.parseInt(reader.readLine());
        }

        SegmentTreeArray tree = new SegmentTreeArray(potentiometers);

        int opCount = Integer.parseInt(reader.readLine());
        for(int k = 0; k < opCount; k++) {
            StringTokenizer opParams = new StringTokenizer(reader.readLine(), " ");
            if(opParams.nextToken().equals("S")) {
                int potentiometer = Integer.parseInt(opParams.nextToken()) - 1;
                int newValue = Integer.parseInt(opParams.nextToken());
                tree.update(potentiometer, newValue);
            } else {
                int startPotentiometer = Integer.parseInt(opParams.nextToken()) - 1;
                int endPotentiometer = Integer.parseInt(opParams.nextToken()) - 1;
                System.out.println(tree.query(startPotentiometer, endPotentiometer));
            }
        }
    }
}

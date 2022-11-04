package kattis.jazz.lab9;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.BitSet;

public class supercomputer {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");
        int queries = Integer.parseInt(line[1]);
        BitSet bitSet = new BitSet(Integer.parseInt(line[0]));

        for (int i = 0; i < queries; i++) {
            line = IN.readLine().split(" ");

            if (line[0].charAt(0) == 'F') {
                bitSet.flip(Integer.parseInt(line[1]));
            } else {
                int leftRange = Integer.parseInt(line[1]);
                int rightRange = Integer.parseInt(line[2]) + 1;

                var tempBitSet = bitSet.get(leftRange, rightRange);

                BUFFER.append(tempBitSet.cardinality()).append("\n");
            }
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

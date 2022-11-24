package competition.kattis.the_class.lab9;

import competition.annotations.Done;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SiteType(type = Website.KATTIS)
@Done(url = "https://open.kattis.com/problems/supercomputer")
public class supercomputer5 {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");

        int bits = Integer.parseInt(line[0]) + 1;
        int queries = Integer.parseInt(line[1]);
        boolean[] set = new boolean[bits];
        int[] count = new int[bits];

        while (queries-- > 0) {
            line = IN.readLine().split(" ");

            if (line[0].charAt(0) == 'F') {
                int number = Integer.parseInt(line[1]);
                set[number] = !set[number];

                if (set[number]) {
                    for (int x = number; x < count.length; x++)
                        count[x]++;

                } else {
                    for (int x = number; x < count.length; x++)
                        count[x]--;
                }
            } else {
                int leftRange = Integer.parseInt(line[1]);
                int rightRange = Integer.parseInt(line[2]);

                BUFFER.append(count[rightRange] - count[leftRange - 1]).append("\n");
            }
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

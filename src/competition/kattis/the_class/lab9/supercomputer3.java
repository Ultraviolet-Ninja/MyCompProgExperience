package competition.kattis.the_class.lab9;

import competition.annotations.SiteType;
import competition.annotations.TLE;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@SiteType(type = Website.KATTIS)
@TLE(url = "https://open.kattis.com/problems/supercomputer")
public class supercomputer3 {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");
        int queries = Integer.parseInt(line[1]);
        boolean[] set = new boolean[Integer.parseInt(line[0]) + 1];

        for (int i = 0; i < queries; i++) {
            line = IN.readLine().split(" ");

            if (line[0].charAt(0) == 'F') {
                int number = Integer.parseInt(line[1]);
                set[number] = !set[number];
            } else {
                int leftRange = Integer.parseInt(line[1]);
                int rightRange = Integer.parseInt(line[2]);
                int count = 0;
                for (int j = leftRange; j <= rightRange; j++) {
                    if (set[j]) {
                        count++;
                    }
                }
                BUFFER.append(count).append("\n");
            }

        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

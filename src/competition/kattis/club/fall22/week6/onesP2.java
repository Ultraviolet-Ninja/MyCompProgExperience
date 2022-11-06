package competition.kattis.club.fall22.week6;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Done(url = "https://open.kattis.com/problems/ones")
public class onesP2 {
    private static final byte[] NEWLINE = "\n".getBytes();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        long[] longs = IN.lines().mapToLong(Long::parseLong).toArray();

        for (long number : longs) {
            int answer = 1;
            int counter = 1;
            while (counter % number != 0) {
                counter *= 10;
                counter++;
                counter %= number;
                answer++;
            }
            OUT.write(String.valueOf(answer).getBytes());
            OUT.write(NEWLINE);
        }
        OUT.flush();
    }
}

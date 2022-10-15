package kattis.club.week6;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class carrots {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] firstLine = IN.readLine().split(" ");

        int times = Integer.parseInt(firstLine[0]);
        for (int i = 0; i < times; i++) {
            IN.readLine();
        }
        OUT.write(firstLine[1].getBytes());
        OUT.flush();
    }
}

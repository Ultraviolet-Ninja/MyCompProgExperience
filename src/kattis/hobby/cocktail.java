package kattis.hobby;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class cocktail {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int timeInterval = Integer.parseInt(IN.readLine().split(" ")[1]);

        int[] potions = IN.lines()
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int last = potions.length - 1;

        OUT.write(potions[last] > (last * timeInterval) ? "yes".getBytes() : "no".getBytes());

        OUT.flush();
    }
}

package competition.kattis.jazz.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hailstone {
    public static void main(String[] args) throws IOException {
        int counter = 1;
        BufferedReader scn = new BufferedReader(new InputStreamReader(System.in));

        long input = Long.parseLong(scn.readLine());
        while (input != 1) {
            counter++;
            if ((input & 1) == 1) {
                input = (3 * input) + 1;
            } else {
                input >>= 1;
            }
        }

        System.out.println(counter);
    }
}

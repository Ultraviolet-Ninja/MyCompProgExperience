package competition.kattis.the_class.lab1;

import competition.annotations.Done;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Done(url = "https://open.kattis.com/problems/hailstone2")
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

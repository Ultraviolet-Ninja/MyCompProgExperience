package competition.kattis.club.winter23.week1;

import competition.annotations.TLE;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@TLE(url = "https://open.kattis.com/problems/consecutivesums")
public class consecutivesums {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int queries = Integer.parseInt(IN.readLine());

        while (queries-- > 0) {
            int number = Integer.parseInt(IN.readLine());
            sum(number);
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }

    public static void sum(int number) {
        int half = number / 2;

        for (int i = 1; i <= half; i++) {
            int temp = 0;
            for (int j = i; j <= half; j++) {
                temp += j;
                if (temp > number) {
                    break;
                } else if (temp == number) {
                    BUFFER.append(number).append(" = ");
                    stringify(i, j);
                    return;
                }
            }
        }
        BUFFER.append("IMPOSSIBLE\n");
    }

    public static void stringify(int start, int stop) {
        while (start < stop) {
            BUFFER.append(start++).append(" + ");
        }
        BUFFER.append(stop).append("\n");
    }
}

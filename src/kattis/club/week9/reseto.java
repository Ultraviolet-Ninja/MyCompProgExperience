package kattis.club.week9;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class reseto {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        Set<Integer> crossOuts = new HashSet<>(n);

        for (int trial = 2; trial <= n; trial++) {
            if (isPrime(trial)) {
                for (int i = trial; i <= n; i += trial) {
                    if (!crossOuts.contains(i)) {
                        crossOuts.add(i);
                        k--;
                    }
                    if (k == 0) {
                        OUT.write(BUFFER.append(i).append("\n").toString().getBytes());
                        OUT.flush();
                        return;
                    }
                }
            }
        }
    }

    private static boolean isPrime(int n) {
        if (n <= 3) {
            return n > 1;
        }

        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        int stop = (int) Math.sqrt(n);

        for (int i = 5; i < stop; i+= 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}

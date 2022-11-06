package competition.kattis.club.fall22.week9;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Done(url = "https://open.kattis.com/problems/reseto")
public class reseto {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");

        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        int[] crossOuts = new int[n+1];

        for (int trial = 2; trial <= n; trial++) {
            if (crossOuts[trial] == 0) {
                for (int i = trial; i <= n; i += trial) {
                    if (crossOuts[i] == 0) {
                        crossOuts[i] = i;
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

//    private static boolean isPrime(int n) {
//        if (n <= 3) {
//            return n > 1;
//        }
//
//        if (n % 2 == 0 || n % 3 == 0) {
//            return false;
//        }
//
//        int stop = (int) Math.sqrt(n);
//
//        for (int i = 5; i < stop; i+= 6) {
//            if (n % i == 0 || n % (i + 2) == 0) {
//                return false;
//            }
//        }
//        return true;
//    }
}

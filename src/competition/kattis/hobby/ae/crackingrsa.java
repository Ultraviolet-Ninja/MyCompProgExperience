package competition.kattis.hobby.ae;

import competition.annotations.Unattempted;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Unattempted(url = "https://open.kattis.com/problems/crackingrsa")
public class crackingrsa {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) {

    }

    private static boolean checkPrime(long number) {
        if (number < 4) {
            return number == 2 || number == 3;
        }

        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        long stop = (long) Math.sqrt(number);

        for (long i = 5; i < stop; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}

package competition.kattis.the_class.final_report;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FasterThanWillans {
    private static final int[] MEMOIZED_PRIME_ARRAY;
    private static int foundPrimes;
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int queries = Integer.parseInt(IN.readLine());

        while (queries-- > 0) {
            int nthPrime = Integer.parseInt(IN.readLine());

            BUFFER.append(findNthPrime(nthPrime)).append("\n");
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }

    private static long findNthPrime(int n) {
        //Protect against bad input
        if (n <= 0)
            return -1;

        //If the prime number is in the array, we don't have to recalculate whether it's prime or not
        if (n <= foundPrimes) {
            return MEMOIZED_PRIME_ARRAY[n-1];
        }

        //The starting number begins at 2 above the previously found prime
        int startNumber = MEMOIZED_PRIME_ARRAY[foundPrimes-1] + 2;
        n -= foundPrimes;

        //Iterate until n is 0, meaning we found the next prime
        while (n > 0) {
            if (isPrime(startNumber)) {
                if (foundPrimes != MEMOIZED_PRIME_ARRAY.length) {
                    MEMOIZED_PRIME_ARRAY[foundPrimes++] = startNumber;
                }
                n--;
            }
            //Iterate to the next number by 2 since we don't need to check even numbers
            if (n != 0)
                startNumber += 2;

            //If the number is divisible by 5, skip it
            if (startNumber % 5 == 0)
                startNumber += 2;
        }

        return startNumber;
    }

    /**
     * Checks to see if the n is prime or not
     * NOTE: All input is guaranteed to be greater than 3
     *
     * @param n The number to check
     * @return True or false whether the input was prime or not
     */
    private static boolean isPrime(long n) {
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        int stop = (int) Math.sqrt(n);

        for (long i = 5; i <= stop; i+=6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    static {
        //Starting out with the first 100 prime numbers
        MEMOIZED_PRIME_ARRAY = new int[1_000_000];
        int[] temp = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
                79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131,
                137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191,
                193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251,
                257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
                317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383,
                389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449,
                457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521,
                523, 541};

        System.arraycopy(temp, 0, MEMOIZED_PRIME_ARRAY, 0, 100);
        foundPrimes = 100;
    }
}

package competition.kattis.the_class.notes.week2;

import java.util.Arrays;

public class Fibonacci {

    public static int fibRecursive(int n) {
        if(n == 0 || n == 1) {
            return 1;
        }
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

    public static int[] getFibRecursive(int count) {
        int[] result = new int[count];
        for(int i = 0; i < count; i++) {
            result[i] = fibRecursive(i);
        }
        return result;
    }

    public static int fibIterative(int n) {
        int prevValue = 1;
        int prevPrevValue = 0;
        int currValue = 1;
        for(int i = 0; i < n; i++) {
            currValue = prevValue + prevPrevValue;
            prevPrevValue = prevValue;
            prevValue = currValue;
        }
        return currValue;
    }

    public static int[] getFibIterative(int count) {
        int[] result = new int[count];
        for(int i = 0; i < count; i++) {
            result[i] = fibIterative(i);
        }
        return result;
    }

    public static int[] getFibDp(int count) {
        int[] result = new int[count];
        for(int i = 0; i < count; i++) {
            if(i == 0 || i == 1) {
                result[i] = 1;
            } else {
                result[i] = result[i-1] + result[i-2];
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int count = 20;

        long start = 0;
        long end = 0;
        int[] result;

        start = System.nanoTime();
        result = getFibRecursive(count);
        end = System.nanoTime();
        System.out.println(Arrays.toString(result));
        System.out.println("Runtime (ns): " + (end - start));

        start = System.nanoTime();
        result = getFibIterative(count);
        end = System.nanoTime();
        System.out.println(Arrays.toString(result));
        System.out.println("Runtime (ns): " + (end - start));

        start = System.nanoTime();
        result = getFibDp(count);
        end = System.nanoTime();
        System.out.println(Arrays.toString(result));
        System.out.println("Runtime (ns): " + (end - start));
    }
}

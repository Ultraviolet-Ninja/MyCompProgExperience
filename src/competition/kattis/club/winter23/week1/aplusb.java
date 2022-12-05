package competition.kattis.club.winter23.week1;

import competition.annotations.Incorrect;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Incorrect(url = "https://open.kattis.com/problems/aplusb")
public class aplusb {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        IN.readLine();

        String[] numbers = IN.readLine().split(" ");
        int[] toIntArray = new int[numbers.length];
        int i = 0;
        HashSet<Integer> set = new HashSet<>(numbers.length * 2);
        for (String num : numbers) {
            int n = Integer.parseInt(num);
            toIntArray[i++] = n;
            set.add(n);
        }

        int counter = 0;
        for (int[] combination : generate(numbers.length, 2)) {
            int a = toIntArray[combination[0]];
            int b = toIntArray[combination[1]];
            if (set.contains(a+b)) {
                counter++;
            }
        }

        OUT.write(String.valueOf(counter*2).getBytes());
        OUT.flush();
    }

    public static List<int[]> generate(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[r];

        // initialize with lowest lexicographic combination
        for (int i = 0; i < r; i++) {
            combination[i] = i;
        }

        while (combination[r - 1] < n) {
            combinations.add(combination.clone());

            // generate next combination in lexicographic order
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < r; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }

        return combinations;
    }
}

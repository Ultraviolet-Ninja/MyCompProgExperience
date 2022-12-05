package competition.kattis.hobby.pt;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Done(url = "https://open.kattis.com/problems/sorttwonumbers")
public class sorttwonumbers {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int[] nums = Arrays.stream(IN.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int a = nums[0];
        int b = nums[1];

        OUT.write(BUFFER.append(Math.min(a, b)).append(" ").append(Math.max(a, b)).toString().getBytes());
        OUT.flush();
    }
}

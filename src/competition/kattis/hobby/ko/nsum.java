package competition.kattis.hobby.ko;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("DuplicatedCode")
@Done(url = "https://open.kattis.com/problems/nsum")
public class nsum {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        IN.readLine();
        String[] arr = IN.readLine().split(" ");
        int sum = 0;
        for (String num : arr) {
            sum += Integer.parseInt(num);
        }
        OUT.write(String.valueOf(sum).getBytes());
        OUT.flush();
    }
}

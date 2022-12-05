package competition.kattis.hobby.pt;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("DuplicatedCode")
@Done(url = "https://open.kattis.com/problems/triarea")
public class triarea {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] arr = IN.readLine().split(" ");
        int prod = 1;
        for (String num : arr) {
            prod *= Integer.parseInt(num);
        }
        OUT.write(String.valueOf(prod/2.0).getBytes());
        OUT.flush();
    }
}

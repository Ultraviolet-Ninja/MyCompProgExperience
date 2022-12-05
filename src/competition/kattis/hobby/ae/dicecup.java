package competition.kattis.hobby.ae;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Done(url = "https://open.kattis.com/problems/dicecup")
public class dicecup {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");
        int a = Integer.parseInt(line[0]);
        int b = Integer.parseInt(line[1]);
        int min = Math.min(a, b);
        int max = Math.max(a, b) + 1;

        for (int i = min; i++ < max;) {
            BUFFER.append(i).append("\n");
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

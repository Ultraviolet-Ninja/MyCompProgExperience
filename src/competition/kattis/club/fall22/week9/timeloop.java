package competition.kattis.club.fall22.week9;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Done(url = "https://open.kattis.com/problems/timeloop")
public class timeloop {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int times = Integer.parseInt(IN.readLine());
        for (int i = 1; i <= times; i++) {
            BUFFER.append(i).append(" Abracadabra\n");
        }
        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

package competition.kattis.club.fall22.week10;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Done(url = "https://open.kattis.com/problems/sgcoin")
public class sgcoin {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        long initialHash = Long.parseLong(IN.readLine());
        int modVal = 1000000007;

        for(int i = 0; i < 2; i++) {
            long val = initialHash * 31 + 'a';
            val %= modVal;

            val *= 7;
            val %= modVal;
            long token = 10000000 - (val % 10000000);

            BUFFER.append("a ").append(token).append("\n");
            initialHash = (val + token) % modVal;
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

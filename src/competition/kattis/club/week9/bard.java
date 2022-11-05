package competition.kattis.club.week9;

import competition.annotations.Unattempted;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Unattempted(url = "https://open.kattis.com/problems/bard")
public class bard {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int villagers = Integer.parseInt(IN.readLine());
        int evenings = Integer.parseInt(IN.readLine());

    }
}

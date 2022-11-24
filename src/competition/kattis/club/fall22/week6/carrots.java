package competition.kattis.club.fall22.week6;

import competition.annotations.Done;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@SiteType(type = Website.KATTIS)
@Done(url = "https://open.kattis.com/problems/carrots")
public class carrots {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] firstLine = IN.readLine().split(" ");

        int times = Integer.parseInt(firstLine[0]);
        for (int i = 0; i < times; i++) {
            IN.readLine();
        }
        OUT.write(firstLine[1].getBytes());
        OUT.flush();
    }
}

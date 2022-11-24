package competition.kattis.club.fall22.week9;

import competition.annotations.SiteType;
import competition.annotations.Unattempted;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@SiteType(type = Website.KATTIS)
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

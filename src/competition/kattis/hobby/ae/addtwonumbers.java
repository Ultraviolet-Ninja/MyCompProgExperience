package competition.kattis.hobby.ae;

import competition.annotations.Done;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SiteType(type = Website.KATTIS)
@Done(url = "https://open.kattis.com/problems/addtwonumbers")
public class addtwonumbers {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");
        BUFFER.append(Integer.parseInt(line[0]) + Integer.parseInt(line[1]));
        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

package competition.kattis.club.fall23.week5;

import competition.annotations.Incorrect;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Incorrect
@SiteType(type = Website.KATTIS)
public class standings {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int iterations = Integer.parseInt(IN.readLine());

        for (int x = 0; x < iterations; x++) {
            int badness = 0;
            IN.readLine();
            int listCount = Integer.parseInt(IN.readLine());
            for (int rank = 1; rank <= listCount; rank++) {
                String team = IN.readLine();
                int desiredRank = Integer.parseInt(team.substring(team.lastIndexOf(' ') + 1));
                badness += Math.abs(rank - desiredRank);
            }
            BUFFER.append(badness)
                    .append('\n');
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

package competition.kattis.the_class.lab9;

import competition.annotations.SiteType;
import competition.annotations.TLE;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

@SiteType(type = Website.KATTIS)
@TLE(url = "https://open.kattis.com/problems/supercomputer")
public class supercomputer2 {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");
        int queries = Integer.parseInt(line[1]);
        Set<String> set = new HashSet<>(Integer.parseInt(line[0]) + 1, 1.0F);

        for (int i = 0; i < queries; i++) {
            line = IN.readLine().split(" ");

            if (line[0].charAt(0) == 'F') {
                String number = line[1];
                if (set.contains(number)) {
                    set.remove(number);
                } else {
                    set.add(number);
                }
            } else {
                int leftRange = Integer.parseInt(line[1]);
                int rightRange = Integer.parseInt(line[2]);
                BUFFER.append(
                        set.stream()
                                .mapToLong(Long::parseLong)
                                .filter(num -> leftRange <= num && rightRange >= num)
                                .count()
                )
                        .append("\n");
            }

        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

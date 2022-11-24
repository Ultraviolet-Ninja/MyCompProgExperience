package competition.kattis.the_class.lab9;

import competition.annotations.Incorrect;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

@SiteType(type = Website.KATTIS)
@Incorrect(url = "https://open.kattis.com/problems/supercomputer")
public class supercomputer4 {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");
        String bits = line[0];
        int queries = Integer.parseInt(line[1]);
        TreeSet<String> set = new TreeSet<>();

        while (queries-- > 0) {
            line = IN.readLine().split(" ");
            String first = line[1];

            if (line[0].charAt(0) == 'F') {
                if (set.contains(first)) {
                    set.remove(first);
                } else {
                    set.add(first);
                }
            } else {
                String second = line[2];
                boolean firstFound = set.contains(first);

                if (first.equals(second)) {
                    BUFFER.append(firstFound ? 1 : 0).append("\n");
                    continue;
                }

                boolean secondFound = set.contains(second);
                int count = set.subSet(first, false, second, false).size();

                if (firstFound) {
                    count++;
                }
                if (secondFound) {
                    count++;
                }

                BUFFER.append(count).append("\n");
            }
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

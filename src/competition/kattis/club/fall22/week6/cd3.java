package competition.kattis.club.fall22.week6;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Done(url = "https://open.kattis.com/problems/cd")
public class cd3 {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] cdCounts = IN.readLine().split(" ");
        int jackCount = Integer.parseInt(cdCounts[0]);
        int jillCount = Integer.parseInt(cdCounts[1]);

        Set<String> set = new HashSet<>(10000);

        while (true) {
            int count = 0;
            while (jackCount-- > 0) {
                set.add(IN.readLine());
            }

            while (jillCount-- > 0) {
                String line = IN.readLine();
                if (set.contains(line)) {
                    count++;
                }
            }

            BUFFER.append(count).append("\n");

            String line = IN.readLine();
            if (line.charAt(0) == '0') {
                break;
            }

            cdCounts = line.split(" ");
            jackCount = Integer.parseInt(cdCounts[0]);
            jillCount = Integer.parseInt(cdCounts[1]);
            set.clear();
        }
        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

package competition.kattis.club.fall22.week6;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

@Done(url = "https://open.kattis.com/problems/cd")
public class cd2 {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] cdCounts = IN.readLine().split(" ");
        int jackCount = Integer.parseInt(cdCounts[0]);
        int jillCount = Integer.parseInt(cdCounts[1]);
        Map<Integer, Integer> numberCounts = new LinkedHashMap<>(Math.min(jackCount*jillCount*2, 7000), 0.9F);

        int current;

        while (true) {
            while (jackCount-- > 0) {
                numberCounts.put(Integer.parseInt(IN.readLine()), 1);
            }

            while (jillCount-- > 0) {
                current = Integer.parseInt(IN.readLine());
                if (numberCounts.containsKey(current)) {
                    int val = numberCounts.get(current);
                    numberCounts.put(current, val + 1);
                }
            }
            countBoth(numberCounts);
            String line = IN.readLine();
            if (line.charAt(0) == '0') {
                break;
            }

            cdCounts = line.split(" ");
            jackCount = Integer.parseInt(cdCounts[0]);
            jillCount = Integer.parseInt(cdCounts[1]);
            numberCounts.clear();
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }

    private static void countBoth(Map<Integer, Integer> counts) {
        int counter = 0;
        for (var val : counts.values()) {
            if (val == 2) {
                counter++;
            }
        }

        BUFFER.append(counter).append("\n");
    }
}

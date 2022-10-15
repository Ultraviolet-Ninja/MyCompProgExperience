package kattis.club.week6;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class cd {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] cdCounts = IN.readLine().split(" ");
        int jackCount = Integer.parseInt(cdCounts[0]);
        int jillCount = Integer.parseInt(cdCounts[1]);
        int both = jillCount + jackCount;
        Set<Integer> jackSet = new HashSet<>(), jillSet = new HashSet<>(),
                resultSet = new HashSet<>();

        int current;

        while (true) {
            for (int i = 0; i < both; i++) {
                current = Integer.parseInt(IN.readLine());

                if (jackCount != 0) {
                    jackSet.add(current);
                    jackCount--;
                } else {
                    jillSet.add(current);
                }
            }

            for (int num : jackSet) {
                if (jillSet.contains(num)) {
                    resultSet.add(num);
                }
            }
            OUT.write(String.valueOf(resultSet.size()).getBytes());
            OUT.write("\n".getBytes());
            String line = IN.readLine();
            if (line.charAt(0) == '0') {
                break;
            }
            cdCounts = line.split(" ");
            jackCount = Integer.parseInt(cdCounts[0]);
            jillCount = Integer.parseInt(cdCounts[1]);
            both = jillCount + jackCount;

            jackSet.clear();
            jillSet.clear();
            resultSet.clear();
        }


        OUT.flush();
    }
}

package competition.kattis.the_class.lab8;

import competition.annotations.Incorrect;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Incorrect(url = "https://open.kattis.com/problems/allfaxnoprinter")
public class allfaxnoprinter {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    private static int[] faxMachines;

    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder("0\n");
        String[] line = IN.readLine().split(" ");

        int jobs = Integer.parseInt(line[0]) - 1;
        faxMachines = new int[Integer.parseInt(line[1])];

        line = IN.readLine().split(" ");
        int previousTimeInterval = Integer.parseInt(line[0]);
        faxMachines[0] = Integer.parseInt(line[1]);

        int timeInterval;
        int optimizationCount = 0;

        for (int i = 0; i < jobs; i++) {
            line = IN.readLine().split(" ");
            timeInterval = Integer.parseInt(line[0]);

            if (timeInterval == previousTimeInterval) {
                int index = getFirstAvailable();
                if (index != -1) {
                    faxMachines[index] = Integer.parseInt(line[1]);
                }
            } else {
                decrementFax();
                int index = getFirstAvailable();
                if (index != -1) {
                    faxMachines[index] = Integer.parseInt(line[1]);
                } else {
                    optimizationCount++;
                }
                previousTimeInterval = timeInterval;
            }
            builder.append(optimizationCount).append("\n");
        }

        OUT.write(builder.toString().getBytes());
        OUT.flush();
    }

    private static void decrementFax() {
        for (int i = 0; i < faxMachines.length; i++) {
            int value = faxMachines[i];
            if (value != 0)
                faxMachines[i] = value - 1;
        }
    }

    private static int getFirstAvailable() {
        for (int i = 0; i < faxMachines.length; i++) {
            if (faxMachines[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}

package competition.kattis.hobby.ae;

import competition.annotations.Done;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;

@SiteType(type = Website.KATTIS)
@Done(url = "https://open.kattis.com/problems/aboveaverage")
public class aboveaverage {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int lineCount = Integer.parseInt(IN.readLine());

        while (lineCount-- > 0) {
            double[] grades = Arrays.stream(IN.readLine().split(" "))
                    .skip(1)
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            double average = average(grades);

            double count = countAboveAverage(grades, average);
            BUFFER.append(roundToThree(count/grades.length*100)).append("%\n");
        }
        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }

    private static double average(double[] array) {
        double sum = 0;
        for (double value : array) {
            sum += value;
        }
        return sum/array.length;
    }

    private static double countAboveAverage(double[] array, double average) {
        double count = 0;
        for (double value : array) {
            if (value > average) {
                count++;
            }
        }
        return count;
    }

    private static double roundToThree(double value) {
        return (Math.ceil(value*1000)) / 1000;
    }
}

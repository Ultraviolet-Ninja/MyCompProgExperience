package competition.kattis.jazz.lab1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Popularity {
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        OutputStream out = new BufferedOutputStream(System.out);
        String[] numberLine = scan.readLine().split(" ");
        int friendPairsCount = Integer.parseInt(numberLine[1]);

        int[] popularityCounts = new int[Integer.parseInt(numberLine[0])];

        for(int i = 0; i < friendPairsCount; i++){
            numberLine = scan.readLine().split(" ");
            int firstIndex = Integer.parseInt(numberLine[0]) - 1;
            int secondIndex = Integer.parseInt(numberLine[1]) - 1;

            popularityCounts[firstIndex]++;
            popularityCounts[secondIndex]++;
        }

        int friendCounter = 1;
        for (int friendPopularity : popularityCounts) {
            int total = friendPopularity - friendCounter++;
            out.write((total + " ").getBytes());
        }
        out.flush();
    }
}

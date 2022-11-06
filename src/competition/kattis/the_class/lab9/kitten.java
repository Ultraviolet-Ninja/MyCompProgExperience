package competition.kattis.the_class.lab9;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class kitten {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int kittenBranch = Integer.parseInt(IN.readLine());
        Map<Integer, List<Integer>> graph = new HashMap<>(101);
        int[] countArray = new int[101];

        String line = IN.readLine();
        while (!"-1".equals(line)) {
            String[] split = line.split(" ");
            int first = Integer.parseInt(split[0]);
            countArray[first]++;
            for (int i = 1; i < split.length; i++) {
                if (!graph.containsKey(first)) {
                    graph.put(first, new ArrayList<>());
                }
                int value = Integer.parseInt(split[i]);
                graph.get(first).add(value);
                countArray[value]++;
            }

            line = IN.readLine();
        }



    }
}

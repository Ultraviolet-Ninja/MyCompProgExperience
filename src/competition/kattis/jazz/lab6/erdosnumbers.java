package competition.kattis.jazz.lab6;

import competition.annotations.Incorrect;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Incorrect(url = "https://open.kattis.com/problems/erdosnumbers")
public class erdosnumbers {
    private static final String[] BUFFER = new String[512];
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        Map<String, List<String>> erdosConnectionMap = new LinkedHashMap<>(1022001, 1);
        Map<String, Integer> erdosDistanceMap = new HashMap<>(1022001, 1);
        List<String[]> lines = IN.lines()
                .map(erdosnumbers::spacialSplit)
                .collect(Collectors.toList());
        List<String> firstAuthorList = new ArrayList<>(lines.size());

        erdosDistanceMap.put("", Integer.MIN_VALUE);
        erdosDistanceMap.put("PAUL_ERDOS", 0);
        for (String[] split : lines) {
            String first = split[0];
            firstAuthorList.add(first);
            if (!erdosConnectionMap.containsKey(first)) {
                erdosConnectionMap.put(first, new ArrayList<>());
                if (!erdosDistanceMap.containsKey(first))
                    erdosDistanceMap.put(first, Integer.MIN_VALUE);
            }

            for (int i = 1; i < split.length; i++) {
                erdosConnectionMap.get(first).add(split[i]);
                erdosDistanceMap.put(split[i], Integer.MIN_VALUE);
            }
        }

        for (var entry : erdosConnectionMap.entrySet()) {
            for (String connection : entry.getValue()) {
                int currentDistance = erdosDistanceMap.get(connection);
                int parentValue = erdosDistanceMap.get(entry.getKey()) + 1;
                if (currentDistance < 0 || parentValue < currentDistance)
                    erdosDistanceMap.put(connection, parentValue);
            }
        }
        
        StringBuilder builder = new StringBuilder();

        for (String firstAuthor : firstAuthorList) {
            int count = erdosDistanceMap.get(firstAuthor);

            builder.append(firstAuthor);
            if (count < 0) {
                builder.append(" no-connection\n");
            } else {
                builder.append(" ").append(count).append("\n");
            }
        }
        OUT.write(builder.toString().getBytes());
        OUT.flush();
    }

    private static String[] spacialSplit(String line) {
        int wordCount = 0, i = 0,
                j = line.indexOf(" ");

        while(j >= 0){
            BUFFER[wordCount++] = line.substring(i,j);
            i = j + 1;
            j = line.indexOf(" ", i);
        }
        BUFFER[wordCount++] =  line.substring(i);
        String[] results = new String[wordCount];
        System.arraycopy(BUFFER, 0, results, 0, wordCount);
        return results;
    }
}

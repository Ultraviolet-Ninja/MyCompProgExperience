package competition.kattis.club.fall23.week6;

import competition.annotations.SiteType;
import competition.annotations.TLE;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static competition.annotations.Website.KATTIS;

@SiteType(type = KATTIS)
@TLE(url = "open.kattis.com/problems/wheresmyinternet")
public class wheresmyinternet {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        Map<Integer, Set<Integer>> graph = new TreeMap<>();
        var lines = IN.lines().map(line -> line.split(" ")).collect(Collectors.toList());
        int houses = Integer.parseInt(lines.get(0)[0]);
        populateKeys(graph, houses);
        lines.remove(0);

        for (var line : lines) {
            int key = Integer.parseInt(line[0]);
            int value = Integer.parseInt(line[1]);
            graph.get(key).add(value);
            graph.get(value).add(key);
        }

        if (graph.containsKey(1)) {
            var visited = new HashSet<Integer>(graph.size() << 1);
            visited.add(1);
            var stack = new ArrayDeque<>(graph.get(1));
            while (!stack.isEmpty()) {
                int vertex = stack.pollLast();
                if (!visited.contains(vertex)) {
                    visited.add(vertex);
                    var edges = graph.get(vertex);
                    stack.addAll(edges);
                }
            }
            if (visited.size() == graph.size()) {
                BUFFER.append("Connected\n");
            } else {
                graph.keySet()
                        .stream()
                        .filter(node -> !visited.contains(node))
                        .forEach(node -> BUFFER.append(node).append('\n'));
            }
        } else {
            graph.forEach((key, value) -> BUFFER.append(key).append('\n'));
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }

    private static void populateKeys(Map<Integer, Set<Integer>> graph, int houses) {
        for (int i = 1; i <= houses; i++) {
            graph.put(i, new LinkedHashSet<>(100));
        }
    }
}

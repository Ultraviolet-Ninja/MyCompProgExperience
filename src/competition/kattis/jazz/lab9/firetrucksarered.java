package competition.kattis.jazz.lab9;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class firetrucksarered {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    private static final Function<Set<Integer>, int[]> TO_ARRAY = set -> set.stream()
            .mapToInt(e -> e)
            .toArray();

    public static void main(String[] args) throws IOException {
        int peopleCount = Integer.parseInt(IN.readLine());
        Map<Integer, Set<Integer>> graph = new LinkedHashMap<>(peopleCount * 2);
        Map<Integer, Set<Integer>> connections = new LinkedHashMap<>(peopleCount * 2);

        for (int person = 1; person <= peopleCount; person++) {
            String[] line = IN.readLine().split(" ");

            for (int j = 1; j < line.length; j++) {
                int value = Integer.parseInt(line[j]);
                if (!graph.containsKey(value)) {
                    graph.put(value, new HashSet<>());
                }

                if (!connections.containsKey(person)) {
                    connections.put(person, new HashSet<>());
                }
                graph.get(value).add(person);
                connections.get(person).add(value);
            }
        }

        graph.entrySet().removeIf(entry -> entry.getValue().size() < 2);
        connections.entrySet().removeIf(entry -> entry.getValue().size() < 2);

        if (connections.size() != peopleCount) {
            OUT.write("impossible\n".getBytes());
        } else {
            int fiveCount = 0;
            for (var entry : graph.entrySet()) {
                var array= TO_ARRAY.apply(entry.getValue());
                int stop = array.length-1;
                int key = entry.getKey();

                for (int i = 0; i < stop; i++) {
                    BUFFER.append(array[i]).append(" ").append(array[stop])
                            .append(" ").append(key).append("\n");
                    fiveCount++;
                    if (fiveCount == 5)
                        break;
                }
                if (fiveCount == 5)
                    break;
            }
            OUT.write(BUFFER.toString().getBytes());
        }
        OUT.flush();
    }
}

package competition.kattis.club.fall22.week4;

import competition.annotations.Incorrect;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Incorrect(url = "https://open.kattis.com/problems/chewbacca")
public class chewbacca {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] firstLine = IN.readLine().split(" ");
        int numNodes = Integer.parseInt(firstLine[0]);
        int maxChildCount = Integer.parseInt(firstLine[1]);
        int queries = Integer.parseInt(firstLine[2]);

        Map<Integer, Integer> reverseConnectionTree = new HashMap<>(numNodes + 1, 1);

        int numberToConnect = 2;
        for (int i = 1; i <= numNodes; i++) {
            for (int j = 0; j < maxChildCount; j++) {
                reverseConnectionTree.put(numberToConnect++, i);
                if (numberToConnect > numNodes) {
                    break;
                }
            }

            if (numberToConnect > numNodes) {
                break;
            }
        }

        Set<Integer> distance = new HashSet<>();
        int first, second;

        for (int i = 0; i < queries; i++) {
            String[] query = IN.readLine().split(" ");
            first = Integer.parseInt(query[0]);
            second = Integer.parseInt(query[1]);

            distance.add(first);
            distance.add(second);

            while (true) {
                if (reverseConnectionTree.containsKey(first)) {
                    first = reverseConnectionTree.get(first);
                    distance.add(first);
                }
                if (reverseConnectionTree.containsKey(second)) {
                    second = reverseConnectionTree.get(second);
                    distance.add(second);
                }

                if (first == second) {
                    distance.add(first);
                    break;
                }
            }
            OUT.write(String.valueOf(distance.size() - 1).getBytes());
            OUT.write("\n".getBytes());
            distance.clear();
        }

        OUT.flush();
    }
}

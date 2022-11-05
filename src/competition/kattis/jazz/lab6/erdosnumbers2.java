package competition.kattis.jazz.lab6;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class erdosnumbers2 {
    private static final String[] BUFFER = new String[512];
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

//    public static void main(String[] args) throws IOException {
//        Map<String, List<String>> erdosConnectionMap = new LinkedHashMap<>(1022001, 1);
//        Map<String, List<Integer>> erdosDistanceMap = new HashMap<>(1022001, 1);
//        List<String[]> lines = IN.lines()
//                .map(erdosnumbers2::spacialSplit)
//                .peek(array -> {
//                    for (String name : array) {
//                        erdosConnectionMap.put(name, new ArrayList<>());
//                    }
//                })
//                .collect(Collectors.toList());
//        List<String> firstAuthorList = new ArrayList<>(lines.size());
//
//        erdosDistanceMap.put("", List.of(Integer.MIN_VALUE));
//        erdosDistanceMap.put("PAUL_ERDOS", List.of(0));
//        for (String[] split : lines) {
//            String first = split[0];
//            firstAuthorList.add(first);
//            if (!erdosDistanceMap.containsKey(first))
//                erdosDistanceMap.put(first, new ArrayList<>());
//
//
//            for (int i = 1; i < split.length; i++) {
//                String coauthor = split[i];
//                erdosConnectionMap.get(first).add(coauthor);
//                erdosConnectionMap.get(coauthor).add(first);
//                erdosDistanceMap.put(split[i], Integer.MIN_VALUE);
//            }
//        }
//
//        for (var entry : erdosConnectionMap.entrySet()) {
//            for (String connection : entry.getValue()) {
//                int currentDistance = erdosDistanceMap.get(connection);
//                int parentValue = erdosDistanceMap.get(entry.getKey()) + 1;
//                if (currentDistance < 0 || parentValue < currentDistance)
//                    erdosDistanceMap.put(connection, parentValue);
//            }
//        }
//
//        StringBuilder builder = new StringBuilder();
//
//        for (String firstAuthor : firstAuthorList) {
//            int count = erdosDistanceMap.get(firstAuthor);
//
//            builder.append(firstAuthor);
//            if (count < 0) {
//                builder.append(" no-connection\n");
//            } else {
//                builder.append(" ").append(count).append("\n");
//            }
//        }
//        OUT.write(builder.toString().getBytes());
//        OUT.flush();
//    }
//
//    private static String[] spacialSplit(String line) {
//        int wordCount = 0, i = 0,
//                j = line.indexOf(" ");
//
//        while(j >= 0){
//            BUFFER[wordCount++] = line.substring(i,j);
//            i = j + 1;
//            j = line.indexOf(" ", i);
//        }
//        BUFFER[wordCount++] =  line.substring(i);
//        String[] results = new String[wordCount];
//        System.arraycopy(BUFFER, 0, results, 0, wordCount);
//        return results;
//    }
}

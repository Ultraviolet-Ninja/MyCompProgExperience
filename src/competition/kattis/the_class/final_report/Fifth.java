package competition.kattis.the_class.final_report;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Fifth {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int numberOfWords = Integer.parseInt(IN.readLine());
        LinkedHashMap<Integer, String> anagramRemoval = new LinkedHashMap<>(numberOfWords * 2);
        int[] codes = new int[numberOfWords];

        int codeCounter = 0;
        while (numberOfWords-- > 0) {
            String line = IN.readLine();
            if (line.length() != 5) continue;
            int code = encodeWord(line);

            if (!anagramRemoval.containsKey(code) && Integer.bitCount(code) == 5) {
                anagramRemoval.put(code, line);
                codes[codeCounter++] = code;
            }
        }

        int[] finalCodes = new int[codeCounter];
        System.arraycopy(codes, 0, finalCodes, 0, codeCounter);

        solve2(finalCodes);

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }

    private static int encodeWord(String word) {
        int result = 0;
        for (char c : word.toCharArray()) {
            result |= (1 << (c - 'a'));
        }
        return result;
    }

    private static Set<Set<Integer>> solve2(int[] codes) {
        Set<Set<Integer>> out = new HashSet<>();
        Map<Integer, Set<Integer>> firstMap = new LinkedHashMap<>(),
                secondMap = new LinkedHashMap<>();

        for (int firstValue : codes) {
            for (int secondValue : codes) {
                if ((firstValue & secondValue) != 0)
                    continue;
                if (!firstMap.containsKey(firstValue | secondValue)) {
                    var temp = new TreeSet<Integer>();
                    temp.add(firstValue);
                    temp.add(secondValue);
                    firstMap.put(firstValue | secondValue, temp);
                }
            }
        }

        for (var entry : firstMap.entrySet()) {
            int currentOrCode  = entry.getKey();
            var set = entry.getValue();

            for (int thirdValue : codes) {
                if ((currentOrCode & thirdValue) == 0) {
                    var temp = new TreeSet<>(set);
                    temp.add(thirdValue);
                    secondMap.put(currentOrCode|thirdValue, temp);
                }
            }
        }
        firstMap.clear();
        for (var entry : secondMap.entrySet()) {
            int currentOrCode  = entry.getKey();
            var set = entry.getValue();

            for (int fourthValue : codes) {
                if ((currentOrCode & fourthValue) == 0) {
                    var temp = new TreeSet<>(set);
                    temp.add(fourthValue);
                    firstMap.put(currentOrCode|fourthValue, temp);
                }
            }
        }


        secondMap.clear();

        for (var entry : firstMap.entrySet()) {
            int currentOrCode  = entry.getKey();
            var set = entry.getValue();

            for (int fifthValue : codes) {
                if ((currentOrCode & fifthValue) == 0) {
                    var temp = new TreeSet<>(set);
                    temp.add(fifthValue);
                    secondMap.put(currentOrCode|fifthValue, temp);
                }
            }
        }

        return null;
    }

}

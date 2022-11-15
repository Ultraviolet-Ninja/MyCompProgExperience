package competition.kattis.the_class.final_report;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FifthCliqueAvenue {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);
    private static final int ARRAY_SIZE = 1 << 26;

    public static void main(String[] args) throws IOException {
        int numberOfWords = Integer.parseInt(IN.readLine());
        List<String> validWords = new ArrayList<>(numberOfWords);
        HashSet<Integer> anagramRemoval = new HashSet<>(numberOfWords * 2);
        int[] maskArray = new int[numberOfWords];

        int i = 0;
        while (numberOfWords-- > 0) {
            String line = IN.readLine();
            if (line.length() != 5) continue;
            int code = encodeWord(line);

            if (!anagramRemoval.contains(code) && Integer.bitCount(code) == 5) {
                anagramRemoval.add(code);
                validWords.add(line);
                maskArray[i++] = code;
            }
        }

        solve(validWords, maskArray);

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

    private static void solve(List<String> words, int[] maskArray) {
        int size = words.size();
        boolean[][] constructionArray = createConstructionArray();

        for (int mask : maskArray) {
            constructionArray[0][mask] = true;
        }

        for (int cnt = 0; cnt < 4; ++cnt) {
            for (int mask = 0; mask < ARRAY_SIZE; ++mask) {
                if (!constructionArray[cnt][mask]) continue;

                for (int i = 0; i < size; ++i) {
                    if ((maskArray[i] & mask) == 0) {
                        constructionArray[cnt+1][maskArray[i] | mask] = true;
                    }
                }
            }
        }


        System.out.println("DNE");

        ArrayDeque<Integer> result = new ArrayDeque<>();
        for (int mask = 0; mask < ARRAY_SIZE; ++mask) {
            if (constructionArray[4][mask]) {
                outputAllSets(constructionArray, words, maskArray, result, mask, 0);
            }
        }
    }

    private static void outputAllSets(boolean[][] constructionArray, List<String> words,
                                      int[] masks, ArrayDeque<Integer> result, int mask, int startingPoint) {
        if (result.size() == 5) {
            for (int resultNumber : result) {
                BUFFER.append(words.get(resultNumber)).append(' ');
            }
            BUFFER.append('\n');
            return;
        }
        int wordCount = words.size();

        for (int currentWord = startingPoint; currentWord < wordCount; ++currentWord) {
            if (((mask & masks[currentWord]) == masks[currentWord]) && (result.size() == 4 ||
                    constructionArray[3-result.size()][mask ^ masks[currentWord]])) {

                result.push(currentWord);
                outputAllSets(constructionArray, words, masks, result, mask ^ masks[currentWord], currentWord + 1);
                result.pop();
            }
        }
    }

    private static boolean[][] createConstructionArray() {
        boolean[][] out = new boolean[5][];
        out[0] = new boolean[ARRAY_SIZE];
        out[1] = new boolean[ARRAY_SIZE];
        out[2] = new boolean[ARRAY_SIZE];
        out[3] = new boolean[ARRAY_SIZE];
        out[4] = new boolean[ARRAY_SIZE];
        return out;
    }
}

package competition.kattis.club.fall22.week9;

import competition.annotations.Unattempted;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Set;

@Unattempted(url = "https://open.kattis.com/problems/1dfroggereasy")
public class froggereasy {
    private static final Set<String> PHRASE_SET = Set.of("magic", "left", "right", "cycle");
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");

        int boardSquares = Integer.parseInt(line[0]);
        int startSquares = Integer.parseInt(line[1]);
        int magicNumber = Integer.parseInt(line[2]);

        int[] secondLine = Arrays.stream(IN.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int hops = 0;


    }
}

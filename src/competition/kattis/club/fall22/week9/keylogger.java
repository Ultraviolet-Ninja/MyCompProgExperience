package competition.kattis.club.fall22.week9;

import competition.annotations.Unattempted;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Unattempted(url = "https://open.kattis.com/problems/keylogger")
public class keylogger {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    private static final List<String> CODE_LIST = List.of("clank", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "");

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(IN.readLine());

        List<String> array = IN.lines().limit(count).collect(Collectors.toList());

        for (String word : array) {

        }
    }
}

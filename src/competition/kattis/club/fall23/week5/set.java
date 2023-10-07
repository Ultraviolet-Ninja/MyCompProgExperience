package competition.kattis.club.fall23.week5;

import competition.annotations.Done;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Done
@SiteType(type = Website.KATTIS)
public class set {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);
    private static final String NO_SET = "no sets";

    public static void main(String[] args) throws IOException {
        String[] array = new String[12];
        for (int i = 0; i < 12;) {
            for (var group : IN.readLine().split(" ")) {
                array[i++] = group;
            }
        }

        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 12; y++) {
                for (int z = 0; z < 12; z++) {
                    if (match(array[x], array[y], array[z]) && x < y && y < z) {
                        BUFFER.append(x + 1).append(' ')
                                .append(y + 1).append(' ')
                                .append(z + 1).append('\n');
                    }
                }
            }
        }

        if (BUFFER.length() != 0) {
            OUT.write(BUFFER.toString().getBytes());
        } else {
            OUT.write(NO_SET.getBytes());
        }
        OUT.flush();
    }

    private static boolean match(String first, String second, String third) {
        int same = 0;
        int diff = 0;
        for (int i = 0; i < 4; i++) {
            char f = first.charAt(i);
            char s = second.charAt(i);
            char t = third.charAt(i);
            if (f == s && f == t) {
                same++;
            } else if (f != s && f != t && t != s) {
                diff++;
            }
        }

        return diff + same == 4;
    }
}

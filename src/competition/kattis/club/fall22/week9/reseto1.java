package competition.kattis.club.fall22.week9;

import competition.annotations.Incorrect;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@SiteType(type = Website.KATTIS)
@Incorrect(url = "https://open.kattis.com/problems/reseto")
public class reseto1 {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");
        Set<Integer> crossOuts = new HashSet<>();

        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        List<Integer> list = new ArrayList<>();

        IntStream.rangeClosed(2, n).forEach(list::add);

        while (k != 0) {
            int p = -1;
            for (int num : list) {
                if (!crossOuts.contains(num) && p == -1) {
                    p = num;
                }

                if (!crossOuts.contains(num) && num % p == 0) {
                    crossOuts.add(num);
                    k--;
                }

                if (k == 0) {
                    BUFFER.append(num).append("\n");
                    break;
                }
            }
            list.removeIf(crossOuts::contains);
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

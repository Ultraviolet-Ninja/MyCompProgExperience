package competition.kattis.hobby.fj;

import competition.annotations.Done;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SiteType(type = Website.KATTIS)
@Done(url = "https://open.kattis.com/problems/fizzbuzz")
public class fizzbuzz {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] nums = IN.readLine().split(" ");

        int fizz = Integer.parseInt(nums[0]);
        int buzz = Integer.parseInt(nums[1]);
        int count = Integer.parseInt(nums[2]);

        for (int i = 0; ++i <= count;) {
            boolean isFizz = i % fizz == 0;
            boolean isBuzz = i % buzz == 0;

            if (isFizz) {
                BUFFER.append("Fizz");
            }

            if (isBuzz) {
                BUFFER.append("Buzz");
            } else if (!isFizz) {
                BUFFER.append(i);
            }
            BUFFER.append("\n");
        }
        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }
}

package competition.kattis.club.fall22.week9;

import competition.annotations.Done;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@SiteType(type = Website.KATTIS)
@Done(url = "https://open.kattis.com/problems/99problems")
public class problems {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(IN.readLine());

        int upNumber = number;
        int downNumber = number;

        int upCount = 0;
        int downCount = 0;

        while (nineNineCheck(upNumber)) {
            upCount++;
            upNumber++;
        }

        while (nineNineCheck(downNumber)) {
            downCount++;
            downNumber--;
        }
        if (downNumber < 0) {
            BUFFER.append(upNumber).append("\n");
        } else if (upNumber < 0) {
            BUFFER.append(downNumber).append("\n");
        } else if (upCount <= downCount) {
            BUFFER.append(upNumber).append("\n");
        } else {
            BUFFER.append(downNumber).append("\n");
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }

    private static boolean nineNineCheck(int number) {
        return (number - 99) % 100 != 0;
    }
}

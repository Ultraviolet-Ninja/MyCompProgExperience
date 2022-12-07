package competition.kattis.hobby.pt;

import competition.annotations.Done;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SiteType(type = Website.KATTIS)
@Done(url = "https://open.kattis.com/problems/sifferprodukt")
public class sifferprodukt {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(IN.readLine());

        while (number > 9) {
            int product = 1;
            while (number > 0) {
                int temp = number % 10;
                if (temp != 0) {
                    product *= temp;
                }
                number /= 10;
            }
            number = product;
        }
        OUT.write(String.valueOf(number).getBytes());
        OUT.flush();
    }
}

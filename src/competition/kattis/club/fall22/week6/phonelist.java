package competition.kattis.club.fall22.week6;

import competition.annotations.SiteType;
import competition.annotations.TLE;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@SiteType(type = Website.KATTIS)
@TLE(url = "https://open.kattis.com/contests/vego4q/problems/phonelist")
public class phonelist {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int rounds = Integer.parseInt(IN.readLine());
        List<String> phoneList = new ArrayList<>();

        for (int i = 0; i < rounds; i++) {
            int count = Integer.parseInt(IN.readLine());
            boolean flag = false;
            for (int j = 0; j < count; j++) {
                String phone = IN.readLine();
                if (phoneList.isEmpty()) {
                    phoneList.add(phone);
                    continue;
                }
                for (String otherPhone : phoneList) {
                    if (otherPhone.startsWith(phone) || phone.startsWith(otherPhone)) {
                        OUT.write("NO\n".getBytes());
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    phoneList.clear();
                    break;
                }
                phoneList.add(phone);
            }
            if (!flag) {
                OUT.write("YES\n".getBytes());
            }
        }
        OUT.flush();
    }
}

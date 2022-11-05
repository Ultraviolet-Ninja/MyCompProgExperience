package competition.kattis.hobby;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Done(url = "https://open.kattis.com/problems/kleptography")
public class kleptography {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        //Grabbing the letter counts
        String[] letterCounts = IN.readLine().split(" ");
        int keyWordLetterCount = Integer.parseInt(letterCounts[0]);
        int cipherTextLetterCount = Integer.parseInt(letterCounts[1]);
        char[] lastLetters = IN.readLine().toCharArray();
        char[] cipherText = IN.readLine().toCharArray();
        char[] plainText = new char[cipherTextLetterCount];

        int temp = cipherTextLetterCount - 1;
        for (int i = keyWordLetterCount - 1; i >= 0; i--) {
            plainText[temp--] = lastLetters[i];
        }

        int temp2 = cipherTextLetterCount - 1;
        while (temp >= 0) {
            int distance = cipherText[temp2] - plainText[temp2--];
            if (distance < 0)
                distance += 26;
            plainText[temp--] = (char) ('a' + distance);
        }
        OUT.write(new String(plainText).getBytes());
        OUT.flush();
    }
}

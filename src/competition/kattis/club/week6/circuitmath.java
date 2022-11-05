package competition.kattis.club.week6;

import competition.annotations.Done;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import static java.lang.Character.isAlphabetic;

@Done(url = "https://open.kattis.com/problems/circuitmath")
public class circuitmath {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        StringBuilder circuitBuilder = new StringBuilder();

        IN.readLine();
        char[] letterValues = IN.readLine().replace(" ", "").toCharArray();
        String circuit = IN.readLine().replace(" ", "");

        int circuitLength = circuit.length();
        char letterIncrement = 'A';
        int letterIndex = 0;
        for (char letter : circuit.toCharArray()) {
            if (letter == letterIncrement) {
                circuitBuilder.append(letterValues[letterIndex++]);
                letterIncrement++;
            } else {
                circuitBuilder.append(letter);
            }
        }

        circuit = circuitBuilder.toString();
        circuitBuilder.setLength(0);

        char twoBefore, oneBefore;

        while (circuitLength != 1) {
            for (int i = 0; i < circuitLength; i++) {
                char currentLetter = circuit.charAt(i);
                switch (currentLetter) {
                    case '+':
                    case '*':
                        twoBefore = circuit.charAt(i - 2);
                        oneBefore = circuit.charAt(i - 1);

                        if (isAlphabetic(twoBefore) && isAlphabetic(oneBefore)) {
                            circuitBuilder.setLength(circuitBuilder.length() - 2);
                            if (currentLetter == '*') {
                                circuitBuilder.append(
                                        (oneBefore == 'T' && twoBefore == 'T') ? 'T' : 'F'
                                );
                            } else {
                                circuitBuilder.append(
                                        (oneBefore == 'T' || twoBefore == 'T') ? 'T' : 'F'
                                );
                            }
                        } else {
                            circuitBuilder.append(currentLetter);
                        }
                        break;
                    case '-':
                        oneBefore = circuit.charAt(i - 1);
                        if (isAlphabetic(oneBefore)) {
                            circuitBuilder.setLength(circuitBuilder.length() - 1);
                            circuitBuilder.append(oneBefore == 'T' ? 'F' : 'T');
                        } else {
                            circuitBuilder.append(currentLetter);
                        }
                        break;
                    default:
                        circuitBuilder.append(currentLetter);
                }

            }

            circuit = circuitBuilder.toString();
            circuitLength = circuit.length();
            circuitBuilder.setLength(0);
        }

        OUT.write(circuit.getBytes());
        OUT.write("\n".getBytes());
        OUT.flush();
    }
}

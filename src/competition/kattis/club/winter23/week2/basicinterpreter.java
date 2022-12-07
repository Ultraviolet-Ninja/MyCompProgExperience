package competition.kattis.club.winter23.week2;

import competition.annotations.RTE;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SiteType(type = Website.KATTIS)
@RTE(url = "https://open.kattis.com/problems/basicinterpreter")
public class basicinterpreter {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        List<Line> lines = IN.lines()
                .map(Line::new)
                .sorted()
                .collect(Collectors.toList());
        Map<String, Integer> variableStack = createMap();

        int size = lines.size();
        for (int i = 0; i < size; i++) {
            Line line = lines.get(i);
            switch (line.lineType) {
                case HARD_CODE_ASSIGNMENT:
                    line.assignVarToNum(variableStack);
                    break;
                case VARIABLE_ASSIGNMENT:
                    line.assignVar(variableStack);
                    break;
                case PRINT:
                    line.print(variableStack);
                    break;
                case PRINT_LN:
                    line.println(variableStack);
                    break;
                default:
                    int temp = line.goTo(variableStack);
                    if (temp != -10) {
                        i = temp;
                    }
            }
        }

        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }

    private static Map<String, Integer> createMap() {
        Map<String, Integer> variableStack = new HashMap<>(27, 1.0f);
        for (int i = 0; i < 26; i ++) {
            variableStack.put(String.valueOf((char) ('A' + i)), 0);
        }
        return variableStack;
    }

    private static class Line implements Comparable<Line> {
        private final int opLineNumber;
        private final LineType lineType;
        private final String grammarLine;

        Line(String line) {
            String[] split = line.split(" ", 3);
            opLineNumber = Integer.parseInt(split[0]);
            String command = split[1];
            grammarLine = split[2];
            switch (command) {
                case "LET":
                    if (grammarLine.contains("+") || grammarLine.contains("-")
                            || grammarLine.contains("*") || grammarLine.contains("/")) {
                        lineType = LineType.VARIABLE_ASSIGNMENT;
                    } else {
                        lineType = LineType.HARD_CODE_ASSIGNMENT;
                    }
                    break;
                case "PRINTLN":
                    lineType = LineType.PRINT_LN;
                    break;
                case "PRINT":
                    lineType = LineType.PRINT;
                    break;
                default:
                    lineType = LineType.CONDITIONAL_GOTO;
                    break;
            }
        }

        public void assignVarToNum(Map<String, Integer> variableStack) {
            String[] lineSplit = grammarLine.split(" ");
            variableStack.put(lineSplit[0], Integer.parseInt(lineSplit[2]));
        }

        public void assignVar(Map<String, Integer> variableStack) {
            String[] lineSplit = grammarLine.split(" ");
            String firstVariable = lineSplit[2];
            String secondVariable = lineSplit[4];

            int a = variableStack.containsKey(firstVariable) ?
                    variableStack.get(firstVariable) :
                    Integer.parseInt(firstVariable);
            int b = variableStack.containsKey(secondVariable) ?
                    variableStack.get(secondVariable) :
                    Integer.parseInt(secondVariable);

            variableStack.put(lineSplit[0], performArithmetic(lineSplit[3].charAt(0), a, b));
        }

        private static int performArithmetic(char op, int a, int b) {
            int result;

            switch (op) {
                case '+':
                    result = a + b;
                    break;
                case '-':
                    result = a - b;
                    break;
                case '*':
                    result = a * b;
                    break;
                default:
                    result = a / b;
            }

            return result;
        }

        public void println(Map<String, Integer> variableStack) {
            print(variableStack);
            BUFFER.append("\n");
        }

        public void print(Map<String, Integer> variableStack) {
            if (grammarLine.charAt(0) == '"') {
                BUFFER.append(grammarLine, 1, grammarLine.length() - 1);
            } else {
                BUFFER.append(variableStack.get(grammarLine));
            }
        }

        public int goTo(Map<String, Integer> variableStack) {
            String[] lineSplit = grammarLine.split(" ");
            String firstVariable = lineSplit[0];
            String secondVariable = lineSplit[2];

            int a = variableStack.containsKey(firstVariable) ?
                    variableStack.get(firstVariable) :
                    Integer.parseInt(firstVariable);
            int b = variableStack.containsKey(secondVariable) ?
                    variableStack.get(secondVariable) :
                    Integer.parseInt(secondVariable);

            if (performBooleanArithmetic(lineSplit[1], a, b)) {
                return Integer.parseInt(lineSplit[5]) / 10 - 2;
            }
            return -10;
        }

        private static boolean performBooleanArithmetic(String op, int a, int b) {
            switch (op) {
                case "=":
                    return a == b;
                case "<":
                    return a < b;
                case "<=":
                    return a <= b;
                case ">":
                    return a > b;
                case ">=":
                    return a >= b;
                case "<>":
                    return a != b;
            }

            return false;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.opLineNumber, o.opLineNumber);
        }

        @Override
        public String toString() {
            return opLineNumber + " " + grammarLine;
        }
    }

    private enum LineType {
        HARD_CODE_ASSIGNMENT, VARIABLE_ASSIGNMENT, PRINT, PRINT_LN, CONDITIONAL_GOTO
    }
}

package kattis.jazz.lab6;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class countingtriangles {
    private static final int X1 = 0, Y1 = 1, X2 = 2, Y2 = 3;
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        int lineCount = Integer.parseInt(IN.readLine());
        List<List<Line>> lineSetsList = new ArrayList<>();
        List<Line> line = new ArrayList<>();

        while (lineCount != 0) {
            for (int i = 0; i < lineCount; i++) {
                line.add(new Line(IN.readLine().split(" ")));
            }
            lineSetsList.add(line);
            line.clear();
            lineCount = Integer.parseInt(IN.readLine());
        }


    }

    private static class Line {
        private final double x1, y1, x2, y2;

        public Line(String[] array) {
            x1 = Double.parseDouble(array[0]);
            y1 = Double.parseDouble(array[1]);
            x2 = Double.parseDouble(array[2]);
            y2 = Double.parseDouble(array[3]);
        }
    }
}

package competition.kattis.the_class.lab4;

import competition.annotations.SiteType;
import competition.annotations.Unattempted;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@SiteType(type = Website.KATTIS)
@Unattempted(url = "https://open.kattis.com/problems/islands")
public class islands {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    private static final List<int[]> MOVEMENT_VECTORS = List.of(new int[]{-1,0}, new int[]{0,1}, new int[]{0,-1},
            new int[]{1,0});

    public static void main(String[] args) throws IOException {
        String[] gridSize = IN.readLine().split(" ");
        int lines = Integer.parseInt(gridSize[0]);

        char[][] grid = new char[lines][];

        for (int i = 0; i < lines; i++) {
            grid[i] = IN.readLine().replace('C', 'L').toCharArray();
        }
        int yLimit = grid[0].length;

        Queue<int[]> openList = new LinkedList<>();
        int offset = 0;

        for (int x = 0; x < lines; x++) {
            for (int y = 0; y < yLimit; y++) {
                if (grid[x][y] == 'L') {
                    grid[x][y] = (char)('a'+offset);
                    openList.add(new int[] {x,y});
                    while (!openList.isEmpty()) {
                        for (int[] vector : MOVEMENT_VECTORS) {
                            int newRow = x + vector[0];
                            int newColumn = y + vector[1];

                            if (newRow >= 0 && newRow < lines && newColumn >= 0 && newColumn < yLimit && grid[newRow][newColumn] == 'L') {
                                grid[newRow][newColumn] = (char)('a'+offset);
                                openList.add(new int[]{newRow, newColumn});
                            }
                        }
                    }
                    offset++;
                }
            }
        }

    }
}

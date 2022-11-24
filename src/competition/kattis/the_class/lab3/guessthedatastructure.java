package competition.kattis.the_class.lab3;

import competition.annotations.Incorrect;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

@SiteType(type = Website.KATTIS)
@Incorrect(url = "https://open.kattis.com/problems/guessthedatastructure")
public class guessthedatastructure {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);


    public static void main(String[] args) throws IOException {
        String line;
        boolean proceed = true;
        while ((line = IN.readLine()) != null && proceed) {
            proceed = determineDataStructure(Integer.parseInt(line));
        }
        OUT.flush();
    }

    public static boolean determineDataStructure(int numberOfLines) throws IOException {
        int resultCode = 0;
        LinkedList<String> stack = new LinkedList<>();
        LinkedList<String> queue = new LinkedList<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(numberOfLines, Collections.reverseOrder());

        for (int i = 0; i < numberOfLines; i++) {
            String[] line = IN.readLine().split(" ");
            String actionNumber = line[1];
            if ("1".equals(line[0])) {
                stack.add(actionNumber);
                queue.add(actionNumber);
                priorityQueue.add(actionNumber);
            } else if (stack.isEmpty()) {
                OUT.write("impossible\n".getBytes());
                return false;
            } else {
                if (!actionNumber.equals(stack.pollLast())) {
                    resultCode |= 1;
                }

                if (!actionNumber.equals(queue.pollFirst())) {
                    resultCode |= 2;
                }

                if (!actionNumber.equals(priorityQueue.poll())) {
                    resultCode |= 4;
                }
            }
        }
        switch (resultCode) {
            case 7:
                OUT.write("impossible\n".getBytes());
                break;
            case 6:
                OUT.write("stack\n".getBytes());
                break;
            case 5:
                OUT.write("queue\n".getBytes());
                break;
            case 3:
                OUT.write("priority queue\n".getBytes());
                break;
            default:
                OUT.write("not sure\n".getBytes());
        }

        return true;
    }
}

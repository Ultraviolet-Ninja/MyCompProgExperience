package competition.kattis.the_class.lab1;

import competition.annotations.Done;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

@SiteType(type = Website.KATTIS)
@Done(url = "https://open.kattis.com/problems/cinema")
public class cinema {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] first = IN.readLine().split(" ");
        int firstNumber = Integer.parseInt(first[0]);
        int secondNumber = Integer.parseInt(first[1]);

        //make a queue for the groups in line and insert them into the queue
        ArrayDeque<Integer> groups = new ArrayDeque<>();

        for (String number : IN.readLine().split(" ")) {
            groups.add(Integer.parseInt(number));
        }

        int peopleCount = 0;
        int groupsAcceptedCount = 0;
        int i = secondNumber;
        while (i-- > 0) {
            int groupSize = groups.pop();
            if (peopleCount + groupSize <= firstNumber) {
                //if they can fit then take not of this and add it to our running count
                groupsAcceptedCount++;
                //update the current theater size
                peopleCount += groupSize;
            }
        }
        OUT.write(String.valueOf(secondNumber-groupsAcceptedCount).getBytes());
        OUT.flush();
    }

    public static void mainBilal() {
        Scanner scan = new Scanner(System.in);

        //get the first line which contains the number of groups and seats
        int[] line1 = new int[] {scan.nextInt(), scan.nextInt()};

        //make a queue for the groups in line and insert them into the queue
        Deque<Integer> groups = new ArrayDeque<>();
        scan.nextLine();
        //have to create a second scanner because using .nextInt() doesn't work well directly from the input
        String line = scan.nextLine();
        Scanner scan2 = new Scanner(line);
        while(scan2.hasNext()){
            int group = Integer.parseInt(scan2.next());
            groups.add(group);
        }

        //now loop through and pop each group in the queue to see if they can go in the theater
        int peopleCount = 0;
        int groupsAcceptedCount = 0;
        for(int i = 0; i < line1[1]; i++){
            int groupSize = groups.pop();
            if (peopleCount + groupSize <= line1[0]) {
                //if they can fit then take not of this and add it to our running count
                groupsAcceptedCount++;
                //update the current theater size
                peopleCount += groupSize;
            }
        }
        //to find the people rejected just subtract the total group count and the groups accepted
        System.out.println(line1[1]-groupsAcceptedCount);
    }
}
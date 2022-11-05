package competition.kattis.jazz.lab1;

import java.util.*;

public class cinema {
    public static void main(String[] args){
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
            //if the new group will make the theater go past capacity then don't do anything
            if(peopleCount+groupSize > line1[0]){

            } else {
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
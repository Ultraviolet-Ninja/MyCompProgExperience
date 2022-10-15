package kattis.club.week4;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class chewbacca {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] firstLine = IN.readLine().split(" ");


    }
}

class IntTree {
    private final int maxBranchSize;
    private IntNode head;

    public IntTree(int maxBranchSize) {
        this.maxBranchSize = maxBranchSize;
    }

    private static class IntNode {
        int data;
        IntNode[] children;



    }


}
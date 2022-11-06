package competition.kattis.the_class.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class variedamusements {
    public static void main(String[] args) throws IOException {
        String[] responses = new BufferedReader(new InputStreamReader(System.in)).readLine().split(" ");
        int[] parsedResponses = new int[responses.length - 1];
        IntTree[] trees = new IntTree[responses.length - 1];
        int counter = 0;

        for (String response : responses) {
            parsedResponses[counter++] = Integer.parseInt(response);
        }


    }

    private static void m1(int[] parsedResponses, IntTree[] trees) {
        int depth = parsedResponses[0];

        for (int i = 0; i < depth; i++) {

        }
    }

    private static void populateTree(IntTree tree, int depth, int[] parsedResponses) {

    }

    private static void summation(IntTree[] trees) {

    }

    static class IntTree {
        private IntNode head = null;
    }

    static class IntNode {
        private int value;
        private IntNode left, right;
    }
}


package competition.hackerrank;

import competition.annotations.Done;
import competition.annotations.InterviewQuestion;
import competition.annotations.SolutionReference;

@Done
@InterviewQuestion(company = "JP Morgan Chase")
@SolutionReference(url = "https://www.geeksforgeeks.org/maximum-index-a-pointer-can-reach-in-n-steps-by-avoiding-a-given-index-b/")
public class MaximumIndex {
    public static void main(String[] args) {
        System.out.println(maxIndex(2, 2));
        System.out.println(maxIndex(2, 1));
        System.out.println(maxIndex(3, 3));
    }

    public static int maxIndex(int steps, int badIndex) {
        return Math.max(maxIndex(steps, badIndex, 0, 1), maxIndex(steps, badIndex, 0, 2));
    }

    private static int maxIndex(int steps, int badIndex, int i, int j) {
        if (j == steps) {
            return i + j;
        } else if (i + j == badIndex) {
            return maxIndex(steps, badIndex, i, j+1);
        }
        return maxIndex(steps, badIndex, i+j, j+1);
    }
}

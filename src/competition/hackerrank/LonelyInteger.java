package competition.hackerrank;

import competition.annotations.Done;
import competition.annotations.SolutionReference;

import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

@Done(url = "https://www.hackerrank.com/challenges/lonely-integer/problem")
@SolutionReference(url = "https://stackoverflow.com/questions/70756228/find-the-odd-integer-js-fundamentals-logic")
public class LonelyInteger {
    public static void main(String[] args) {
        List<Integer> test1 = List.of(1),
                test2 = List.of(1, 1, 2),
                test3 = List.of(0, 0, 1, 2, 1);

        System.out.println("Counting Solution: " + countingSolution(test1));
        System.out.println("Counting Solution: " + countingSolution(test2));
        System.out.println("Counting Solution: " + countingSolution(test3));
        System.out.println();

        System.out.println("First XOR Solution: " + xorSolution(test1));
        System.out.println("First XOR Solution: " + xorSolution(test2));
        System.out.println("First XOR Solution: " + xorSolution(test3));
        System.out.println();

        System.out.println("Second XOR solution: " + xorSolution2(test1));
        System.out.println("Second XOR solution: " + xorSolution2(test2));
        System.out.println("Second XOR solution: " + xorSolution2(test3));

        System.out.println();

        System.out.println("Array Solution: " + arraySolution(test1));
        System.out.println("Array Solution: " + arraySolution(test2));
        System.out.println("Array Solution: " + arraySolution(test3));
    }

    public static int countingSolution(List<Integer> a) {
        if (a.size() == 1) {
            return a.get(0);
        }

        return a.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .get()
                .getKey();
    }

    public static int xorSolution(List<Integer> a) {
        int x = 0;

        for (int i : a) {
            x ^= i;
        }
        return x;
    }

    public static int xorSolution2(List<Integer> a) {
        return a.stream().reduce((i, j) -> i ^ j).get();
    }

    public static int arraySolution(List<Integer> a) {
        int[] fullArray = new int[101];
        TreeSet<Integer> insertedInts = new TreeSet<>(a);

        for (int i : a) {
            fullArray[i]++;
        }

        for (int i : insertedInts) {
            if (fullArray[i] == 1) {
                return i;
            }
        }
        return -1;
    }
}

package competition.hackerrank;

import competition.annotations.Done;
import competition.annotations.InterviewQuestion;
import competition.annotations.SolutionReference;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Done
@InterviewQuestion(companies = "JP Morgan Chase")
@SolutionReference(url = "https://www.geeksforgeeks.org/minimum-number-of-distinct-elements-after-removing-m-items/")
public class SellingProducts {
    /*
     * The problem “Minimum number of distinct elements after removing m items”
     * states that you have an array and an integer m. Each element of the array
     * indicates an item id’s. The problem statement asks to remove m elements in
     * such a way that there should be a minimum number of distinct id’s left.
     */
    public static void main(String[] args) {
        List<Integer> list = List.of(2, 4, 1, 5, 3, 5, 1, 3);
        System.out.println(distinctSold(list, 2));
    }

    public static int distinctSold(List<Integer> ids, int m) {
        int[] freqArray = ids.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .sorted()
                .mapToInt(Long::intValue)
                .toArray();

        int distinctRemovalCount = 0;
        for (int freq : freqArray) {
            if (freq <= m) {
                distinctRemovalCount++;
                m -= freq;
            }
        }
        return freqArray.length - distinctRemovalCount;
    }
}

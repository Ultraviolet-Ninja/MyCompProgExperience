package competition.kattis.the_class.notes.week3;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    private final int n;
    private final int k;
    private final int[] values;
    private final ArrayList<List<Integer>> result;

    Combinations(int n, int k) {
        this.n = n;
        this.k = k;
        values = new int[k];
        result = new ArrayList<>();
    }

    public void solve(int i, int j) {
        if (j == k) {
            // Reached the limit to 'k' add elements to the result
            ArrayList<Integer> list = new ArrayList<>(k);
            for (int x : values) list.add(x);
            result.add(list);
        } else {
            // Build up the potential values in the subset
            values[j] = i;

            // Solve for the next larger n and k
            solve(i + 1, j + 1);

            // If current 'n' is larger than current 'k'
            //    solve for the next size larger 'n'
            if (n - i >= k - j) {
                solve(i + 1, j);
            }
        }
    }

    public static void main(String[] args) {
        Combinations solver = new Combinations(4, 2);
        solver.solve(1, 0);
        System.out.println(solver.result.toString());
    }
}

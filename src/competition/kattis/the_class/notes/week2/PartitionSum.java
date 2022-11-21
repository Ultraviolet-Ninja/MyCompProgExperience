package competition.kattis.the_class.notes.week2;

public class PartitionSum {

    public static void main(String[] args) {
        int[] array = {1, 5, 1, 5, 7, 3, 6, 1, 8, 9, 3};
        int k = 4;

        int n = array.length;
        int[] sums = new int[n];
        for (int i = 0; i < n; i++) {

            // Base case if we chose to include a sub set of 1
            int prevValue = i > 0 ? sums[i - 1] : 0;
            sums[i] = array[i] + prevValue;

            // Check the value for all other subset sizes
            //   Don't go past the end of the list
            int temp = array[i];
            for (int j = 1; j <= Math.min(k, i); j++) {
                temp = Math.max(temp, array[i - j]);
                sums[i] = Math.max(sums[i], temp*j + sums[i-j]);
            }
        }
        System.out.println(sums[n-1]);
    }

}

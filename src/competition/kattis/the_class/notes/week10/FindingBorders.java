package competition.kattis.the_class.notes.week10;

import java.util.ArrayList;
import java.util.List;

public class FindingBorders {

    private static int[] computePrefixes(String s) {
        // Build length of prefix strings
        int[] T = new int[s.length()];

        // Initial prefix is of length
        T[0] = 0;

        // For each target, check substrings to that target
        for(int i = 1; i < s.length(); i++) {
            int j = T[i-1];

            // Check substrings of one length less if the characters don't match
            while(j > 0 && s.charAt(i) != s.charAt(j)) {
                j = T[j-1];
            }

            // Found a match, this character extends the substring by 1
            if(s.charAt(i) == s.charAt(j)) {
                j++;
            }

            //Set the substring length
            T[i] = j;
        }
        return T;
    }

    public static List<Integer> findingBorders(String s) {
        List<Integer> matches = new ArrayList<>();

        // Compute prefix values
        int[] prefixes = computePrefixes(s);

        // Search for prefix matches from the end
        int i = prefixes[prefixes.length-1];
        while(i > 0) {
            matches.add(i);
            i = prefixes[i-1];
        }

        return matches;
    }

    public static void main(String[] args) {
        String s = "abcababcab";
        System.out.println(findingBorders(s));
    }
}
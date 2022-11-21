package competition.kattis.the_class.notes.week10;

import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPratt {

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

    public static List<Integer> stringMatches(String w, String p) {
        List<Integer> matches = new ArrayList<>();

        // Augment the string
        String augWP = p + "#" + w;

        // Compute prefix values
        int[] prefixes = computePrefixes(augWP);

        // Search for prefix matches that match the search string
        for(int i = p.length() + 1; i < augWP.length(); i++) {
            if(prefixes[i] == p.length()) {
                matches.add(i - 2*p.length());
            }
        }

        return matches;
    }

    public static void main(String[] args) {
        String w = "ababacbcbaba";
        String p = "aba";
        System.out.println(stringMatches(w,p));
    }
}
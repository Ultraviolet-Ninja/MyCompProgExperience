package competition.kattis.the_class.notes.week10;

import java.util.*;

public class AhoCorasick {

    private final TrieNode root;
    private final String[] words;

    /**
     * Match records the range in a string for a substring match
     */
    private static class Match {
        public int startIndex;
        public int endIndex;

        public Match(int i, int j) {
            startIndex = i;
            endIndex = j;
        }

        @Override
        public boolean equals(Object o) {
            // Doesn't equal if the other object is null
            if (o == null) {
                return false;
            }
            // Doesn't equal if classes are not the same
            if (!getClass().equals(o.getClass())) {
                return false;
            }
            // Cast to a Match object and check values
            Match other = (Match)o;
            return startIndex == other.startIndex && endIndex == other.endIndex;
        }

        @Override
        public String toString() {
            return "(" + startIndex + "," + endIndex + ")";
        }
    }

    private static class TrieNode {
        public HashMap<Character, TrieNode> children;
        public TrieNode suffix;
        public TrieNode dictionary;
        public int wordIndex;

        public TrieNode() {
            children = new HashMap<>();
            suffix = null;
            dictionary = null;
            wordIndex = -1;
        }

        public void insertChild(String str, int index, int pos) {
            if (pos != str.length()) {
                if (!children.containsKey(str.charAt(pos))) {
                    children.put(str.charAt(pos), new TrieNode());
                }
                children.get(str.charAt(pos)).insertChild(str, index, pos + 1);
            } else {
                wordIndex = index;
            }
        }
    }

    private void buildTrie() {

        // Build a trie with all the words in the list
        for(int i = 0; i < words.length; i++) {
            root.insertChild(words[i], i,0);
        }
    }

    private void buildSuffixLinks() {

        // Set the suffix link for all children of the root
        //   to point to the root node
        Queue<TrieNode> openList = new LinkedList<>();
        for(TrieNode child : root.children.values()) {
            child.suffix = root;
            openList.add(child);
        }

        // Breadth first search to fill in suffix and dictionary reference links
        while(!openList.isEmpty()) {
            TrieNode curr = openList.poll();

            for(Map.Entry<Character, TrieNode> child : curr.children.entrySet()) {
                char childChar = child.getKey();
                TrieNode childNode = child.getValue();

                // Search the suffix links for a node that contains this child's substring
                TrieNode temp = curr.suffix;
                while(temp != null && !temp.children.containsKey(childChar)) {
                    temp = temp.suffix;
                }
                // Special case if search reaches the root of the trie
                childNode.suffix = temp == null ? root : temp.children.get(childChar);

                // Search for the closest dictionary word to this node
                temp = childNode.suffix;
                while(temp != null) {
                    if(temp.wordIndex >= 0) {
                        childNode.dictionary = temp;
                        break;
                    }
                    temp = temp.suffix;
                }

                // Add the child to the open list
                openList.add(child.getValue());
            }
        }
    }

    public List<Match> searchForMatches(String str) {
        List<Match> retValue = new ArrayList<>();

        TrieNode curr = root;
        for(int i = 0; i < str.length(); i++) {

            // Search the suffix list for the current character
            while(curr != null && !curr.children.containsKey(str.charAt(i))) {
                curr = curr.suffix;
            }
            // Special case if search reaches the root of the trie
            curr = curr == null ? root : curr.children.get(str.charAt(i));

            // Find all dictionary words that end in this character
            TrieNode dictionaryWord = curr.dictionary;
            while(dictionaryWord != null) {
                retValue.add(new Match(i - words[dictionaryWord.wordIndex].length() + 1, i));
                dictionaryWord = dictionaryWord.dictionary;
            }

            // Store the match if this element ends a word
            if(curr.wordIndex >= 0) {
                retValue.add(new Match(i - words[curr.wordIndex].length() + 1, i));
            }
        }

        return retValue;
    }

    public AhoCorasick(String[] w) {
        words = w;
        root = new TrieNode();
        buildTrie();
        buildSuffixLinks();
    }

    public static void main(String[] args) {
        String[] words = {"a", "ab", "bab", "bc", "bca", "c", "caa"};
        AhoCorasick myAhoCorasick = new AhoCorasick(words);
        System.out.println(myAhoCorasick.searchForMatches("abccab").toString());
    }
}

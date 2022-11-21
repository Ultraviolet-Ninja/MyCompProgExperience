package competition.kattis.the_class.notes.week10;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    private static class TrieNode {
        public HashMap<Character, TrieNode> children;
        boolean isWord;

        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }

        /**
         * Insert a child into the trie
         * @param str - the string to insert
         * @param pos - the current position in the string
         */
        public void insertChild(String str, int pos) {
            if(pos != str.length()) {
                if(!children.containsKey(str.charAt(pos))) {
                    children.put(str.charAt(pos), new TrieNode());
                }
                children.get(str.charAt(pos)).insertChild(str, pos + 1);
            } else {
                isWord = true;
            }
        }

        /**
         * Search for a string in the trie at the current position
         * @param str - the string to search
         * @param pos - the current position
         * @return - true if the word exists, false otherwise
         */
        public boolean searchRecursive(String str, int pos) {
            if(pos != str.length()) {
                if(!children.containsKey(str.charAt(pos))) {
                    return false;
                }
                return children.get(str.charAt(pos)).searchRecursive(str, pos + 1);
            }
            return isWord;
        }
    }

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String str) {
        root.insertChild(str, 0);
    }

    public boolean query(String str) {
        return root.searchRecursive(str, 0);
    }

    public static void main(String[] args) {
        String[] words = {
                "to",
                "tod",
                "toad",
                "tee",
                "tea",
                "team",
                "teeth",
        };
        Trie myTrie = new Trie();
        for (String word : words) {
            myTrie.insert(word);
        }
        System.out.println("to: " + myTrie.query("to"));
        System.out.println("tom: " + myTrie.query("tom"));
    }
}

package competition.kattis.the_class.notes.week10;

import java.util.HashMap;
import java.util.Map;

public class TypePrinter {

    private static class Trie {
        private final TrieNode root;

        private static class TrieNode {
            public HashMap<Character, TrieNode> children;
            boolean isWord;

            public TrieNode() {
                children = new HashMap<>();
                isWord = false;
            }

            public void insertChild(String str, int pos) {
                if (pos != str.length()) {
                    if (!children.containsKey(str.charAt(pos))) {
                        children.put(str.charAt(pos), new TrieNode());
                    }
                    children.get(str.charAt(pos)).insertChild(str, pos + 1);
                } else {
                    isWord = true;
                }
            }

            public void search(int depth, String longest) {

                // Search the children getting their letter and child nodes
                //   Skip a child and traverse it last if it is part of the
                //      longest word
                TrieNode longestChild = null;
                for(Map.Entry<Character, TrieNode> entry : children.entrySet()) {

                    // Grab the child values (letter and child node)
                    char key = entry.getKey();
                    TrieNode child = entry.getValue();

                    // If child is not part of the longest word, traverse it
                    if(key != longest.charAt(depth)) {
                        searchPrint(depth, longest, key, child.isWord);
                    }
                    // If it is part of the longest word record it for later traversal
                    else {
                        longestChild = child;
                    }
                }
                // If the is a child in the longest word, traverse it now
                if(longestChild != null) {
                    searchPrint(depth, longest, longest.charAt(depth), longestChild.isWord);
                }
            }

            public void searchPrint(int depth, String longest, char output, boolean wordEnd) {

                // Add the current letter to the type printer
                System.out.println(output);

                // If this indicates the end of a word, print it
                if(wordEnd) {
                    System.out.println("P");
                }

                // Traverse the children
                children.get(output).search(depth + 1, longest);

                // If this is not part of the longest word (printed at the end)
                //   Remove any added characters
                if(longest.charAt(depth) != output) {
                    System.out.println("-");
                }
            }
        }

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String str) {
            root.insertChild(str, 0);
        }

        public void traverse(String longest) {
            root.search(0, longest);
        }
    }

    public static void typePrinter(String[] words) {
        Trie myTrie = new Trie();

        String longestWord = words[0];
        for(String word : words) {
            myTrie.insert(word);
            if(longestWord.length() < word.length()) {
                longestWord = word;
            }
        }

        myTrie.traverse(longestWord);
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
        typePrinter(words);
    }
}

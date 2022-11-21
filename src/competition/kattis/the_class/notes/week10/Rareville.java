package competition.kattis.the_class.notes.week10;

import java.util.HashMap;

public class Rareville {

    private static class Trie {
        private final TrieNode root;

        private static class TrieNode {
            public HashMap<Character, TrieNode> children;
            public int prefixes;

            public TrieNode() {
                children = new HashMap<>();
                prefixes = 0;
            }

            public void insertChild(String str, int pos) {
                prefixes++;
                if (pos != str.length()) {
                    if (!children.containsKey(str.charAt(pos))) {
                        children.put(str.charAt(pos), new TrieNode());
                    }
                    children.get(str.charAt(pos)).insertChild(str, pos + 1);
                }
            }

            public int searchRecursive(String str, int pos) {
                int answer = prefixes;
                if (pos != str.length()) {
                    if (children.containsKey(str.charAt(pos))) {
                        answer += children.get(str.charAt(pos)).searchRecursive(str, pos + 1);
                    }
                }
                return answer;
            }
        }

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String str) {
            root.insertChild(str, 0);
        }

        public int query(String str) {
            return root.searchRecursive(str, 0);
        }
    }

    public static void main(String[] args) {
        String[] names = {
                "aaron",
                "adam",
                "alexander",
                "alicia",
                "allison",
                "amanda",
                "amber",
                "amy",
                "andrea",
        };

        Trie myTrie = new Trie();
        for(String name : names) {
            myTrie.insert(name);
        }
        System.out.println(myTrie.query("andrew"));
    }
}

package competition.kattis.the_class.notes.week10;

import java.util.*;

public class Censorship {

    private static class AhoCorasick {

        private final TrieNode root;
        private final String[] words;

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

        private static class SearchHistory {
            private final ArrayList<TrieNode> history;
            private int nextIndex;

            public SearchHistory() {
                history = new ArrayList<>();
                nextIndex = 0;
            }

            public TrieNode getNext() {
                return nextIndex < 0 ? null : history.get(nextIndex);
            }

            public TrieNode rewind(int i) {
                nextIndex -= i;
                return getNext();
            }

            public void push(TrieNode n) {
                if(nextIndex < history.size()) {
                    history.set(nextIndex, n);
                } else {
                    history.add(n);
                }
                nextIndex++;
            }
        }

        private void buildTrie() {

            // Build a trie with all the words in the list
            for (int i = 0; i < words.length; i++) {
                root.insertChild(words[i], i, 0);
            }
        }

        private void buildSuffixLinks() {

            // Set the suffix link for all children of the root
            //   to point to the root node
            Queue<TrieNode> openList = new LinkedList<>();
            for (TrieNode child : root.children.values()) {
                child.suffix = root;
                openList.add(child);
            }

            // Breadth first search to fill in suffix and dictionary reference links
            while (!openList.isEmpty()) {
                TrieNode curr = openList.poll();

                for (Map.Entry<Character, TrieNode> child : curr.children.entrySet()) {
                    char childChar = child.getKey();
                    TrieNode childNode = child.getValue();

                    // Search the suffix links for a node that contains this child's substring
                    TrieNode temp = curr.suffix;
                    while (temp != null && !temp.children.containsKey(childChar)) {
                        temp = temp.suffix;
                    }
                    // Special case if search reaches the root of the trie
                    childNode.suffix = temp == null ? root : temp.children.get(childChar);

                    // Search for the closest dictionary word to this node
                    temp = childNode.suffix;
                    while (temp != null) {
                        if (temp.wordIndex >= 0) {
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

        public AhoCorasick(String[] w) {
            words = w;
            root = new TrieNode();
            buildTrie();
            buildSuffixLinks();
        }

        public String searchAndRemove(String str) {

            StringBuilder retValue = new StringBuilder();

            // History used to rewind the search when a match is found
            SearchHistory history = new SearchHistory();
            history.push(root);

            // Start searching at the root
            TrieNode curr = root;
            for(int i = 0; i < str.length(); i++) {

                retValue.append(str.charAt(i));

                // Search the suffix list for the current character
                while(curr != null && !curr.children.containsKey(str.charAt(i))) {
                    curr = curr.suffix;
                }
                // Special case if search reaches the root of the trie
                curr = curr == null ? root : curr.children.get(str.charAt(i));

                // Push the current node on the search history
                history.push(curr);

                // Check for a word match
                TrieNode match = curr.dictionary;
                if(curr.wordIndex >= 0 && match == null) {
                    match = curr;
                }

                // Match found, remove it from the returned word (censored) and rewind the search
                if(match != null) {
                    retValue.setLength(retValue.length() - words[match.wordIndex].length());
                    curr = history.rewind(words[match.wordIndex].length() + 1);
                }
            }

            return retValue.toString();
        }
    }

    public static void main(String[] args) {
        String s = "stealescapesecondescswearapebase";
        String[] p = {"escape", "swear"};

        AhoCorasick myAhoCorasick = new AhoCorasick(p);
        System.out.println(myAhoCorasick.searchAndRemove(s));
    }
}

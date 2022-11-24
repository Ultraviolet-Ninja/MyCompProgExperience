package competition.leetcode;

import competition.annotations.TLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@TLE(url = "https://leetcode.com/problems/word-search-ii/")
public class WordSearchII {
    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}};
        String[] testList = {"oath", "pea", "eat", "rain"};
        runSearch(board, testList);

        board = new char[][]{{'a', 'b'}, {'c', 'd'}};
        testList = new String[]{"abcb"};
        runSearch(board, testList);

    }

    private static void runSearch(char[][] board, String[] testList) {
        WordSearchII trial = new WordSearchII();
        long start = System.nanoTime();
        var list = trial.findWords(board, testList);
        long stop = System.nanoTime();

        System.out.println(list);
        System.out.printf("%,d us\n", (stop - start) / 1000);
    }

    private static Trie trie, antiPrefixTrie;
    private static int boardWidth, boardHeight, maxWordLength;
    private static final Set<String> FOUND_WORDS = new TreeSet<>(),
            DICTIONARY = new TreeSet<>();

    private static final int[][] FOUR_WAY_VECTORS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        if (words.length == 0) {
            return new ArrayList<>();
        }
        precompute(board, words);
        Cell[][] grid = transmuteGrid(board);
        HashSet<Cell> visited = new HashSet<>(boardHeight * boardWidth);

        for (int x = 0; x < boardHeight; x++) {
            for (int y = 0; y < boardWidth; y++) {
                Cell start = grid[x][y];
                visited.add(start);
                searchRecursive(x, y, start.toString(), grid, visited);
                visited.remove(start);
            }
        }

        return new ArrayList<>(FOUND_WORDS);
    }

    private static void precompute(char[][] board, String[] words) {
        trie = new Trie(words);
        antiPrefixTrie = new Trie();
        boardHeight = board.length;
        boardWidth = board[0].length;
        var tempList = Arrays.asList(words);
        maxWordLength = tempList.stream()
                .mapToInt(String::length)
                .max()
                .orElse(10);
        DICTIONARY.clear();
        DICTIONARY.addAll(tempList);
        FOUND_WORDS.clear();
    }

    private static void searchRecursive(int x, int y, String word, Cell[][] grid, HashSet<Cell> visited) {
        if (!trie.containsPrefix(word)) {
            antiPrefixTrie.addWord(word);
            return;
        } else if (antiPrefixTrie.search(word))
            return;

        if (DICTIONARY.contains(word)) {
            FOUND_WORDS.add(word);
            DICTIONARY.remove(word);
            trie.remove(word);
            antiPrefixTrie.addWord(word);
        }

        if (word.length() == maxWordLength)
            return;

        for (int[] vector : FOUR_WAY_VECTORS) {
            int newX = x + vector[0];
            int newY = y + vector[1];
            if (boundaryCheck(newX, newY)) {
                Cell next = grid[newX][newY];
                if (!visited.contains(next)) {
                    visited.add(next);
                    searchRecursive(newX, newY, word + next, grid, visited);
                    visited.remove(next);
                }
            }
        }
    }

    private static boolean boundaryCheck(int x, int y) {
        return x >= 0 && x < boardHeight && y >= 0 && y < boardWidth;
    }

    private static Cell[][] transmuteGrid(char[][] board) {
        int x = 0;
        int y = 0;
        Cell[][] cells = new Cell[boardHeight][];

        for (char[] row : board) {
            var temp = new Cell[boardWidth];
            for (char c : row) {
                temp[y] = new Cell(x, y, c);
                y++;
            }
            y = 0;
            cells[x++] = temp;
        }
        return cells;
    }

    private static class Cell {
        private final int x, y;
        private final char c;
        private final int precomputedHash;

        public Cell(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
            precomputedHash = Objects.hash(x, y, c);
        }

        @Override
        public int hashCode() {
            return precomputedHash;
        }

        @Override
        public String toString() {
            return String.valueOf(c);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (!(obj instanceof Cell)) {
                return false;
            }
            Cell o = (Cell) obj;
            return x == o.x && y == o.y && c == o.c;
        }
    }

    private static class Trie {
        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public Trie(String[] array) {
            this();
            for (String word : array) {
                addWord(word);
            }
        }

        public void addWord(String word) {
            if (word == null || word.isEmpty())
                return;
            TrieNode currentNode = root;

            for (char c : word.toCharArray()) {
                if (!currentNode.children.containsKey(c))
                    currentNode.addConnection(c);

                currentNode = currentNode.children.get(c);
            }
            currentNode.isEndOfWord = true;
        }

        public boolean containsPrefix(String prefix) {
            if (prefix == null || prefix.isEmpty())
                return false;
            TrieNode currentNode = root;

            for (char c : prefix.toCharArray()) {
                if (!currentNode.children.containsKey(c))
                    return false;
                currentNode = currentNode.children.get(c);
            }
            return true;
        }

        public boolean search(String word) {
            if (word == null || word.isEmpty())
                return false;

            TrieNode currentNode = root;
            for (char letter : word.toCharArray()) {
                currentNode = currentNode.children.get(letter);
                if (currentNode == null)
                    return false;
            }
            return currentNode.isEndOfWord;
        }

        public void remove(String word) {
            if (word == null || word.isEmpty())
                return;
            remove(word, 0, word.length(), root);
        }

        private static boolean remove(String word, int index, final int wordLength, TrieNode node) {
            if (index + 1 == wordLength) {
                char letter = word.charAt(index);
                TrieNode last = node.children.get(letter);
                if (last.children.isEmpty()) {
                    node.children.remove(letter);
                    return true;
                } else {
                    last.isEndOfWord = false;
                    return false;
                }
            }
            char letter = word.charAt(index);
            TrieNode next = node.children.get(letter);

            if (next == null) {
                return false;
            }

            boolean callback = remove(word, index + 1, wordLength, next);
            if (callback && next.children.isEmpty()) {
                node.children.remove(letter);
                return true;
            }

            return false;
        }

        private static class TrieNode {
            private final Map<Character, TrieNode> children;
            private boolean isEndOfWord;

            public TrieNode() {
                children = new HashMap<>(16, 0.9f);
                isEndOfWord = false;
            }

            public void addConnection(char c) {
                children.put(c, new TrieNode());
            }
        }
    }
}

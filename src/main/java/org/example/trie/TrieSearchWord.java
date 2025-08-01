package org.example.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * TrieSearchWord implements a Trie data structure to support word insertion, search, and prefix checking.
 * It allows for efficient storage and retrieval of strings, making it suitable for applications like autocomplete.
 * Time Complexity: O(m) for insert, search, and startsWith operations, where m is the length of the word or prefix.
 * Space Complexity: O(n * m) where n is the number of words and m is the average length of the words.
 *
 */
public class TrieSearchWord {

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd;
    }

    private TrieNode root;

    public TrieSearchWord() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) return false;
            node = node.children.get(c);
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return false;
            node = node.children.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        TrieSearchWord trie = new TrieSearchWord();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("app"));     // false
        System.out.println(trie.startsWith("app")); // true
        trie.insert("app");
        System.out.println(trie.search("app"));     // true
    }
}

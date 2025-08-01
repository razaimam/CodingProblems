package org.example.trie;

import java.util.List;

/**
 * Trie implementation to check for prefix conditions in a list of words.
 * The function noPrefix checks if any word is a prefix of another word in the list.
 * If it finds such a case, it prints "BAD SET" followed by the offending word.
 * If no prefixes are found, it prints "GOOD SET".
 * Time Complexity: O(n * m), where n is the number of words and m is the average length of the words.
 * Space Complexity: O(n * m) for the trie structure.
 */
public class NoPrefixSet {
    /*
     * Complete the 'noPrefix' function below.
     *
     * The function accepts STRING_ARRAY words as parameter.
     */
    static class TrieNode {
        TrieNode[] children = new TrieNode[10]; // a-j
        boolean isEnd = false;
    }

    public static void noPrefix(List<String> words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';  // Maps 'a'-'j' to 0-9
                if (current.children[idx] == null) {
                    current.children[idx] = new TrieNode();
                }
                current = current.children[idx];
                if (current.isEnd) {
                    System.out.println("BAD SET");
                    System.out.println(word);
                    return;
                }
            }
            // If current node has children, this word is a prefix of another
            for (TrieNode child : current.children) {
                if (child != null) {
                    System.out.println("BAD SET");
                    System.out.println(word);
                    return;
                }
            }
            current.isEnd = true;
        }
        System.out.println("GOOD SET");
    }


    public static void main(String[] args) {
        List<String> words = List.of("abc", "ab", "cd", "de", "efg");
        noPrefix(words); // Should print "BAD SET" and the word "ab"

        List<String> goodWords = List.of("abc", "def", "ghi");
        noPrefix(goodWords); // Should print "GOOD SET"
    }
}

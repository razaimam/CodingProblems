package org.example.greedy.minheap;

import java.util.*;


// Huffman Tree Node
class HuffmanNode {
    char c;            // Character
    int freq;          // Frequency
    HuffmanNode left;  // Left child
    HuffmanNode right; // Right child

    HuffmanNode(char c, int freq) {
        this.c = c;
        this.freq = freq;
    }
}
/**
 * Huffman Coding implementation for data compression.
 * This class provides methods to build a Huffman tree, generate codes,
 * encode a string, and decode a Huffman encoded string.
 *
 * Time Complexity:
 * - Building the tree: O(m log m), where m is the number of unique characters.
 * - Generating codes: O(m).
 * - Encoding: O(n), where n is the length of the input string.
 * - Decoding: O(n).
 *
 * Space Complexity:
 * - O(m) for the priority queue and nodes in the tree.
 * - O(m) for storing the codes in a map.
 */

public class HuffmanCoding {

    /**
     * Build a Huffman tree from the frequency map
     * @param freqMap A map containing characters and their frequencies
     * @return The root of the Huffman tree
     *
     * Time Complexity: O(m log m), where m is the number of unique characters.
     * Space Complexity: O(m), for the priority queue and nodes.
     */
    private static HuffmanNode buildTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.freq));

        // Step 1: Create leaf nodes and add to priority queue
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Step 2: Build the tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            HuffmanNode parent = new HuffmanNode('-', left.freq + right.freq);
            parent.left = left;
            parent.right = right;

            pq.add(parent);
        }

        return pq.poll(); // Root node
    }

    /**
     * Generate Huffman codes for each character in the tree
     * @param root The root of the Huffman tree
     * @param code The current code being built
     * @param codeMap The map to store characters and their corresponding Huffman codes
     *
     * Time Complexity: O(m), where m is the number of unique characters.
     * Space Complexity: O(m), for the codeMap storing the codes.
     */
    private static void generateCodes(HuffmanNode root, String code, Map<Character, String> codeMap) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            codeMap.put(root.c, code);
        }

        generateCodes(root.left, code + "0", codeMap);
        generateCodes(root.right, code + "1", codeMap);
    }

    /*
     * Encode a string using the generated Huffman codes
     * @param text The input string to encode
     * @param codeMap The map containing characters and their corresponding Huffman codes
     * @return The encoded string
     *
     * Time Complexity: O(n), where n is the length of the input string.
     * Space Complexity: O(m), where m is the number of unique characters in the string.
     */
    public static String encode(String text, Map<Character, String> codeMap) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codeMap.get(c));
        }
        return encoded.toString();
    }

    /**
     * Decode a Huffman encoded string using the Huffman tree
     * @param encoded The encoded string
     * @param root The root of the Huffman tree
     * @return The decoded string
     *
     * Time Complexity: O(n), where n is the length of the encoded string.
     * Space Complexity: O(1), since we are using a StringBuilder for output.
     */
    public static String decode(String encoded, HuffmanNode root) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encoded.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            if (current.left == null && current.right == null) {
                decoded.append(current.c);
                current = root; // Go back to root
            }
        }

        return decoded.toString();
    }

    public static void main(String[] args) {
        String text = "ABRACADABRA";

        // Step 1: Frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Build Huffman Tree
        HuffmanNode root = buildTree(freqMap);

        // Step 3: Generate Huffman Codes
        Map<Character, String> codeMap = new HashMap<>();
        generateCodes(root, "", codeMap);

        System.out.println("Huffman Codes: " + codeMap);

        // Step 4: Encode
        String encoded = encode(text, codeMap);
        System.out.println("Encoded String: " + encoded);

        // Step 5: Decode
        String decoded = decode(encoded, root);
        System.out.println("Decoded String: " + decoded);
    }
}

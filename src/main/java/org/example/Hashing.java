package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    public static String getMD5Hash(String input) {
        try {
            // Create MD5 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Compute hash
            byte[] hashBytes = md.digest(input.getBytes());

            // Convert to hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    public static void main(String[] args) {
        // Test cases
        String[] inputs = {
                "Hello, World!",
                "Hello, World", // Small change
                "",             // Empty string
                "MD5 Hashing Example"
        };

        for (String input : inputs) {
            System.out.println("Input: " + input);
            System.out.println("MD5 Hash: " + getMD5Hash(input));
            System.out.println();
        }
    }
}

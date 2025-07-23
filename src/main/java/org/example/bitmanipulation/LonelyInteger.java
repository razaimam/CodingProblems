package org.example.bitmanipulation;

public class LonelyInteger {

    public static int findLonelyInteger(int[] arr) {
        int lonely = 0;
        for (int num : arr) {
            lonely ^= num; // XOR operation
        }
        return lonely;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 1};
        System.out.println("The lonely integer is: " + findLonelyInteger(arr)); // Output: 3
    }
}

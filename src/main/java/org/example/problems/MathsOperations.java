package org.example.problems;

import java.util.List;

public class MathsOperations {


    public static void main(String[] args) {

        // Example usage
        List<Integer> arr = List.of(1, 3, 2, 5, 4);
        System.out.println("Median: " + getMedian(arr)); // Output: Median: 3


    }

    public static double getMedian(List<Integer> arr) {
        if (arr.isEmpty()) {
            throw new IllegalArgumentException("Cannot calculate median of empty list");
        }
        int n = arr.size();
        if (n % 2 == 1) {
            return arr.get(n / 2);
        } else {
            return (arr.get(n / 2 - 1) + arr.get(n / 2)) / 2.0;
        }
    }
}

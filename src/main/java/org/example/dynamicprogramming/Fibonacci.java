package org.example.dynamicprogramming;

/**
 * This class provides a method to compute the nth Fibonacci number using dynamic programming.
 * The Fibonacci sequence is defined as follows:
 * F(0) = 0, F(1) = 1, and F(n) = F(n-1) + F(n-2) for n > 1.
 * Time Complexity: O(n), where n is the input number.
 * Space Complexity: O(n), due to the array used to store Fibonacci numbers.
 */
public class Fibonacci {

    public static int fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        if (n <= 1) {
            return n;
        }
        // …rest of the implementation…
    }
        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    public static void main(String[] args) {
        int n = 10; // Example input
        System.out.println("Fibonacci of " + n + " is: " + fibonacci(n)); // Output: 55
    }
}

package org.example.assesment;


import java.util.Arrays;

/**
 * Problem: Count Subsequences Lexicographically Greater Than a Given String
 *
 * <p>Given:
 * <ul>
 *     <li><b>c</b> — The customer's password (length ≤ 10^5)</li>
 *     <li><b>s</b> — The system-generated password (length ≤ 100)</li>
 * </ul>
 *
 * <p>Task:
 * Count the number of subsequences of {@code c} that are lexicographically greater than {@code s}.
 * Return the result modulo {@code 10^9 + 7}.
 *
 * <p>Definitions:
 * <ul>
 *     <li>A <b>subsequence</b> is formed by deleting zero or more characters from {@code c}
 *         without reordering the remaining characters.</li>
 *     <li>A string {@code x} is greater than {@code y} if:
 *         <ul>
 *             <li>At the first differing position, {@code x[i] > y[i]}, OR</li>
 *             <li>{@code y} is a prefix of {@code x} and {@code |x| > |y|}.</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <p>Constraints:
 * <ul>
 *     <li>1 ≤ |c| ≤ 10^5</li>
 *     <li>1 ≤ |s| ≤ 100</li>
 *     <li>Both strings consist of lowercase English letters.</li>
 * </ul>
 *
 * <p>Expected Approach:
 * Use dynamic programming with precomputed powers of two to handle large {@code c} efficiently.
 */


import java.util.Arrays;

public class SecurePasswordVariation {

    static final int MOD = 1_000_000_007;

    public static int countSecurePasswordVariations(String c, String s) {
        int n = c.length();
        int m = s.length();

        // dp[i][j] = number of subsequences starting at i in c and j in s which are lex greater
        int[][] dp = new int[n + 1][m + 1];
        for (int[] row : dp) Arrays.fill(row, -1);

        return count(0, 0, c.toCharArray(), s.toCharArray(), dp);
    }

    static int count(int i, int j, char[] c, char[] s, int[][] dp) {
        int n = c.length;
        int m = s.length;

        if (j == m) {
            // s is exhausted — any non-empty subsequence of c[i..] is greater
            return (modPow(2, n - i) - 1 + MOD) % MOD;
        }

        if (i == n) {
            return 0; // No characters left in c to form subsequence
        }

        if (dp[i][j] != -1) return dp[i][j];

        // Choice 1: skip c[i]
        long skip = count(i + 1, j, c, s, dp);

        // Choice 2: include c[i]
        long include = 0;
        if (c[i] > s[j]) {
            include = modPow(2, n - i - 1); // Any continuation is valid
        } else if (c[i] == s[j]) {
            include = count(i + 1, j + 1, c, s, dp);
        }
        // Else: c[i] < s[j], can't be greater

        dp[i][j] = (int) ((skip + include) % MOD);
        return dp[i][j];
    }

    static int modPow(int base, int exp) {
        long res = 1;
        long b = base;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * b) % MOD;
            b = (b * b) % MOD;
            exp >>= 1;
        }
        return (int) res;
    }

    // Sample test
    public static void main(String[] args) {
        String c = "afa";
        String s = "af";
        System.out.println(countSecurePasswordVariations(c, s)); // Expected output: 3
    }
}

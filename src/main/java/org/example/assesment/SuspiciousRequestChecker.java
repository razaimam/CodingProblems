package org.example.assesment;


import java.util.*;
import java.util.regex.Pattern;

/**
 * Problem: Flag Suspicious Access Requests
 *
 * <p>Amazon Prime Video requires a system to monitor streaming access keys and flag suspicious
 * requests.
 *
 * <p>Inputs:
 * <ul>
 *     <li><b>restrictedKeys</b> — Array of {@code n} pattern strings, each containing:
 *         <ul>
 *             <li>Alphanumeric characters</li>
 *             <li>Literal dots {@code .} (match only a dot in the key)</li>
 *             <li>Wildcard {@code *} (matches zero or more characters)</li>
 *         </ul>
 *     </li>
 *     <li><b>accessRequests</b> — Array of {@code q} access key strings. The request at index {@code i}
 *         occurs at time {@code i + 1} seconds.</li>
 * </ul>
 *
 * <p>Example pattern: {@code *111.*} matches {@code "111.2.33.44"} and {@code "121.111.22"},
 * but not {@code "121.11.21"}.
 *
 * <p>Flag an access request if:
 * <ol>
 *     <li>The key matches any pattern in {@code restrictedKeys} (wildcard {@code *} allowed).</li>
 *     <li>The same key appears at least 2 times (including current) in the last 5 seconds
 *         without being previously flagged.</li>
 * </ol>
 *
 * <p>Return:
 * An integer array of length {@code q}, where {@code 1} indicates a flagged request
 * and {@code 0} indicates a non-flagged request.
 *
 * <p>Notes:
 * <ul>
 *     <li>Flagged requests are not counted toward the duplicate-attempt rule.</li>
 *     <li>Dots {@code .} are treated as literal dots, not regex wildcards, when pattern matching.</li>
 * </ul>
 */

public class SuspiciousRequestChecker {

    public static List<Integer> flagSuspiciousRequests(List<String> restrictedKeys, List<String> accessRequests) {
        List<Integer> result = new ArrayList<>();
        int n = accessRequests.size();

        // Step 1: Compile restrictedKeys to regex patterns
        List<Pattern> restrictedPatterns = new ArrayList<>();
        for (String key : restrictedKeys) {
            String regex = "^" + key.replace(".", "\\.").replace("*", ".*") + "$";
            restrictedPatterns.add(Pattern.compile(regex));
        }

        // Step 2: Track unflagged accesses in a 5-second sliding window
        Map<String, Deque<Integer>> accessMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String accessKey = accessRequests.get(i);
            int second = i + 1; // 1-based index

            boolean isFlagged = false;

            // Rule 1: Check if access matches any restricted pattern
            for (Pattern pattern : restrictedPatterns) {
                if (pattern.matcher(accessKey).matches()) {
                    result.add(1);
                    isFlagged = true;
                    break;
                }
            }

            // Rule 2: Check for duplicates in last 5 seconds (unflagged only)
            if (!isFlagged) {
                accessMap.putIfAbsent(accessKey, new LinkedList<>());
                Deque<Integer> timestamps = accessMap.get(accessKey);

                // Remove timestamps outside the 5s window
                while (!timestamps.isEmpty() && timestamps.peekFirst() < second - 4) {
                    timestamps.pollFirst();
                }

                if (timestamps.size() >= 2) {
                    result.add(1); // Flag current
                    // Don't add to deque (since flagged)
                } else {
                    result.add(0); // Not flagged
                    timestamps.addLast(second); // Track unflagged request
                }
            }
        }

        return result;
    }

    // Sample test
    public static void main(String[] args) {
        List<String> restrictedKeys = Arrays.asList("111.*.255");
        List<String> accessRequests = Arrays.asList(
                "12.13.5.255",
                "121.3.5.255",
                "111.3.5.255",
                "121.3.5.255"
        );

        List<Integer> result = flagSuspiciousRequests(restrictedKeys, accessRequests);
        for (int flag : result) {
            System.out.println(flag);
        }
        // Expected Output:
        // 0
        // 0
        // 1
        // 0
    }
}

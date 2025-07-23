package org.example.greedy.minheap;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Jessica and Cookies problem solution.
 * Given a list of cookie sweetness levels, combine the least sweet cookies
 * until all cookies are at least a certain sweetness level k.
 * Returns the minimum number of operations needed or -1 if impossible.
 *
 * Time Complexity: O(n log n), where n is the number of cookies.
 * Space Complexity: O(n) for the priority queue.
 */
public class JessicaAndCookies {

    public static int cookies(int k, List<Integer> A) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(A);
        int ops = 0;
        while (minHeap.peek() < k && minHeap.size() >= 2) {
            int least = minHeap.poll();
            int second = minHeap.poll();
            int combined = least + 2 * second;
            minHeap.add(combined);
            ops++;
        }
        return minHeap.peek() >= k ? ops : -1;
    }

    public static void main(String[] args) {
        // Example usage
        List<Integer> cookies = Arrays.asList(1, 2, 3, 9, 10, 12);
        int k = 7;
        System.out.println(cookies(k, cookies)); // Output: 2
    }
}

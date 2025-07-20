package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {


    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();

        // Example usage of maxSumSubArray
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 3;
        int maxSum = slidingWindow.maxSumSubArray(arr, k);
        System.out.println("Maximum sum of subarray of length " + k + " is: " + maxSum);// Output: 24

        // Example usage of maxSubArrayDistinctSum
        int[] nums = {1, 2, 3, 4, 5, 9, 9, 9, 9, 1, 2, 3};
        long maxDistinctSum = slidingWindow.maxSubArrayDistinctSum(nums, 5);
        System.out.println("Maximum sum of distinct subarray of length 5 is: " + maxDistinctSum);
        // Output: 23 (subarray [2, 3, 4, 5, 9])


        String s = "abcabcbb";
        int length = slidingWindow.lengthOfLongestSubstring(s);
        System.out.println("Length of longest substring without repeating characters in '" + s + "' is: " + length);
        // Output: 3 (substring "abc")


    }

    public int maxSumSubArray(int[] arr, int k) {
        int maxSum = 0, windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    /**
     * Returns the maximum sum of any length-k subarray whose elements are all distinct.
     * If no such subarray exists, returns 0.
     */
    public long maxSubArrayDistinctSum(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length < k) {
            return 0L;
        }

        long ans = 0L;
        long currentSum = 0L;

        int begin = 0; // left edge of current window
        Map<Integer, Integer> lastSeen = new HashMap<>(); // number -> last index it appeared

        // Expand the window with 'end'
        for (int end = 0; end < nums.length; end++) {
            int curr = nums[end];

            // Where was this number last seen? -1 if never
            int lastIdx = lastSeen.getOrDefault(curr, -1);

            /*
             * We are about to include nums[end] in the window [begin..end].
             * We must ensure two invariants BEFORE adding nums[end] to currentSum:
             *   1) No duplicate of curr remains in the window -> begin > lastIdx
             *   2) Window size <= k  -> (end - begin + 1) <= k
             *
             * While either condition is violated, shrink from the left.
             * Note: currentSum currently reflects the window [begin..end-1], so we
             * subtract elements as we move 'begin' forward.
             */
            while (begin <= lastIdx || (end - begin + 1) > k) {
                currentSum -= nums[begin];
                begin++;
            }

            // Safe to include nums[end]
            currentSum += curr;
            lastSeen.put(curr, end);

            // If we have exactly k distinct elements, consider for answer
            if (end - begin + 1 == k) {
                ans = Math.max(ans, currentSum);
            }
        }

        return ans;
    }


    public int lengthOfLongestSubstring(String s) {
        // Track the max length of a substring without repeating characters found so far.
        int maxLength = 0;

        // Left edge of our current sliding window.
        int left = 0;

        // Maps each character to the *most recent* index where it appeared.
        Map<Character, Integer> charToIndex = new HashMap<>();

        // Expand the window character by character using 'right'.
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            Character.toUpperCase(c);
            // If we've seen this character before *and* it's inside the current window,
            // move 'left' just past its previous occurrence so the window becomes distinct again.
            if (charToIndex.containsKey(c)) {
                // We never move 'left' backward, so take max.
                left = Math.max(left, charToIndex.get(c) + 1);
            }

            // Record / update the last seen index of this character.
            charToIndex.put(c, right);

            // Current window size = right - left + 1.
            maxLength = Math.max(maxLength, right - left + 1);
        }


        return maxLength;
    }

    public int lengthOfLongestSubstringUsingArrayASCII(String s) {
        int[] lastIndex = new int[128];
        int maxLen = 0, start = 0;
        Arrays.fill(lastIndex, -1);
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            //int index =  Character.toUpperCase(c) - 'A';
            start = Math.max(start, lastIndex[c] + 1);
            maxLen = Math.max(maxLen, end - start + 1);
            lastIndex[c] = end;
        }
        return maxLen;
    }


    public int lengthOfLongestSubstringWithoutFill(String s) {
        final int[] seenAt = new int[128];
        int gen = 1;
        // instead of fill, bump generation
        if (++gen == 0) {
            // wrap-around: reset
            Arrays.fill(seenAt, 0);
            gen = 1;
        }
        int start = 0, max = 0;
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            int c = cs[i];
            int prev = seenAt[c] - gen; // encoded index
            if (prev >= start)
                start = prev + 1;
            seenAt[c] = i + gen;
            int len = i - start + 1;
            if (len > max)
                max = len;
        }
        return max;
    }
}

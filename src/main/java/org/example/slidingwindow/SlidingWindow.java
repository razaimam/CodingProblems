package org.example.slidingwindow;

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
        System.out.println("Maximum sum of subarray of length " + k + " is: " + maxSum); // Output: 24

        // Example usage of maxSubArrayDistinctSum
        int[] nums = {1, 2, 3, 4, 5, 9, 9, 9, 9, 1, 2, 3};
        long maxDistinctSum = slidingWindow.maxSubArrayDistinctSum(nums, 5);
        System.out.println("Maximum sum of distinct subarray of length 5 is: " + maxDistinctSum);
        // Output: 23 (subarray [2, 3, 4, 5, 9])

        String s = "abcabcbb";
        int length = slidingWindow.lengthOfLongestSubstring(s);
        System.out.println("Length of longest substring without repeating characters in '" + s + "' is: " + length);
        // Output: 3 (substring "abc")

        int lengthUsingArray = slidingWindow.lengthOfLongestSubstringUsingArrayASCII(s);
        System.out.println("Length of longest substring using ASCII array in '" + s + "' is: " + lengthUsingArray);

        int lengthWithoutFill = slidingWindow.lengthOfLongestSubstringWithoutFill(s);
        System.out.println("Length of longest substring without fill in '" + s + "' is: " + lengthWithoutFill);

        String s1 = "ADOBECODEBANC";
        String t = "ABC";
        String minWindow = slidingWindow.minWindow(s1, t);
        System.out.println("Minimum window substring of '" + s1 + "' that contains all characters of '" + t + "' is: " + minWindow);
        // Output: "BANC"
    }

    public int maxSumSubArray(int[] arr, int k) {
        if (arr.length < k) return 0;
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

    public long maxSubArrayDistinctSum(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length < k) {
            return 0L;
        }

        long ans = 0L, currentSum = 0L;
        int begin = 0;
        Map<Integer, Integer> lastSeen = new HashMap<>();

        for (int end = 0; end < nums.length; end++) {
            int curr = nums[end];
            int lastIdx = lastSeen.getOrDefault(curr, -1);

            while (begin <= lastIdx || (end - begin + 1) > k) {
                currentSum -= nums[begin];
                begin++;
            }

            currentSum += curr;
            lastSeen.put(curr, end);

            if (end - begin + 1 == k) {
                ans = Math.max(ans, currentSum);
            }
        }

        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0, left = 0;
        Map<Character, Integer> charToIndex = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charToIndex.containsKey(c)) {
                left = Math.max(left, charToIndex.get(c) + 1);
            }
            charToIndex.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength; // <-- Moved outside the loop
    }

    public int lengthOfLongestSubstringUsingArrayASCII(String s) {
        int[] lastIndex = new int[128];
        int maxLen = 0, left = 0;
        Arrays.fill(lastIndex, -1);
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            left = Math.max(left, lastIndex[c] + 1);
            maxLen = Math.max(maxLen, right - left + 1);
            lastIndex[c] = right;
        }
        return maxLen;
    }

    public int lengthOfLongestSubstringWithoutFill(String s) {
        final int[] seenAt = new int[128];
        int gen = 1;
        if (++gen == 0) {
            Arrays.fill(seenAt, 0);
            gen = 1;
        }
        int start = 0, max = 0;
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            int c = cs[i];
            int prev = seenAt[c] - gen;
            if (prev >= start)
                start = prev + 1;
            seenAt[c] = i + gen;
            int len = i - start + 1;
            if (len > max)
                max = len;
        }
        return max;
    }

    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";

        Map<Character, Integer> dictT = new HashMap<>();
        for (char c : t.toCharArray()) {
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }

        int required = dictT.size();
        int formed = 0;

        Map<Character, Integer> windowCounts = new HashMap<>();
        int left = 0, right = 0;
        int[] ans = {-1, 0, 0};

        while (right < s.length()) {
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            while (left <= right && formed == required) {
                c = s.charAt(left);
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c) < dictT.get(c)) {
                    formed--;
                }
                left++;
            }
            right++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}

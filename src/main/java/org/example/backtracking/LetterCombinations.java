package org.example.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could
 * represent.
 * This problem is typically solved using backtracking, as it involves exploring all combinations of letters that can be
 * formed from the given digits.
 * Time Complexity: O(4^n), where n is the length of the input string. Each digit can map to 3 or 4 letters, leading to an exponential number of combinations.
 * Space Complexity: O(n) for the recursion stack and the result list.
 * <p>
 * Recursion Tree for digits "23":
 * <p>
 *     "" (start)
 *    /    |    \
 *   a      b      c      <- letters from digit "2"
 *  /|\    /|\    /|\
 * d e f  d e f  d e f    <- letters from digit "3"
 * <p>
 * Explanation:
 * - First choose one letter from "2" (a, b, or c).
 * - Then for each choice, combine it with every letter from "3" (d, e, f).
 * - Final combinations: ad, ae, af, bd, be, bf, cd, ce, cf.
 */

public class LetterCombinations {
    private static final String[] KEYPAD = {
            "",    // 0
            "",    // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs",// 7
            "tuv", // 8
            "wxyz" // 9
    };

    public static void main(String[] args) {
        LetterCombinations lc = new LetterCombinations();
        System.out.println(lc.letterCombinations("23"));
        // Output: [ad, ae, af, bd, be, bf, cd, ce, cf]
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

 public List<String> letterCombinations(String digits) {
     List<String> result = new ArrayList<>();
     if (digits == null || digits.length() == 0) return result;

     // Validate input contains only digits 2-9
     for (char c : digits.toCharArray()) {
         if (c < '2' || c > '9') {
             throw new IllegalArgumentException("Input must contain only digits 2-9");
         }
     }

     backtrack(result, new StringBuilder(), digits, 0);
     return result;
 }
}

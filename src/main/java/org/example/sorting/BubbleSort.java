package org.example.sorting;

/**
 * BubbleSort class that implements the bubble sort algorithm.
 * This algorithm repeatedly steps through the list, compares adjacent elements,
 * and swaps them if they are in the wrong order. The pass through the list is repeated
 * until no swaps are needed, which means the list is sorted.
 *
 * Time Complexity: O(n^2) in the worst and average cases, O(n) in the best case (when the array is already sorted).
 * Space Complexity: O(1) since it sorts the array in place.
 */
public class BubbleSort {
    /**
     * Sorts the given array using bubble sort algorithm.
     * Note: This method modifies the input array in-place.
     *
     * @param arr the array to be sorted
     * @return the same array reference after sorting
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        int n = arr.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    // Swap arr[i] and arr[i + 1]
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            n--; // Reduce the range of the next pass
        } while (swapped);
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int[] sortedArr = bubbleSort(arr);
        for (int num : sortedArr) {
            System.out.print(num + " ");
        }
        // Output: 11 12 22 25 34 64 90
    }
}

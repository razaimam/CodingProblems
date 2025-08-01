package org.example.sorting;

/**
 * InsertionSort class that implements the insertion sort algorithm.
 * This algorithm builds a sorted array one element at a time by repeatedly taking an element
 * from the unsorted part and inserting it into the correct position in the sorted part.
 *
 * Time Complexity: O(n^2) in the worst case, O(n) in the best case (when the array is already sorted).
 * Space Complexity: O(1) since it sorts the array in place.
 */
public class InsertionSort {
    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        int[] sortedArr = insertionSort(arr);
        for (int num : sortedArr) {
            System.out.print(num + " ");
        }
        // Output: 5 6 11 12 13
    }
}

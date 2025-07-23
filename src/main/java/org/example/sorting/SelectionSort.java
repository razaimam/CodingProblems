package org.example.sorting;

/**
 * SelectionSort class that implements the selection sort algorithm.
 * This algorithm divides the input array into two parts: a sorted part and an unsorted part.
 * It repeatedly selects the smallest (or largest) element from the unsorted part and moves it to the end of the sorted part.
 *
 * Time Complexity: O(n^2) in all cases (worst, average, and best).
 * Space Complexity: O(1) since it sorts the array in place.
 */
public class SelectionSort {

    public static int[] selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // Find the index of the minimum element in the unsorted part
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element of the unsorted part
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int[] sortedArr = selectionSort(arr);
        for (int num : sortedArr) {
            System.out.print(num + " ");
        }
        // Output: 11 12 22 25 64
    }
}

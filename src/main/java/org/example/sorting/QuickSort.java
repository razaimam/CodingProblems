package org.example.sorting;

/**
 * QuickSort class that implements the quick sort algorithm.
 * This algorithm selects a 'pivot' element and partitions the array into two halves,
 * sorting each half recursively.
 *
 * Time Complexity: O(n log n) on average, O(n^2) in the worst case (when the pivot is the smallest or largest element).
 * Space Complexity: O(log n) for the recursive stack space.
 */
public class QuickSort {

    public static int[] quickSort(int[] array) {
        if (array.length < 2) {
            return array; // Base case: array is already sorted
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1); // Recursively sort the left part
            quickSort(array, pivotIndex + 1, high); // Recursively sort the right part
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Choose the last element as pivot
        int i = low - 1; // Pointer for the smaller element

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j); // Swap if current element is smaller than or equal to pivot
            }
        }
        swap(array, i + 1, high); // Place the pivot in the correct position
        return i + 1; // Return the index of the pivot
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {10, 7, 8, 9, 1, 5};
        int[] sortedArray = quickSort(array);

        System.out.print("Sorted Array: ");
        for (int num : sortedArray) {
            System.out.print(num + " ");
        }
        // Output: Sorted Array: 1 5 7 8 9 10
    }
}

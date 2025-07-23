package org.example.sorting;

/**
 * HeapSort class that implements the heap sort algorithm.
 * This algorithm builds a max heap from the input array and then repeatedly extracts the maximum element
 * to create a sorted array.
 *
 * Time Complexity: O(n log n) in all cases (worst, average, and best).
 * Space Complexity: O(1) since it sorts the array in place.
 */
public class HeapSort {

    public static int[] heapSort(int[] array) {
        int n = array.length;

        // Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // One by one extract elements from the heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            swap(array, 0, i);
            // Call heapify on the reduced heap
            heapify(array, i, 0);
        }

        return array;
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left child index
        int right = 2 * i + 2; // right child index

        // If left child is larger than root
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            swap(array, i, largest);
            // Recursively heapify the affected sub-tree
            heapify(array, n, largest);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6};
        int[] sortedArray = heapSort(array);

        System.out.print("Sorted Array: ");
        for (int num : sortedArray) {
            System.out.print(num + " ");
        }
    }
}

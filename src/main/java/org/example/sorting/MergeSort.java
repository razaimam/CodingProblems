package org.example.sorting;

/**
 * MergeSort class that implements the merge sort algorithm.
 * This algorithm divides the array into halves, sorts each half, and then merges them back together.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n) for the temporary arrays used during merging.
 */
public class MergeSort {
    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array; // Base case: array is already sorted
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        // Split the array into two halves
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, array.length - mid);

        // Recursively sort both halves
        left = mergeSort(left);
        right = mergeSort(right);

        // Merge the sorted halves
        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        int[] sortedArray = mergeSort(array);

        System.out.print("Sorted Array: ");
        for (int num : sortedArray) {
            System.out.print(num + " ");
        }
    }
}

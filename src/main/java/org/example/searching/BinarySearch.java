package org.example.searching;

public class BinarySearch {
    /**
     * Performs binary search on a sorted array.
     *
     * @param arr   the sorted array to search
     * @param target the value to search for
     * @return the index of the target if found, otherwise -1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoids overflow

            if (arr[mid] == target) {
                return mid; // Target found
            } else if (arr[mid] < target) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }

        return -1; // Target not found
    }

    // Recursive version of binary search
    public static int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }
    private static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1; // Base case: target not found
        }

        int mid = left + (right - left) / 2; // Avoids overflow

        if (arr[mid] == target) {
            return mid; // Target found
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right); // Search in the right half
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1); // Search in the left half
        }
    }

    public static void main(String[] args) {
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 5;

        int result = binarySearch(sortedArray, target);
        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found in the array.");
        }

        // Testing the recursive version
        int recursiveResult = binarySearchRecursive(sortedArray, target);
        if (recursiveResult != -1) {
            System.out.println("Element found at index (recursive): " + recursiveResult);
        } else {
            System.out.println("Element not found in the array (recursive).");

        }
    }

}

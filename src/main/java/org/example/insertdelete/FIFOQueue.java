package org.example.insertdelete;

import java.util.HashMap;
import java.util.Map;


/**
 * Node is a class representing a node in a doubly linked list.
 * It contains an integer value and pointers to the previous and next nodes.
 */
class Node {
    int value;
    Node prev, next;
    public Node(int value) {
        this.value = value;
    }
}

/**
 * FIFOQueue is a data structure that implements a First-In-First-Out (FIFO) queue.
 * It supports enqueue, dequeue, remove, and contains operations.
 * - enqueue(value): Adds an element to the end of the queue.
 * - dequeue(): Removes and returns the element at the front of the queue.
 * - remove(value): Removes a specific element from the queue if it exists.
 * - contains(value): Checks if a specific value exists in the queue.
 * * Time Complexity:
 * - enqueue: O(1)
 * - dequeue: O(1)
 * - remove: O(1) on average, O(n) in the worst case if the element is at the end of the queue.
 * - contains: O(1) on average, O(n) in the worst case if the element is at the end of the queue.
 * * Space Complexity: O(n), where n is the number of elements in the queue.
 *
 */

public class FIFOQueue {
    private Node head, tail;
    private Map<Integer, Node> map;

    public FIFOQueue() {
        head = new Node(0); // Dummy head
        tail = new Node(0); // Dummy tail
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    /**
     * Enqueue an element to the end of the queue.
     * @param value The value to be added to the queue.
     */
    public void enqueue(int value) {
        Node node = new Node(value);
        map.put(value, node);
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }
    /**
     * Dequeue an element from the front of the queue.
     * @return The value of the dequeued element.
     */
    public int dequeue() {
        if (head.next == tail) {
            throw new IllegalStateException("Queue is empty");
        }
        Node node = head.next;
        head.next = node.next;
        node.next.prev = head;
        map.remove(node.value);
        return node.value;
    }
    /**
     * Remove an element from the queue.
     * @param value The value to be removed from the queue.
     * @return true if the element was removed, false if it was not found.
     */
    public boolean remove(int value) {
        Node node = map.get(value);
        if (node == null) {
            return false; // Element not found
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        map.remove(value);
        return true; // Element removed successfully
    }
    /**
     * Check if the queue contains a specific value.
     * @param value The value to check for existence in the queue.
     * @return true if the value exists, false otherwise.
     */
    public boolean contains(int value) {
        return map.containsKey(value);
    }

    public static void main(String[] args) {
        FIFOQueue queue = new FIFOQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue()); // Output: 1
        System.out.println(queue.contains(2)); // Output: true
        System.out.println(queue.remove(2)); // Output: true
        System.out.println(queue.contains(2)); // Output: false
        System.out.println(queue.dequeue()); // Output: 3
    }

}


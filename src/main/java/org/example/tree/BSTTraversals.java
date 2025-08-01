package org.example.tree;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class BSTTraversals {

    // Insert node into BST
    public static Node insert(Node root, int data) {
        if (root == null) return new Node(data);
        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);
        return root;
    }

    // BFS (Level Order Traversal)
    public static void bfs(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    // DFS Traversals

    public static void dfsWithStack(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.data + " ");
            // Push right first so that left is processed first
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
    }

    public static void inorder(Node root) {  // Left -> Root -> Right
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void preorder(Node root) { // Root -> Left -> Right
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void postorder(Node root) { // Left -> Right -> Root
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }



    public static void main(String[] args) {
        Node root = null;
        int[] values = {50, 30, 20, 40, 70, 60, 80};

        for (int val : values) {
            root = insert(root, val);
        }

        System.out.print("BFS (Level Order): ");
        bfs(root); // Output: 50 30 70 20 40 60 80
        System.out.println();

        System.out.print("DFS (Using Stack): ");
        dfsWithStack(root); // Output: 50 30 20 40 70 60 80
        System.out.println();

        System.out.print("DFS - Preorder: ");
        preorder(root); // Output: 50 30 20 40 70 60 80
        System.out.println();

        System.out.print("DFS - Inorder: ");
        inorder(root); // Output: 20 30 40 50 60 70 80
        System.out.println();

        System.out.print("DFS - Postorder: ");
        postorder(root); // Output: 20 40 30 60 80 70 50
        System.out.println();
    }
}

package org.example.console;

public class ConsoleChart {
    public static void main(String[] args) {
        int[][] points = {{1, 2}, {4, 6}, {6, 3}, {5, 9}, {2, 5}, {3, 1}, {0, 0}, {7, 8}, {8, 4}};

        // Step 1: Find max X and Y
        int maxX = 0;
        int maxY = 0;
        for (int[] point : points) {
            maxX = Math.max(maxX, point[0]);
            maxY = Math.max(maxY, point[1]);
        }

        // Step 2: Create matrix (rows = Y+1, cols = X+1)
        char[][] grid = new char[maxY + 1][maxX + 1];


        // Step 3: Mark points with '*'
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            grid[y][x] = '*';
        }

        // Step 4: Print the grid from top (maxY) to bottom (0)
        for (int y = maxY; y >= 0; y--) {
            System.out.printf("%2d | ", y); // Y-axis label
            for (int x = 0; x <= maxX; x++) {
                System.out.print(grid[y][x] == '*' ? "* " : "  ");
            }
            System.out.println();
        }

        // Step 5: X-axis
        System.out.print("   +--");
        for (int x = 1; x <= maxX; x++) {
            System.out.print("--");
        }
        System.out.println();

        // X-axis labels
        System.out.print("     ");
        for (int x = 0; x <= maxX; x++) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}


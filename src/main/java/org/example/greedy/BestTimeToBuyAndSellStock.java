package org.example.greedy;

/*
 * Best Time to Buy and Sell Stock
 *
 * Given an array prices where prices[i] is the price of a given stock on the ith day,
 * return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Note: You may complete at most one transaction (i.e., buy one and sell one share of the stock).
 * Time Complexity: O(n), where n is the number of days (length of the prices array).
 * Space Complexity: O(1), as we are using only a constant amount of space.
 */

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // Initially, no price is considered
        int maxProfit = 0; // Start with zero profit

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price; // Update min price
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice; // Update max profit
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock stock = new BestTimeToBuyAndSellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Max Profit: " + stock.maxProfit(prices)); // Output: 5
    }
}


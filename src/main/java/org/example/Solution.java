package org.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    public static String caesarCipher(String s, int k) {
        k = k % 26;
       StringBuffer sb= new StringBuffer();
        for(char c: s.toCharArray()){
            if(Character.isUpperCase(c)) {
                c = (char) ((c - 'A' + k ) % 26 + 'A');
                sb.append(c);
            }if(Character.isLowerCase(c)) {
                c = (char) ((c - 'a' + k ) % 26 + 'a');
                sb.append(c);
            } else {
                sb.append(c);
            }

        }
        return sb.toString();
    }

    public static int lonelyInteger(List<Integer> a) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for(Integer num : a){
            int count = numCountMap.containsKey(num) ? numCountMap.get(num) : 0;
            numCountMap.put(num, count+1);
        }
        Iterator<Map.Entry<Integer, Integer>> numCountIterator = numCountMap.entrySet().iterator();
        while(numCountIterator.hasNext()){
            Map.Entry<Integer, Integer> entry  = numCountIterator.next();
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return 0;
    }

    public static int lonelyIntegerWithXOR(List<Integer> a) {
        int lonely = 0;
        for (Integer num : a) {
            lonely ^= num; // XOR operation
        }
        return lonely; // The result will be the lonely integer
    }


    public static int cookies(int k, List<Integer> A) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(A);
        int ops = 0;
        while (minHeap.peek() < k && minHeap.size() >= 2) {
            int least = minHeap.poll();
            int second = minHeap.poll();
            int combined = least + 2 * second;
            minHeap.add(combined);
            ops++;
        }
        return minHeap.peek() >= k ? ops : -1;
    }

    public static void main(String[] args) {
        // Example usage of caesarCipher
        String encrypted = caesarCipher("Hello, World!", 3);
        System.out.println("Encrypted: " + encrypted); // Khoor, Zruog!

        // Example usage of lonelyInteger
        List<Integer> numbers = List.of(1, 2, 3, 2, 1);
        int lonely = lonelyInteger(numbers);
        System.out.println("Lonely Integer: " + lonely); // 3

        // Example usage of lonelyIntegerWithXOR
        int lonelyXOR = lonelyIntegerWithXOR(numbers);
        System.out.println("Lonely Integer with XOR: " + lonelyXOR); // 3

        // Example usage of cookies
        List<Integer> sweetness = List.of(1, 2, 3, 9, 10, 12);
        int ops = cookies(7, sweetness);
        System.out.println("Operations needed: " + ops); // 2


    }
    public static int getMedian(List<Integer> arr) {
        if (arr.isEmpty()) {
            return 0; // or throw an exception
        }
        int n = arr.size();
        if (n % 2 == 1) {
            return arr.get(n / 2);
        } else {
            return (arr.get(n / 2 - 1) + arr.get(n / 2)) / 2;
        }
    }
}

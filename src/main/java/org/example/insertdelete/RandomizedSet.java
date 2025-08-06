package org.example.insertdelete;

import java.util.*;

/**
 * RandomizedSet is a data structure that supports insert, remove, and getRandom operations.
 * - insert(val): Inserts an item val into the set if not already present. Returns true if the item was added successfully.
 * - remove(val): Removes an item val from the set if present. Returns true if the item was removed successfully.
 * - getRandom(): Returns a random element from the current set of elements.
 *
 * Time Complexity:
 * - insert: O(1)
 * - remove: O(1)
 * - getRandom: O(1)
 *
 * Space Complexity: O(n), where n is the number of elements in the set.
 */
public class RandomizedSet {
     private List<Integer> list;
     private Map<Integer, Integer> map;
     private Random random;
     public RandomizedSet() {
         list = new ArrayList<>();
         map = new HashMap<>();
         random = new Random();
     }
     public boolean insert(int val) {
         if (map.containsKey(val)) {
             return false; // Element already exists
         }
         map.put(val, list.size());
         list.add(val);
         return true; // Element inserted successfully
     }
     public boolean remove(int val) {
         if (!map.containsKey(val)) {
             return false; // Element does not exist
         }
         int index = map.get(val);
         int lastElement = list.get(list.size() - 1);
         list.set(index, lastElement);
         map.put(lastElement, index);
         list.remove(list.size() - 1);
         map.remove(val);
         return true; // Element removed successfully
     }
     public int getRandom() {
         return list.get(random.nextInt(list.size())); // Return a random element
     }
     public static void main(String[] args) {
         RandomizedSet randomizedSet = new RandomizedSet();
         System.out.println(randomizedSet.insert(1)); // Returns true
         System.out.println(randomizedSet.remove(2)); // Returns false (2 does not exist)
         System.out.println(randomizedSet.insert(2)); // Returns true
         System.out.println(randomizedSet.getRandom()); // Returns either 1 or 2 (randomly chosen)
         System.out.println(randomizedSet.remove(1)); // Returns true (1 exists and is removed)
         System.out.println(randomizedSet.insert(2)); // Returns false (2 already exists)
         System.out.println(randomizedSet.getRandom()); // Returns 2 (the only element left)
     }

}

package org.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
}

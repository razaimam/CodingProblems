package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //List<Integer> a = Arrays.asList(4, 4, 1, 2, 1);
        //System.out.println(Solution.lonelyinteger(a));
        //
        //Scanner scanner = new Scanner(System.in);

        String str = "abcdefghijklmnopqrstuvwxyz";
        String expected = "defghijklmnopqrstuvwxyzabc";
        System.out.println(str);
        String output = Solution.caesarCipher(str, 3);
        System.out.println(output);
        System.out.println("EXPECTED == ACTUAL : " + output.equals(expected));
    }
}
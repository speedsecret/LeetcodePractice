package String;

/*
*  Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
*
* Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000

* For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.
*
* Input: s = "LVIII"
    Output: 58
    Explanation: L = 50, V= 5, III = 3.
* */

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    // As Roman alphabet is limited number
    // we could use a hashMap to store string to mapping it to integer and then to seek adding it up

    public static int romanToInteger(String input)
    {
        // corner case
        if (input == null || input.length() == 0) {
            return 0;
        }
        Map<String, Integer> romanMap = new HashMap<>();
        romanMap.put("I", 1);
        romanMap.put("V", 5);
        romanMap.put("X", 10);
        romanMap.put("L", 50);
        romanMap.put("C", 100);
        romanMap.put("D", 500);
        romanMap.put("M", 1000);
        romanMap.put("IV", 4);
        romanMap.put("IX", 9);
        romanMap.put("XL", 40);
        romanMap.put("XC", 90);
        romanMap.put("CD", 400);
        romanMap.put("CM", 900);
        int sum = 0;
        int curIndex = 0;
        while (curIndex < input.length()) {
            // increment two to check if that one is in map
            if (curIndex < input.length() - 1) {
                String cur = input.substring(curIndex, curIndex + 2);
                if (romanMap.containsKey(cur)) {
                    sum += romanMap.get(cur);
                    curIndex += 2;
                    continue;
                }
            }
            String cur = input.substring(curIndex, curIndex + 1);
            if (romanMap.containsKey(cur)) {
                sum += romanMap.get(cur);
                curIndex += 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String input_1 = "V";
        String input_2 = "IX";
        String input_3 = "XII";
        System.out.printf("Current input_1 is: " + romanToInteger(input_1));
        System.out.printf("Current input_2 is: " + romanToInteger(input_2));
        System.out.printf("Current input_3 is: " + romanToInteger(input_3));
    }
}

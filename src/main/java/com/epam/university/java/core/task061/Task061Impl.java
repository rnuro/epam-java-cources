package com.epam.university.java.core.task061;

import java.util.HashMap;
import java.util.Map;

public class Task061Impl implements Task061 {
    @Override
    public String convertToRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException();
        }
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strings =
                new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                result.append(strings[i]);
            }
        }
        return result.toString();
    }

    @Override
    public int convertToArabic(String number) {
        Map<Character, Integer> match = new HashMap<Character, Integer>();
        match.put('I', 1);
        match.put('V', 5);
        match.put('X', 10);
        match.put('L', 50);
        match.put('C', 100);
        match.put('D', 500);
        match.put('M', 1000);
        for (char num : number.toCharArray()) {
            if (!match.containsKey(num)) {
                throw new IllegalArgumentException();
            }
        }
        int result = 0;
        for (int i = 0; i < number.length(); i++) {
            if (i == 0 || match.get(number.charAt(i)) <= match.get(number.charAt(i - 1))) {
                result += match.get(number.charAt(i));
            } else {
                result += match.get(number.charAt(i)) - 2 * match.get(number.charAt(i - 1));
            }
        }
        return result;
    }
}

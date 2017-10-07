package com.epam.university.java.core.task027;


import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;

/**
 * Numbers extraction.
 */
public class Task027Impl implements Task027 {
    /**
     * Given a number string that can be split into the sequence of two or more positive
     * integers [a1, a2, ..., an] satisfying the following conditions:
     *
     * <p>
     *     1. ai - a(i-1) = 1 for any element
     *     2. No ai contains a leading zero
     *     3. Content of the sequence cannot be rearranged.
     * </p>
     *
     * <p>
     *     You should extract that numbers or return an empty collection if it is not possible.
     * </p>
     *
     * <p>
     *     Example: given a string "1234", result should be [1, 2, 3, 4]
     *     Example: given a string "91011", result should be [9, 10, 11]
     *     Example: given a string "99100", result should be [99, 100]
     *     Example: given a string "4123", result should be []
     * </p>
     *
     * @param sourceString source string
     * @return collection of extracted integers
     */
    @Override
    public Collection<Integer> extract(String sourceString) {

        if (sourceString == null || !sourceString.matches("[0-9]+")) {
            throw new IllegalArgumentException();
        }

        if (sourceString.charAt(0) == '0') {
            return Collections.emptyList();
        }

        for (int i = 1; i < (sourceString.length() / 2) + 1; i++) {

            int cursor = 0;
            int interval = i;
            ArrayDeque<Integer> toReturn = new ArrayDeque<>();
            toReturn.add(Integer.parseInt(sourceString.substring(cursor, interval)));
            cursor = cursor + interval;

            while (cursor <= sourceString.length() - interval) {

                String substring = sourceString.substring(cursor, cursor + interval);

                if (substring.charAt(0) == '0') {
                    break;
                }

                int a = toReturn.getLast();
                int b = Integer.parseInt(substring);

                if (a + 1 == b) {
                    toReturn.add(b);
                    cursor = cursor + interval;
                } else if (a + 1 > b) {
                    interval++;
                } else {
                    break;
                }

            }

            if (cursor >= sourceString.length()) {
                return toReturn;
            }

        }

        return Collections.emptyList();

    }
}

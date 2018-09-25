import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Rohit Mittapalli
 * @userid rmittapalli3
 * @GTID 903309727
 * @version 1.0
 */
public class PatternMatching {

    /**
     * Brute force pattern matching algorithm to find all matches.
     *
     * You should check each substring of the text from left to right,
     * stopping early if you find a mismatch.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> bruteForce(CharSequence pattern,
        CharSequence text, CharacterComparator comparator) {

        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        ArrayList<Integer> list = new ArrayList();

        if (pattern.length() > text.length()) {
            return list;
        }
        boolean cont;
        for (int k = 0; pattern.length() + k <= text.length(); k++) {
            cont = true;
            for (int j = 0; j < pattern.length() && cont; j++) {
                if (comparator.compare(pattern.charAt(j), text.charAt(k + j))
                        != 0) {
                    cont = false;
                }
            }
            if (cont) {
                list.add(k);
            }
        }
        return list;
    }

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the failure table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        List<Integer> list = new ArrayList<>();

        if (pattern.length() > text.length()) {
            return list;
        }
        int[] ft = buildFailureTable(pattern, comparator);
        int j = 0;
        int i = 0;
        int nextAlignment;
        while (i <= text.length() - pattern.length()) {
            System.out.println("CL " + (pattern.length() - j));
            System.out.println("TL " + (text.length() - i));
            while (j < pattern.length()
                    && comparator.compare(pattern.charAt(j), text.charAt(i+j))
                    == 0) {
                j++;
            }
            if (j == 0) {
                i++;
            } else {
                if (j == pattern.length()) {
                    list.add(i);
                }
                nextAlignment = ft[j-1];
                i = i + j - nextAlignment;
                j = nextAlignment;

            }
        }

        return list;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @throws IllegalArgumentException if the pattern or comparator is null
     * @param pattern a {@code CharSequence} you're building a failure table for
     * @param comparator you MUST use this for checking character equality
     * @return integer array holding your failure table
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        //suffix equals prefix
        int pre = 0;
        int i = 1;
        int[] table = new int[pattern.length()];
        table[0] = 0;

        while (i < pattern.length()) {
            if (comparator.compare(pattern.charAt(i), pattern.charAt(pre))
                    == 0) {
                pre++;
                table[i] = pre;
                i++;
            } else {
                if (pre != 0) {
                    pre = table[pre - 1];
                } else {
                    table[i] = pre;
                    i++;
                }
            }
        }
        return table;

    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Make sure to implement the last occurrence table before implementing this
     * method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text or comparator is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for the pattern
     * @param comparator you MUST use this for checking character equality
     * @return list containing the starting index for each match found
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                       CharSequence text, CharacterComparator comparator) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        List<Integer> matches = new ArrayList<>();

        if (pattern.length() > text.length()) {
            return matches;
        }
        Map<Character, Integer> table = buildLastTable(pattern);
        int j = 0;
        int current = 0;
        char temp;
        int shiftIndex;
        while (current <= text.length() - pattern.length()) {
            j =  pattern.length() - 1;
            temp = text.charAt(current + j);
            while (j >= 0 && comparator.compare(temp, pattern.charAt(j)) == 0) {
                j = j - 1;
                if (j >= 0) {
                    temp = text.charAt(current + j);
                }
            }
            if (j == -1) {
                matches.add(current);
                current++;
            } else {
                if (table.containsKey(temp)) {
                    shiftIndex = table.get(temp);
                } else {
                    shiftIndex = -1;
                }
                if (shiftIndex < j) {
                    current = current + (j - shiftIndex);
                } else {
                    current++;
                }
            }
        }
        return matches;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            map.put(pattern.charAt(i), i);
        }
        return map;
    }

}

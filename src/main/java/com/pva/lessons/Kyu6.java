package com.pva.lessons;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kyu6 {

    public static String decrypt(String encrypted) {
        final int BTL = 8;
        char[] enChar = encrypted.toCharArray();
        StringBuilder res = new StringBuilder();
        String p;
        for (int i = 0; i < enChar.length; i++) {
            p = Integer.toBinaryString((int) enChar[i]);
            for (int j = 0; j < 2 * BTL - p.length(); j++) {
                p = "0".concat(p);
            }
            res.append((char) Integer.parseInt(p.substring(BTL), 2));
        }
        return res.toString();
    }

    public static String extractFileName(String dirtyFileName) {
        Matcher m = Pattern.compile("_([a-zA-Z0-9_\\-]+\\.[a-zA-Z0-9\\[\\]]+)").matcher(dirtyFileName);
        String s = "";
        if (m.find()) {
            s = m.group(1);
        }
        return s;
    }

    public static long checkchoose(long m, int n) {
        long res = -1;
        BigInteger cc;
        for (long i = n - 1; i > 0; i--) {
            cc = (factorial((long) n).divide(factorial(i).multiply(factorial(n - i))));
            if (cc.equals(BigInteger.valueOf(m))) res = i;
        }
        return res;
    }

    public static BigInteger factorial(Long number) {
        BigInteger result = BigInteger.valueOf(1);
        for (long factor = 2; factor <= number; factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }
        return result;
    }

    static String toCamelCase(String s) {
        String[] stringList = s.split("-|_");
        StringBuilder res = new StringBuilder();
        res.append(stringList[0]);
        for (int i = 1; i < stringList.length; i++) {
            res.append(stringList[i].substring(0, 1).toUpperCase().concat(stringList[i].substring(1)));
        }
        return res.toString();
        /*
        * static String toCamelCase(String str){
        String[] words = str.split("[-_]");
            return Arrays.stream(words, 1, words.length)
            .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
            .reduce(words[0], String::concat);
        }*/
    }

    public static class Fraction implements Comparable<Fraction> {
        private final long top;
        private final long bottom;

        public Fraction(long numerator, long denominator) {
            int rs = getGBC(numerator, denominator);
            top = numerator / rs;
            bottom = denominator / rs;
        }

        @Override
        public int hashCode() {
            return 17 * Long.hashCode(top) + Long.hashCode(bottom);
        }

        @Override
        public boolean equals(Object o) {
            return compareTo((Fraction) o) == 0;
        }

        @Override
        public int compareTo(Fraction f2) {
            return Long.compare(top * f2.bottom, f2.top * bottom);
        }


        public Fraction add(Fraction f2) {
            return new Fraction(top * f2.bottom + bottom * f2.top, bottom * f2.bottom);
        }

        @Override
        public String toString() {
            return String.format("%d/%d", top, bottom);
        }

        private int getGBC(long a, long b) {
            BigInteger b1 = BigInteger.valueOf(a);
            BigInteger b2 = BigInteger.valueOf(b);
            BigInteger gcd = b1.gcd(b2);
            return gcd.intValue();
        }

    }

    public static int solution(int number) {
        int res = 0;
        for (int i = 0; i < number; i++) {
            if ((i%3==0) || (i%5==0)) {
                res += i;
            }
        }
        return res;
    }

    public static String[] inArray(String[] array1, String[] array2) {
        List<String> res = new ArrayList<>();
        for (String s : array1) {
            for (String s1 : array2) {
                if (s1.contains(s)) {
                    if (!res.contains(s)) res.add(s);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res.toArray(new String[res.size()]);

//        return Arrays.stream(array1)
//                .filter(str ->
//                        Arrays.stream(array2).anyMatch(s -> s.contains(str)))
//                .distinct()
//                .sorted()
//                .toArray(String[]::new);
    }

}

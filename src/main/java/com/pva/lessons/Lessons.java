package com.pva.lessons;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

public class Lessons {

    public static int toBinary(int n) {
        return Integer.valueOf(Integer.toBinaryString(n));
    }

    public static String stringy(int size) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < size; i++) {
            res.append(i%2==1 ? "0" : "1");
        }
        return res.toString();
    }

    public static String slope(int[] points) {
        int dx = points[2] - points[0];
        int dy = points[3] - points[1];
        if (dx==0.0) {
            return "undefined";
        }
        return String.valueOf(dy/dx);
    }

    public static int century(int number) {
        return number%100==0 ? number/100 : number/100 +1;
    }

    public static int twiceAsOld(int dadYears, int sonYears){
        int diff = dadYears - sonYears;
        for (int i = 0; i < diff * 2; i++) {
            if (i * 2 == diff) {
                return Math.abs(diff - dadYears);
            } else {
                diff++;
            }
        }
        return diff;
    }

    public static int[] invert(int[] array) {
        return IntStream.of(array).map(n -> -n).toArray();

    }

    public static String fakeString(String numberString) {

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : numberString.toCharArray()) {
            if (Character.getNumericValue(c) < 5) {
                stringBuilder.append('0');
            } else {
                stringBuilder.append('1');
            }
        }
        return stringBuilder.toString();
    }

    public static int grow(int[] x) {
        return Arrays.stream(x).reduce((s1, s2) -> s1 * s2).orElse(0);
    }

    public static int[] map(int[] arr) {
        return Arrays.stream(arr).map(num -> num *2).toArray();
    }

    public static int sum(int[] arr) {
        return IntStream.of(arr).map(num -> num>0 ? num : 0).sum();
    }

    public static String seriesSum(int n) {
        Double res = 0.0;
        Double m = 1.0;
        for (int i = 0; i < n; i++) {
            res+=1.0/m;
            m+=3.0;
        }
        return new BigDecimal(res).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static int nbDig(int n, int d) {
        long nn;
        int res = 0;
        String dd = String.valueOf(d);
        for (int i = 0; i <= n; i++) {
            nn = i*i;
            res+=String.valueOf(nn).replaceAll("[^" + dd + "]", "").length();
        }
        return res;
    }

    public static int findShort(String s) {
        return Arrays.stream(s.split(" ")).map(st -> st.length()).min(Integer::compare).get();
    }

    public static int unluckyDays(int year) {
        GregorianCalendar cal;
        cal = new GregorianCalendar();
        cal.setGregorianChange(new Date(Long.MIN_VALUE));
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DATE, 13);
        int res = 0;
        for (int i = 0; i < 12; i++) {
            cal.set(Calendar.MONTH, i);
            if (cal.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY) {
                res++;
            }
        }

        return res;
    }

    public static int sumTriangularNumbers(int n) {
        int res = 0;
        for (int i = 0; i < n + 1; i++) {
            res += i * (i + 1) / 2;
        }
        return res;
    }

    public static String makeComplement(String dna) {
        return dna.replaceAll("T", "n").replaceAll("A", "T").replaceAll("n", "A")
                .replaceAll("G", "n").replaceAll("C", "G").replaceAll("n", "C");
    }
}

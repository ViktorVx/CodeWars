package com.pva.lessons;

import java.util.stream.Stream;

public class Kyu7 {

    public static String reverseLetter(final String str) {
        return new StringBuilder(str.replaceAll("[^a-zA-Z]", "")).reverse().toString();
    }

    public static int timedReading(final int maxLength, final String text) {
        String ss = text.replaceAll("[^A-Za-z\\s]", "");
        if (ss.length()==0) return 0;
        return (int) Stream.of(ss.split(" ")).filter(s -> s.length()<=maxLength).count();
    }
}

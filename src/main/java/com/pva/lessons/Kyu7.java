package com.pva.lessons;

public class Kyu7 {

    public static String reverseLetter(final String str) {
        return new StringBuilder(str.replaceAll("[^a-zA-Z]", "")).reverse().toString();
    }
}

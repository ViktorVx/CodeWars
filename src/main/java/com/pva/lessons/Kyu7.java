package com.pva.lessons;

import java.util.*;
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

    public static int rowSumOddNumbers(int n) {
        return (((n-1)*n/2)^2 - (n*(n+1)/2)^2);
    }

    public static class Fighter {
        public String name;
        public int health, damagePerAttack;
        public Fighter(String name, int health, int damagePerAttack) {
            this.name = name;
            this.health = health;
            this.damagePerAttack = damagePerAttack;
        }
    }

    public static String declareWinner(Fighter fighter1, Fighter fighter2, String firstAttacker) {
        Fighter f1;
        Fighter f2;
        if (fighter1.name.equals(firstAttacker)) {
            f1 = fighter1;
            f2 = fighter2;
        } else {
            f1 = fighter2;
            f2 = fighter1;
        }
        boolean turn1 = true;
        while (true) {
            if (turn1) {
                f2.health -= f1.damagePerAttack;
                if (f2.health <= 0) return f1.name;
            } else {
                f1.health -= f2.damagePerAttack;
                if (f1.health<=0) return f2.name;
            }
            turn1 = !turn1;
        }
    }

    public static int ConvertBinaryArrayToInt(List<Integer> binary) {
        return Integer.parseInt(binary.toString().replaceAll("\\[|\\]|,| ", ""),2);
    }
}

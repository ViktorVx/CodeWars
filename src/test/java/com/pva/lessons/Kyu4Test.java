package com.pva.lessons;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class Kyu4Test {

    private static void testing(int actual, int expected) {
        assertEquals(expected, actual);
    }

    @Test
    public void dblLinear() {
        testing(Kyu4.dblLinear(10), 22);
        testing(Kyu4.dblLinear(20), 57);
        testing(Kyu4.dblLinear(30), 91);
        testing(Kyu4.dblLinear(50), 175);
        testing(Kyu4.dblLinear(5759), 77302);
        testing(Kyu4.dblLinear(500), 3355);
        testing(Kyu4.dblLinear(60000), 1511311);
    }

    @Test
    public void mix() {
        assertEquals("2:eeeee/2:yy/=:hh/=:rr", Kyu4.mix("Are they here", "yes, they are here"));
        assertEquals("1:ooo/1:uuu/2:sss/=:nnn/1:ii/2:aa/2:dd/2:ee/=:gg",
                Kyu4.mix("looping is fun but dangerous", "less dangerous than coding"));
        assertEquals("1:aaa/1:nnn/1:gg/2:ee/2:ff/2:ii/2:oo/2:rr/2:ss/2:tt",
                Kyu4.mix(" In many languages", " there's a pair of functions"));
        assertEquals("1:ee/1:ll/1:oo", Kyu4.mix("Lords of the Fallen", "gamekult"));
        assertEquals("", Kyu4.mix("codewars", "codewars"));
        assertEquals("1:nnnnn/1:ooooo/1:tttt/1:eee/1:gg/1:ii/1:mm/=:rr",
                Kyu4.mix("A generation must confront the looming ", "codewarrs"));
    }

    public static TreeMap<String, String[]> expectations = new TreeMap<String, String[]>() {
        {
            put("8", new String[]{"5", "7", "8", "9", "0"});
            put("11", new String[]{"11", "21", "41", "12", "22", "42", "14", "24", "44"});
            put("369", new String[]{"236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699"});
        }
    };
    private final static Comparator<String> comp = (s1, s2) -> s1.compareTo(s2);

    @Test
    public void testPins() {
        for (String entered : expectations.keySet()) {
            test(entered, Arrays.asList(expectations.get(entered)), Kyu4.getPINs(entered));
        }
    } // testPins

    private void test(String entered, List<String> expected, List<String> result) {
        result.sort(comp);
        expected.sort(comp);
        Assert.assertEquals("For observed PIN " + entered, expected, result);
    }

    @Test
    public void nextSmaller() {
        assertEquals(12, Kyu4.nextSmaller(21));
        assertEquals(790, Kyu4.nextSmaller(907));
        assertEquals(513, Kyu4.nextSmaller(531));
        assertEquals(-1, Kyu4.nextSmaller(1027));
        assertEquals(414, Kyu4.nextSmaller(441));
        assertEquals(123456789, Kyu4.nextSmaller(123456798));
        assertEquals(-1, Kyu4.nextSmaller(9999999999L));
        assertEquals(51226262627551L, Kyu4.nextSmaller(51226262651257L));
        assertEquals(59884848459853L, Kyu4.nextSmaller(59884848483559L));
        assertEquals(-1, Kyu4.nextSmaller(202233445566L));
        assertEquals(-1, Kyu4.nextSmaller(1023456789L));
        assertEquals(1072, Kyu4.nextSmaller(1207));
        assertEquals(208, Kyu4.nextSmaller(280L));
        assertEquals(-1, Kyu4.nextSmaller(2));

    }

    private static Object[][] testArr = new Object[][] {
            new Object[] {false, "" },
            new Object[] {false, "abc"},
            new Object[] {true,  "000"},
            new Object[] {true,  "101"},
            new Object[] {true,  "1010"},
            new Object[] {true,  "10100"},
            new Object[] {true,  Integer.toBinaryString(65020)},

            new Object[] {false, "10110101"},
            new Object[] {false, "1110001"},

            new Object[] {false,  Integer.toBinaryString(21)},
            new Object[] {false,  Integer.toBinaryString(15392)},
            new Object[] {false,  Integer.toBinaryString(23573)},
            new Object[] {false,  Integer.toBinaryString(19344)},

            new Object[] {false,  Integer.toBinaryString(43936)},
            new Object[] {false,  Integer.toBinaryString(32977)},
            new Object[] {false,  Integer.toBinaryString(328)},
            new Object[] {false,  Integer.toBinaryString(5729)}
    };

    @Test
    public void exampleTests() {
        for (Object[] arr: testArr) {
            boolean exp    = (boolean) arr[0];
            String  toTest = (String) arr[1];
            assertEquals(String.format("Should work with '%s':", toTest), exp, Kyu4.pattern().matcher(toTest).matches());
        }
    }
}
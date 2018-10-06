package com.pva.lessons;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Kyu6Test {

    @Test
    public void decrypt() {
//        SpyGraduation spy = new SpyGraduation();
        char[] expected = {17011, 20336, 20345, 19744, 17003, 20329, 20340};
        Assert.assertEquals("spy kit", Kyu6.decrypt(String.valueOf(expected)));

        //the result of ancestorsBlowfishEncrypt("Tandy", "NO MATTER WHAT THE PASSWORD IS")
        expected = new char[]{20052, 20321, 8302, 19812, 16761};
        Assert.assertEquals("Tandy", Kyu6.decrypt(String.valueOf(expected)));

        expected = new char[]{20340, 20321, 8302, 19812, 16761};
        Assert.assertEquals("tandy", Kyu6.decrypt(String.valueOf(expected)));

        //the result of ancestorsBlowfishEncrypt("Tandy", "abc")
        expected = new char[]{24916, 25185, 25454, 24932, 25209};
        Assert.assertEquals("Tandy", Kyu6.decrypt(String.valueOf(expected)));

        expected = new char[]{25185, 25454, 24932, 25209};
        Assert.assertEquals("andy", Kyu6.decrypt(String.valueOf(expected)));

//        String example = "Convert Java String";
//        byte[] bytes = example.getBytes();

//        byte[] text = "spy kit".getBytes();
//        byte[] password = "BOOM".getBytes();
//        System.out.println(text[0]^password[0]);
//        String textString = "spy kit";
//        String passwordString = "BOOM";
//
//        char[] textChar = textString.toCharArray();
//        char[] passwordChar = passwordString.toCharArray();
//
//        byte[] tx = textString.getBytes();
//        byte[] ps = passwordString.getBytes();
//
//        int t =Integer.parseInt("00000000" + Integer.toBinaryString((int) tx[0]), 2);
//        int p = Integer.parseInt(Integer.toBinaryString((int) ps[0]) + "00000000", 2);
//        int res = t^p;
//        System.out.println(t);
//        System.out.println(res);
//        System.out.println(res^p);


    }

    public char[] decode(String text) {
        String password = Integer.toBinaryString((int) 't');
        char[] res = new char[text.length()];
        char[] ch = text.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            Integer.toBinaryString((int) ch[i]);
//            int t = Integer.parseInt("00000000" + Integer.toBinaryString((int) ch[i]), 2);
//            int p = Integer.parseInt(Integer.toBinaryString((int) ps[0]) + "00000000", 2);
        }
        return null;
    }

    @Test
    public void extractFileName() {
        assertEquals(
                "FILE_NAME.EXTENSION",
                Kyu6.extractFileName("1231231223123131_FILE_NAME.EXTENSION.OTHEREXTENSION")
        );
        assertEquals(
                "FILE_NAME.EXTENSION",
                Kyu6.extractFileName("1_FILE_NAME.EXTENSION.OTHEREXTENSIONadasdassdassds34")
        );

        assertEquals(
                "FILE_NAM-E.EXTENSION",
                Kyu6.extractFileName("1_FILE_NAM-E.EXTENSION.OTHEREXTENSIONadasdassdassds34")
        );

        assertEquals(
                "8fj7jbi0mq0hl9m6s.ec[4]",
                Kyu6.extractFileName("1_8fj7jbi0mq0hl9m6s.ec[4].OTHEREXTENSIONadasdassdassds34")
        );
    }

    @Test
    public void checkchoose() {
        assertEquals(2, Kyu6.checkchoose(6, 4));
        assertEquals(1, Kyu6.checkchoose(4, 4));
        assertEquals(3, Kyu6.checkchoose(35, 7));
        assertEquals(-1, Kyu6.checkchoose(4, 2));
        assertEquals(-1, Kyu6.checkchoose(36, 7));
        assertEquals(6, Kyu6.checkchoose(100947, 23));
    }

    @Test
    public void toCamelCase() {
        String input = "the_Stealth_Warrior";
        System.out.println("input: "+input);
        assertEquals("theStealthWarrior", Kyu6.toCamelCase(input));

        input = "the-Stealth-Warrior";
        System.out.println("input: "+input);
        assertEquals("theStealthWarrior", Kyu6.toCamelCase(input));
    }

    @Test
    public void FractionTest() {
        Assert.assertEquals(new Kyu6.Fraction(37, 40), new Kyu6.Fraction(1, 8).add(new Kyu6.Fraction(4, 5)));
        Assert.assertEquals(new Kyu6.Fraction(1, 1), new Kyu6.Fraction(1, 4).add(new Kyu6.Fraction(3, 4)));
        Assert.assertEquals(new Kyu6.Fraction(863483, 416760), new Kyu6.Fraction(911, 920).add(new Kyu6.Fraction(980, 906)));
        Assert.assertEquals(new Kyu6.Fraction(838923, 926885), new Kyu6.Fraction(610, 941).add(new Kyu6.Fraction(253, 985)));
        Assert.assertEquals(new Kyu6.Fraction(16880, 3591), new Kyu6.Fraction(956, 798).add(new Kyu6.Fraction(662, 189)));
        Assert.assertEquals(new Kyu6.Fraction(1011239, 417585), new Kyu6.Fraction(694, 485).add(new Kyu6.Fraction(853, 861)));
        Assert.assertEquals(new Kyu6.Fraction(191737, 20757), new Kyu6.Fraction(219, 561).add(new Kyu6.Fraction(982, 111)));
        Assert.assertEquals(new Kyu6.Fraction(41201, 23571), new Kyu6.Fraction(344, 873).add(new Kyu6.Fraction(658, 486)));
        Assert.assertEquals(new Kyu6.Fraction(184563, 68951), new Kyu6.Fraction(662, 361).add(new Kyu6.Fraction(322, 382)));
        Assert.assertEquals(new Kyu6.Fraction(33926, 23577), new Kyu6.Fraction(740, 813).add(new Kyu6.Fraction(184, 348)));
        Assert.assertEquals(new Kyu6.Fraction(78524, 39543), new Kyu6.Fraction(579, 441).add(new Kyu6.Fraction(543, 807)));
        Assert.assertEquals(new Kyu6.Fraction(83997, 283910), new Kyu6.Fraction(212, 979).add(new Kyu6.Fraction(46, 580)));

        Assert.assertEquals(new Kyu6.Fraction(1, 2), new Kyu6.Fraction(4, 8));
        Assert.assertEquals(new Kyu6.Fraction(2, 3), new Kyu6.Fraction(10, 15));
        Assert.assertEquals(new Kyu6.Fraction(4, 9), new Kyu6.Fraction(24, 54));

        Assert.assertEquals("4/5", (new Kyu6.Fraction(2, 5).add(new Kyu6.Fraction(2, 5))).toString());
        Assert.assertEquals("5/6", (new Kyu6.Fraction(2, 4).add(new Kyu6.Fraction(1, 3))).toString());
        Assert.assertEquals("13/15", (new Kyu6.Fraction(1, 5).add(new Kyu6.Fraction(4, 6))).toString());
    }

    @Test
    public void solution() {
        assertEquals(23, Kyu6.solution(10));
    }

    @Test
    public void inArray() {
        String a[] = new String[]{ "arp", "live", "strong" };
        String b[] = new String[] { "lively", "alive", "harp", "sharp", "armstrong" };
        String r[] = new String[] { "arp", "live", "strong" };
        assertArrayEquals(r, Kyu6.inArray(a, b));
    }
}
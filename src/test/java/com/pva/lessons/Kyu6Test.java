package com.pva.lessons;

import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

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

        Assert.assertEquals("tandy", Kyu6.decrypt("tandy"));

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
        System.out.println("input: " + input);
        assertEquals("theStealthWarrior", Kyu6.toCamelCase(input));

        input = "the-Stealth-Warrior";
        System.out.println("input: " + input);
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
        String a[] = new String[]{"arp", "live", "strong"};
        String b[] = new String[]{"lively", "alive", "harp", "sharp", "armstrong"};
        String r[] = new String[]{"arp", "live", "strong"};
        assertArrayEquals(r, Kyu6.inArray(a, b));
    }

    @Test
    public void spinWords() {
        assertEquals("emocleW", Kyu6.spinWords("Welcome"));
        assertEquals("Hey wollef sroirraw", Kyu6.spinWords("Hey fellow warriors"));
    }

    @Test
    public void test1() {
        assertTrue(Kyu6.fortune(100000, 1, 2000, 15, 1));
        assertFalse(Kyu6.fortune(100000, 1, 9185, 12, 1));
        assertTrue(Kyu6.fortune(100000000, 1, 100000, 50, 1));
        assertFalse(Kyu6.fortune(100000000, 1.5, 10000000, 50, 1));
        assertTrue(Kyu6.fortune(100000000, 5, 1000000, 50, 1));
        assertTrue(Kyu6.fortune(4390592, 7.0, 293256, 17, 5.0));
//        assertTrue(Kyu6.fortune(4007965, 3.0, 268211, 22, 2.0));
//        assertTrue(Kyu6.fortune(4390592, 7.0, 293256, 17, 5.0));
        assertTrue(Kyu6.fortune(10669590, 1.0, 712798, 11, 2.0));
        assertTrue(Kyu6.fortune(5336102, 8.0, 356570, 22, 5.0));


    }

    @Test
    public void encrypt() {
        assertEquals("text = '', rule = 1", "", Kyu6.encrypt("", 1));
        assertEquals("text = 'a', rule = 1", "b", Kyu6.encrypt("a", 1));
        assertEquals("text = 'please encrypt me', rule = 2", "rngcug\"gpet{rv\"og", Kyu6.encrypt("please encrypt me", 2));

    }

    @Test
    public void crackSha256() {
//        assertEquals("GoOutside", Kyu6.crackSha256("b8c49d81cb795985c007d78379e98613a4dfc824381be472238dbd2f974e37ae", "deGioOstu"));
//        assertEquals(null, Kyu6.crackSha256("f58262c8005bb64b8f99ec6083faf050c502d099d9929ae37ffed2fe1bb954fb", "abc"));
    }

    @Test
    public void game() {
        assertEquals("[0]", Kyu6.game(0));
        assertEquals("[1, 2]", Kyu6.game(1));
        assertEquals("[2]", Kyu6.game(2));
        assertEquals("[9, 2]", Kyu6.game(3));
        assertEquals("[8]", Kyu6.game(4));
        assertEquals("[25, 2]", Kyu6.game(5));
        assertEquals("[18]", Kyu6.game(6));
        assertEquals("[49, 2]", Kyu6.game(7));
        assertEquals("[32]", Kyu6.game(8));
        assertEquals("[81, 2]", Kyu6.game(9));
        assertEquals("[50]", Kyu6.game(10));
    }

    @Test
    public void thirt() {
        assertEquals(87, Kyu6.thirt(1234567));
        assertEquals(79, Kyu6.thirt(8529));
        assertEquals(31, Kyu6.thirt(85299258));
        assertEquals(57, Kyu6.thirt(5634));
        assertEquals(71, Kyu6.thirt(1111111111));
        assertEquals(30, Kyu6.thirt(987654321));
    }


    @Test
    public void countOddPentaFib() {
        long[] lstI = new long[]{45, 68, 76, 100, 121, 2100, 15000, 3948, 7637, 8555};
        long[] resultsI = new long[]{15, 23, 25, 33, 40, 699, 4999, 1315, 2545, 2851};
        for (int i = 0; i <= 9; i++) {
            assertEquals(resultsI[i], Kyu6.countOddPentaFib(lstI[i]));
        }
    }

    @Test
    public void countBits() {
        assertEquals(5, Kyu6.countBits(1234));
        assertEquals(1, Kyu6.countBits(4));
        assertEquals(3, Kyu6.countBits(7));
        assertEquals(2, Kyu6.countBits(9));
        assertEquals(2, Kyu6.countBits(10));
    }

    @Test
    public void computeDepth() {
        assertEquals(10, Kyu6.computeDepth(1));
        assertEquals(9, Kyu6.computeDepth(42));
    }

    private void testing(long actual, long expected) {
        long r = Math.abs(actual - expected);
        boolean inrange = r <= 1;
        if (inrange == false) {
            DecimalFormat df = new DecimalFormat("#.0");
            System.out.println("abs(actual - expected) must be <= 1 but was " + df.format(r));
        }
        assertEquals(true, inrange);
    }

    @Test
    public void epidemicTest() {
        System.out.println("Fixed Tests: Epidemic");
        int tm = 18;
        int n = 432;
        int s0 = 1004;
        int i0 = 1;
        double b = 0.00209;
        double a = 0.51;
        testing(Kyu6.epidemic(tm, n, s0, i0, b, a), 420);
        tm = 12;
        n = 288;
        s0 = 1007;
        i0 = 2;
        b = 0.00206;
        a = 0.45;
        testing(Kyu6.epidemic(tm, n, s0, i0, b, a), 461);
        tm = 13;
        n = 312;
        s0 = 999;
        i0 = 1;
        b = 0.00221;
        a = 0.55;
        testing(Kyu6.epidemic(tm, n, s0, i0, b, a), 409);
        tm = 24;
        n = 576;
        s0 = 1005;
        i0 = 1;
        b = 0.00216;
        a = 0.45;
        testing(Kyu6.epidemic(tm, n, s0, i0, b, a), 474);
        tm = 24;
        n = 576;
        s0 = 982;
        i0 = 1;
        b = 0.00214;
        a = 0.44;
        testing(Kyu6.epidemic(tm, n, s0, i0, b, a), 460);
        tm = 20;
        n = 480;
        s0 = 1000;
        i0 = 1;
        b = 0.00199;
        a = 0.53;
        testing(Kyu6.epidemic(tm, n, s0, i0, b, a), 386);
        tm = 28;
        n = 672;
        s0 = 980;
        i0 = 1;
        b = 0.00198;
        a = 0.44;
        testing(Kyu6.epidemic(tm, n, s0, i0, b, a), 433);
        tm = 14;
        n = 336;
        s0 = 996;
        i0 = 2;
        b = 0.00206;
        a = 0.41;
        testing(Kyu6.epidemic(tm, n, s0, i0, b, a), 483);
        tm = 13;
        n = 312;
        s0 = 993;
        i0 = 2;
        b = 0.0021;
        a = 0.51;
        testing(Kyu6.epidemic(tm, n, s0, i0, b, a), 414);
        tm = 28;
        n = 672;
        s0 = 999;
        i0 = 1;
        b = 0.00197;
        a = 0.55;
        testing(Kyu6.epidemic(tm, n, s0, i0, b, a), 368);
    }

    @Test
    public void tankVol() {
        assertEquals(2940, Kyu6.tankVol(5, 7, 3848));
        assertEquals(907, Kyu6.tankVol(2, 7, 3848));
    }

    @Test
    public void digPow() {
        assertEquals(1, Kyu6.digPow(89, 1));
        assertEquals(-1, Kyu6.digPow(92, 1));
        assertEquals(51, Kyu6.digPow(46288, 3));
    }

    @Test
    public void numPrimorial() {
        assertEquals(  "30", Kyu6.numPrimorial(3));
        assertEquals( "210", Kyu6.numPrimorial(4));
        assertEquals("2310", Kyu6.numPrimorial(5));
        assertEquals("9699690", Kyu6.numPrimorial(8));
        assertEquals("223092870", Kyu6.numPrimorial(9));
    }

    @Test
    public void persistence() {
        assertEquals(3, Kyu6.persistence(39));
        assertEquals(0, Kyu6.persistence(4));
        assertEquals(2, Kyu6.persistence(25));
        assertEquals(4, Kyu6.persistence(999));
    }

    @Test
    public void whoLikesIt() {
        assertEquals("no one likes this", Kyu6.whoLikesIt());
        assertEquals("Peter likes this", Kyu6.whoLikesIt("Peter"));
        assertEquals("Jacob and Alex like this", Kyu6.whoLikesIt("Jacob", "Alex"));
        assertEquals("Max, John and Mark like this", Kyu6.whoLikesIt("Max", "John", "Mark"));
        assertEquals("Alex, Jacob and 2 others like this", Kyu6.whoLikesIt("Alex", "Jacob", "Mark", "Max"));
    }
}
package com.pva.lessons;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class Kyu5Test {

    @Test
    public void ipsBetween() {
        assertEquals( 50, Kyu5.ipsBetween( "10.0.0.0", "10.0.0.50" ) );
        assertEquals( 246, Kyu5.ipsBetween( "20.0.0.10", "20.0.1.0" ) );
        assertEquals( 1, Kyu5.ipsBetween( "150.0.0.0", "150.0.0.1" ) );
    }


    @Test
    public void checkCourse() {
        char[][] map = new char[5][6];
        char[] line1 = {'0','0','0','0','N','0'};
        char[] line2 = {'0','0','0','0','0','0'};
        char[] line3 = {'X','0','0','0','0','0'};
        char[] line4 = {'0','0','0','0','0','0'};
        char[] line5 = {'0','0','0','0','0','0'};
        map[0] = line1;
        map[1] = line2;
        map[2] = line3;
        map[3] = line4;
        map[4] = line5;
        assertEquals(false,Kyu5.checkCourse(map));

        char[][] map2 = new char[5][6];
        char[] line11 = {'0','0','0','0','0','0'};
        char[] line12 = {'0','0','0','0','0','0'};
        char[] line13 = {'X','0','0','0','0','0'};
        char[] line14 = {'0','0','0','0','0','0'};
        char[] line15 = {'0','0','N','0','0','0'};
        map2[0] = line11;
        map2[1] = line12;
        map2[2] = line13;
        map2[3] = line14;
        map2[4] = line15;
        assertEquals(false,Kyu5.checkCourse(map2));

        char[][] map3 = new char[29][132];
        for (int i = 0; i < map3.length; i++) {
            for (int j = 0; j < map3[0].length; j++) {
                map3[i][j] = 0;
            }
        }
        map3[11][0] = 'X';
        map3[0][6] = 'N';
        map3[0][14] = 'N';
        map3[0][15] = 'N';
        map3[0][20] = 'N';
        map3[0][21] = 'N';
        map3[0][52] = 'N';
        map3[0][59] = 'N';
        map3[0][60] = 'N';
        map3[0][61] = 'N';
        map3[0][82] = 'N';
        map3[0][100] = 'N';
        map3[0][116] = 'N';
        map3[0][128] = 'N';
        map3[28][4] = 'N';
        map3[28][7] = 'N';
        map3[28][9] = 'N';
        map3[28][46] = 'N';
        map3[28][54] = 'N';
        map3[28][55] = 'N';
        map3[28][76] = 'N';
        map3[28][84] = 'N';
        map3[28][99] = 'N';
        map3[28][112] = 'N';
        map3[28][126] = 'N';
        assertEquals(false,Kyu5.checkCourse(map3));

        char[][] map4 = new char[84][163];
        for (int i = 0; i < map3.length; i++) {
            for (int j = 0; j < map3[0].length; j++) {
                map3[i][j] = 0;
            }
        }
        map4[24][0] = 'X';
        map4[0][5] = 'N';
        map4[0][10] = 'N';
        map4[0][11] = 'N';
        map4[0][17] = 'N';
        map4[0][36] = 'N';
        map4[0][47] = 'N';
        map4[0][48] = 'N';
        map4[0][54] = 'N';
        map4[0][69] = 'N';
        map4[0][76] = 'N';
        map4[0][85] = 'N';
        map4[0][101] = 'N';
        map4[0][117] = 'N';
        map4[0][118] = 'N';
        map4[0][145] = 'N';
        map4[0][151] = 'N';
        map4[83][9] = 'N';
        map4[83][32] = 'N';
        map4[83][35] = 'N';
        map4[83][39] = 'N';
        map4[83][52] = 'N';
        map4[83][63] = 'N';
        map4[83][89] = 'N';
        map4[83][91] = 'N';
        map4[83][92] = 'N';
        map4[83][99] = 'N';
        map4[83][130] = 'N';
        map4[83][134] = 'N';
        map4[83][137] = 'N';
        map4[83][156] = 'N';
        assertEquals(true, Kyu5.checkCourse(map4));
    }

    @Test
    public void smallest() {
        testing1(Long.valueOf("128276222169554528"), "[112827622269554528, 9, 0]");
        testing1(269045, "[26945, 3, 0]");
        testing1(261235, "[126235, 2, 0]");
        testing1(285365, "[238565, 3, 1]");
        testing1(296837, "[239687, 4, 1]");
        testing1(209917, "[29917, 0, 1]");
    }

    private static void testing1(long n, String res) {
        assertEquals(res,
                Arrays.toString(Kyu5.smallest(n)));
    }

    @Test
    public void phone() {
        String dr = "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n"
                + "+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n"
                + "+1-741-984-3090 <Peter Reedgrave> _Chicago\n :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n"
                + "+1-111-544-8973 <Peter Pan> LA\n +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n"
                + "<Peter Gone> LA ?+1-121-544-8974 \n <R Steell> Quora Street AB-47209 +1-481-512-2222\n"
                + "<Arthur Clarke> San Antonio $+1-121-504-8974 TT-45120\n <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n"
                + "<Sophia Loren> +1-421-674-8974 Bern TP-46017\n <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n"
                + "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n <P Salinger> Main Street, +1-098-512-2222, Denver\n"
                + "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n"
                + "+1-099-500-8000 <Peter Crush> Labrador Bd.\n +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n"
                + "<P Salinge> Main Street, +1-098-512-2222, Denve\n"+ "<P Salinge> Main Street, +1-098-512-2222, Denve\n";

        testing(Kyu5.phone(dr, "48-421-674-8974"), "Phone => 48-421-674-8974, Name => Anastasia, Address => Via Quirinal Roma");
        testing(Kyu5.phone(dr, "1-921-512-2222"), "Phone => 1-921-512-2222, Name => Wilfrid Stevens, Address => Wild Street AA-67209");
        testing(Kyu5.phone(dr, "1-908-512-2222"), "Phone => 1-908-512-2222, Name => Peter O'Brien, Address => High Street CC-47209");
        testing(Kyu5.phone(dr, "1-541-754-3010"), "Phone => 1-541-754-3010, Name => J Steeve, Address => 156 Alphand St.");
        testing(Kyu5.phone(dr, "1-121-504-8974"), "Phone => 1-121-504-8974, Name => Arthur Clarke, Address => San Antonio TT-45120");
        testing(Kyu5.phone(dr, "1-498-512-2222"), "Phone => 1-498-512-2222, Name => Bernard Deltheil, Address => Mount Av. Eldorado");
        testing(Kyu5.phone(dr, "1-098-512-2222"), "Error => Too many people: 1-098-512-2222");
        testing(Kyu5.phone(dr, "5-555-555-5555"), "Error => Not found: 5-555-555-5555");
    }
    private static void testing(String actual, String expected) {
        assertEquals(expected, actual);
    }

    @Test
    public void BasicTests1() {
        System.out.println("****** Basic Tests small numbers******");
        List<Integer> ts = new ArrayList<>(Arrays.asList(50, 55, 56, 57, 58));
        int n = Kyu5.chooseBestSum(163, 3, ts);
        assertEquals(163, n);
        ts = new ArrayList<>(Arrays.asList(50));
        Integer m = Kyu5.chooseBestSum(163, 3, ts);
        assertEquals(null, m);
        ts = new ArrayList<>(Arrays.asList(91, 74, 73, 85, 73, 81, 87));
        n = Kyu5.chooseBestSum(230, 3, ts);
        assertEquals(228, n);
        ts = new ArrayList<>(Arrays.asList(100, 76, 56, 44, 89, 73, 68, 56, 64, 123, 2333, 144, 50, 132, 123, 34, 89));
        n = Kyu5.chooseBestSum(230, 4, ts);
        assertEquals(230, n);
        ts = new ArrayList<>(Arrays.asList(100, 76, 56, 44, 89, 73, 68, 56, 64, 123, 2333, 144, 50, 132, 123, 34, 89));
        n = Kyu5.chooseBestSum(430, 5, ts);
        assertEquals(430, n);
        ts = new ArrayList<>(Arrays.asList(100, 76, 56, 44, 89, 73, 68, 56, 64, 123, 2333, 144, 50, 132, 123, 34, 89));
        m = Kyu5.chooseBestSum(430, 8, ts);
        assertEquals(null, m);
        ts = new ArrayList<>(Arrays.asList(100, 76, 56, 44, 89, 73, 68, 56, 64, 123, 2333, 144, 50, 132, 123, 34, 89));
        n = Kyu5.chooseBestSum(880, 8, ts);
        assertEquals( 876, n);
    }

    @Test
    public void orderWeight() {
        assertEquals("2000 103 123 4444 99", Kyu5.orderWeight("103 123 4444 99 2000"));
        assertEquals("11 11 2000 10003 22 123 1234000 44444444 9999", Kyu5.orderWeight("2000 10003 1234000 44444444 9999 11 11 22 123"));

    }

    @Test
    public void whoIsNext() {
        String[] names = new String[] { "Sheldon", "Leonard", "Penny", "Rajesh", "Howard" };
        assertEquals("Sheldon", Kyu5.WhoIsNext(names, 6));
        assertEquals("Penny", Kyu5.WhoIsNext(names, 52));
//        assertEquals("Leonard", Kyu5.WhoIsNext(names, Integer.valueOf("7230702951")));
        assertEquals("Sheldon", Kyu5.WhoIsNext(names, 1));
    }

    @Test
    public void removNb() {
        List<long[]> res = new ArrayList<>();
        res.add(new long[] {15, 21});
        res.add(new long[] {21, 15});
        List<long[]> a = Kyu5.removNb(26);
        assertArrayEquals(res.get(0), a.get(0));
        assertArrayEquals(res.get(1), a.get(1));

        res = new ArrayList<>();
        a = Kyu5.removNb(100);
        assertTrue(res.size() == a.size());

        a = Kyu5.removNb(3200016);
        a = Kyu5.removNb(Long.MAX_VALUE);
    }

    @Test
    public void dirReduc() {
        assertArrayEquals("\"NORTH\", \"SOUTH\", \"SOUTH\", \"EAST\", \"WEST\", \"NORTH\", \"WEST\"",
                new String[]{"WEST"},
                Kyu5.dirReduc(new String[]{"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"}));
        assertArrayEquals("\"NORTH\",\"SOUTH\",\"SOUTH\",\"EAST\",\"WEST\",\"NORTH\"",
                new String[]{},
                Kyu5.dirReduc(new String[]{"NORTH","SOUTH","SOUTH","EAST","WEST","NORTH"}));
    }

    @Test
    public void productFib() {
        long[] r = new long[] {55, 89, 1};
        assertArrayEquals(r, Kyu5.productFib(4895));
        r = new long[] {89, 144, 0};
        assertArrayEquals(r, Kyu5.productFib(5895));
    }

    @Test
    public void listSquared() {
        assertEquals("[[1, 1], [42, 2500], [246, 84100]]", Kyu5.listSquared2(1, 250));
        assertEquals("[[42, 2500], [246, 84100]]", Kyu5.listSquared2(42, 250));
        assertEquals("[[287, 84100]]", Kyu5.listSquared2(250, 500));
        assertEquals("[]", Kyu5.listSquared2(3, 6));


    }

    @Test
    public void going() {
        final double DELTA = 1e-10;
        assertEquals(1.275, Kyu5.going(5), DELTA);
        assertEquals(1.2125, Kyu5.going(6), DELTA);
        assertEquals(1.173214, Kyu5.going(7), DELTA);
        assertEquals(1.001915, Kyu5.going(523), DELTA);
    }

    @Test
    public void greedy() {
        assertEquals("Score for [5,1,3,4,1] must be 250:", 250, Kyu5.greedy(new int[]{5,1,3,4,1}));
        assertEquals("Score for [1,1,1,3,1] must be 1100:", 1100, Kyu5.greedy(new int[]{1,1,1,3,1}));
        assertEquals("Score for [2,4,4,5,4] must be 450:", 450, Kyu5.greedy(new int[]{2,4,4,5,4}));
    }


    @Test
    public void multiply() {
        int[][] a = new int[][] {
                new int[] {1, 2, 3},
                new int[] {0, 1, 4},
                new int[] {5, 6, 0}
        };
        int[][] b = new int[][] {
                new int[] {-24, 18, 5},
                new int[] {20, -15, -4},
                new int[] {-5, 4, 1}
        };
        assertArrayEquals(new int[][] {
                new int[] {1, 0, 0},
                new int[] {0, 1, 0},
                new int[] {0, 0, 1}
        }, Kyu5.multiply(a, b));
        //***
        int[][] a1 = new int[][] {
                new int[] {1, 2, 0},
                new int[] {0, 1, 2},
        };
        int[][] b1 = new int[][] {
                new int[] {3, 1},
                new int[] {2, 4},
                new int[] {3, 7}
        };
        assertArrayEquals(new int[][] {
                new int[] {7, 9},
                new int[] {8, 18},
        }, Kyu5.multiply(a1, b1));

    }

    @Test
    public void change() {
        String s1="Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-0091\nDate: Tues April 9, 2005\nVersion: 6.7\nLevel: Alpha";
        dotest(s1, "Ladder", "1.1", "Program: Ladder Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 1.1");
        String s11="Program title: Hammer\nAuthor: Tolkien\nCorporation: IB\nPhone: +1-503-555-0090\nDate: Tues March 29, 2017\nVersion: 2.0\nLevel: Release";
        dotest(s11, "Balance", "1.5.6", "Program: Balance Author: g964 Phone: +1-503-555-0090 Date: 2019-01-01 Version: 2.0");
        String s12="Program title: Primes\nAuthor: Kern\nCorporation: Gold\nPhone: +1-503-555-009\nDate: Tues April 9, 2005\nVersion: 6.7\nLevel: Alpha";
        dotest(s12, "Ladder", "1.1", "ERROR: VERSION or PHONE");
        String s13 = "Program title: Circular\n" +
                "Author: Cornwell\n" +
                "Corporation: MS\n" +
                "Phone: +1-503-555-0073\n" +
                "Date: Tues March 10, 2004\n" +
                "Version: 500\n" +
                "Level: Release\n" +
                "prog: Exhaust\n" +
                "version: 2.0";
        dotest(s12, "Exhaust", "2.0", "ERROR: VERSION or PHONE");

    }

    private static void dotest(String s, String prog, String version, String exp) {
        System.out.println("s:\n" + s);
        System.out.println("prog: " + prog);
        System.out.println("version: " + version);
        String ans = Kyu5.change(s, prog, version);
        System.out.println("Actual: " + ans);
        System.out.println("Expect: " + exp);
        assertEquals(exp, ans);
    }

}
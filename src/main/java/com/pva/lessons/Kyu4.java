package com.pva.lessons;


import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Kyu4 {

    public static int dblLinear(int n) {
        Set resSet = new TreeSet();
        Long totalLevels = Math.round(Math.ceil(Math.log(n) / Math.log(2))) + 2;
        resSet.add(1);
        addNew(resSet, 1, 1L, totalLevels);
        return (Integer) resSet.toArray()[n];
    }

    public static void addNew(Set treeSet, Integer num, Long level, Long totalLevels) {
        if (level > totalLevels) return;
        level++;
        Integer two = (num<<1) + 1;
        Integer three = 3 * num + 1;
        treeSet.add(two);
        treeSet.add(three);
        addNew(treeSet, two, level, totalLevels);
        addNew(treeSet, three, level, totalLevels);
    }

    //********************************

    public static String mix(String s1, String s2) {
        char[] ch1 = s1.replaceAll("[^a-z]", "").toCharArray();
        char[] ch2 = s2.replaceAll("[^a-z]", "").toCharArray();

        Map<Character, String> hm1 = new TreeMap<>();
        Map<Character, String> hm2 = new TreeMap<>();

        for (char c : ch1) if (hm1.containsKey(c)) hm1.put(c, hm1.get(c).concat(String.valueOf(c))); else hm1.put(c, String.valueOf(c));
        for (char c : ch2) if (hm2.containsKey(c)) hm2.put(c, hm2.get(c).concat(String.valueOf(c))); else hm2.put(c, String.valueOf(c));

        for(Iterator<Map.Entry<Character, String>> it = hm1.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Character, String> entry = it.next();
            if(entry.getValue().length() == 1) it.remove();
        }
        for(Iterator<Map.Entry<Character, String>> it = hm2.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Character, String> entry = it.next();
            if(entry.getValue().length() == 1) it.remove();
        }

        Set<Character> chSet = new TreeSet<>();
        chSet.addAll(hm1.keySet());
        chSet.addAll(hm2.keySet());

        final Integer CHAR_SORT = 1000;
        final Integer S1_SORT = 2000;
        final Integer S2_SORT = 1000;
        final Integer LEN_SORT = 10000;
        Map<Integer, String> resMap = new TreeMap<>();
        for (Character ch : chSet) {
            if (hm1.get(ch)==null && hm2.get(ch)!=null){ resMap.put(S2_SORT + hm2.get(ch).length()*LEN_SORT + (CHAR_SORT - Integer.valueOf(ch)),"2:".concat(hm2.get(ch)).concat("/")); continue;}
            if (hm1.get(ch)!=null && hm2.get(ch)==null){ resMap.put(S1_SORT + hm1.get(ch).length()*LEN_SORT + (CHAR_SORT - Integer.valueOf(ch)),"1:".concat(hm1.get(ch)).concat("/")); continue;}

            if (hm1.get(ch).length()>hm2.get(ch).length()) { resMap.put(S1_SORT + hm1.get(ch).length()*LEN_SORT + (CHAR_SORT - Integer.valueOf(ch)), "1:".concat(hm1.get(ch)).concat("/")); continue;}
            if (hm1.get(ch).length()<hm2.get(ch).length()) { resMap.put(S2_SORT + hm2.get(ch).length()*LEN_SORT + (CHAR_SORT - Integer.valueOf(ch)), "2:".concat(hm2.get(ch)).concat("/")); continue;}

            if (hm1.get(ch).length() == hm2.get(ch).length()) resMap.put(hm1.get(ch).length()*LEN_SORT + (CHAR_SORT - Integer.valueOf(ch)), "=:".concat(hm1.get(ch)).concat("/"));
        }

        String res = "";
        for (String s : resMap.values()) res = s.concat(res);

        return res.length() == 0 ? "" : res.substring(0, res.length() - 1);
    }

    //********************************
    static final String[][] ARR = {
            {"0", "8"},
            {"1", "2", "4"},
            {"1", "2", "3", "5"},
            {"2", "3", "6"},
            {"1", "4", "5", "7"},
            {"2", "4", "5", "6", "8"},
            {"3", "5", "6", "9"},
            {"4", "7", "8"},
            {"0", "5", "7", "8", "9"},
            {"6", "8", "9"},
    };
    public static List<String> getPINs(String observed) {
        if (observed==null || observed.length() == 0) return new ArrayList<>();
        List<String> resList = new ArrayList<>();
        generatePin(resList, observed.length(), 0, observed, "");
        return resList;
    }

    public static void generatePin(List<String> resList, Integer codeLen, Integer level, String observed, String pin) {
        if (pin.length() == codeLen) {
            resList.add(pin);
            return;
        }
        Integer l = Character.getNumericValue(observed.charAt(level));
        for (int i = 0; i < ARR[l].length; i++) generatePin(resList, codeLen, level + 1, observed, pin.concat(ARR[l][i]));
    }

    public static long nextSmaller(long n) {
        String s = String.valueOf(n);
        if (s.length() == 1) return -1;
        //****
        if (s.charAt(1) == '0') {
            Integer iLast = -1;
            boolean isGrouth = true;
            for (char c : s.toCharArray()) {
                Integer i = Character.getNumericValue(c);
                if (i == 0) continue;
                if (i < iLast) {
                    isGrouth = false;
                    break;
                }
                iLast = i;
            }
            if (isGrouth) return -1;
        }
        //****
        char[] nChars = s.toCharArray();
        Set<Character> ss = new String(nChars).chars().mapToObj(i->(char) i).collect(Collectors.toSet());
        if (ss.size() == 1) return -1;
        int len = nChars.length;
        Arrays.sort(nChars);

        while (n != 0) {
            char[] chars = String.valueOf(--n).toCharArray();
            if (chars.length > len) break;
            Arrays.sort(chars);
            if (Arrays.equals(chars, nChars)) return n;
        }
        return -1;
    }

    public static long nextBiggerNumber(long n) {
        String s = String.valueOf(n);
        if (s.length() == 1) return -1;
        //****
            Integer iLast = Integer.MAX_VALUE;
            boolean isGrouth = true;
            for (char c : s.toCharArray()) {
                Integer i = Character.getNumericValue(c);
                if (i == 0) continue;
                if (i > iLast) {
                    isGrouth = false;
                    break;
                }
                iLast = i;
            }
            if (isGrouth) return -1;
        //****
        char[] nChars = s.toCharArray();
        Set<Character> ss = new String(nChars).chars().mapToObj(i->(char) i).collect(Collectors.toSet());
        if (ss.size() == 1) return -1;
        int len = nChars.length;
        Arrays.sort(nChars);

        while (true) {
            char[] chars = String.valueOf(++n).toCharArray();
            if (chars.length < len) break;
            Arrays.sort(chars);
            if (Arrays.equals(chars, nChars)) return n;
        }
        return -1;
    }

    public static Pattern pattern() {
        return Pattern.compile("(((((0)|(0)(0)*(0)))|(((1)|(0)(0)*(1))((0)))(((1)(0)*(1))((0)))*(((1)|(1)(0)*(0))))|((((1)|(0)(0)*(1))((1)))|(((1)|(0)(0)*(1))((0)))(((1)(0)*(1))((0)))*(((1)(0)*(1))((1))))((((0))((1)))|(((1))|((0))((0)))(((1)(0)*(1))((0)))*(((1)(0)*(1))((1))))*((((1))|((0))((0)))(((1)(0)*(1))((0)))*(((1)|(1)(0)*(0)))))|(((((1)|(0)(0)*(1))((0)))(((1)(0)*(1))((0)))*(((0))))|((((1)|(0)(0)*(1))((1)))|(((1)|(0)(0)*(1))((0)))(((1)(0)*(1))((0)))*(((1)(0)*(1))((1))))((((0))((1)))|(((1))|((0))((0)))(((1)(0)*(1))((0)))*(((1)(0)*(1))((1))))*((((1))|((0))((0)))(((1)(0)*(1))((0)))*(((0)))))(((((1))))|((((0))))((((0))((1)))|(((1))|((0))((0)))(((1)(0)*(1))((0)))*(((1)(0)*(1))((1))))*((((1))|((0))((0)))(((1)(0)*(1))((0)))*(((0)))))*(((((0))))((((0))((1)))|(((1))|((0))((0)))(((1)(0)*(1))((0)))*(((1)(0)*(1))((1))))*((((1))|((0))((0)))(((1)(0)*(1))((0)))*(((1)|(1)(0)*(0)))))");
    }

    public static String sumOfDivided(int[] l) {
        List<Integer> intList = IntStream.of(l).sorted().boxed().collect(Collectors.toList());

        Collections.sort(intList, (o1, o2) -> -Integer.compare(Math.abs(o1), Math.abs(o2)));

        Map<Integer, Long> resMap = new HashMap<>();

        Integer max = intList.stream().map(Math::abs).max(Integer::max).get();

        BigInteger pff = BigInteger.ONE;
        while (true) {
            pff = pff.nextProbablePrime();
            Integer pf = pff.intValue();
            if (pf >= max) break;
            for (Integer integer : intList) {
                if (integer % pf == 0) {
                    if (!resMap.containsKey(pf)) resMap.put(pf, 0L);
                    resMap.compute(pf, (key, val) -> val+=integer);
                }
            }
        }

        StringBuilder res = new StringBuilder();
        List<Integer> keys = new ArrayList<>(resMap.keySet());
        Collections.sort(keys);
        for (Integer key : keys) {
            res.append(String.format("(%s %s)", String.valueOf(key), String.valueOf(resMap.get(key))));
        }

        return res.toString();
    }

    //******************************************************************************************************************

    private static final int WIN_LINES_LENGTH = 4;

    enum Direction {
        DOWN_LEFT, DOWN_RIGHT, DOWN, RIGHT
    }

    public static String whoIsWinner(List<String> piecesPositionList) {
        String coord = "A0-B1-C2-D3-E4-F5-G6";
        Map<String, Integer> coordMap = new HashMap<>();
        for (String s : coord.split("-")) {
            coordMap.put(String.valueOf(s.charAt(0)), Integer.parseInt(String.valueOf(s.charAt(1))));
        }

        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Yellow");

        Integer[][] field = new Integer[6][7];

        for (String s : piecesPositionList) {
            Integer crd = coordMap.get(s.split("_")[0]);
            Integer color = colors.indexOf(s.split("_")[1]);
            makeMove(field, crd, color);
            Integer winner = findWinner(field, color);
            if (winner!= null) return colors.get(color);
        }

        return null;
    }

    private static Integer findWinner(Integer[][] field, int color) {
        boolean downLeft;
        boolean downRight;
        boolean down;
        boolean right;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == null || field[i][j]!=color) continue;

                right = findLine(field, color, i, j, Direction.RIGHT, 0);
                downLeft = findLine(field, color, i, j, Direction.DOWN_LEFT, 0);
                downRight= findLine(field, color, i, j, Direction.DOWN_RIGHT, 0);
                down = findLine(field, color, i, j, Direction.DOWN, 0);

                if (down || downLeft || downRight || right)
                    return color;

            }
        }
        return null;
    }

    private static boolean findLine(Integer[][] field, int color, int i, int j, Direction direction, int lineLen) {
        lineLen++;
        if (lineLen == WIN_LINES_LENGTH) return true;
        int x = i + 1;
        int y = j;
        switch (direction) {
            case RIGHT:
                if (j == field[0].length-1) return false;
                x = i;
                y = j + 1;
                break;
            case DOWN:
                if (i == field.length-1) return false;
                y = j;
                break;
            case DOWN_LEFT:
                if (i == field.length-1 || j == 0) return false;
                y = j - 1;
                break;
            case DOWN_RIGHT:
                if (i == field.length-1 || j == field[0].length-1) return false;
                y = j + 1;
        }
        if ((field[x][y] == null) || (field[x][y] != null && field[x][y] != color)) {
            return false;
        } else {
            return findLine(field, color, x, y, direction, lineLen);
        }
    }

    private static void makeMove(Integer[][] field, Integer crd, Integer color) {
        for (int i = 0; i < field.length-1; i++) {
            if (field[i+1][crd] != null) {
                field[i][crd] = color;
                //**************************************
                printField(field);
                //**************************************
                return;
            }
        }
        field[field.length-1][crd] = color;
        //**************************************
        printField(field);
        //**************************************
    }

    private static void printField(Integer[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print((field[i][j]==null ? "*" : field[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

}

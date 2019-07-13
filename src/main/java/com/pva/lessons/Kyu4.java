package com.pva.lessons;


import java.util.*;
import java.util.stream.Collectors;

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
            if (chars.length < len) break;
            Arrays.sort(chars);
            if (Arrays.equals(chars, nChars)) return n;
        }
        return -1;
    }

}

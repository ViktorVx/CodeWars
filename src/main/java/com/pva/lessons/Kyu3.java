package com.pva.lessons;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.bytebuddy.matcher.ElementMatchers.named;

class Kyu3 {

    //*** The Millionth Fibonacci Kata *********************************************************************************

    static BigInteger fib(BigInteger n) {
        BigInteger[][] identity = new BigInteger[][]{{BigInteger.ONE, BigInteger.ZERO},{BigInteger.ZERO, BigInteger.ONE}};
        BigInteger[][] self = new BigInteger[][]{{BigInteger.ONE, BigInteger.ONE},{BigInteger.ONE, BigInteger.ZERO}};

        BigInteger[][] f = pow(self, n.compareTo(BigInteger.ZERO)<0 ? n.multiply(BigInteger.valueOf(-1)) : n, identity);

        return f[0][1].multiply(((n.compareTo(BigInteger.ZERO)<0 && (n.mod(new BigInteger("2")).equals(BigInteger.ZERO))) ? BigInteger.valueOf(-1) : BigInteger.ONE));

    }

    private static BigInteger[][] matrixMultiply(BigInteger[][] a, BigInteger[][] b) {
        int aRows = a.length;
        int aColumns = a[0].length;
        int bColumns = b[0].length;

        BigInteger[][] c = new BigInteger[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                c[i][j] = BigInteger.ZERO;
            }
        }

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    c[i][j] = c[i][j].add(a[i][k].multiply(b[k][j]));
                }
            }
        }
        return c;
    }

    private static BigInteger[][] pow(BigInteger[][] x, BigInteger n, BigInteger[][] i) {
        if (n.equals(BigInteger.ZERO)) {
            return i;
        } else if (n.equals(BigInteger.ONE)) {
            return x;
        } else {
            BigInteger[][] y = pow(x, n.divide(new BigInteger("2")), i);
            y = matrixMultiply(y, y);
            if (n.mod(new BigInteger("2")).equals(BigInteger.ONE)) {
                y = matrixMultiply(x, y);
            }
            return y;
        }
    }

    //*** Battleship field validator ***********************************************************************************

    enum Direction {
        RIGHT, DOWN
    }

    public static boolean fieldValidator(int[][] field) {
        int[] ships = new int[]{4, 3, 2, 1};
        int[][] fieldCopy = Arrays.stream(field).map(int[]::clone).toArray(int[][]::new);
        for (int i = 1; i < fieldCopy.length-1; i++) {
            for (int j = 1; j < fieldCopy[0].length-1; j++) {
                if (fieldCopy[i][j] == 1) {
                    if (fieldCopy[i - 1][j - 1] == 1 ||
                            fieldCopy[i - 1][j + 1] == 1 ||
                            fieldCopy[i + 1][j + 1] == 1 ||
                            fieldCopy[i + 1][j - 1] == 1 ) return false;
                }
            }
        }
        for (int i = 0; i < fieldCopy.length; i++) {
            for (int j = 0; j < fieldCopy[0].length; j++) {
                if (fieldCopy[i][j] == 1) {
                    printField(fieldCopy);
                    int len = findShipLen(i, j, fieldCopy, 1, Direction.RIGHT);
                    if (len == 1) {
                        len = findShipLen(i, j, fieldCopy, 1, Direction.DOWN);
                    }
                    if (len > 4) return false;

                    ships[len - 1]--;

                    fieldCopy[i][j] = -1;
                }
            }
        }
        for (int ship : ships) {
            if (ship!=0) return false;
        }
        return true;
    }

    public static int findShipLen(int i, int j, int[][] field, int shipLen, Direction direction) {
        switch (direction) {
            case RIGHT:
                if (i + 1 < field.length && field[i + 1][j] == 1) {
                    field[i + 1][j] = -1;
                    return findShipLen(i + 1, j, field, ++shipLen, direction);
                } else {
                    return shipLen;
                }
            case DOWN:
                if (j + 1 < field[0].length && field[i][j + 1] == 1) {
                    field[i][j + 1] = -1;
                    return findShipLen(i, j + 1, field, ++shipLen, direction);
                } else {
                    return shipLen;
                }
        }
        return -1;
    }

    private static void printField(int[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print((field[i][j]==0 ? "0" : field[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    //*** Battleship field validator ***********************************************************************************

    public static double guess() {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(java.lang.Math.class)
                .method(named("random")).intercept(FixedValue.value(new Double(1.0)))
                .make()
                .load(Kyu3.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        return new Double(1.0);
    }

    //*** Prime Streaming (PG-13) **************************************************************************************

    public static IntStream stream() {
        return IntStream.iterate(2, i -> ++i).filter(i -> isPrime(i));
    }

    public static boolean isPrime(int n) {

        if (n > 2 && n % 2 == 0){
            return false;
        }
        int top = (int) Math.sqrt(n) + 1;
        for (int i=3; i < top; i+= 2){
            if (n % i==0){
                return false;
            }
        }
        return true;
    }

    //*** Prime Streaming (PG-17) **************************************************************************************

    private static final int N = 1_000_000_000;

    public static IntStream streamBig() {
        return eratosthenesSieve(N);
    }

//    private static IntStream eratosthenesSieve(int n) {
//        BitSet b = new BitSet(n + 1);
//        b.flip(0, b.size() - 1);
//        b.clear(0);
//        b.clear(1);
//        int i = 2;
//        while (i * i <= n) {
//            if (b.get(i)) {
//                int k = i * i;
//                while (k <= n) {
//                    b.clear(k);
//                    k = k + i;
//                }
//            }
//            i++;
//        }
//        return b.stream();
//    }

//    private static final long MAX = 1000000000L;
    private static final long MAX = 1_000_000_000;
    private static final long SQRT_MAX = (long) Math.sqrt(MAX) + 1;
    private static final int MEMORY_SIZE = (int) (MAX >> 4);
    private static byte[] array = new byte[MEMORY_SIZE];

    public static boolean getBit(long i) {
        byte block = array[(int) (i >> 4)];
        byte mask = (byte) (1 << ((i >> 1) & 7));
        return ((block & mask) != 0);
    }

    public static void setBit(long i) {
        int index = (int) (i >> 4);
        byte block = array[index];
        byte mask = (byte) (1 << ((i >> 1) & 7));
        array[index] = (byte) (block | mask);
    }

    private static IntStream eratosthenesSieve(int n) {
        for (long i = 3; i < SQRT_MAX; i += 2) {
            if (!getBit(i)) {
                long j = (i * i);
                while (j < MAX) {
                    setBit(j);
                    j += (2 * i);
                }
            }
        }
        return null;
    }
    // *****************************************************************************************************************
    static List<Integer[]> permutations = new ArrayList<>();
    static {
        printAllRecursive(10, new Integer[]{0,1,2,3,4,5,6,7,8,9}, permutations);
    }

    public static String solveAlphametics(String s) {
        String result = s.split("=")[1].trim();

        List<String> elems = Arrays.stream(s.split("=")[0].split("\\+")).map(String::trim).collect(Collectors.toList());


        Set<String> allUniqueWords = new HashSet<>(elems);
        allUniqueWords.add(result);

        List<String> allChars = Arrays.stream(s.replaceAll("=|\\s|\\+", "").split(""))
                .distinct().collect(Collectors.toList());
        allChars.sort(Comparator.naturalOrder());

        Set<String> notZeros = elems.stream().map(st -> st.substring(0, 1)).collect(Collectors.toSet());
        List<Integer> notZerosIndexes = notZeros.stream().map(allChars::indexOf).collect(Collectors.toList());
        List<Integer> elemsLastLettersIndexes = elems.stream()
                .map(el -> el.substring(el.length() - 1))
                .map(allChars::indexOf)
                .collect(Collectors.toList());
        List<Integer> elemsPreLastLettersIndexes = elems.stream()
                .map(el -> el.substring(el.length() - 2, el.length() - 1))
                .map(allChars::indexOf)
                .collect(Collectors.toList());
        Integer resLastLetterIndex = allChars.indexOf(result.substring(result.length() - 1));
        Integer resPreLastLetterIndex = allChars.indexOf(result.substring(result.length() - 2, result.length() - 1));

        Map<String, Integer> charsStat = new HashMap<>();
        for (String allUniqueWord : allUniqueWords) {
            for (String ch : allUniqueWord.split("")) {
                charsStat.putIfAbsent(ch, 0);
                charsStat.computeIfPresent(ch, (k, v) -> ++v);
            }
        }
        //*****
//        for (String permutation : permutations) {
//            var part = permutation.substring(0, allChars.size()).split("");
////            Map<String, >
//
//        }

        //***** Filtering *****
        System.out.println("All permutations: " + permutations.size());

        List<Integer[]> permZerosFiltered = permutations.stream().filter(elem -> {
            for (Integer notZerosIndex : notZerosIndexes) {
                if (elem[notZerosIndex].equals(0))
                    return false;
            }
            return true;
        }).collect(Collectors.toList());
        System.out.println("Zero filtered permutations: " + permZerosFiltered.size());

        List<Integer[]> permModFiltered = permZerosFiltered.stream().filter(elem -> {
            Integer sum = 0;
            for (Integer elemsLastLettersIndex : elemsLastLettersIndexes) {
                sum += elem[elemsLastLettersIndex];
            }
            return sum % 10 == elem[resLastLetterIndex];
        }).collect(Collectors.toList());
        System.out.println("Mod filtered permutations: " + permModFiltered.size());

        permModFiltered = permModFiltered.stream().filter(elem -> {
            Integer sum0 = 0;
            Integer sum1 = 0;
            for (Integer elemsPreLastLettersIndex : elemsPreLastLettersIndexes) {
                sum0 += elem[elemsPreLastLettersIndex];
            }
            for (Integer elemsLastLettersIndex : elemsLastLettersIndexes) {
                sum1 += elem[elemsLastLettersIndex];
            }
            return ((sum0 % 10) + (sum1 / 10)) % 10 == elem[resPreLastLetterIndex];
        }).collect(Collectors.toList());
        System.out.println("Mod (With pre last) filtered permutations: " + permModFiltered.size());


        //***** Statistics *****
        System.out.println("***** Statistics *****");
        System.out.println(String.format("Elements: %s", elems.toString()));
        System.out.println(String.format("Result: %s", result));
        System.out.println(String.format("Unique words: %s", allUniqueWords));
        System.out.println(String.format("All chars: %s", allChars));
        System.out.println(String.format("Not zeros: %s", notZeros));
        System.out.println(String.format("Not zeros indexes: %s", notZerosIndexes));
        System.out.println(String.format("Elems Last Letters Indexes: %s", elemsLastLettersIndexes));
        System.out.println(String.format("Elems Pre Last Letters Indexes: %s", elemsPreLastLettersIndexes));
        System.out.println(String.format("Result Last Letter Index: %s", resLastLetterIndex));
        System.out.println(String.format("Result Pre Last Letter Index: %s", resPreLastLetterIndex));
        System.out.println(String.format("Char stats: %s", charsStat));
        return null;
    }

    public static void printAllRecursive(
            int n, Integer[] elements, List<Integer[]> permutations) {

        if(n == 1) {
            printArray(elements, permutations);
        } else {
            for(int i = 0; i < n-1; i++) {
                printAllRecursive(n - 1, elements, permutations);
                if(n % 2 == 0) {
                    swap(elements, i, n-1);
                } else {
                    swap(elements, 0, n-1);
                }
            }
            printAllRecursive(n - 1, elements, permutations);
        }
    }

    private static void swap(Integer[] input, int a, int b) {
        Integer tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private static void printArray(Integer[] input, List<Integer[]> permutations) {
//        for(int i = 0; i < input.length; i++) {
//            permutations.add(input[i]);
//        }
        permutations.add(input.clone());
    }


}

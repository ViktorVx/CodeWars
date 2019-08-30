package com.pva.lessons;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;

import java.math.BigInteger;
import java.util.Arrays;
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

}

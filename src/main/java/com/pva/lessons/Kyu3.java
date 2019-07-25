package com.pva.lessons;

import java.math.BigInteger;
import java.util.Arrays;

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

}

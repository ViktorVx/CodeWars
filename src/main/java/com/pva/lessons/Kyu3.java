package com.pva.lessons;

import java.math.BigInteger;

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

    //******************************************************************************************************************



}

package com.pva.lessons;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Kyu3Test {

    //*** Fibonacci ****************************************************************************************************
    @Test
    public void testFib0() {
        testFib(0, 0);
    }

    @Test
    public void testFib1() {
        testFib(1, 1);
    }

    @Test
    public void testFib2() {
        testFib(1, 2);
    }

    @Test
    public void testFib3() {
        testFib(2, 3);
    }

    @Test
    public void testFib4() {
        testFib(3, 4);
    }

    @Test
    public void testFib5() {
        testFib(5, 5);
    }

    @Test
    public void testFib6() {
        testFib(8, 6);
    }

    @Test
    public void testFib6m() {
        testFib(-8, -6);
    }

    @Test
    public void testFib43m() {
        testFib(433494437, -43);
    }

    @Test
    public void testFib10() {
        testFib(55, 10);
    }

    private static void testFib(long expected, long input) {
        BigInteger found;
        found = Kyu3.fib(BigInteger.valueOf(input));
        assertEquals(BigInteger.valueOf(expected), found);
    }

    //******************************************************************************************************************

    @Test
    public void SampleTest() {
        int[][] battleField = {{1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 0, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        assertEquals(true, Kyu3.fieldValidator(battleField));
    }

    //******************************************************************************************************************

    @Test
    public void test_0_10() {
        test(0, 10, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
    }

    @Test
    public void test_10_10() {
        test(10, 10, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71);
    }

    @Test
    public void test_100_10() {
        test(100, 10, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601);
    }

    @Test
    public void test_1000_10() {
        test(1000, 10, 7927, 7933, 7937, 7949, 7951, 7963, 7993, 8009, 8011, 8017);
    }

    private void test(int skip, int limit, int... expect) {
        int[] found = Kyu3.stream().skip(skip).limit(limit).toArray();
        assertArrayEquals(expect, found);
    }

    //******************************************************************************************************************


    @Test
    public void test_0_10b() {
        test1(0, 10, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
    }

    @Test
    public void test_10_10b() {
        test1(10, 10, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71);
    }

    @Test
    public void test_100_10b() {
        test1(100, 10, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601);
    }

    @Test
    public void test_1000_10b() {
        test1(1000, 10, 7927, 7933, 7937, 7949, 7951, 7963, 7993, 8009, 8011, 8017);
    }

    @Test
    public void test_14M_2b() {
        test1(14_000_000, 2, 256203221, 256203223);
    }

    @Test
    public void test_50M_2b() {
        test1(50_000_000, 2, 982451707, 982451737);
    }

    @Test
    public void test_55M_2b() {
        test1(55_000_000, 2, 1086218501, 1086218561);
    }

    private void test1(int skip, int limit, int... expect) {
        int[] found = Kyu3.streamBig().skip(skip).limit(limit).toArray();
        assertArrayEquals(expect, found);
    }

    //******************************************************************************************************************

    @Test
    public void solveAlphameticsTest() {
        assertEquals("9567 + 1085 = 10652", Kyu3.solveAlphametics("SEND + MORE = MONEY"));
    }

}
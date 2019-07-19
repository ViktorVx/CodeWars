package com.pva.lessons;

import org.junit.Test;

import java.math.BigInteger;

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

}
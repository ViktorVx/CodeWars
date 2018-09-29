package com.pva.lessons;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class Kyu8Test {

    @Test
    public void toBinary() {
        assertEquals(1, Kyu8.toBinary(1));
        assertEquals(10, Kyu8.toBinary(2));
        assertEquals(11, Kyu8.toBinary(3));
        assertEquals(101, Kyu8.toBinary(5));
    }

    @Test
    public void stringy() {
        Random randGen = new Random();
        for (int i = 0; i < 10; i++) {
            int size = randGen.nextInt(50);
            assertEquals("Wrong length using size " + size, size, Kyu8.stringy(size).length());
        }
    }

    @Test
    public void slope() {
        assertEquals("0",Kyu8.slope(new int[]{19,3,20,3}));
        assertEquals("undefined",Kyu8.slope(new int[]{-7,2,-7,4}));
        assertEquals("5",Kyu8.slope(new int[]{10,50,30,150}));
        assertEquals("-5",Kyu8.slope(new int[]{15,45,12,60}));
        assertEquals("6",Kyu8.slope(new int[]{10,20,20,80}));
        assertEquals("undefined",Kyu8.slope(new int[]{-10,6,-10,3}));
    }

    @Test
    public void century() {
        assertEquals(18, Kyu8.century(1705));
        assertEquals(19, Kyu8.century(1900));
        assertEquals(17, Kyu8.century(1601));
        assertEquals(20, Kyu8.century(2000));
        assertEquals(1,  Kyu8.century(89));
    }

    @Test
    public void twiceAsOld() {
        assertEquals(30, Kyu8.twiceAsOld(30,0));
        assertEquals(16, Kyu8.twiceAsOld(30,7));
        assertEquals(15, Kyu8.twiceAsOld(45,30));
    }

    @Test
    public void invert() {
        int[] input = new int[] {-1,-2,-3,-4,-5};
        int[] expected = new int[] {1,2,3,4,5};
        assertEquals(Arrays.toString(expected), Arrays.toString(Kyu8.invert(input)));

        input = new int[] {-1,2,-3,4,-5};
        expected = new int[] {1,-2,3,-4,5};
        assertEquals(Arrays.toString(expected), Arrays.toString(Kyu8.invert(input)));

        input = new int[] {};
        expected = new int[] {};
        assertEquals(Arrays.toString(expected), Arrays.toString(Kyu8.invert(input)));

        input = new int[] {0};
        expected = new int[] {0};
        assertEquals(Arrays.toString(expected), Arrays.toString(Kyu8.invert(input)));
    }

    @Test
    public void fakeString() {
        assertEquals("1110010010", Kyu8.fakeString("8753493462"));
    }

    @Test
    public void grow() {
        assertEquals(6, Kyu8.grow(new int[]{1,2,3}));
        assertEquals(16, Kyu8.grow(new int[]{4,1,1,1,4}));
        assertEquals(64, Kyu8.grow(new int[]{2,2,2,2,2,2}));
    }

    @Test
    public void map() {
        assertArrayEquals(new int[] {2, 4, 6}, Kyu8.map(new int[] {1, 2, 3}));
        assertArrayEquals(new int[] {8, 2, 2, 2, 8}, Kyu8.map(new int[] {4, 1, 1, 1, 4}));
        assertArrayEquals(new int[] {2, 2, 2, 2, 2, 2}, Kyu8.map(new int[] {1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void sum() {
        assertEquals(15, Kyu8.sum(new int[]{1,2,3,4,5}));
        assertEquals(13, Kyu8.sum(new int[]{1,-2,3,4,5}));
        assertEquals(0, Kyu8.sum(new int[]{}));
        assertEquals(0, Kyu8.sum(new int[]{-1,-2,-3,-4,-5}));
        assertEquals(9, Kyu8.sum(new int[]{-1,2,3,4,-5}));
    }

    @Test
    public void seriesSumTest() {
        assertEquals("1.00", Kyu8.seriesSum(1));
        assertEquals("1.25", Kyu8.seriesSum(2));
        assertEquals("1.39", Kyu8.seriesSum(3));
        assertEquals("1.49", Kyu8.seriesSum(4));
        assertEquals("1.57", Kyu8.seriesSum(5));
    }


    @Test
    public void nbDig() {
        assertEquals(Kyu8.nbDig(10, 1), 4);
        assertEquals(Kyu8.nbDig(5750, 0), 4700);
        assertEquals(Kyu8.nbDig(11011, 2), 9481);
        assertEquals(Kyu8.nbDig(12224, 8), 7733);
        assertEquals(Kyu8.nbDig(11549, 1), 11905);
    }

    @Test
    public void findShort() {
        assertEquals(3, Kyu8.findShort("bitcoin take over the world maybe who knows perhaps"));
        assertEquals(3, Kyu8.findShort("turns out random test cases are easier than writing out basic ones"));
    }

    @Test
    public void unluckyDays() {
        assertEquals(3, Kyu8.unluckyDays(2015));
        assertEquals(1, Kyu8.unluckyDays(1986));
        assertEquals(1, Kyu8.unluckyDays(842));
        assertEquals(3, Kyu8.unluckyDays(1001));
    }

    @Test
    public void sumTriangularNumbers() {
        assertEquals(56, Kyu8.sumTriangularNumbers(6));
        assertEquals(7140, Kyu8.sumTriangularNumbers(34));
        assertEquals(0, Kyu8.sumTriangularNumbers(-291));
        assertEquals(140205240, Kyu8.sumTriangularNumbers(943));
        assertEquals(0, Kyu8.sumTriangularNumbers(-971));
    }

    @Test
    public void makeComplement() {
        assertEquals("TTTT", Kyu8.makeComplement("AAAA"));
        assertEquals("TAACG", Kyu8.makeComplement("ATTGC"));
        assertEquals("CATA", Kyu8.makeComplement("GTAT"));
    }
}
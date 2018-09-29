package com.pva.lessons;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class LessonsTest {

    @Test
    public void toBinary() {
        assertEquals(1, Lessons.toBinary(1));
        assertEquals(10, Lessons.toBinary(2));
        assertEquals(11, Lessons.toBinary(3));
        assertEquals(101, Lessons.toBinary(5));
    }

    @Test
    public void stringy() {
        Random randGen = new Random();
        for (int i = 0; i < 10; i++) {
            int size = randGen.nextInt(50);
            assertEquals("Wrong length using size " + size, size, Lessons.stringy(size).length());
        }
    }

    @Test
    public void slope() {
        assertEquals("0",Lessons.slope(new int[]{19,3,20,3}));
        assertEquals("undefined",Lessons.slope(new int[]{-7,2,-7,4}));
        assertEquals("5",Lessons.slope(new int[]{10,50,30,150}));
        assertEquals("-5",Lessons.slope(new int[]{15,45,12,60}));
        assertEquals("6",Lessons.slope(new int[]{10,20,20,80}));
        assertEquals("undefined",Lessons.slope(new int[]{-10,6,-10,3}));
    }

    @Test
    public void century() {
        assertEquals(18, Lessons.century(1705));
        assertEquals(19, Lessons.century(1900));
        assertEquals(17, Lessons.century(1601));
        assertEquals(20, Lessons.century(2000));
        assertEquals(1,  Lessons.century(89));
    }

    @Test
    public void twiceAsOld() {
        assertEquals(30, Lessons.twiceAsOld(30,0));
        assertEquals(16, Lessons.twiceAsOld(30,7));
        assertEquals(15, Lessons.twiceAsOld(45,30));
    }

    @Test
    public void invert() {
        int[] input = new int[] {-1,-2,-3,-4,-5};
        int[] expected = new int[] {1,2,3,4,5};
        assertEquals(Arrays.toString(expected), Arrays.toString(Lessons.invert(input)));

        input = new int[] {-1,2,-3,4,-5};
        expected = new int[] {1,-2,3,-4,5};
        assertEquals(Arrays.toString(expected), Arrays.toString(Lessons.invert(input)));

        input = new int[] {};
        expected = new int[] {};
        assertEquals(Arrays.toString(expected), Arrays.toString(Lessons.invert(input)));

        input = new int[] {0};
        expected = new int[] {0};
        assertEquals(Arrays.toString(expected), Arrays.toString(Lessons.invert(input)));
    }

    @Test
    public void fakeString() {
        assertEquals("1110010010", Lessons.fakeString("8753493462"));
    }

    @Test
    public void grow() {
        assertEquals(6, Lessons.grow(new int[]{1,2,3}));
        assertEquals(16, Lessons.grow(new int[]{4,1,1,1,4}));
        assertEquals(64, Lessons.grow(new int[]{2,2,2,2,2,2}));
    }

    @Test
    public void map() {
        assertArrayEquals(new int[] {2, 4, 6}, Lessons.map(new int[] {1, 2, 3}));
        assertArrayEquals(new int[] {8, 2, 2, 2, 8}, Lessons.map(new int[] {4, 1, 1, 1, 4}));
        assertArrayEquals(new int[] {2, 2, 2, 2, 2, 2}, Lessons.map(new int[] {1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void sum() {
        assertEquals(15, Lessons.sum(new int[]{1,2,3,4,5}));
        assertEquals(13, Lessons.sum(new int[]{1,-2,3,4,5}));
        assertEquals(0, Lessons.sum(new int[]{}));
        assertEquals(0, Lessons.sum(new int[]{-1,-2,-3,-4,-5}));
        assertEquals(9, Lessons.sum(new int[]{-1,2,3,4,-5}));
    }

    @Test
    public void seriesSumTest() {
        assertEquals("1.00", Lessons.seriesSum(1));
        assertEquals("1.25", Lessons.seriesSum(2));
        assertEquals("1.39", Lessons.seriesSum(3));
        assertEquals("1.49", Lessons.seriesSum(4));
        assertEquals("1.57", Lessons.seriesSum(5));
    }


    @Test
    public void nbDig() {
        assertEquals(Lessons.nbDig(10, 1), 4);
        assertEquals(Lessons.nbDig(5750, 0), 4700);
        assertEquals(Lessons.nbDig(11011, 2), 9481);
        assertEquals(Lessons.nbDig(12224, 8), 7733);
        assertEquals(Lessons.nbDig(11549, 1), 11905);
    }

    @Test
    public void findShort() {
        assertEquals(3, Lessons.findShort("bitcoin take over the world maybe who knows perhaps"));
        assertEquals(3, Lessons.findShort("turns out random test cases are easier than writing out basic ones"));
    }

    @Test
    public void unluckyDays() {
        assertEquals(3, Lessons.unluckyDays(2015));
        assertEquals(1, Lessons.unluckyDays(1986));
        assertEquals(1, Lessons.unluckyDays(842));
        assertEquals(3, Lessons.unluckyDays(1001));
    }

    @Test
    public void sumTriangularNumbers() {
        assertEquals(56, Lessons.sumTriangularNumbers(6));
        assertEquals(7140, Lessons.sumTriangularNumbers(34));
        assertEquals(0, Lessons.sumTriangularNumbers(-291));
        assertEquals(140205240, Lessons.sumTriangularNumbers(943));
        assertEquals(0, Lessons.sumTriangularNumbers(-971));
    }

    @Test
    public void makeComplement() {
        assertEquals("TTTT", Lessons.makeComplement("AAAA"));
        assertEquals("TAACG", Lessons.makeComplement("ATTGC"));
        assertEquals("CATA", Lessons.makeComplement("GTAT"));
    }
}
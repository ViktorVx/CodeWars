package com.pva.lessons;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class Kyu7Test {

    @Test
    public void reverseLetter() {
        assertEquals("", Kyu7.reverseLetter("54321"));
        assertEquals("", Kyu7.reverseLetter("12345"));
        assertEquals("ytrewq", Kyu7.reverseLetter("qwerty"));
        assertEquals(Kyu7.reverseLetter("krishan"), "nahsirk");
        assertEquals(Kyu7.reverseLetter("ultr53o?n"), "nortlu");
        assertEquals(Kyu7.reverseLetter("ab23c"), "cba");
        assertEquals(Kyu7.reverseLetter("krish21an"), "nahsirk");
    }

    @Test
    public void timedReading() {
        assertEquals(7, Kyu7.timedReading(4,"The Fox asked the stork, 'How is the soup?'"));
        assertEquals(0, Kyu7.timedReading(1,"..."));
        assertEquals(3, Kyu7.timedReading(3,"This play was good for us."));
        assertEquals(5, Kyu7.timedReading(3,"Suddenly he stopped, and glanced up at the houses"));
        assertEquals(11, Kyu7.timedReading(6, "Zebras evolved among the Old World horses within the last four million years."));
        assertEquals(6, Kyu7.timedReading(5, "Although zebra species may have overlapping ranges, they do not interbreed."));
        assertEquals(0, Kyu7.timedReading(1,"Oh!"));
        assertEquals(14, Kyu7.timedReading(5,"Now and then, however, he is horribly thoughtless, and seems to take a real delight in giving me pain."));
    }


    @Test
    public void declareWinner() {
        assertEquals("Lew", Kyu7.declareWinner(new Kyu7.Fighter("Lew", 10, 2),new Kyu7.Fighter("Harry", 5, 4), "Lew"));
        assertEquals("Harry", Kyu7.declareWinner(new Kyu7.Fighter("Lew", 10, 2),new Kyu7.Fighter("Harry", 5, 4), "Harry"));
        assertEquals("Harald", Kyu7.declareWinner(new Kyu7.Fighter("Harald", 20, 5), new Kyu7.Fighter("Harry", 5, 4), "Harry"));
        assertEquals("Harald", Kyu7.declareWinner(new Kyu7.Fighter("Harald", 20, 5), new Kyu7.Fighter("Harry", 5, 4), "Harald"));
        assertEquals("Harald", Kyu7.declareWinner(new Kyu7.Fighter("Jerry", 30, 3), new Kyu7.Fighter("Harald", 20, 5), "Jerry"));
        assertEquals("Harald", Kyu7.declareWinner(new Kyu7.Fighter("Jerry", 30, 3), new Kyu7.Fighter("Harald", 20, 5), "Harald"));
    }

    @Test
    public void convertBinaryArrayToInt() {
        assertEquals(1, Kyu7.ConvertBinaryArrayToInt(new ArrayList<>(Arrays.asList(0,0,0,1))));
        assertEquals(15, Kyu7.ConvertBinaryArrayToInt(new ArrayList<>(Arrays.asList(1,1,1,1))));
        assertEquals(6, Kyu7.ConvertBinaryArrayToInt(new ArrayList<>(Arrays.asList(0,1,1,0))));
        assertEquals(9, Kyu7.ConvertBinaryArrayToInt(new ArrayList<>(Arrays.asList(1,0,0,1))));
    }

    @Test
    public void testCase1() {
        assertEquals("Nope!", 5, Kyu7.getCount("abracadabra"));
    }

}
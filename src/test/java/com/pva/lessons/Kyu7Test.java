package com.pva.lessons;

import org.junit.Test;

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
        assertEquals("zmpp[h]tqktdxaviqzizsssikkdqatde", Kyu7.reverseLetter("e4dtaqdkkiss%sz3izqivaxd|t=kqt]h_pp mz"));
    }
}
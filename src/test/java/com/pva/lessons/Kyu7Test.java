package com.pva.lessons;

import org.junit.Test;

import static org.junit.Assert.*;

public class Kyu7Test {

    @Test
    public void reverseLetter() {
        assertEquals("12345", Kyu7.reverseLetter("54321"));
        assertEquals("54321", Kyu7.reverseLetter("12345"));
        assertEquals("ytrewq", Kyu7.reverseLetter("qwerty"));
    }
}
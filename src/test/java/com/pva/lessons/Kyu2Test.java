package com.pva.lessons;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class Kyu2Test {

    @Test
    public void EdgeCases() {
        Pattern regex = Pattern.compile(Kyu2.multipleOf7Full());
        System.out.println("Testing for: empty string");
        assertEquals(false, regex.matcher("").matches());
        System.out.println("Testing for: 0");
        assertEquals(true, regex.matcher("0").matches());

        for(int i=1; i<100; i++) {
            System.out.println("Testing for: "+i);
            assertEquals(i%7 == 0, regex.matcher(Integer.toBinaryString(i)).matches());
        }
    }
}

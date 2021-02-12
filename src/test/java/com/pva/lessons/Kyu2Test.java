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

    @Test
    public void basicTests() {

        assertEquals("1", Kyu2.integerSquareRoot("1"));
        assertEquals("2", Kyu2.integerSquareRoot("5"));
        assertEquals("4", Kyu2.integerSquareRoot("17"));
        assertEquals("10", Kyu2.integerSquareRoot("100"));
        assertEquals("31", Kyu2.integerSquareRoot("1000"));
        assertEquals("152421548093487868711992623730429930751178496967",
                Kyu2.integerSquareRoot("23232328323215435345345345343458098856756556809400840980980980980809092343243243243243098799634"));
        assertEquals("3510457208086937291253621317073222057793129",
                Kyu2.integerSquareRoot("12323309809809534545458098709854808654685688665486860956865654654654324238000980980980"));

    }
}

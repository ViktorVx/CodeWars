package com.pva.lessons;

import org.junit.Test;

import static org.junit.Assert.*;

public class Kyu5Test {

    @Test
    public void ipsBetween() {
        assertEquals( 50, Kyu5.ipsBetween( "10.0.0.0", "10.0.0.50" ) );
        assertEquals( 246, Kyu5.ipsBetween( "20.0.0.10", "20.0.1.0" ) );
        assertEquals( 1, Kyu5.ipsBetween( "150.0.0.0", "150.0.0.1" ) );
    }


    @Test
    public void checkCourse() {
        char[][] map = new char[5][6];
        char[] line1 = {'0','0','0','0','N','0'};
        char[] line2 = {'0','0','0','0','0','0'};
        char[] line3 = {'X','0','0','0','0','0'};
        char[] line4 = {'0','0','0','0','0','0'};
        char[] line5 = {'0','0','0','0','0','0'};
        map[0] = line1;
        map[1] = line2;
        map[2] = line3;
        map[3] = line4;
        map[4] = line5;
        assertEquals(false,Kyu5.checkCourse(map));

        char[][] map2 = new char[5][6];
        char[] line11 = {'0','0','0','0','0','0'};
        char[] line12 = {'0','0','0','0','0','0'};
        char[] line13 = {'X','0','0','0','0','0'};
        char[] line14 = {'0','0','0','0','0','0'};
        char[] line15 = {'0','0','N','0','0','0'};
        map2[0] = line11;
        map2[1] = line12;
        map2[2] = line13;
        map2[3] = line14;
        map2[4] = line15;
        assertEquals(false,Kyu5.checkCourse(map2));

        char[][] map3 = new char[29][132];
        for (int i = 0; i < map3.length; i++) {
            for (int j = 0; j < map3[0].length; j++) {
                map3[i][j] = 0;
            }
        }
        map3[11][0] = 'X';
        map3[0][6] = 'N';
        map3[0][14] = 'N';
        map3[0][15] = 'N';
        map3[0][20] = 'N';
        map3[0][21] = 'N';
        map3[0][52] = 'N';
        map3[0][59] = 'N';
        map3[0][60] = 'N';
        map3[0][61] = 'N';
        map3[0][82] = 'N';
        map3[0][100] = 'N';
        map3[0][116] = 'N';
        map3[0][128] = 'N';
        map3[28][4] = 'N';
        map3[28][7] = 'N';
        map3[28][9] = 'N';
        map3[28][46] = 'N';
        map3[28][54] = 'N';
        map3[28][55] = 'N';
        map3[28][76] = 'N';
        map3[28][84] = 'N';
        map3[28][99] = 'N';
        map3[28][112] = 'N';
        map3[28][126] = 'N';
        assertEquals(false,Kyu5.checkCourse(map3));

        char[][] map4 = new char[84][163];
        for (int i = 0; i < map3.length; i++) {
            for (int j = 0; j < map3[0].length; j++) {
                map3[i][j] = 0;
            }
        }
        map4[24][0] = 'X';
        map4[0][5] = 'N';
        map4[0][10] = 'N';
        map4[0][11] = 'N';
        map4[0][17] = 'N';
        map4[0][36] = 'N';
        map4[0][47] = 'N';
        map4[0][48] = 'N';
        map4[0][54] = 'N';
        map4[0][69] = 'N';
        map4[0][76] = 'N';
        map4[0][85] = 'N';
        map4[0][101] = 'N';
        map4[0][117] = 'N';
        map4[0][118] = 'N';
        map4[0][145] = 'N';
        map4[0][151] = 'N';
        map4[83][9] = 'N';
        map4[83][32] = 'N';
        map4[83][35] = 'N';
        map4[83][39] = 'N';
        map4[83][52] = 'N';
        map4[83][63] = 'N';
        map4[83][89] = 'N';
        map4[83][91] = 'N';
        map4[83][92] = 'N';
        map4[83][99] = 'N';
        map4[83][130] = 'N';
        map4[83][134] = 'N';
        map4[83][137] = 'N';
        map4[83][156] = 'N';
        assertEquals(true, Kyu5.checkCourse(map4));
    }
}
package com.pva.lessons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Kyu1 {

    //*** Java Hacking: Hijack a JVM ***********************************************************************************

    public static class Buglary$Targer {
        private static String securityCode;

        public Buglary$Targer() {
            securityCode = "777";
        }
    }

    public static String guessSecurityCode(final String pid) throws IOException {
        //***
        /*
        stack - хранит последовательность вызовов функций
        heap - область памяти, которая хранит объекты
        1) Искомая строка хранится в куче +++
        2) ? Нужно сделать дамп кучи????
         */
        //***
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("jcmd -l");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(p);
        StringBuffer sbInput = new StringBuffer();
        BufferedReader brInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        String foundLine = "UNKNOWN";
        try {
            while ((line = brInput.readLine()) != null) {
                if (line.contains(pid)){
                    foundLine = line;
                }
                System.out.println(line);
                sbInput.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.destroy();

//        String result = foundLine.substring(0, foundLine.indexOf(" "));
//        String result = foundLine;
//        System.out.println(result);

        return null;

    }

    //******************************************************************************************************************

}

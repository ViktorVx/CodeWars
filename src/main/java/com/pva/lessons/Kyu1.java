package com.pva.lessons;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Kyu1 {

    //*** Java Hacking: Hijack a JVM ***********************************************************************************
    private static String secureString = "WWWWWWWWWWWWWWWWWWWWWWWWWW";

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
        2) ? Нужно сделать дамп кучи ?
         */
        //***
        System.out.println(secureString);
        long pd = ProcessHandle.current().pid();
        Process p = null;
        try {
//            p = Runtime.getRuntime().exec("jcmd -l");
            p = Runtime.getRuntime().exec("jcmd " + pd +" GC.heap_dump /tmp/dump.hprof");
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

        return null;

//        System.out.println(secureString);
//        while (true) {
//            System.out.println(secureString);
//        }

//        String name = ManagementFactory.getRuntimeMXBean().getName();
//        String pd = name.substring(0, name.indexOf("@"));
//        String[] cmd = { "jmap", "-dump:file=D:\\temp\\heapdumps\\dump.bin", pd };
//        Process p = Runtime.getRuntime().exec(cmd);




    }

    //*** Hack-22 ******************************************************************************************************

    public abstract class Yossarian {
        public final boolean isCrazy() {
            return false;
        }
    }

    class NewYoss extends Yossarian{ }

    public Yossarian loophole() throws Throwable {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(Yossarian.class)
                .method(named("isCrazy")).intercept(FixedValue.value(true))
                .make()
                .load(NewYoss.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        NewYoss newYoss = new NewYoss();

        return newYoss;

    }

    //******************************************************************************************************************


}

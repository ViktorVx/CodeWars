package com.pva.lessons;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    static class DFA {

        Map<Integer, State> states;

        public DFA(State initialState, int number) {
            states = new HashMap<>();
            addState(initialState);
            for(int i = 1; i < number; i++) {
                addState(new State(i,false,false));
            }
            addTranss(number);
        }

        public void addState(State s) {
            states.put(s.getN(),s);
        }

        public State getState(int n) {
            return states.get(n);
        }

        public List<State> getStates() {
            return new ArrayList<>(states.values());
        }

        private void addTranss(int number) {
            for(Map.Entry<Integer, State> entry : states.entrySet()) {
                Integer n = entry.getKey();
                State s = entry.getValue();

                int remainder = Integer.parseInt(Integer.toBinaryString(n)+"1",2) % number;
                s.addTrans(new Trans(states.get(remainder),"1"));

                remainder = Integer.parseInt(Integer.toBinaryString(n)+"0",2) % number;
                s.addTrans(new Trans(states.get(remainder),"0"));
            }
        }

        public String generateRegex() {
            String [][] T = new String[states.size()][states.size()];
            for(int i = 0; i < T.length; i++) {
                for(int j = 0; j < T[0].length; j++) {
                    State from = getState(i);
                    State to = getState(j);
                    Trans t = from.getTrans(to);
                    String with = "";
                    if( t == null)
                        if(i == j)
                            with = "e";
                        else
                            with = "NULL";
                    else
                        with = t.getWith();
                    T[i][j] = with;
                }
            }

            for(int i = 0; i < states.size(); i++) {
                T = applyDinamicRegexConstruction(T,i);
            }

            return T[0][0];
        }

        private String [][] applyDinamicRegexConstruction(String [][] T , int k){
            //Apartado 4.4.2 de: http://cs.brown.edu/people/jsavage/book/pdfs/ModelsOfComputation_Chapter4.pdf
            //Ecuacion de recurrencia: r(k)i,j = (r(k-1)i,j) | (r(k-1)i,k) (r(k-1)k,k)* (r(k-1)k,j)
            String [][] TNext = new String [T.length][T[0].length];
            for(int i = 0; i < T.length; i++) {
                for(int j = 0; j < T[0].length; j++) {
                    String s0 = T[i][j], s1 = T[i][k], s2 = T[k][k], s3 = T[k][j];
                    StringBuilder sB = new StringBuilder();
                    boolean left = false, right = false;
                    if(!s0.equals("e") && !s0.equals("NULL")) {
                        sB.append("(").append(s0).append(")");
                        left = true;
                    }
                    if(!s1.equals("NULL") && !s3.equals("NULL")) {
                        right = true;
                        if(left && right)
                            sB.append("|");
                        if(!s1.equals("e"))
                            sB.append("(").append(s1).append(")");
                        if(!s2.equals("e") && !s2.equals("NULL"))
                            sB.append("(").append(s2).append(")*");
                        if(!s3.equals("e"))
                            sB.append("(").append(s3).append(")");
                    }
                    if(!left && !right)
                        sB.append(T[i][j]);
                    TNext[i][j] = sB.toString();
                }
            }
            return TNext;
        }
    }

    static class State {
        private boolean isFinal, isStart;
        private int n;
        private Map<String,Trans> transs;

        public State(int n, boolean isFinal, boolean isStart) {
            this.n = n;
            this.isFinal = isFinal;
            this.isStart = isStart;
            transs = new HashMap<>();
        }

        public int getN() {
            return n;
        }

        public void addTrans(Trans t) {
            transs.put(t.getWith(),t);
        }

        public Trans getTrans(State to) {
            for(Trans t : new ArrayList<>(transs.values())) {
                if(t.getTo().getN() == to.getN())
                    return t;
            }
            return null;
        }
    }

    static class Trans {

        private State to;
        private String with;

        public Trans(State to, String with) {
            this.to = to;
            this.with = with;
        }

        public State getTo() {
            return to;
        }

        public String getWith() {
            return with;
        }
    }

    public static String regexDivisibleBy(int n) {
        if (n == 1) return "$[01]+^";
        DFA dfa = new DFA(new State(0, true, true), n);
        return dfa.generateRegex();
    }


}

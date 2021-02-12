package com.pva.lessons;

import jdk.jshell.JShell;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kyu2 {

    //*** Multiple of 7 ************************************************************************************************

    public static String multipleOf7() {
        return "^(0|111|100((1|00)0)*011|(101|100((1|00)0)*" +
                "(1|00)1)(1((1|00)0)*(1|00)1)*(01|1((1|00)0)*011)|(110|100((1|00)0)*" +
                "010|(101|100((1|00)0)*(1|00)1)(1((1|00)0)*(1|00)1)*(00|1((1|00)0)*010))" +
                "(1|0(1((1|00)0)*(1|00)1)*(00|1((1|00)0)*010))*0(1((1|00)0)*(1|00)1)*" +
                "(01|1((1|00)0)*011))+$";
    }

    static class DFA { //Represents a DFA that matches if a binary number is divisible by a given number
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

        public boolean isFinal() {
            return isFinal;
        }

        public boolean isStart() {
            return isStart;
        }

        public List<Trans> getTranss() {
            return new ArrayList<>(transs.values());
        }

        public void addTrans(Trans t) {
            transs.put(t.getWith(),t);
        }

        public Trans getTrans(String with) {
            return transs.get(with);
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

    public static String multipleOf7Full() {
        DFA dfa = new DFA(new State(0,true,true),7);
        return dfa.generateRegex();
    }

    //******************************************************************************************************************

    /**
     * Challenge Fun #10: Integer Square Root
     * @param n
     * @return
     */
    public static String integerSquareRoot(String n) {
        // \u000d \u0072\u0065\u0074\u0075\u0072\u006e \u006e\u0065\u0077 \u0042\u0069\u0067\u0049\u006e\u0074\u0065\u0067\u0065\u0072\u0028\u006e\u0029\u002e\u0073\u0071\u0072\u0074\u0028\u0029\u002e\u0074\u006f\u0053\u0074\u0072\u0069\u006e\u0067\u0028\u0029\u003b
    }

}

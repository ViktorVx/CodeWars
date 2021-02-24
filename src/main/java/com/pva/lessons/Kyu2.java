package com.pva.lessons;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.*;

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

    private static DoStuff iTest;

    static {
        String className = "MySqrt";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        final JavaByteObject byteObject = new JavaByteObject(className);
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);
        JavaFileManager fileManager = createFileManager(standardFileManager, byteObject);
        JavaCompiler.CompilationTask task = compiler.getTask(null,
                fileManager, diagnostics, null, null, getCompilationUnits(className));
        task.call();
        try {
            fileManager.close();
            final ClassLoader inMemoryClassLoader = createClassLoader(byteObject);
            Class<DoStuff> test = (Class<DoStuff>) inMemoryClassLoader.loadClass(className);
            iTest = test.newInstance();
        } catch (Exception e) {
        }
    }

    /**
     * Challenge Fun #10: Integer Square Root
     * @param n
     * @return
     */
    public static String integerSquareRoot(String n) {
        return iTest.doStuff(n);
    }

    public static String getSource() {
        String s =  "import java.math.BigInteger;  public class MySqrt implements Kata.DoStuff { @Override public String doStuff(String n) { return new BigInteger(n).sqrt().toString(); }}";
        System.out.println(Base64.getEncoder().encodeToString(s.getBytes()));

        String d = "aW1wb3J0IGphdmEubWF0aC5CaWdJbnRlZ2VyOyAgcHVibGljIGNsYXNzIE15U3FydCBpbXBsZW1lbnRzIGNvbS5wdmEubGVzc29ucy5LeXUyLkRvU3R1ZmYgeyBAT3ZlcnJpZGUgcHVibGljIFN0cmluZyBkb1N0dWZmKFN0cmluZyBuKSB7IHJldHVybiBuZXcgQmlnSW50ZWdlcihuKS5zcXJ0KCkudG9TdHJpbmcoKTsgfX0=";
        return new String(Base64.getDecoder().decode(d));
    }

    public static Iterable<? extends JavaFileObject> getCompilationUnits(String className) {
        JavaStringObject stringObject = new JavaStringObject(className, getSource());
        return Arrays.asList(stringObject);
    }

    private static JavaFileManager createFileManager(StandardJavaFileManager fileManager,
                                                     JavaByteObject byteObject) {
        return new ForwardingJavaFileManager<>(fileManager) {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location,
                                                       String className, JavaFileObject.Kind kind,
                                                       FileObject sibling) {
                return byteObject;
            }
        };
    }

    private static ClassLoader createClassLoader(final JavaByteObject byteObject) {
        return new ClassLoader() {
            @Override
            public Class<?> findClass(String name) {
                byte[] bytes = byteObject.getBytes();
                return defineClass(name, bytes, 0, bytes.length);
            }
        };
    }

    public interface DoStuff {
        String doStuff(String n);
    }
}

class JavaByteObject extends SimpleJavaFileObject {
    private ByteArrayOutputStream outputStream;

    protected JavaByteObject(String name) {
        super(URI.create("bytes:///" + name + name.replaceAll("\\.", "/")), Kind.CLASS);
        outputStream = new ByteArrayOutputStream();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return outputStream;
    }

    public byte[] getBytes() {
        return outputStream.toByteArray();
    }
}

class JavaStringObject extends SimpleJavaFileObject {
    private final String source;

    protected JavaStringObject(String name, String source) {
        super(URI.create("string:///" + name.replaceAll("\\.", "/") +
                Kind.SOURCE.extension), Kind.SOURCE);
        this.source = source;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return source;
    }
}

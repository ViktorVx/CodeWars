package com.pva.lessons;


import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kyu5 {

    public static long ipsBetween(String start, String end) {
        System.out.println(start + " " + end);
        String[] s = start.split("\\.");
        String s1 = fulfillDig(Integer.toBinaryString(Integer.valueOf(s[0]))) +
                fulfillDig(Integer.toBinaryString(Integer.valueOf(s[1]))) +
                fulfillDig(Integer.toBinaryString(Integer.valueOf(s[2]))) +
                fulfillDig(Integer.toBinaryString(Integer.valueOf(s[3])));
        long sst = Long.parseLong(s1, 2);

        s = end.split("\\.");
        s1 = fulfillDig(Integer.toBinaryString(Integer.valueOf(s[0]))) +
                fulfillDig(Integer.toBinaryString(Integer.valueOf(s[1]))) +
                fulfillDig(Integer.toBinaryString(Integer.valueOf(s[2]))) +
                fulfillDig(Integer.toBinaryString(Integer.valueOf(s[3])));
        long ennd = Long.parseLong(s1, 2);
        return ennd - sst;

    }

    public static String fulfillDig(String dig) {
        String res = dig;
        for (int i = 0; i < 8 - dig.length(); i++) {
            res = "0".concat(res);
        }
        return res;
    }

    public static String listSquared(long m, long n) {
        //"[[42, 2500], [246, 84100]]"
        long sq = 0;
        String res = "[";
        for (long j = m; j <= n; j++) {
            for (long i = 1; i <= j; i++) {
                if (j % i == 0) sq += i * i;
                if (i >= m && Math.sqrt(sq) % 1 == 0) res = res.concat(String.format("[%d, %d], ", i, sq));
            }
        }

        return res.substring(0, res.length() - 2).concat("]");
    }

    //******************************************************************************************************************
    enum Direction {
        DOWN, UP;
    }

    interface Movable {
        public void move();
    }


    abstract static class Point implements Movable{
        int x, y;


        public Point(){}

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Patrool extends Point {
        Direction direction;
        int mapHeight;

        public Patrool(int x, int y, int mapHeight) {
            super(x, y);
            if (x == 0) direction = Direction.DOWN;
            else direction = Direction.UP;
            this.mapHeight = mapHeight;
        }

        @Override
        public void move() {
            if (mapHeight==1) return;
            if (x==0) direction = Direction.DOWN;
            if (x==mapHeight) direction = Direction.UP;
            if (direction==Direction.UP) x--;
                else x++;
        }
    }

    static class Ship extends Point{

        public Ship(){ }

        public Ship(int x, int y, int mapHeight) {
            super(x, y);
        }

        @Override
        public void move() {
            this.y++;
        }
    }

    public static boolean checkCourse(char[][] map) {

        final int xL = map.length;
        final int yL = map[0].length;

        List<Patrool> patroolList = new ArrayList<>();
        Ship ship = new Ship();

        for (int i = 0; i < xL; i++) {
            for (int j = 0; j < yL; j++) {
                if (map[i][j] == '0') continue;
                if (map[i][j] == 'N') {
                    patroolList.add(new Patrool(i, j, xL-1));
                    continue;
                }
                if (map[i][j] == 'X') ship = new Ship(i, j, yL-1);
            }
        }
        //**
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println(" ");
        }
        //
        System.out.println(String.format("xL: %d ** yL: %d", xL, yL));
        System.out.println(String.format("Ship.x: %d ** Ship.y: %d", ship.x, ship.y));
        for (Patrool patrool1 : patroolList) {
            System.out.println(String.format("Patrool.x: %d ** Patrool.y: %d", patrool1.x, patrool1.y));
        }
        System.out.println("***********************");
        //**
        while (ship.y != yL-1) {
            for (Patrool patrool1 : patroolList) {
                if (!check(ship, patrool1)) return false;
            }
            ship.move();
            for (Patrool patrool1 : patroolList) {
                patrool1.move();
            }
        }

        return true;
    }

    public static boolean check(Ship ship, Patrool patrool) {
        int xs = ship.x;
        int ys = ship.y;
        int xp = patrool.x;
        int yp = patrool.y;
        if (((xs-1==xp) && ((ys-1==yp)||(ys==yp)||(ys+1==yp)))||
            ((xs+1==xp) && ((ys-1==yp)||(ys==yp)||(ys+1==yp)))||
                (xs==xp && ((ys-1==yp)||(ys+1==yp)))) return false;

        return true;
    }

    public static long[] smallest(long num) {
        System.out.println(String.valueOf(num));
        long[] res = new long[3];
        String s = String.valueOf(num);
        long from = s.length(), to = s.length();
        long minValue = num;
        //***
        char[] chars = s.toCharArray();
        List<String> stringList = new LinkedList<String>();
        for (int i = 0; i < chars.length; i++) {
            stringList.add(String.valueOf(chars[i]));
        }
        //
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i == j) continue;
                String jStr = stringList.get(j);
                stringList.add(i>j ? i+1: i, jStr);
                stringList.remove(i>j? j :j + 1);
                long n = Long.valueOf(new String(String.valueOf(stringList).
                        replaceAll("[,|\\[|\\]| ]", "")));
                if (n <= minValue) {
                    if (n == minValue) {
                        if (from == j) {
                            if (to > i) {
                                to = i;
                            }
                        }
                        if (from > j) {
                            from = j;
                            to = i;
                        }
                    } else {
                        from = j;
                        to = i;
                    }



                    minValue = n;
                }
                stringList.remove(i);
                stringList.add(j, jStr);
            }

        }
        //***
        res[0] = minValue;
        res[1] = from;
        res[2] = to;

        return res;
    }

    public static  String phone(String strng, String num) {
        class Contact{
            private String name;
            private String adress;
            private String phone;
            private Boolean tooManyPeople = false;

            public String getPhone() {
                return phone;
            }

            public Boolean getTooManyPeople() {
                return tooManyPeople;
            }

            public void setTooManyPeople(Boolean tooManyPeople) {
                this.tooManyPeople = tooManyPeople;
            }

            public Contact(String str) {
                Pattern pattern = Pattern.compile("\\+([0-9]{1,2}-[0-9]{3}-[0-9]{3}-[0-9]{4})");
                Matcher matcher = pattern.matcher(str);
                matcher.find();
                phone = matcher.group(1);

                pattern = Pattern.compile("<([ 0-9a-zA-Z']+)>");
                matcher = pattern.matcher(str);
                if (matcher.find()) {
                    name = matcher.group(1);
                } else {
                    name = "";
                }
                adress = str.replaceAll("\\+".concat(phone), "").replaceAll("<".concat(name).concat(">"), "").
                        replaceAll("[^0-9a-zA-Z.-]+", " ").trim();

            }

            @Override
            public String toString() {
                if (tooManyPeople) {
                    return String.format("Error => Too many people: %s", phone);
                } else {
                    return String.format("Phone => %s, Name => %s, Address => %s", phone, name, adress);
                }
            }
        }
        String[] strings = strng.split("\n");
        Contact contact;
        Map<String, Contact> contactMap = new HashMap<String, Contact>();
        for (String string : strings) {
            contact = new Contact(string);
            if (contactMap.containsKey(contact.getPhone()))
                contact.setTooManyPeople(true);
            contactMap.put(contact.getPhone(), contact);

        }
        if (contactMap.containsKey(num))
            return contactMap.get(num).toString();
        else
            return String.format("Error => Not found: %s", num);
    }

    public static Integer chooseBestSum(int t, int k, List<Integer> ls) {
        // your code

        return 0;
    }

}

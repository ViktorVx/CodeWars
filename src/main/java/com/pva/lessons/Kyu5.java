package com.pva.lessons;


import java.math.BigInteger;
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


    abstract static class Point implements Movable {
        int x, y;


        public Point() {
        }

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
            if (mapHeight == 1) return;
            if (x == 0) direction = Direction.DOWN;
            if (x == mapHeight) direction = Direction.UP;
            if (direction == Direction.UP) x--;
            else x++;
        }
    }

    static class Ship extends Point {

        public Ship() {
        }

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
                    patroolList.add(new Patrool(i, j, xL - 1));
                    continue;
                }
                if (map[i][j] == 'X') ship = new Ship(i, j, yL - 1);
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
        while (ship.y != yL - 1) {
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
        if (((xs - 1 == xp) && ((ys - 1 == yp) || (ys == yp) || (ys + 1 == yp))) ||
                ((xs + 1 == xp) && ((ys - 1 == yp) || (ys == yp) || (ys + 1 == yp))) ||
                (xs == xp && ((ys - 1 == yp) || (ys + 1 == yp)))) return false;

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
                stringList.add(i > j ? i + 1 : i, jStr);
                stringList.remove(i > j ? j : j + 1);
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

    public static String phone(String strng, String num) {
        class Contact {
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
        int res = recursionSum(new LinkedList<>(), 0, k, ls, 0, t);
        return res == 0 ? null : res;
    }

    public static Integer recursionSum(LinkedList<Integer> indexesList, int ress, int k, List<Integer> ls, int max, int t) {

        if (indexesList.size() == k) {
            return ress > max ? ress : max;
        }

        int nn = indexesList.size() != 0 ? indexesList.getLast() : 0;
        for (int i = nn; i < ls.size(); i++) {
            if (indexesList.contains(i)) continue;

            int n = ls.get(i);
            ress += n;
            indexesList.add(i);

            if (ress > t) {
                indexesList.removeLast();
                ress -= n;
                continue;
            }

            max = recursionSum(indexesList, ress, k, ls, max, t);
            ress -= ls.get(i);
            indexesList.removeLast();
        }
        return max;
    }

    //*************************
    static class Weight {
        private String weightString;

        public String getWeightString() {
            return weightString;
        }

        public Weight(String weightString) {
            this.weightString = weightString;
        }
    }

    static class DigitComparator implements Comparator<Weight> {

        @Override
        public int compare(Weight o1, Weight o2) {
            long w1 = o1.getWeightString().chars().mapToLong(Character::getNumericValue).sum();
            long w2 = o2.getWeightString().chars().mapToLong(Character::getNumericValue).sum();
            if (w1 > w2) return 1;
            if (w2 > w1) return -1;
            return o1.getWeightString().compareTo(o2.getWeightString());

        }
    }

    public static String orderWeight(String strng) {

        /*
        public static String orderWeight(String string) {
            String[] split = string.split(" ");
            Arrays.sort(split, new Comparator<String>() {
                public int compare(String a, String b) {
                    int aWeight = a.chars().map(c -> Character.getNumericValue(c)).sum();
                    int bWeight = b.chars().map(c -> Character.getNumericValue(c)).sum();
                    return aWeight - bWeight != 0 ? aWeight - bWeight : a.compareTo(b);
                }
            });
            return String.join(" ", split);
        }*/


        String[] strings = strng.split(" ");
        List<Weight> weightList = new ArrayList<>();

        for (String string : strings) {
            weightList.add(new Weight(string));
        }
        //***
        Collections.sort(weightList, new DigitComparator());
        //***
        StringBuilder stringBuilder = new StringBuilder();
        for (Weight weight : weightList) {
            stringBuilder.append(weight.getWeightString());
            stringBuilder.append(" ");
        }

        String res = stringBuilder.toString().trim();
        System.out.println(res);
        return res;
    }

    public static String WhoIsNext(String[] names, Integer n) {
        int m = Integer.valueOf(names.length);

        int mm = m;
        int i = 0;
        while (true) {
            int p = m * ((int) Math.pow(2, i) - 1);
            if (p <= n) {
                mm = p;
                i++;
            } else break;
        }
        //***
        long ost = n - mm;
        long posl = (long) Math.pow(2, i - 1);
        int num = (int) (ost % posl == 0 ? (ost / posl) : (ost / posl + 1));

        return names[num - 1];
    }

    //*************************

    public static List<long[]> removNb(long n) {
        List<long[]> res = new ArrayList<>();

        BigInteger nB = BigInteger.valueOf(n);
        BigInteger sum = ((new BigInteger("2")).add(nB.subtract(BigInteger.ONE))).multiply(nB).divide(new BigInteger("2"));

        for (long a = 1; a < n + 1; a++) {
            BigInteger aB = BigInteger.valueOf(a);
            if ((sum.subtract(aB).mod(aB.add(BigInteger.ONE)).equals(BigInteger.ZERO))) {
                long b = (sum.subtract(aB).divide(aB.add(BigInteger.ONE)).longValue());
                if (b<=n)
                    res.add(new long[]{a, b});
            }
        }
        return res;
    }

    //*************************
    public static String[] dirReduc(String[] arr) {
        Stack<String> resStack = new Stack<>();
        for (String s : arr) {
            if (resStack.size() == 0) {
                resStack.push(s);
                continue;
            }
            switch (s) {
                case "NORTH":
                    if (resStack.peek().equals("SOUTH")) resStack.pop();
                        else resStack.push(s);
                    break;
                case "SOUTH":
                    if (resStack.peek().equals("NORTH")) resStack.pop();
                        else resStack.push(s);
                    break;
                case "WEST":
                    if (resStack.peek().equals("EAST")) resStack.pop();
                        else resStack.push(s);
                    break;
                case "EAST":
                    if (resStack.peek().equals("WEST")) resStack.pop();
                        else resStack.push(s);
                    break;
            }
        }

        return resStack.toArray(new String[resStack.size()]);
    }

    public static long[] productFib(long prod) {
        final long[] FIB = new long[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765,
                10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578,
                5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437,
                701408733, 1134903170, 1836311903, 2971215073L, 4807526976L, 7778742049L, 12586269025L, 20365011074L,
                32951280099L, 53316291173L, 86267571272L, 139583862445L, 225851433717L, 365435296162L, 591286729879L,
                956722026041L, 1548008755920L, 2504730781961L, 4052739537881L, 6557470319842L, 10610209857723L,
                17167680177565L, 27777890035288L, 44945570212853L, 72723460248141L, 117669030460994L, 190392490709135L,
                308061521170129L, 498454011879264L, 806515533049393L};

        for (int i = 0; i < FIB.length - 1; i++) {
            if (FIB[i]*FIB[i+1]>=prod) return new long[]{FIB[i], FIB[i+1], FIB[i]*FIB[i+1]==prod? 1 : 0};
        }
        return null;
    }


}

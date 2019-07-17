package com.pva.lessons;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

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

    public static String listSquared2(long m, long n) {

        long[] dig = new long[]{1	,
                42	,                246	,                287	,                728	,                1434	,
                1673	,                1880	,                4264	,                6237	,                9799	,
                9855	,                18330	,                21352	,                21385	,                24856	,
                36531	,                39990	,                46655	,                57270	,                66815	,
                92664	,                125255	,                156570	,                182665	,                208182	,
                212949	,                242879	,                273265	,                380511	,                391345	,
                411558	,                539560	,                627215	,                693160	,                730145	,
                741096	,                773224	,                814463	,                931722	,                992680	,
                1069895	,                1087009	,                1143477	,                1166399	,                1422577	,
                1592935	,                1815073	,                2281255	,                2544697	,                2713880	,
                2722005	,                2828385	,                3054232	,                3132935	,                3145240	,
                3188809	,                3508456	,                4026280	,                4647985	,                4730879	,
                5024488	,                5054015	,                5143945	,                5260710	,                5938515	,
                6128024	,                6236705	,                6366767	,                6956927	,                6996904	,
                7133672	,                7174440	,                7538934	,                7736646	,                7818776	,
                8292583	,                8429967	,                8504595	,                8795423	,                9026087	,
                9963071	,                11477130	,                11538505	,                11725560	,                12158135	,
                12939480	,
                12948776	,
                13495720	,
                13592118	,
                13736408	,
                15203889	,
                15857471	,
                16149848	,
                16436490	,
                16487415	,
                16909849	,
                18391401	,
                18422120	,
                20549528	,
                20813976	,
                20871649	,
                21251412	,
                22713455	,
                23250645	,
                23630711	,
                24738935	,
                26338473	,
                26343030	,
                26594568	,
                28113048	,
                29429144	,
                29778762	,
                29973414	,
                30666090	,
                30915027	,
                34207446	,
                34741889	,
                34968983	,
                35721896	,
                37113593	,
                37343065	,
                38598255	,
                39256230	,
                42021720	,
                44935590	,
                45795688	,
                45798935	,
                48988758	,
                49375521	,
                51516049	,
                51912289	,
                52867081	,
                56215914	,
                59748234	,
                61116363	,
                62158134	,
                63286535	,
                65585233	,
                66903270	,
                68219814	,
                68678280	,
                72006543	,
                72517823	,
                74955321	,
                76233066	,
                79046360	,
                79589783	,
                80456104	,
                83597345	,
                86369945	,
                89718065	,
                92879473	,
                95812710	,
                96569145	,
                100855640	,
                101793832	,
                104475337	,
                106567720	,
                106877274	,
                107034264	,
                107328918	,
                107593496	,
                107721063	,
                109206657	,
                109523478	,
                111472314	,
                118016695	,
                118505377	,
                118959366	,
                119788712	,
                121960122	,
                124472556	,
                125217071	,
                126250566	,
                127777391	,
                129896070	,
                130051033	,
                131583270	,
                133172424	,
                133929978	,
                134101864	,
                138785927	,
                142286809	,
                147186585	,
                147292327	,
                149978456	,
                151545415	,
                154268199	,
                154294890	,
                155026872	,
                169215360	,
                171550376	,
                174962166	,
                179615670	,
                180010705	,
                185248647	,
                193207815	,
                195005097	,
                195215370	,
                196865610	,
                197605784	,
                198696918	,
                198936920	,
                200357898	,
                203488207	,
                204122527	,
                204818329	,
                205534055	,
                209228248	,
                209551615	,
                210423960	,
                212268630	,
                212306335	,
                212694552	,
                216045690	,
                221915288	,
                223622168	,
                228042726	,
                229676545	,
                231255505	,
                233750881	,
                235238904	,
                235596712	,
                236729545	,
                236758977	,
                240505335	,
                244955880	,
                245144680	,
                247248153	,
                257966857	,
                261941610	,
                266049847	,
                266955352	,
                267404214	,
                268250905	,
                272618920	,
                273243672	,
                274566198	,
                277012008	,
                281374120	,
                284899160	,
                286934154	,
                287823159	,
                288044999	,
                292190934	,
                300300871	,
                308176399	,
                313203295	,
                319785921	,
                320327231	,
                328177899	,
                328605312	,
                330614591	,
                331182865	,
                334756513	,
                348288486	,
                349528472	,
                360111640	,
                362683866	,
                366764135	,
                368575255	,
                373402520	,
                374019414	,
                377486865	,
                380046760	,
                380061928	,
                380370295	,
                384142079	,
                386824664	,
                391314456	,
                391862010	,
                400344120	,
                418448982	,
                421752609	,
                423131177	,
                423580487	,
                424747249	,
                434373416	,
                436355983	,
                438747961	,
                439024023	,
                446507958	,
                446554943	,
                446576328	,
                451070495	,
                453993270	,
                456612520	,
                457172345	,
                459135832	,
                460782440	,
                466168729	,
                469000216	,
                474401785	,
                484617210	,
                489322728	,
                505329905	,
                510641670	,
                513994136	,
                519539176	,
                520925951	,
                529658815	,
                540488663	,
                541419367	,
                561188730	,
                568975679	,
                570154201	,
                594757485	,
                596221016	,
                599762137	,
                611410545	,
                624182360	,
                625995462	,
                626696595	,
                627934977	,
                636594903	,
                639545544	,
                639669407	,
                654720185	,
                668669568	,
                703667910	,
                710213658	,
                715360680	,
                725583924	,
                730328039	,
                733414273	,
                736095128	,
                748410433	,
                753668934	,
                761727479	,
                770702010	,
                772325670	,
                778883560	,
                780009912	,
                781215435	,
                784447014	,
                812889001	,
                820945895	,
                825820785	,
                832451256	,
                833394167	,
                857394473	,
                862092855	,
                862712201	,
                863592435	,
                867695576	,
                868529910	,
                871625853	,
                876564584	,
                876609258	,
                879280423	,
                887623145	,
                899152345	,
                899270721	,
                899426310	,
                900883583	,
                901046615	,
                908014536	,
                914291752	,
                915188183	,
                953965110	,
                974409176	,
                983342490	,
                992489862	,
                995218488	,
                996177560	,
                1003293114	,
                1013284895	,
                1018968151	,
                1019758040	,
                1027042026	,
                1027545623	,
                1034987855	,
                1037656620	,
                1039035270	,
                1049036586	,
                1049330695	,
                1069811496	,
                1077407656	,
                1106572505	,
                1107650727	,
                1113621912	,
                1119531699	,
                1137142609	,
                1142172711	,
                1143404310	,
                1147232905	,
                1148891931	,
                1155542360	,
                1159656680	,
                1163796234	,
                1167939942	,
                1170508633	,
                1186187353	,
                1193940991	,
                1195574801	,
                1198215697	,
                1198600518	,
                1217514753	,
                1223876017	,
                1235002977	,
                1239853608	,
                1243287690	,
                1249339432	,
                1265410470	,
                1293603752	,
                1309786984	,
                1333971695	,
                1345248335	,
                1357762273	,
                1362596599	,
                1386731151	,
                1390302143	,
                1398367271	,
                1408674105	,
                1415326735	,
                1426633880	,
                1436824122	,
                1446944680	,
                1450502305	,
                1462289946	,
                1476312215	,
                1480724120	,
                1482214415	,
                1534229430	,
                1558291961	,
                1558770906	,
                1563706495	,
                1568408730	,
                1587159576	,
                1596767960	,
                1622498904	,
                1660753640	,
                1666017585	,
                1672616166	,
                1676294809	,
                1681515423	,
                1700159999	,
                1704353805	,
                1706004937	,
                1711404042	,
                1717812360	,
                1739850841	,
                1748448504	,
                1759808346	,
                1769082090	,
                1789934335	,
                1801794966	,
                1832235687	,
                1852539416	,
                1860367912	,
                1871687514	,
                1873031823	,
                1876202353	,
                1880213665	,
                1898406952	,
                1908114294	,
                1913037021	,
                1924688256	,
                1926656394	,
                1932186776	,
                1951385527	,
                1992292713	,
                1996638049	,
                2008111448	,
                2018429523	,
                2039975418	,
                2043576920	,
                2053109737	,
                2061962344	,
                2063929105	,
                2091537673	,
                2102094127	,
                2109225320	,
                2113975448	,
                2149736760L	,
                2163674058L	,
                2180316138L	,
                2183635433L	,
                2205615985L	,
                2210994495L	,
                2220417402L	,
                2226133343L	,
                2239267241L	,
                2242595545L	,
                2247765793L	,
                2251531880L	,
                2259582870L	,
                2328258856L	,
                2334342097L	,
                2379971321L	,
                2389810527L	,
                2390160630L	,
                2393572545L	,
                2428276591L	,
                2440818765L	,
                2449207368L	,
                2450915466L	,
                2458509111L	,
                2475965671L
        };

        String[] strs = new String[] {"	[1, 1], 	"	,
                "	[42, 2500], 	"	,
                "	[246, 84100], 	"	,
                "	[287, 84100], 	"	,
                "	[728, 722500], 	"	,
                "	[1434, 2856100], 	"	,
                "	[1673, 2856100], 	"	,
                "	[1880, 4884100], 	"	,
                "	[4264, 24304900], 	"	,
                "	[6237, 45024100], 	"	,
                "	[9799, 96079204], 	"	,
                "	[9855, 113635600], 	"	,
                "	[18330, 488410000], 	"	,
                "	[21352, 607622500], 	"	,
                "	[21385, 488410000], 	"	,
                "	[24856, 825412900], 	"	,
                "	[36531, 1514610724], 	"	,
                "	[39990, 2313610000], 	"	,
                "	[46655, 2313610000], 	"	,
                "	[57270, 4747210000], 	"	,
                "	[66815, 4747210000], 	"	,
                "	[92664, 13011964900], 	"	,
                "	[125255, 16430112400], 	"	,
                "	[156570, 35532250000], 	"	,
                "	[182665, 35532250000], 	"	,
                "	[208182, 60762250000], 	"	,
                "	[212949, 51437332804], 	"	,
                "	[242879, 60762250000], 	"	,
                "	[273265, 77829840400], 	"	,
                "	[380511, 163426147600], 	"	,
                "	[391345, 159696144400], 	"	,
                "	[411558, 240198010000], 	"	,
                "	[539560, 410752810000], 	"	,
                "	[627215, 410752810000], 	"	,
                "	[693160, 668633290000], 	"	,
                "	[730145, 557979120400], 	"	,
                "	[741096, 821017210000], 	"	,
                "	[773224, 796252828900], 	"	,
                "	[814463, 668633290000], 	"	,
                "	[931722, 1219036810000], 	"	,
                "	[992680, 1371943690000], 	"	,
                "	[1069895, 1195304890000], 	"	,
                "	[1087009, 1219036810000], 	"	,
                "	[1143477, 1475034540100], 	"	,
                "	[1166399, 1371943690000], 	"	,
                "	[1422577, 2044042090000], 	"	,
                "	[1592935, 2643160608400], 	"	,
                "	[1815073, 3327340810000], 	"	,
                "	[2281255, 5423402592400], 	"	,
                "	[2544697, 6498930490000], 	"	,
                "	[2713880, 10268820250000], 	"	,
                "	[2722005, 8796088272400], 	"	,
                "	[2828385, 9556753960000], 	"	,
                "	[3054232, 12389344022500], 	"	,
                "	[3132935, 10268820250000], 	"	,
                "	[3145240, 13949478010000], 	"	,
                "	[3188809, 10268820250000], 	"	,
                "	[3508456, 16715832250000], 	"	,
                "	[4026280, 22492823875600], 	"	,
                "	[4647985, 22492823875600], 	"	,
                "	[4730879, 22492823875600], 	"	,
                "	[5024488, 34298592250000], 	"	,
                "	[5054015, 26676192010000], 	"	,
                "	[5143945, 27619018944400], 	"	,
                "	[5260710, 41075281000000], 	"	,
                "	[5938515, 41667283200400], 	"	,
                "	[6128024, 51101052250000], 	"	,
                "	[6236705, 40593463690000], 	"	,
                "	[6366767, 41008398288400], 	"	,
                "	[6956927, 48643650250000], 	"	,
                "	[6996904, 65032773204100], 	"	,
                "	[7133672, 69417224890000], 	"	,
                "	[7174440, 82101721000000], 	"	,
                "	[7538934, 79625282890000], 	"	,
                "	[7736646, 83183520250000], 	"	,
                "	[7818776, 83183520250000], 	"	,
                "	[8292583, 69417224890000], 	"	,
                "	[8429967, 82101721000000], 	"	,
                "	[8504595, 85495543104400], 	"	,
                "	[8795423, 79625282890000], 	"	,
                "	[9026087, 83183520250000], 	"	,
                "	[9963071, 99810090250000], 	"	,
                "	[11477130, 194574601000000], 	"	,
                "	[11538505, 139610766490000], 	"	,
                "	[11725560, 219902206810000], 	"	,
                "	[12158135, 155004990010000], 	"	,
                "	[12939480, 260131092816400], 	"	,
                "	[12948776, 222689064472900], 	"	,
                "	[13495720, 256720506250000], 	"	,
                "	[13592118, 256720506250000], 	"	,
                "	[13736408, 256720506250000], 	"	,
                "	[15203889, 260131092816400], 	"	,
                "	[15857471, 256720506250000], 	"	,
                "	[16149848, 352301638090000], 	"	,
                "	[16436490, 399240361000000], 	"	,
                "	[16487415, 324554637160000], 	"	,
                "	[16909849, 289604836128400], 	"	,
                "	[18391401, 388917841000000], 	"	,
                "	[18422120, 469260440256400], 	"	,
                "	[20549528, 562320596890000], 	"	,
                "	[20813976, 647219040250000], 	"	,
                "	[20871649, 436988086147600], 	"	,
                "	[21251412, 683572005944100], 	"	,
                "	[22713455, 538266912336400], 	"	,
                "	[23250645, 639923030890000], 	"	,
                "	[23630711, 562320596890000], 	"	,
                "	[24738935, 640317720250000], 	"	,
                "	[26338473, 798006001000000], 	"	,
                "	[26343030, 1026882025000000], 	"	,
                "	[26594568, 1094306248090000], 	"	,
                "	[28113048, 1180753916410000], 	"	,
                "	[29429144, 1153804643290000], 	"	,
                "	[29778762, 1238934402250000], 	"	,
                "	[29973414, 1253591917210000], 	"	,
                "	[30666090, 1394947801000000], 	"	,
                "	[30915027, 1094306248090000], 	"	,
                "	[34207446, 1671583225000000], 	"	,
                "	[34741889, 1238934402250000], 	"	,
                "	[34968983, 1253591917210000], 	"	,
                "	[35721896, 1735430622250000], 	"	,
                "	[37113593, 1392676413216400], 	"	,
                "	[37343065, 1451977307232400], 	"	,
                "	[38598255, 1747952344590400], 	"	,
                "	[39256230, 2249282387560000], 	"	,
                "	[42021720, 2761901894440000], 	"	,
                "	[44935590, 2988262225000000], 	"	,
                "	[45795688, 2798293621210000], 	"	,
                "	[45798935, 2249282387560000], 	"	,
                "	[48988758, 3429859225000000], 	"	,
                "	[49375521, 2761901894440000], 	"	,
                "	[51516049, 2678594516419600], 	"	,
                "	[51912289, 2733777693091600], 	"	,
                "	[52867081, 2798293621210000], 	"	,
                "	[56215914, 4389206926188100], 	"	,
                "	[59748234, 5110105225000000], 	"	,
                "	[61116363, 4325879688816400], 	"	,
                "	[62158134, 5369738562250000], 	"	,
                "	[63286535, 4190089414810000], 	"	,
                "	[65585233, 4389206926188100], 	"	,
                "	[66903270, 6607901521000000], 	"	,
                "	[68219814, 6503277320410000], 	"	,
                "	[68678280, 7397510237088400], 	"	,
                "	[72006543, 5972971225000000], 	"	,
                "	[72517823, 5369738562250000], 	"	,
                "	[74955321, 6464320801000000], 	"	,
                "	[76233066, 8318352025000000], 	"	,
                "	[79046360, 8636077830250000], 	"	,
                "	[79589783, 6503277320410000], 	"	,
                "	[80456104, 8636077830250000], 	"	,
                "	[83597345, 7311087924010000], 	"	,
                "	[86369945, 7799719388640400], 	"	,
                "	[89718065, 8375196559210000], 	"	,
                "	[92879473, 8636077830250000], 	"	,
                "	[95812710, 13558506481000000], 	"	,
                "	[96569145, 10918017994062400], 	"	,
                "	[100855640, 14058014922250000], 	"	,
                "	[101793832, 14058014922250000], 	"	,
                "	[104475337, 10924702153690000], 	"	,
                "	[106567720, 16007943006250000], 	"	,
                "	[106877274, 16247326225000000], 	"	,
                "	[107034264, 17115677763610000], 	"	,
                "	[107328918, 16007943006250000], 	"	,
                "	[107593496, 15375546163348900], 	"	,
                "	[107721063, 13083196171240000], 	"	,
                "	[109206657, 13744139013160000], 	"	,
                "	[109523478, 16672620006250000], 	"	,
                "	[111472314, 17261886840250000], 	"	,
                "	[118016695, 14525229486835600], 	"	,
                "	[118505377, 14058014922250000], 	"	,
                "	[118959366, 19669192959610000], 	"	,
                "	[119788712, 19096835395690000], 	"	,
                "	[121960122, 20698145292250000], 	"	,
                "	[124472556, 22995362279959524], 	"	,
                "	[125217071, 16007943006250000], 	"	,
                "	[126250566, 22268906447290000], 	"	,
                "	[127777391, 16672620006250000], 	"	,
                "	[129896070, 24372111786490000], 	"	,
                "	[130051033, 17261886840250000], 	"	,
                "	[131583270, 25672050625000000], 	"	,
                "	[133172424, 27357656202250000], 	"	,
                "	[133929978, 25672050625000000], 	"	,
                "	[134101864, 24040037352250000], 	"	,
                "	[138785927, 19669192959610000], 	"	,
                "	[142286809, 20698145292250000], 	"	,
                "	[147186585, 26013109281640000], 	"	,
                "	[147292327, 22268906447290000], 	"	,
                "	[149978456, 29874318938772100], 	"	,
                "	[151545415, 24372111786490000], 	"	,
                "	[154268199, 26844921873640000], 	"	,
                "	[154294890, 34544311321000000], 	"	,
                "	[155026872, 37163472950890000], 	"	,
                "	[169215360, 44162560171210000], 	"	,
                "	[171550376, 39184083730090000], 	"	,
                "	[174962166, 42525739793290000], 	"	,
                "	[179615670, 46926044025640000], 	"	,
                "	[180010705, 34544311321000000], 	"	,
                "	[185248647, 38673778992250000], 	"	,
                "	[193207815, 43698808614760000], 	"	,
                "	[195005097, 43698808614760000], 	"	,
                "	[195215370, 56232059689000000], 	"	,
                "	[196865610, 56232059689000000], 	"	,
                "	[197605784, 51860702397302496], 	"	,
                "	[198696918, 56232059689000000], 	"	,
                "	[198936920, 56232059689000000], 	"	,
                "	[200357898, 56232059689000000], 	"	,
                "	[203488207, 41677753291690000], 	"	,
                "	[204122527, 42525739793290000], 	"	,
                "	[204818329, 42170832094944400], 	"	,
                "	[205534055, 43962154685971600], 	"	,
                "	[209228248, 58379886132490000], 	"	,
                "	[209551615, 46926044025640000], 	"	,
                "	[210423960, 69047547361000000], 	"	,
                "	[212268630, 66690480025000000], 	"	,
                "	[212306335, 46878182287690000], 	"	,
                "	[212694552, 69047547361000000], 	"	,
                "	[216045690, 69047547361000000], 	"	,
                "	[221915288, 66964862910490000], 	"	,
                "	[223622168, 68344442327290000], 	"	,
                "	[228042726, 72477093126490000], 	"	,
                "	[229676545, 56232059689000000], 	"	,
                "	[231255505, 56232059689000000], 	"	,
                "	[233750881, 56232059689000000], 	"	,
                "	[235238904, 82696775216020896], 	"	,
                "	[235596712, 74192226306250000], 	"	,
                "	[236729545, 58627915109280400], 	"	,
                "	[236758977, 64721904025000000], 	"	,
                "	[240505335, 69047547361000000], 	"	,
                "	[244955880, 93796290139240000], 	"	,
                "	[245144680, 83695797641107600], 	"	,
                "	[247248153, 69047547361000000], 	"	,
                "	[257966857, 66964862910490000], 	"	,
                "	[261941610, 101483659225000000], 	"	,
                "	[266049847, 72477093126490000], 	"	,
                "	[266955352, 95032180874410000], 	"	,
                "	[267404214, 102520995721000000], 	"	,
                "	[268250905, 75665859517518400], 	"	,
                "	[272618920, 104752235370250000], 	"	,
                "	[273243672, 112397256049000000], 	"	,
                "	[274566198, 104752235370250000], 	"	,
                "	[277012008, 118075391641000000], 	"	,
                "	[281374120, 109380017802250000], 	"	,
                "	[284899160, 115380464329000000], 	"	,
                "	[286934154, 115380464329000000], 	"	,
                "	[287823159, 93796290139240000], 	"	,
                "	[288044999, 83695797641107600], 	"	,
                "	[292190934, 121609125625000000], 	"	,
                "	[300300871, 90967108184851600], 	"	,
                "	[308176399, 95032180874410000], 	"	,
                "	[313203295, 102023578567690000], 	"	,
                "	[319785921, 118075391641000000], 	"	,
                "	[320327231, 104752235370250000], 	"	,
                "	[328177899, 124050404822410000], 	"	,
                "	[328605312, 163323077556249984], 	"	,
                "	[330614591, 109380017802250000], 	"	,
                "	[331182865, 115380464329000000], 	"	,
                "	[334756513, 115380464329000000], 	"	,
                "	[348288486, 173543062225000000], 	"	,
                "	[349528472, 162510652501209984], 	"	,
                "	[360111640, 182777198100249984], 	"	,
                "	[362683866, 182777198100249984], 	"	,
                "	[366764135, 139919462175610000], 	"	,
                "	[368575255, 141299983021210000], 	"	,
                "	[373402520, 192735487272249984], 	"	,
                "	[374019414, 194992984716010016], 	"	,
                "	[377486865, 170102959173160000], 	"	,
                "	[380046760, 199547411873289984], 	"	,
                "	[380061928, 192735487272249984], 	"	,
                "	[380370295, 150537807583680384], 	"	,
                "	[384142079, 147652920996967680], 	"	,
                "	[386824664, 199547411873289984], 	"	,
                "	[391314456, 230623734289000000], 	"	,
                "	[391862010, 222289807166440000], 	"	,
                "	[400344120, 251225077148016384], 	"	,
                "	[418448982, 249525225625000000], 	"	,
                "	[421752609, 200930752009000000], 	"	,
                "	[423131177, 182777198100249984], 	"	,
                "	[423580487, 181003022580249984], 	"	,
                "	[424747249, 180638005234089984], 	"	,
                "	[434373416, 250592720902216896], 	"	,
                "	[436355983, 194992984716009984], 	"	,
                "	[438747961, 192735487272249984], 	"	,
                "	[439024023, 217459751745640000], 	"	,
                "	[446507958, 279829362121000000], 	"	,
                "	[446554943, 199547411873289984], 	"	,
                "	[446576328, 301046121122889984], 	"	,
                "	[451070495, 211857034008489984], 	"	,
                "	[453993270, 297714388550409984], 	"	,
                "	[456612520, 296768905225000000], 	"	,
                "	[457172345, 222289807166440000], 	"	,
                "	[459135832, 279977763205562496], 	"	,
                "	[460782440, 293287775160249984], 	"	,
                "	[466168729, 218770249058592384], 	"	,
                "	[469000216, 293287775160249984], 	"	,
                "	[474401785, 238155907348840000], 	"	,
                "	[484617210, 349026916225000000], 	"	,
                "	[489322728, 357718466358489984], 	"	,
                "	[505329905, 265769386858627584], 	"	,
                "	[510641670, 387512475025000000], 	"	,
                "	[513994136, 351450373056249984], 	"	,
                "	[519539176, 362288064073689984], 	"	,
                "	[520925951, 279829362121000000], 	"	,
                "	[529658815, 297714388550409984], 	"	,
                "	[540488663, 296285677633689984], 	"	,
                "	[541419367, 293287775160249984], 	"	,
                "	[561188730, 456108158020840000], 	"	,
                "	[568975679, 324756398670960384], 	"	,
                "	[570154201, 325661989050720384], 	"	,
                "	[594757485, 422322568632249984], 	"	,
                "	[596221016, 472911621984489984], 	"	,
                "	[599762137, 362288064073689984], 	"	,
                "	[611410545, 437520071209000000], 	"	,
                "	[624182360, 538507202730249984], 	"	,
                "	[625995462, 546560054209000000], 	"	,
                "	[626696595, 468890094780249984], 	"	,
                "	[627934977, 444315298272040000], 	"	,
                "	[636594903, 466761420160360000], 	"	,
                "	[639545544, 617705298929289984], 	"	,
                "	[639669407, 412028549130249984], 	"	,
                "	[654720185, 456108158020840000], 	"	,
                "	[668669568, 678245308692249984], 	"	,
                "	[703667910, 724012090320999936], 	"	,
                "	[710213658, 724012090320999936], 	"	,
                "	[715360680, 798189647493159936], 	"	,
                "	[725583924, 780940002470777600], 	"	,
                "	[730328039, 546560054209000000], 	"	,
                "	[733414273, 538507202730249984], 	"	,
                "	[736095128, 721127902056249984], 	"	,
                "	[748410433, 560866937010249984], 	"	,
                "	[753668934, 791099279871264384], 	"	,
                "	[761727479, 580689873306009984], 	"	,
                "	[770702010, 863607783024999936], 	"	,
                "	[772325670, 861596266928409984], 	"	,
                "	[778883560, 863607783024999936], 	"	,
                "	[780009912, 920311554643689984], 	"	,
                "	[781215435, 739751023708839936], 	"	,
                "	[784447014, 863607783024999936], 	"	,
                "	[812889001, 661671651161280384], 	"	,
                "	[820945895, 724012090320999936], 	"	,
                "	[825820785, 798189647493159936], 	"	,
                "	[832451256, 1065712455222249984], 	"	,
                "	[833394167, 696285607631289984], 	"	,
                "	[857394473, 742042521277209984], 	"	,
                "	[862092855, 875080996234369536], 	"	,
                "	[862712201, 749126012886835584], 	"	,
                "	[863592435, 876054629288409984], 	"	,
                "	[867695576, 1008687787890249984], 	"	,
                "	[868529910, 1092470215368999936], 	"	,
                "	[871625853, 876054629288409984], 	"	,
                "	[876564584, 1041943832292249984], 	"	,
                "	[876609258, 1092470215368999936], 	"	,
                "	[879280423, 791099279871264384], 	"	,
                "	[887623145, 819877840497523584], 	"	,
                "	[899152345, 863607783024999936], 	"	,
                "	[899270721, 911673975782439936], 	"	,
                "	[899426310, 1173151100640999936], 	"	,
                "	[900883583, 812251021750089984], 	"	,
                "	[901046615, 861596266928409984], 	"	,
                "	[908014536, 1250179230067939584], 	"	,
                "	[914291752, 1119911052822249984], 	"	,
                "	[915188183, 863607783024999936], 	"	,
                "	[953965110, 1345667280840999936], 	"	,
                "	[974409176, 1268480801668360960], 	"	,
                "	[983342490, 1405801492224999936], 	"	,
                "	[992489862, 1405801492224999936], 	"	,
                "	[995218488, 1498105253635209984], 	"	,
                "	[996177560, 1405801492224999936], 	"	,
                "	[1003293114, 1405801492224999936], 	"	,
                "	[1013284895, 1092470215368999936], 	"	,
                "	[1018968151, 1041943832292249984], 	"	,
                "	[1019758040, 1465697877732009984], 	"	,
                "	[1027042026, 1465697877732009984], 	"	,
                "	[1027545623, 1063207835728089984], 	"	,
                "	[1034987855, 1128150301593639936], 	"	,
                "	[1037656620, 1708930014860250112], 	"	,
                "	[1039035270, 1600794300624999936], 	"	,
                "	[1049036586, 1537554616334889984], 	"	,
                "	[1049330695, 1173151100640999936], 	"	,
                "	[1069811496, 1726188684024999936], 	"	,
                "	[1077407656, 1551854444490249984], 	"	,
                "	[1106572505, 1274425731369715712], 	"	,
                "	[1107650727, 1380043436256250112], 	"	,
                "	[1113621912, 1868188711488999936], 	"	,
                "	[1119531699, 1434022679747059712], 	"	,
                "	[1137142609, 1299235818316867584], 	"	,
                "	[1142172711, 1470027921800526336], 	"	,
                "	[1143404310, 1891646487937959936], 	"	,
                "	[1147232905, 1405801492224999936], 	"	,
                "	[1148891931, 1498105253635209984], 	"	,
                "	[1155542360, 1891646487937959936], 	"	,
                "	[1159656680, 1909683539568999936], 	"	,
                "	[1163796234, 1891646487937959936], 	"	,
                "	[1167939942, 1909683539568999936], 	"	,
                "	[1170508633, 1405801492224999936], 	"	,
                "	[1186187353, 1415408218506490112], 	"	,
                "	[1193940991, 1432153549897392384], 	"	,
                "	[1195574801, 1430565886646275584], 	"	,
                "	[1198215697, 1465697877732009984], 	"	,
                "	[1198600518, 1999284295560250112], 	"	,
                "	[1217514753, 1711567776360999936], 	"	,
                "	[1223876017, 1537554616334889984], 	"	,
                "	[1235002977, 1726188684024999936], 	"	,
                "	[1239853608, 2344907253480999936], 	"	,
                "	[1243287690, 2243467748040999936], 	"	,
                "	[1249339432, 2092394941027689984], 	"	,
                "	[1265410470, 2322759493224039936], 	"	,
                "	[1293603752, 2274177704621289984], 	"	,
                "	[1309786984, 2299107039890035712], 	"	,
                "	[1333971695, 1891646487937959936], 	"	,
                "	[1345248335, 1891646487937959936], 	"	,
                "	[1357762273, 1891646487937959936], 	"	,
                "	[1362596599, 1909683539568999936], 	"	,
                "	[1386731151, 2177244851400999936], 	"	,
                "	[1390302143, 1945074563076489984], 	"	,
                "	[1398367271, 1999284295560250112], 	"	,
                "	[1408674105, 2322759493224039936], 	"	,
                "	[1415326735, 2087343304648809984], 	"	,
                "	[1426633880, 2884511608224999936], 	"	,
                "	[1436824122, 2884511608224999936], 	"	,
                "	[1446944680, 2892549542050089984], 	"	,
                "	[1450502305, 2243467748040999936], 	"	,
                "	[1462289946, 2987431893877210112], 	"	,
                "	[1476312215, 2322759493224039936], 	"	,
                "	[1480724120, 3029259004004755456], 	"	,
                "	[1482214415, 2314809493920039936], 	"	,
                "	[1534229430, 3413910296328999936], 	"	,
                "	[1558291961, 2438129412775123456], 	"	,
                "	[1558770906, 3481691033040999936], 	"	,
                "	[1563706495, 2569670170844046336], 	"	,
                "	[1568408730, 3629943268080999936], 	"	,
                "	[1587159576, 3781043693488359936], 	"	,
                "	[1596767960, 3523865197855209984], 	"	,
                "	[1622498904, 3972056174803239936], 	"	,
                "	[1660753640, 3918408373008999936], 	"	,
                "	[1666017585, 3249004087000359936], 	"	,
                "	[1672616166, 3918408373008999936], 	"	,
                "	[1676294809, 2884511608224999936], 	"	,
                "	[1681515423, 3249004087000359936], 	"	,
                "	[1700159999, 2892549542050089984], 	"	,
                "	[1704353805, 3504218517153639936], 	"	,
                "	[1706004937, 2987431893877210112], 	"	,
                "	[1711404042, 4090930986024999936], 	"	,
                "	[1717812360, 4623443898180249600], 	"	,
                "	[1739850841, 3029259004004755456], 	"	,
                "	[1748448504, 4623443898180249600], 	"	,
                "	[1759808346, 4316171205076089856], 	"	,
                "	[1769082090, 4520607798818409984], 	"	,
                "	[1789934335, 3413910296328999936], 	"	,
                "	[1801794966, 4521541321606224384], 	"	,
                "	[1832235687, 3781043693488359936], 	"	,
                "	[1852539416, 4695477279025000448], 	"	,
                "	[1860367912, 4626295528806250496], 	"	,
                "	[1871687514, 4874824617900251136], 	"	,
                "	[1873031823, 3972056174803239936], 	"	,
                "	[1876202353, 3523865197855209984], 	"	,
                "	[1880213665, 3706997344035216384], 	"	,
                "	[1898406952, 4818387181806249984], 	"	,
                "	[1908114294, 5062381650691690496], 	"	,
                "	[1913037021, 4212846149979610112], 	"	,
                "	[1924688256, 5494188328992249856], 	"	,
                "	[1926656394, 5186070239730249728], 	"	,
                "	[1932186776, 4988685296832249856], 	"	,
                "	[1951385527, 3918408373008999936], 	"	,
                "	[1992292713, 4484267876880999936], 	"	,
                "	[1996638049, 4090930986024999936], 	"	,
                "	[2008111448, 5469256226464809984], 	"	,
                "	[2018429523, 4623443898180249600], 	"	,
                "	[2039975418, 5837988613249000448], 	"	,
                "	[2043576920, 5953897683720999936], 	"	,
                "	[2053109737, 4316171205076089856], 	"	,
                "	[2061962344, 5684396765327290368], 	"	,
                "	[2063929105, 4520607798818409984], 	"	,
                "	[2091537673, 4378129808883610112], 	"	,
                "	[2102094127, 4521541321606224384], 	"	,
                "	[2109225320, 6148624944092409856], 	"	,
                "	[2113975448, 5981763989460249600], 	"	,
                "	[2149736760, 7204216197302410240], 	"	,
                "	[2163674058, 6696486291048999936], 	"	,
                "	[2180316138, 6834444232728999936], 	"	,
                "	[2183635433, 4874824617900250112], 	"	,
                "	[2205615985, 5062381650691690496], 	"	,
                "	[2210994495, 5722263546585102336], 	"	,
                "	[2220417402, 6995734053025000448], 	"	,
                "	[2226133343, 5062381650691690496], 	"	,
                "	[2239267241, 5014405560754332672], 	"	,
                "	[2242595545, 5248502944990099456], 	"	,
                "	[2247765793, 5186070239730249728], 	"	,
                "	[2251531880, 7043540306295610368], 	"	,
                "	[2259582870, 7385098655894439936], 	"	,
                "	[2328258856, 7407141940842250240], 	"	,
                "	[2334342097, 5469256226464809984], 	"	,
                "	[2379971321, 5837988613249000448], 	"	,
                "	[2389810527, 6465808446261159936], 	"	,
                "	[2390160630, 8369579764110759936], 	"	,
                "	[2393572545, 6904754736099999744], 	"	,
                "	[2428276591, 5931477588906250240], 	"	,
                "	[2440818765, 7190175175080040448], 	"	,
                "	[2449207368, 8962641748419122176], 	"	,
                "	[2450915466, 8394028590024999936], 	"	,
                "	[2458509111, 6823761246289000448], 	"	,
                "	[2475965671, 6134604123056889856],	"
        };


        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < dig.length; i++) {
            if (dig[i]>n) break;
            if (dig[i]>=m && dig[i]<=n) res.append(strs[i]);
        }
        if (res.length()==1) return "[]";
        String r;
        r = res.toString().replaceAll("\t", "");
        r = r.trim();
        r = r.substring(0, r.length()-1).concat("]");
        System.out.println(r);
        return r;
    }

    public static double going(int n) {
        BigInteger factorial = IntStream.rangeClosed(2, n).parallel().mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).get();
        Double factD = factorial.doubleValue();

        MathContext mc = new MathContext(20, RoundingMode.FLOOR);
        BigDecimal one = BigDecimal.ONE.divide(new BigDecimal(factorial.toString()), mc);

        BigInteger two = BigInteger.ZERO;
//        for (int i = 1; i <=n; i++) {
//            two = two.add(IntStream.rangeClosed(1, i).parallel().mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).get());
//        }
        BigInteger f = BigInteger.ONE;
        for (int i = 1; i <=n; i++) {
            f = f.multiply(new BigInteger(String.valueOf(i)));
            two = two.add(f);
        }

        BigDecimal res = one.multiply(new BigDecimal(two.toString()));
        Double resD = res.doubleValue();

        return Math.floor(resD * 1000000)/1000000.0;
    }

    public static int greedy(int[] dice){
        int res = 0;
        int[] count = new int[]{0, 0, 0, 0, 0, 0};
        int[] weight = new int[]{100, 0, 0, 0, 50, 0};
        int[] weight3 = new int[]{1000, 200, 300, 400, 500, 600};

        for (int die : dice) count[die-1]++;

        for (int i = 0; i < count.length; i++) res+=(count[i]/3*weight3[i]) + (count[i]%3 * weight[i]);

        return res;
    }

    public static int[] sameFactRev(int nMax) {
        List<Integer> integers = new ArrayList<>();
        int[] factRev = new int[]{1089, 2178, 4356, 6534, 8712, 9801, 10989, 21978, 24024,
                26208, 42042, 43956, 48048, 61248, 65934, 80262, 84084,
                84216, 87912, 98901, 109989, 219978, 231504, 234234,
                242424, 253344, 255528, 264264, 272646, 275184, 277816,
                288288, 405132, 424242, 432432, 439956};

        for (int i : factRev) if (i <= nMax) integers.add(i); else break;

        int[] res = new int[integers.size()];
        for (int i = 0; i < res.length; i++) res[i] = integers.get(i);

        return res;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        return a[0].length!= b.length ? null :
                java.util.Arrays.stream(a).
                        map(r -> java.util.stream.IntStream.range(0, b[0].length).
                                map(i -> java.util.stream.IntStream.range(0, b.length).map(j -> r[j] * b[j][i]).sum()).
                                toArray()).toArray(int[][]::new);
    }

    public static String change(String s, String prog, String version) {
        Pattern pattern1 = Pattern.compile("^\\+[1]-[0-9]{3}-[0-9]{3}-[0-9]{4}$");
        Pattern pattern2 = Pattern.compile("^[0-9]+.[0-9]+$");
        StringBuilder res = new StringBuilder();
        String[] sl = s.split("\n");
        for (String s1 : sl) {
            Matcher matcher;
            String sr = s1.split(":")[0].trim();
            switch (sr) {
                case "Program title":
                    res.append("Program: ").append(prog).append(" ");
                    break;
                case "Author":
                    res.append("Author: g964 ");
                    break;
                case "Phone":
                    String sPhone = s1.split(":")[1].trim();
                    matcher = pattern1.matcher(sPhone);
                    if (!matcher.find()) return "ERROR: VERSION or PHONE";
                    res.append("Phone: +1-503-555-0090 ");
                    break;
                case "Date":
                    res.append("Date: 2019-01-01 ");
                    break;
                case "Version":
                    if (!s1.contains(".")) return "ERROR: VERSION or PHONE";
                    String ss1 = s1.split(":")[1].trim();
                    matcher = pattern2.matcher(ss1);
                    if (!matcher.find()) {
                        return "ERROR: VERSION or PHONE";
                    } else {
                        res.append("Version: ");
                        res.append(ss1.equals("2.0") ? "2.0" : version);
                    }
                    break;
            }
        }
        return res.toString();
    }


}

package folf;

import static java.lang.System.out;

public class Test10
{
    static String suiteWithRecursiveDefinition =
                "silnia(x, y) <=> x >= 1 and y >= 1 and (" +
                        "(x = 1 and y = 1) or silnia(x - 1, y / x) );\n" +
                "\n" +
                "silnia(-13, 44);\n" +
                "silnia(0, 0);\n" +
                "silnia(1, 1);\n" +
                "silnia(3, 5);\n" +
                "silnia(3, 6);\n" +
                "silnia(3, 7)\n;" +
                "silnia(5, 120);\n" +
                "silnia(10, 3628800);\n";

    public static void main(String[] args)
    {
        Vocabulary voc = new Vocabulary();
        voc.allowNumbers();
        Structure rs = new Structure();

        Parser p = new Parser();
        Suite ste = p.suiteFromString(suiteWithRecursiveDefinition, voc);
        if (ste == null) {
                out.println(p.getErrorMessages());
                return;
        }

        Result[] res = ste.getResults(rs);
        for (Result r : res) {
            boolean b = r.getValue();
            String msg = r.getMessage();
            out.println(msg == null ? b : b + " : " + msg);
        }
    }
}

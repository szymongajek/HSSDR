package folf;

import java.util.List;
import java.util.Map;

public class Test09
{
    static Vocabulary voc;
    static Structure rs;

    static class Abs extends AbstractFunction
    {
        public Abs()
        {
            super(1);
        }

        // Tylko dla obiektów będących liczbami - bierze i zwraca Double
        public Double value(List<Object> args)
        {
            if (args.size() != 1)
                throw new IllegalArgumentException("bad arity");
            return Math.abs((Double) args.get(0));
        }
    }

    static void createVocRS()
    {
        Function f;
        Relation r;

        voc = new Vocabulary();
        voc.allowNumbers();
        voc.allowStrings();
        rs = new Structure();

        // kocury i kotki
        Object k1 = new Object();
        rs.addDomainObject(k1);
        Object k2 = new Object();
        rs.addDomainObject(k2);
        Object k3 = new Object();
        rs.addDomainObject(k3);

        voc.addFunction("name", 1);
        f = new TabularFunction(1);
        f.add(k1, "Mruczek");
        f.add(k2, "Szarka");
        f.add(k3, "Filemon");
        rs.addFunction("name", f);

        voc.addRelation("Kocury", 1);
        r = new UnaryTabularRelation();
        r.add(k1);
        r.add(k3);
        rs.addRelation("Kocury", r);

        voc.addRelation("Kotki", 1);
        r = new UnaryTabularRelation();
        r.add(k2);
        rs.addRelation("Kotki", r);

        voc.addFunction("age", 1);
        f = new TabularFunction(1);
        f.add(k1, Double.valueOf(2));
        f.add(k2, Double.valueOf(3));
        f.add(k3, Double.valueOf(7));
        rs.addFunction("age", f);

        voc.addFunction("abs", 1);
        rs.addFunction("abs", new Abs());
    }

    static void printResult(Result r)
    {
        boolean b = r.isTrue();
        String msg = r.getMessage();
        System.out.println(msg == null ? b : b + " : " + msg);
        if (r.getQVarsState() != null) {
            for (int i = 0; ; ++i) {
                Map<String, Object> qvars = r.getQVarsState(i);
                if (qvars == null)
                    break;
                for (String name : qvars.keySet())
                    System.out.print(name + "=" + qvars.get(name) + " ");
                System.out.print("; ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        createVocRS();
        Parser p = new Parser();
        for (int no = 1; ; ++no) {
            String fname = "test09." + no;
            System.out.println("Processing " + fname + "...");
            Suite su = p.suiteFromFile(fname, voc);
            if (su == null) {
                System.out.println("syntax or file error!");
                String msg = p.getErrorMessages();
                if (msg != null)
                    System.out.print(msg);
                break;
            }
            Result[] res = su.getCompleteResults(rs, 99);
            for (int i = 0; i < res.length; ++i) {
                System.out.print("#" + (i+1) + ": ");
                printResult(res[i]);
            }
            System.out.println();
        }
    }
}

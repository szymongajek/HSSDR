package folf;

import java.util.Map;

public class Test07
{
    static Vocabulary voc;
    static Structure rs;

    static void createModuloExample(int modulo)
    {
        voc = new Vocabulary();
        rs = new Structure();
        int i, j;
        TabularFunction f_add, f_sub, f_mul;
        TabularRelation r_lt, r_le, r_gt, r_ge;
        Object[] arr2 = new Object[2];

        for (i = 0; i < modulo; ++i) {
            rs.addDomainObject(Integer.valueOf(i));
            voc.addConstant("n" + i);
            rs.createConstant("n" + i, Integer.valueOf(i));
        }

        voc.addFunction("add", 2);
        f_add = new TabularFunction(2);
        rs.addFunction("add", f_add);
        voc.addFunction("sub", 2);
        f_sub = new TabularFunction(2);
        rs.addFunction("sub", f_sub);
        voc.addFunction("mul", 2);
        f_mul = new TabularFunction(2);
        rs.addFunction("mul", f_mul);
        for (i = 0; i < modulo; ++i) {
            for (j = 0; j < modulo; ++j) {
                arr2[0] = Integer.valueOf(i);
                arr2[1] = Integer.valueOf(j);
                f_add.add(arr2, Integer.valueOf((i + j) % modulo));
                f_sub.add(arr2, Integer.valueOf((modulo + i - j) % modulo));
                f_mul.add(arr2, Integer.valueOf((i * j) % modulo));
            }
        }

        voc.addRelation("lt", 2);
        r_lt = new TabularRelation(2);
        rs.addRelation("lt", r_lt);
        voc.addRelation("le", 2);
        r_le = new TabularRelation(2);
        rs.addRelation("le", r_le);
        voc.addRelation("gt", 2);
        r_gt = new TabularRelation(2);
        rs.addRelation("gt", r_gt);
        voc.addRelation("ge", 2);
        r_ge = new TabularRelation(2);
        rs.addRelation("ge", r_ge);
        for (i = 0; i < modulo; ++i) {
            for (j = 0; j < modulo; ++j) {
                arr2[0] = Integer.valueOf(i);
                arr2[1] = Integer.valueOf(j);
                if (i < j) r_le.add(arr2);
                if (i <= j) r_lt.add(arr2);
                if (i > j) r_gt.add(arr2);
                if (i >= j) r_ge.add(arr2);
            }
        }
    }

    static void printResult(Result r)
    {
        boolean b = r.getValue();
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
        createModuloExample(7);
        Parser p = new Parser();
        for (int no = 1; no < 5; ++no) {
            String fname = "test07." + no;
            System.out.println("Processing " + fname + "...");
            Suite su = p.suiteFromFile(fname, voc);
            if (su == null) {
                System.out.println("syntax or file error!");
                String msg = p.getErrorMessages();
                if (msg != null)
                    System.out.print(msg);
                System.out.println();
                continue;
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

package folf;

import java.util.Map;

public class Test05
{
    static Vocabulary voc;
    static Structure rs;

    static void createMod7()
    {
        voc = new Vocabulary();
        rs = new Structure();
        int i, j;
        TabularFunction f_add, f_sub, f_mul;
        Object[] arr2 = new Object[2];

        for (i = 0; i < 7; ++i) {
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
        for (i = 0; i < 7; ++i) {
            for (j = 0; j < 7; ++j) {
                arr2[0] = Integer.valueOf(i);
                arr2[1] = Integer.valueOf(j);
                f_add.add(arr2, Integer.valueOf((i + j) % 7));
                f_sub.add(arr2, Integer.valueOf((7 + i - j) % 7));
                f_mul.add(arr2, Integer.valueOf((i * j) % 7));
            }
        }
    }

    static String inputs[] = {
        "add(n2, n2) = n4",
        "mul(n4, n4) = n2",
        "add(n2, n2) = 4",
        "exists x : add(x, n2) = n4",
        "exists y : add(n1, n1) = n3",
        "forall x : exists y : sub(x, y) = n4",
        "forall x : exists y : mul(x, y) = n1",
        "exists x,y: add(x,y)=n4",
    };

    public static void main(String[] args)
    {
        createMod7();
        Parser p = new Parser();
        for (String src : inputs) {
            System.out.print(src);
            System.out.print(" : ");
            Sentence sen = p.sentenceFromString(src, voc);
            if (sen == null) {
                System.out.println("syntax error!");
                String msg = p.getErrorMessages();
                if (msg != null)
                    System.out.print(msg);
                continue;
            }
            Result res = sen.getResult(rs);
            System.out.print(res.getValue());
            Map<String, Object> qvars = res.getQVarsState();
            if (qvars != null)
                for (String name : qvars.keySet())
                    System.out.print(" " + name + "=" + qvars.get(name));
            System.out.println();
            System.out.println(sen.formula);
        }
    }
}

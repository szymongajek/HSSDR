package folf;

public class Test04
{
    static Vocabulary createExampleVocabulary()
    {
        Vocabulary voc = new Vocabulary();
        for (int i = 1; i <= 9; ++i) {
            voc.addRelation("r" + i, i);
            voc.addFunction("f" + i, i);
        }
        voc.addConstant("c");
        return voc;
    }

    static String inputs[] = {
        "exists x1, y1, y2 : r1(x1) or r2(y1, y2) or r1(c)",
        "exists x1, y1, y2 : r1(x1) or (r2(y1, y2) or r1(c))",
        "exists x1, y1, y2 : r1(x1) or r4(y1, y2) or r1(c)",
        "exists x1, y1, y2 : r1(x1) or r2(y1, y2) or r3(c)",
        "r1(x1) or r2(y1, y2) or r1(c)",
    };

    public static void main(String[] args)
    {
        Vocabulary voc = createExampleVocabulary();
        Parser p = new Parser();
        for (String s : inputs) {
            System.out.println(s);
            Sentence sen = p.sentenceFromString(s, voc);
            if (sen != null) {
                System.out.println(sen.formula);
                System.out.println();
            } else
                System.out.println(p.getErrorMessages());
        }
    }
}

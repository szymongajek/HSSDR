package folf;

public class Test08
{
    static Vocabulary voc;
    static Structure rs;

    static String inputs[] = {
        "2 = 2",
        "2 != 2",
        "2 < 2",
        "2 < 3",
        "2 + 2 = --4",
        "3/2 = 1.5",

        "\"ala\" = \"ala\"",
        "\"ala\" = \"ola\"",
        "\"ala\" + 2 = \"ola\"",    // to ma zgłosić błąd
    };

    public static void main(String[] args)
    {
        voc = new Vocabulary();
        voc.allowNumbers();
        voc.allowStrings();
        rs = new Structure();

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
            System.out.println(res.isTrue());
            System.out.println(sen.formula);
        }
    }
}

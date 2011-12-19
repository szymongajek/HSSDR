package folf;

public class Test03
{
    static void przeciazona(java.util.List<Object> args)
    {
        System.out.println("W wersji listowej, " + args.get(0));
    }

    static void przeciazona(Object... args)
    {
        System.out.println("W wersji vararg, " + args[0]);
    }

    public static void main(String[] args)
    {
        java.util.ArrayList<Object> l1 = new java.util.ArrayList<Object>();
        l1.add("lista raz");
        Object[] a1 = new Object[] { "tablica raz" };

        przeciazona(l1);
        przeciazona(a1);
        przeciazona("vararg raz");

        java.util.ArrayList<Object> l2 = new java.util.ArrayList<Object>();
        l2.add("lista dwa");
        l2.add("dwa");
        Object[] a2 = new Object[] { "tablica dwa", "dwa" };

        przeciazona(l2);
        przeciazona(a2);
        przeciazona("vararg dwa", "dwa");
    }
}

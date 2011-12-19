package folf;

import java.util.ArrayList;

public class Test01
{
    public static void main(String args[]) throws Exception
    {
        String h1 = new  String("h1");
        String h2 = new  String("h2");
        String h3 = new  String("h3");

        String[] a1 = new String[] {h1,h2,h3};
        String[] x1 = new String[] {h1};
        ArrayList <String>   x2 = new ArrayList<String>();
        x2.add(h2);

        TabularRelation tr = new TabularRelation(1);

//      tr.add(a1);
        tr.add(x1);
        tr.add(x2);

        String[] a2 = new String[] {h1,h2,h3};

//      System.out.println("cont a1 " + tr.value(a1));
//      System.out.println("cont a2 " + tr.value(a2));

        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Object o4 = new Object();
        Object[] a;

        TabularRelation r = new TabularRelation(2);

        a = new Object[2];
        a[0] = o1; a[1] = o3; r.add(a);
        a[0] = o2; a[1] = o4; r.add(a);

        a[0] = o1; a[1] = o2;
        System.out.println("1,2 -> " + r.value(a));
        a[0] = o1; a[1] = o3;
        System.out.println("1,3 -> " + r.value(a));

        a = new Object[2];
        a[0] = o1; a[1] = o2;
        System.out.println("1,2 -> " + r.value(a));
        a[0] = o1; a[1] = o3;
        System.out.println("1,3 -> " + r.value(a));

        a = new Object[3];
        a[0] = o2; a[1] = o3; a[2] = o4;
//      System.out.println("2,3,4 -> " + r.value(a));

        TabularFunction f = new TabularFunction(1);

        a = new Object[1];
        a[0] = o1; f.add(a, "pierwszy");
        a[0] = o2; f.add(a, "drugi");
        a[0] = o3; f.add(a, "trzeci");

        a[0] = o1;
        System.out.println("1 -> " + f.value(a));
        a[0] = o3;
        System.out.println("3 -> " + f.value(a));
        a[0] = o4;
//      System.out.println("4 -> " + f.value(a));
    }
}

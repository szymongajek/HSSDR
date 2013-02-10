package folf;

import java.util.*;

public class Test02
{
    static class StringKrotszyOdDrugiego extends AbstractRelation
    {
        public StringKrotszyOdDrugiego()
        {
            super(2);
        }

        public boolean value(List<Object> args)
        {
            Object o1 = args.get(0), o2 = args.get(1);
            return (o1 instanceof String) && (o2 instanceof String)
                && (((String) o1).length() < ((String) o2).length());
        }
    }

    public static void main(String[] args)
    {
        // Przykład ładuje do struktury dane z fig. 1 w artykule
        Object pokoje[] = { "kuchnia", "lazienka", "hol", "pokoj" };
        Object drzwi[] = { "k-h", "l-h", "h-p" };

        Object arr1[] = new Object[1];
        Object arr2[] = new Object[2];

        Structure rs = new Structure();

        TabularRelation Rooms = new TabularRelation(1);
        rs.addRelation("Rooms", Rooms);
        for (int i = 0; i < pokoje.length; ++i) {
            rs.addDomainObject(pokoje[i]);
            arr1[0] = pokoje[i];
            Rooms.add(arr1);
        }

        UnaryTabularRelation Doors = new UnaryTabularRelation();
        rs.addRelation("Doors", Doors);
        for (int i = 0; i < drzwi.length; ++i) {
            rs.addDomainObject(drzwi[i]);
            Doors.unaryAdd(drzwi[i]);
        }

        TabularRelation accessible = new TabularRelation(2);
        rs.addRelation("accessible", accessible);
        arr2[0] = pokoje[0]; arr2[1] = pokoje[2];
        accessible.add(arr2);
        arr2[0] = pokoje[2]; arr2[1] = pokoje[0];
        accessible.add(arr2);
        arr2[0] = pokoje[1]; arr2[1] = pokoje[2];
        accessible.add(arr2);
        arr2[0] = pokoje[2]; arr2[1] = pokoje[1];
        accessible.add(arr2);
        arr2[0] = pokoje[2]; arr2[1] = pokoje[3];
        accessible.add(arr2);
        arr2[0] = pokoje[3]; arr2[1] = pokoje[2];
        accessible.add(arr2);

        rs.addRelation("customRel", new StringKrotszyOdDrugiego());

        TabularFunction pokojNaLewoLubNaDol = new TabularFunction(1);
        rs.addFunction("pokojNaLewoLubNaDol", pokojNaLewoLubNaDol);
        arr1[0] = drzwi[0];
        pokojNaLewoLubNaDol.add(arr1, pokoje[0]);
        arr1[0] = drzwi[1];
        pokojNaLewoLubNaDol.add(arr1, pokoje[1]);
        pokojNaLewoLubNaDol.add(Arrays.asList(drzwi[2]), pokoje[2]);

        System.out.println("Rooms(pok0) -> " +
                rs.relationValue("Rooms", new Object[] { pokoje[0] }));
        System.out.println("Rooms(drz2) -> " +
                rs.relationValue("Rooms", new Object[] { drzwi[2] }));

        arr1[0] = pokoje[1];
        System.out.println("Doors(pok1) -> " +
                rs.relationValue("Doors", arr1));
        arr1[0] = drzwi[1];
        System.out.println("Doors(drz1) -> " +
                rs.relationValue("Doors", arr1));

        System.out.println("customRel(pok2, pok3) -> " +
                rs.relationValue("customRel",
                        Arrays.asList(pokoje[2], pokoje[3])));
        System.out.println("customRel(pok3, pok2) -> " +
                rs.relationValue("customRel",
                        Arrays.asList(pokoje[3], pokoje[2])));

        System.out.println("pokojNaLewoLubNaDol(drz0) -> " +
                rs.functionValue("pokojNaLewoLubNaDol",
                        Arrays.asList(drzwi[0])));
        System.out.println("pokojNaLewoLubNaDol(drz1) -> " +
                rs.functionValue("pokojNaLewoLubNaDol",
                        new Object[] { drzwi[1] }));
        arr1[0] = drzwi[2];
        System.out.println("pokojNaLewoLubNaDol(drz2) -> " +
                rs.functionValue("pokojNaLewoLubNaDol", arr1));
    }
}

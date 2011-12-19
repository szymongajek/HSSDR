package folf;

import java.util.*;

/**
 * Implementacja relacji poprzez jej stablicowanie.
 * Świeżo po skonstruowaniu jest ona pusta (tzn. będzie zwracać false
 * dla wszystkich możliwych argumentów), trzeba ją wypełnić danymi
 * wywołując wielokrotnie metodę add().
 * Dopiero potem warto wysyłać do niej zapytania przy pomocy value().
 */
public class TabularRelation extends AbstractRelation
{
    HashSet<List<Object>> members = new HashSet<List<Object>>();

    public TabularRelation(int arity)
    {
        super(arity);
    }

    public boolean value(List<Object> args)
    {
        if (args.size() != arity)
            throw new IllegalArgumentException("bad arity");
        return members.contains(args);
    }

    public void add(List<Object> args)
    {
        if (args.size() != arity)
            throw new RuntimeException("bad arity");
        members.add(new ArrayList<Object>(args));
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (List<Object> lst : members) {
            for (Object o : lst) {
                sb.append(o);
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}

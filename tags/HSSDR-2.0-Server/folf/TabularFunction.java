package folf;

import java.util.*;

/**
 * Implementacja funkcji ze stablicowanymi wartościami.
 * Świeżo po skonstruowaniu jest ona pusta (będzie rzucać wyjątkiem
 * "undefined"), trzeba ją zdefiniować wywołując metodę add() dla
 * wszystkich możliwych kombinacji argumentów.
 */
public class TabularFunction extends AbstractFunction
{
    HashMap<List<Object>, Object> values =
        new HashMap<List<Object>, Object>();

    public TabularFunction(int arity)
    {
        super(arity);
    }

    public Object value(List<Object> args)
    {
        if (args.size() != arity)
            throw new IllegalArgumentException("bad arity");
        Object o = values.get(args);
        if (o == null)
            throw new IllegalArgumentException("undefined");
        return o;
    }

    public void add(List<Object> args, Object value)
    {
        if (args.size() != arity)
            throw new RuntimeException("bad arity");
        values.put(new ArrayList<Object>(args), value);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (List<Object> lst : values.keySet()) {
            for (Object o : lst) {
                sb.append(o);
                sb.append(' ');
            }
            sb.append("-> ");
            sb.append(values.get(lst));
            sb.append('\n');
        }
        return sb.toString();
    }
}

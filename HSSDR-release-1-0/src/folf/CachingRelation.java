package folf;

import java.util.*;

/**
 * Klasa pomocnicza pozwalająca stablicować wyniki jakiejś innej relacji
 * i w ten sposób przyspieszyć działanie programu (zakładamy, że ta inna
 * relacja jest kosztowna obliczeniowo).
 */
public class CachingRelation extends AbstractRelation
{
    private Relation relation;

    private HashMap<List<Object>, Boolean> cache =
                new HashMap<List<Object>, Boolean>();

    public CachingRelation(Relation relation)
    {
        super(relation.getArity());
        this.relation = relation;
    }

    public boolean value(List<Object> args)
    {
        Boolean bo = cache.get(args);
        if (bo != null)
            return bo.booleanValue();
        boolean b = relation.value(args);
        cache.put(new ArrayList<Object>(args), Boolean.valueOf(b));
        return b;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (List<Object> lst : cache.keySet()) {
            if (cache.get(lst)) {
                for (Object o : lst) {
                    sb.append(o);
                    sb.append(' ');
                }
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}

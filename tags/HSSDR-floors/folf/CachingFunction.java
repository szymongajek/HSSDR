package folf;

import java.util.*;

/**
 * Klasa pomocnicza pozwalająca stablicować wyniki jakiejś innej funkcji
 * i w ten sposób przyspieszyć działanie programu (zakładamy, że ta inna
 * funkcja jest kosztowna obliczeniowo).
 */
public class CachingFunction extends AbstractFunction
{
    private Function function;

    private HashMap<List<Object>, Object> cache =
                new HashMap<List<Object>, Object>();

    public CachingFunction(Function function)
    {
        super(function.getArity());
        this.function = function;
    }

    public Object value(List<Object> args)
    {
        Object obj = cache.get(args);
        if (obj != null)
            return obj;
        obj = function.value(args);
        cache.put(new ArrayList<Object>(args), obj);
        return obj;
    }
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (List<Object> lst : cache.keySet()) {
                for (Object o : lst) {
                sb.append(o);
                sb.append(' ');
            }
            sb.append("-> ");
            sb.append(cache.get(lst));
            sb.append('\n');
        }
        return sb.toString();
    }
}

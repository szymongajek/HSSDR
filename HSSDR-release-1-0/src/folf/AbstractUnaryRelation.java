package folf;

import java.util.List;

public abstract class AbstractUnaryRelation
    extends AbstractRelation implements UnaryRelation
{
    public AbstractUnaryRelation()
    {
        super(1);
    }

    /**
     * Convenience method.
     * Wyłuskuje obiekt z jednoelementowej listy i wywołuje
     * {@link #unaryValue(java.lang.Object)}.
     */
    public boolean value(List<Object> args)
    {
        if (args.size() != 1)
            throw new IllegalArgumentException("bad arity");
        return unaryValue(args.get(0));
    }

    public void unaryAdd(Object arg)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Convenience method.
     * Wyłuskuje obiekt z jednoelementowej listy i wywołuje
     * {@link #unaryAdd(java.lang.Object)}.
     */
    public void add(List<Object> args)
    {
        if (args.size() != 1)
            throw new IllegalArgumentException("bad arity");
        unaryAdd(args.get(0));
    }
}

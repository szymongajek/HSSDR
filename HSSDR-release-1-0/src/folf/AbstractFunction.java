package folf;

import java.util.*;

/**
 * Klasa bazowa do wyprowadzania swoich własnych funkcji.
 * Trzeba nadpisać {@link #value(java.util.List)} swoją własną implementacją,
 * a jeśli potrzebujesz również {@link #add(java.util.List, Object)}.
 */
public abstract class AbstractFunction implements Function
{
    public final static List<Object> emptyArgs = new ArrayList<Object>(0);

    protected int arity;

    public AbstractFunction(int arity)
    {
        this.arity = arity;
    }

    public int getArity()
    {
        return arity;
    }

    public Object value(Object... args)
    {
        return value(Arrays.asList(args));
    }

    public Object value()
    {
        return value(emptyArgs);
    }

    public void add(List<Object> args, Object value)
    {
        throw new UnsupportedOperationException();
    }

    public void add(Object[] args, Object value)
    {
        add(Arrays.asList(args), value);
    }

    public void add(Object... args_and_value)
    {
        int len = args_and_value.length - 1;
        ArrayList<Object> args = new ArrayList<Object>(len);
        for (int i = 0; i < len; ++i)
            args.add(args_and_value[i]);
        add(args, args_and_value[len]);
    }

    public void set(Object value)
    {
        add(emptyArgs, value);
    }
}

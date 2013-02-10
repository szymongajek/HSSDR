package folf;

import java.util.Arrays;
import java.util.List;

/**
 * Klasa bazowa do wyprowadzania swoich własnych relacji.
 * Trzeba nadpisać {@link #value(java.util.List)} swoją własną implementacją,
 * a jeśli potrzebujesz również {@link #add(java.util.List)}.
 */
public abstract class AbstractRelation implements Relation
{
    protected int arity;

    public AbstractRelation(int arity)
    {
        this.arity = arity;
    }

    public int getArity()
    {
        return arity;
    }

    public boolean value(Object... args)
    {
        return value(Arrays.asList(args));
    }

    public void add(List<Object> args)
    {
        throw new UnsupportedOperationException();
    }

    public void add(Object... args)
    {
        add(Arrays.asList(args));
    }
}

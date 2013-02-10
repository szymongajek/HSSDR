package folf;

import java.util.ArrayList;
import java.util.List;

/**
 * Wersja specjalizowana - dwuargumentowa symetryczna.
 * Przy dodawaniu (x, y) dodaje również automatycznie (y, x).
 */
public class SymmetricTabularRelation extends TabularRelation
{
    public SymmetricTabularRelation()
    {
        super(2);
    }

    public void add(List<Object> args)
    {
        super.add(args);
        ArrayList<Object>  sym =   new ArrayList<Object>();
        sym.add(args.get(1));
        sym.add(args.get(0));
        super.add(sym );
    }
}

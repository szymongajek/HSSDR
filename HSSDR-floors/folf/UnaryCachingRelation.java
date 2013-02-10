package folf;

import java.util.*;

/**
 * Klasa pomocnicza pozwalająca stablicować wyniki jakiejś innej relacji
 * unarnej i w ten sposób przyspieszyć działanie programu (zakładamy, że ta
 * inna relacja jest kosztowna obliczeniowo).
 * Dodatkowo zapewnia efektywną implementację kwantyfikatorów na zbiorze
 * reprezentowanym przez tę relację.
 */
public class UnaryCachingRelation extends AbstractUnaryRelation
{
    private UnaryRelation relation;

    private HashSet<Object> membersCache = new HashSet<Object>();
    private HashSet<Object> notMembersCache = new HashSet<Object>();
    private boolean cacheIsFilled = false;

    UnaryCachingRelation(UnaryRelation relation)
    {
        if (relation.getArity() != 1)
            throw new IllegalArgumentException("bad arity");
        this.relation = relation;
    }

    /** Pomocnicza metoda dla klasy Structure. */
    Iterator<Object> subsetIter(Iterator<Object> domainIter)
    {
        if (! cacheIsFilled) {
            while (domainIter.hasNext()) {
                Object arg = domainIter.next();
                if (relation.unaryValue(arg))
                    membersCache.add(arg);
            }
            cacheIsFilled = true;
            notMembersCache = null;
        }
        return membersCache.iterator();
    }

    public boolean unaryValue(Object arg)
    {
        if (cacheIsFilled)
            return membersCache.contains(arg);
        if (membersCache.contains(arg))
            return true;
        else if (notMembersCache.contains(arg))
            return false;
        else {
            boolean b = relation.unaryValue(arg);
            if (b)
                membersCache.add(arg);
            else
                notMembersCache.add(arg);
            return b;
        }
    }
}

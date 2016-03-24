package folf;

import java.util.HashSet;

/**
 * Implementacja relacji poprzez jej stablicowanie, w specjalizowanej
 * wersji dla relacji jednoargumentowych.
 * Szybsze, zajmuje mniej miejsca w pamięci, i pozwala na efektywne
 * wykorzystanie relacji jako równoważnika podzbioru domeny.
 */
public class UnaryTabularRelation extends AbstractUnaryRelation
{
    protected HashSet<Object> members = new HashSet<Object>();

    public boolean unaryValue(Object arg)
    {
        return members.contains(arg);
    }

    public void unaryAdd(Object arg)
    {
        members.add(arg);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Object o : members) {
            sb.append(o);
            sb.append('\n');
        }
        return sb.toString();
    }
}

package folf;

import java.util.*;
import java.util.regex.*;

/**
 * Słownik z symbolami, które mogą wystąpić w formule logicznej.
 * Używane przy parsowaniu formuł; jest też metoda sprawdzająca, czy dana
 * struktura relacyjna jest kompatybilna ze słownikiem (tzn. czy struktura
 * dostarcza interpretacji dla wszystkich symboli ze słownika).
 */
public class Vocabulary
{
    private static final Pattern id_re =
        Pattern.compile("[a-zA-Z][a-zA-Z0-9_]*");

    private static class Pair
    {
        int type, arity;

        Pair(int type, int arity)
        {
            this.type = type;
            this.arity = arity;
        }
    }

    public static final int UNKNOWN = -1;
    public static final int RELATION = 1;
    public static final int FUNCTION = 2;
    public static final int CONSTANT = 3;

    private HashMap<String, Pair> symbols;
    private boolean numbersAllowed;
    private boolean stringsAllowed;

    public Vocabulary()
    {
        symbols = new HashMap<String, Pair>();
        numbersAllowed = false;
        stringsAllowed = false;
    }

    public Vocabulary(Vocabulary voc)
    {
        symbols = new HashMap<String, Pair>(voc.symbols);
        numbersAllowed = voc.numbersAllowed;
        stringsAllowed = voc.stringsAllowed;
    }

    public int getType(String name)
    {
        Pair p = symbols.get(name);
        if (p == null)
            return UNKNOWN;
        return p.type;
    }

    public int getArity(String name)
    {
        Pair p = symbols.get(name);
        if (p == null)
            throw new IllegalArgumentException("unknown arity:"+name);
        return p.arity;
    }

    private void add(String name, Pair pair)
    {
        if (name == null || ! id_re.matcher(name).matches())
            throw new IllegalArgumentException("bad symbol name");
        if (symbols.containsKey(name))
            throw new IllegalArgumentException("duplicate symbol");
        symbols.put(name, pair);
    }

    public void addRelation(String name, int arity)
    {
        add(name, new Pair(RELATION, arity));
    }

    public void addFunction(String name, int arity)
    {
        if (arity < 1)
            throw new IllegalArgumentException("bad arity");
        add(name, new Pair(FUNCTION, arity));
    }

    public void addConstant(String name)
    {
        add(name, new Pair(CONSTANT, 0));
    }

    /**
     * Czy oprócz symboli zdeiniowanych w słowniku formuły mogą zawierać
     * literały i operatory liczbowe?
     */
    public boolean areNumbersAllowed()
    {
        return numbersAllowed;
    }

    public void allowNumbers()
    {
        numbersAllowed = true;
    }

    /** Czy formuły mogą zawierać literały łańcuchowe? */
    public boolean areStringsAllowed()
    {
        return stringsAllowed;
    }

    public void allowStrings()
    {
        stringsAllowed = true;
    }

    public boolean isCompatible(Structure rs)
    {
        for (Map.Entry<String, Pair> e: symbols.entrySet()) {
            String name = e.getKey();
            Object o = rs.rels_and_funcs.get(name);
            if (o == null)
                return false;
            Pair p = e.getValue();
            switch (p.type) {
                case RELATION:
                    if (! (o instanceof Relation))
                        return false;
                    if (((Relation) o).getArity() != p.arity)
                        return false;
                    break;
                case FUNCTION:
                case CONSTANT:
                    if (! (o instanceof Function))
                        return false;
                    if (((Function) o).getArity() != p.arity)
                        return false;
                    break;
            }
        }
        return true;
    }
}

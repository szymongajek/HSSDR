package folf;

import java.util.*;

/**
 * Struktura relacyjna, zwana też czasem modelem.
 * Zawiera:
 * <ul>
 * <li> zbiór obiektów tworzących jej domenę;
 * <li> relacje o rozmaitej arności;
 * <li> funkcje o rozmaitej arności;
 * <li> stałe (czyli funkcje o arności 0).
 * </ul>
 */
public class Structure
{
    private static java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger("folf.Structure");

    HashSet<Object> domain;
    HashMap<String, Object> rels_and_funcs;

    public Structure()
    {
        logger.finer("constructor this=" + this.hashCode());
        domain = new HashSet<Object>();
        rels_and_funcs = new HashMap<String, Object>();
    }

    public Structure(Structure rs)
    {
        logger.finer("constructor this=" + this.hashCode() +
                " copy_of=" + rs.hashCode());
        domain = new HashSet<Object>(rs.domain);
        rels_and_funcs = new HashMap<String, Object>(rs.rels_and_funcs);
    }



    public void addDomainObject(Object obj)
    {
        domain.add(obj);
    }

    public Iterator<Object> domainIterator()
    {
        return domain.iterator();
    }

    public Iterator<Object> domainSubsetIterator(String name)
    {
        Relation r = (Relation) rels_and_funcs.get(name);
        if (r instanceof UnaryTabularRelation)
            return ((UnaryTabularRelation) r).members.iterator();
        if (r instanceof UnaryCachingRelation)
            return ((UnaryCachingRelation) r).subsetIter(domain.iterator());
        if (r.getArity() != 1)
            throw new IllegalArgumentException("not an arity 1 relation");
        return new SubsetIterator(r);
    }

    private class SubsetIterator implements Iterator<Object>
    {
        Relation subsetRel;
        Iterator<Object> domainIter;
        Object nextObject;
        ArrayList<Object> arg1;

        SubsetIterator(Relation r)
        {
            subsetRel = r;
            domainIter = domainIterator();
            arg1 = new ArrayList<Object>(1);
            arg1.add(null);
            advance();
        }

        private void advance()
        {
            while (domainIter.hasNext()) {
                nextObject = domainIter.next();
                arg1.set(0, nextObject);
                if (subsetRel.value(arg1))
                    return;
            }
            nextObject = null;
        }

        public boolean hasNext()
        {
            return nextObject != null;
        }

        public Object next()
        {
            if (nextObject == null)
                throw new NoSuchElementException();
            Object o = nextObject;
            advance();
            return o;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }



    public void addRelation(String name, Relation r)
    {
        rels_and_funcs.put(name, r);
        logger.fine("this=" + this.hashCode()
                + " " + name + ": " + r.hashCode());
    }

    public Relation getRelation(String name)
    {
        return (Relation) rels_and_funcs.get(name);
    }

    public void addFunction(String name, Function f)
    {
        rels_and_funcs.put(name, f);
        logger.fine("this=" + this.hashCode()
                + " " + name + ": " + f.hashCode());
    }

    public Function getFunction(String name)
    {
        return (Function) rels_and_funcs.get(name);
    }

    public void createConstant(String name, Object value)
    {
        TabularFunction f = new TabularFunction(0);
        f.set(value);
        rels_and_funcs.put(name, f);
        logger.fine("this=" + this.hashCode()
                + " " + name + ": " + f.hashCode());
    }



    public boolean relationValue(String name, List<Object> args)
    {
        Relation r = (Relation) rels_and_funcs.get(name);
        return r.value(args);
    }

    public boolean relationValue(String name, Object... args)
    {
        Relation r = (Relation) rels_and_funcs.get(name);
        return r.value(args);
    }

    public Object functionValue(String name, List<Object> args)
    {
        Function f = (Function) rels_and_funcs.get(name);
        return f.value(args);
    }

    public Object functionValue(String name, Object... args)
    {
        Function f = (Function) rels_and_funcs.get(name);
        return f.value(args);
    }

    public Object constantValue(String name)
    {
        Function f = (Function) rels_and_funcs.get(name);
        return f.value();
    }



    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (String key : rels_and_funcs.keySet())
        {
            sb.append("Name: ");
            sb.append(key);
            sb.append(" Values:\n");
            sb.append(rels_and_funcs.get(key));
        }
        return sb.toString();
    }
}

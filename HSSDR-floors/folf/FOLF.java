package folf;

import java.util.*;

/**
 * Formuła logiczna pierwszego rzędu.
 * Zawiera drzewko reprezentujące formułę, listę użytych zmiennych, itp.
 * Formułę-zdanie można zwartościować w określonym modelu, podczas tego
 * procesu zmiennym kwantyfikowanym będą przypisywane nowe wartości.
 */
public class FOLF
{
    private static java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger("folf.FOLF");

    protected Formula formula;
    protected List<Variable> freeVariables;
    protected List<Variable> allVariables;

    // tymczasowo tutaj na czas testów
    protected String successMsg, failureMsg;

    protected FOLF()
    { }

    protected boolean getValue(Structure rs)
    {
        logger.finer("entry this=" + this.hashCode() +
                " rs=" + rs.hashCode());
        boolean b = formula.value(new ValuationContext(rs));
        logger.finer("return this=" + this.hashCode() +
                " rs=" + rs.hashCode() + " result=" + b);
        return b;
    }

    protected Result getResult(Structure rs)
    {
        ValuationContext ctx = new ValuationContext(rs);
        boolean b = formula.value(ctx);
        Map<String, Object> qv = null;
        if (formula instanceof Quantifier) {
            Quantifier q = (Quantifier) formula;
            if ((q.type == EXISTENTIAL) == b)
                qv = q.makeVarStateMap(ctx);
        }
        return new Result(b, null, qv, null);
    }

    protected Result getCompleteResult(Structure rs, int limit)
    {
        boolean b;
        String msg = null;
        Map<String, Object> qv = null;
        List<Map<String, Object>> qv_states = null;

        ValuationContext ctx = new ValuationContext(rs);
        if (formula instanceof Quantifier) {
            Quantifier q = (Quantifier) formula;
            qv_states = new ArrayList<Map<String, Object>>();
            b = q.valueAndStates(ctx, qv_states, limit);
            if ((q.type == EXISTENTIAL) == b)
                qv = qv_states.get(0);
            else    // kwantyfikator wyczerpał wszystkie obiekty i nic
                qv_states = null;
        } else {
            b = formula.value(ctx);
        }
        msg = b ? successMsg : failureMsg;
        return new Result(b, msg, qv, qv_states);
    }

    /**
     * Kontekst wartościowania formuły.
     * Przechowuje referencję do struktury relacyjnej na której odbywa się
     * wartościowanie, bieżące wartości zmiennych, i co tam jeszcze jest
     * potrzebne.
     */
    class ValuationContext
    {
        Structure rs;
        Map<Variable, Object> values;
        // nie można użyć mapowania String->Object, bo możemy mieć kilka
        // zmiennych tak samo się nazywających

        // dla kwantyfikatorów:
        Map<Variable, Iterator> iters;
        Map<Quantifier, Integer> qstate;

        ValuationContext(Structure rs)
        {
            this.rs = rs;
            values = new HashMap<Variable, Object>(2 * allVariables.size());
            iters = new HashMap<Variable, Iterator>(2 * allVariables.size());
            qstate = new HashMap<Quantifier, Integer>();
        }
    }

    // ---- klasy-węzły drzewa reprezentującego formułę ----

    protected static final int ADD = 11;
    protected static final int SUB = 12;
    protected static final int MUL = 13;
    protected static final int DIV = 14;
    protected static final int UMINUS = 15;

    protected static final int EQUAL = 21;
    protected static final int NOT_EQUAL = 22;
    protected static final int LESS_THAN = 23;
    protected static final int LESS_OR_EQUAL = 24;
    protected static final int GREATER_THAN = 25;
    protected static final int GREATER_OR_EQUAL = 26;

    protected static final int NEGATION = 31;
    protected static final int CONJUNCTION = 32;
    protected static final int DISJUNCTION = 33;
    protected static final int IMPLICATION = 34;
    protected static final int BICONDITIONAL = 35; // equivalence

    protected static final int UNIVERSAL = 41;
    protected static final int EXISTENTIAL = 42;



    protected abstract class Node
    {
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            appendTo(sb);
            return sb.toString();
        }

        abstract protected void appendTo(StringBuilder sb);

        final void append(StringBuilder sb, Node node)
        {
            if (node != null)
                node.appendTo(sb);
            else
                sb.append("null");
        }
    }



    abstract protected class Formula extends Node
    {
        abstract protected boolean value(ValuationContext ctx);
    }



    abstract protected class Term extends Node
    {
        abstract protected Object value(ValuationContext ctx);
    }



    /**
     * Stała liczbowa bądź łańcuchowa podana wprost w formule.
     * Nie wymaga odwoływania się do str. rel. w celu interpretacji.
     */
    protected class Literal extends Term
    {
        Object value;

        protected Literal(Object value)
        {
            this.value = value;
        }

        protected Object value(ValuationContext ctx)
        {
            return value;
        }

        protected void appendTo(StringBuilder sb)
        {
            if (value instanceof Double)
                sb.append(((Double) value).doubleValue());
            else {
                sb.append('"');
                sb.append(value);
                sb.append('"');
            }
        }
    }



    /** Zmienna, mogąca przyjmować różne wartości z domeny str. rel. */
    protected class Variable extends Term
    {
        String name;

        protected Variable(String name)
        {
            this.name = name;
        }

        protected Object value(ValuationContext ctx)
        {
            return ctx.values.get(this);
        }

        protected void set(ValuationContext ctx, Object value)
        {
            ctx.values.put(this, value);
        }

        protected String getName()
        {
            return name;
        }

        protected void appendTo(StringBuilder sb)
        {
            sb.append(name);
        }
    }



    /** Stała ze str. rel. */
    protected class Constant extends Term
    {
        String name;

        protected Constant(String name)
        {
            this.name = name;
        }

        protected Object value(ValuationContext ctx)
        {
            return ctx.rs.constantValue(name);
        }

        protected void appendTo(StringBuilder sb)
        {
            sb.append(name);
        }
    }



    /** Operator liczbowy. */
    protected class Operator extends Term
    {
        int type;
        Term arg1, arg2;

        protected Operator(int type, Term arg1, Term arg2)
        {
            this.type = type;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }

        protected Object value(ValuationContext ctx)
        {
            double r;
            switch (type) {
                case ADD:
                    r = (Double) arg1.value(ctx) + (Double) arg2.value(ctx);
                    break;
                case SUB:
                    r = (Double) arg1.value(ctx) - (Double) arg2.value(ctx);
                    break;
                case MUL:
                    r = (Double) arg1.value(ctx) * (Double) arg2.value(ctx);
                    break;
                case DIV:
                    r = (Double) arg1.value(ctx) / (Double) arg2.value(ctx);
                    break;
                case UMINUS:
                    r = - (Double) arg1.value(ctx);
                    break;
                default:
                    throw new RuntimeException();
            }
            return Double.valueOf(r);
        }

        protected void appendTo(StringBuilder sb)
        {
            sb.append("(op-");
            sb.append(type);
            sb.append(" ");
            append(sb, arg1);
            sb.append(" ");
            append(sb, arg2);
            sb.append(")");
        }
    }



    /** Funkcja ze str. rel. */
    protected class Function extends Term
    {
        String name;
        List<Term> args;

        protected Function(String name, List<Term> args)
        {
            this.name = name;
            this.args = args;
        }

        protected Object value(ValuationContext ctx)
        {
            logger.finer("entry this=" + this.hashCode()
                    + " name=" + name);
            ArrayList<Object> vals = new ArrayList<Object>(args.size());
            for (int i = 0; i < args.size(); ++i)
                vals.add(args.get(i).value(ctx));
            logger.fine("calling this=" + this.hashCode()
                    + " name=" + name + " args=" + vals);
            Object result = ctx.rs.functionValue(name, vals);
            logger.finer("return this=" + this.hashCode()
                    + " name=" + name + " result=" + result.hashCode());
            return result;
        }

        protected void appendTo(StringBuilder sb)
        {
            sb.append("(");
            sb.append(name);
            for (Term arg : args) {
                sb.append(" ");
                append(sb, arg);
            }
            sb.append(")");
        }
    }



    /**
     * Operator porównywania termów.
     * Wszystkie termy można porównywać na identyczność, a liczb dodatkowo
     * na mniejszość/większość.
     */
    protected class Comparator extends Formula
    {
        int type;
        Term arg1, arg2;

        protected Comparator(int type, Term arg1, Term arg2)
        {
            this.type = type;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }

        protected boolean value(ValuationContext ctx)
        {
            Object v1 = arg1.value(ctx), v2 = arg2.value(ctx);
            if (! (v1 instanceof Double && v2 instanceof Double)) {
                switch (type) {
                    case EQUAL:
                        return v1 == v2;
                    case NOT_EQUAL:
                        return v1 != v2;
                    default:
                        throw new RuntimeException();
                }
            } else {
                double d1 = ((Double) v1).doubleValue();
                double d2 = ((Double) v2).doubleValue();
                switch (type) {
                    case EQUAL:
                        return d1 == d2;
                    case NOT_EQUAL:
                        return d1 != d2;
                    case LESS_THAN:
                        return d1 < d2;
                    case LESS_OR_EQUAL:
                        return d1 <= d2;
                    case GREATER_THAN:
                        return d1 > d2;
                    case GREATER_OR_EQUAL:
                        return d1 >= d2;
                    default:
                        throw new RuntimeException();
                }
            }
        }

        protected void appendTo(StringBuilder sb)
        {
            sb.append("(cmp-");
            sb.append(type);
            sb.append(" ");
            append(sb, arg1);
            sb.append(" ");
            append(sb, arg2);
            sb.append(")");
        }
    }



    /** Relacja ze str. rel. */
    protected class Predicate extends Formula
    {
        String name;
        List<Term> args;

        protected Predicate(String name, List<Term> args)
        {
            this.name = name;
            this.args = args;
        }

        protected boolean value(ValuationContext ctx)
        {
            logger.finer("entry this=" + this.hashCode()
                    + " name=" + name);
            ArrayList<Object> vals = new ArrayList<Object>(args.size());
            for (int i = 0; i < args.size(); ++i)
                vals.add(args.get(i).value(ctx));
            logger.fine("calling this=" + this.hashCode()
                    + " name=" + name + " args=" + vals);
            boolean result = ctx.rs.relationValue(name, vals);
            logger.finer("return this=" + this.hashCode()
                    + " name=" + name + " result=" + result);
            return result;
        }

        protected void appendTo(StringBuilder sb)
        {
            sb.append("(");
            sb.append(name);
            for (Term arg : args) {
                sb.append(" ");
                append(sb, arg);
            }
            sb.append(")");
        }
    }



    /** Spójnik (operator) logiczny. */
    protected class Connective extends Formula
    {
        int type;
        Formula arg1, arg2;

        protected Connective(int type, Formula arg1, Formula arg2)
        {
            this.type = type;
            this.arg1 = arg1;
            this.arg2 = arg2;
        }

        protected boolean value(ValuationContext ctx)
        {
            switch (type) {
                case NEGATION:
                    return ! arg1.value(ctx);
                case CONJUNCTION:
                    return arg1.value(ctx) && arg2.value(ctx);
                case DISJUNCTION:
                    return arg1.value(ctx) || arg2.value(ctx);
                case IMPLICATION:
                    return ! arg1.value(ctx) || arg2.value(ctx);
                case BICONDITIONAL:
                    return arg1.value(ctx) == arg2.value(ctx);
                default:
                    throw new RuntimeException();
            }
        }

        protected void appendTo(StringBuilder sb)
        {
            sb.append("(conn-");
            sb.append(type);
            sb.append(" ");
            append(sb, arg1);
            sb.append(" ");
            append(sb, arg2);
            sb.append(")");
        }
    }



    /** Kwantyfikator. */
    protected class Quantifier extends Formula
    {
        int type;
        List<Variable> vars;
        List<String> ranges;
        Formula subformula;

        protected Quantifier(int type, List<Variable> vars,
                                List<String> ranges, Formula subformula)
        {
            this.type = type;
            this.vars = vars;
            this.ranges = ranges;
            this.subformula = subformula;
        }

        protected boolean value(ValuationContext ctx)
        {
            beforeSearch(ctx);
            boolean what = (type == EXISTENTIAL);
            boolean found = find(ctx, what);
            return what ? found : ! found;
        }

        boolean valueAndStates(ValuationContext ctx,
                    List<Map<String, Object>> states, int limit)
        {
            beforeSearch(ctx);
            boolean what = (type == EXISTENTIAL);
            boolean found = find(ctx, what);
            if (found) {
                states.add(makeVarStateMap(ctx));
                while (--limit > 0) {
                    if (find(ctx, what))
                        states.add(makeVarStateMap(ctx));
                }
            }
            return what ? found : ! found;
        }

        Iterator<Object> makeIter(ValuationContext ctx, int which)
        {
            String r = ranges.get(which);
            return r == null ? ctx.rs.domainIterator() :
                    ctx.rs.domainSubsetIterator(r);
        }

        Map<String, Object> makeVarStateMap(ValuationContext ctx)
        {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            for (Variable var : vars)
                map.put(var.getName(), var.value(ctx));
            return map;
        }

        void beforeSearch(ValuationContext ctx)
        {
            int current = 0;
            ctx.qstate.put(this, current);
            ctx.iters.put(vars.get(current), makeIter(ctx, current));
        }

        boolean find(ValuationContext ctx, boolean what)
        {
            boolean result = false;
            int current = ctx.qstate.get(this);
            int last = vars.size() - 1;
            Object obj;
            while (current >= 0) {
                // spróbuj popchnąć bieżący iterator do przodu
                // jeśli się nie da, cofnij się do poprzedniego
                try {
                    Iterator it = ctx.iters.get(vars.get(current));
                    obj = it.next();
                } catch (NoSuchElementException ex) {
                    --current;
                    continue;
                }
                // ustaw nową wartość bieżącej zmiennej
                vars.get(current).set(ctx, obj);
                // jeśli jeszcze nie ustawiłeś wszystkich zmiennych,
                // to zainicjuj następny iterator
                if (current < last) {
                    ++current;
                    ctx.iters.put(vars.get(current),
                                    makeIter(ctx, current));
                    continue;
                }
                // wszystkie zmienne ustawione, sprawdź czy dają
                // szukane wartościowanie
                if (subformula.value(ctx) == what) {
                    result = true;
                    break;
                }
            }
            ctx.qstate.put(this, current);
            return result;
        }

        protected void appendTo(StringBuilder sb)
        {
            sb.append("(Q-");
            sb.append(type);
            sb.append(" ");
            for (Variable var : vars) {
                append(sb, var);
                sb.append(" ");
            }
            sb.append("(");
            append(sb, subformula);
            sb.append("))");
        }
    }
}

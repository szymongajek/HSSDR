package folf;

import java.util.List;

/**
 * Defincja predykatu w postaci "nazwa(x, y, z) <=> formuła".
 * Po podłączeniu jej do określonej struktury robi się z tego relacja,
 * z której można korzystać tak jak z innych.
 */
public class Definition extends FOLF
{
    protected String name;

    public String getName()
    {
        return name;
    }

    public int getArity()
    {
        return freeVariables.size();
    }

    private class FormulaicRelation extends AbstractRelation
    {
        Structure rs;

        public FormulaicRelation(Structure rs)
        {
            super(freeVariables.size());
            this.rs = rs;
        }

        public boolean value(List<Object> args)
        {
            if (args.size() != arity)
                throw new IllegalArgumentException("bad arity");
            ValuationContext ctx = new ValuationContext(rs);
            for (int i = arity - 1; i >= 0; --i)
                freeVariables.get(i).set(ctx, args.get(i));
            return formula.value(ctx);
        }
    }

    private class UnaryFormulaicRelation extends AbstractUnaryRelation
    {
        Structure rs;

        public UnaryFormulaicRelation(Structure rs)
        {
            if (freeVariables.size() != 1)
                throw new IllegalArgumentException("bad arity");
            this.rs = rs;
        }

        public boolean unaryValue(Object arg)
        {
            ValuationContext ctx = new ValuationContext(rs);
            freeVariables.get(0).set(ctx, arg);
            return formula.value(ctx);
        }
    }

    public Relation toRelation(Structure rs)
    {
        if (getArity() == 1)
            return new UnaryCachingRelation(new UnaryFormulaicRelation(rs));
        else
            return new CachingRelation(new FormulaicRelation(rs));
    }
}

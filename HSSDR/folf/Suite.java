package folf;

/**
 * Zestaw testów.
 */
public class Suite
{
    private static java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger("folf.Suite");

    protected Definition[] localRelations;
    protected Sentence[] tests;

    private Structure addLocalDefinitions(Structure orig_rs)
    {
        logger.finer("entry this=" + this.hashCode() +
                " orig_rs=" + orig_rs.hashCode());
        Structure rs = new Structure(orig_rs);
        for (Definition d : localRelations)
            rs.addRelation(d.getName(), d.toRelation(rs));
        logger.finer("return this=" + this.hashCode() +
                " result=" + rs.hashCode());
        return rs;
    }

    /** Wyniki poszczególnych testów. */
    public Result[] getResults(Structure rs)
    {
        rs = addLocalDefinitions(rs);
        Result[] r = new Result[tests.length];
        for (int i = 0; i < tests.length; ++i)
            r[i] = tests[i].getResult(rs);
        return r;
    }

    /**
     * Wyniki testów z wszystkimi (<= limit) wartościami zmiennych
     * z głównych kwantyfikatorów.
     */
    public Result[] getCompleteResults(Structure rs, int limit)
    {
        rs = addLocalDefinitions(rs);
        Result[] r = new Result[tests.length];
        for (int i = 0; i < tests.length; ++i)
            r[i] = tests[i].getCompleteResult(rs, limit);
        return r;
    }
}

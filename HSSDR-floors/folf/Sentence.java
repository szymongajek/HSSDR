package folf;

/**
 * Formuła zdaniowa, w kontekście danej struktury prawdziwa albo fałszywa.
 * Struktura musi być kompatybilna ze słownikiem użytym podczas parsowania
 * formuły, wpp podczas wartościowania mogą się pojawić wyjątki.
 */
public class Sentence extends FOLF
{
    /** Wynik wartościowania zdania w podanej strukturze. */
    public Result getResult(Structure rs)
    {
        return super.getResult(rs);
    }

    /**
     * Wynik z wszystkimi (<= limit) wartościami zmiennych z głównego
     * kwantyfikatora.
     */
    public Result getCompleteResult(Structure rs, int limit)
    {
        return super.getCompleteResult(rs, limit);
    }
}

package folf;

/**
 * Relacja unarna.
 * Skoro argument jest tylko jeden, to nie ma po co opakowywać
 * go w listę - można przekazać wprost, będzie szybciej.
 */
public interface UnaryRelation extends Relation
{
    /**
     * Czy dany obiekt należy do tej relacji?
     */
    boolean unaryValue(Object arg);

    /**
     * Rozrzerz relację o dany obiekt.
     * Metoda dla relacji budowanych dynamicznie, w innych będzie pewnie
     * rzucany wyjątek UnsupportedOperationException.
     */
    void unaryAdd(Object arg);
}

package folf;

/**
 * Relacja o określonej krotności.
 */
public interface Relation
{
    /** Krotność relacji. */
    int getArity();

    /** Sprawdzenie czy dana entka obiektów należy do tej relacji. */
    boolean value(java.util.List<Object> args);

    /**
     * Convenience method.
     * Konwertuje tablicę do postaci listy i wywołuje
     * {@link #value(java.util.List)}.
     */
    boolean value(Object... args);

    /**
     * Metoda dla tych relacji, które są tworzone puste i dopiero potem
     * wypełniane entkami.
     * Próba wywołania tej metody w relacjach innego typu prawdopodobnie
     * skończy się rzuceniem wyjątku UnsupportedOperationException.
     */
    void add(java.util.List<Object> args);

    /**
     * Convenience method.
     * Konwertuje tablicę do postaci listy i wywołuje
     * {@link #add(java.util.List)}.
     */
    void add(Object... args);
}

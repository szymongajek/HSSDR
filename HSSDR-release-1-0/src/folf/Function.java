package folf;

/**
 * Funkcja w strukturze relacyjnej.
 * Funkcje o krotności zero nazywane są stałymi. Wartości zwracane przez
 * funkcje z zasady powinny należeć do domeny struktury.
 */
public interface Function
{
    /** Krotność funkcji. */
    int getArity();

    /** Zwróć wartość odpowiadającą danej entce argumentów. */
    Object value(java.util.List<Object> args);

    /**
     * Convenience method dla funkcji o arności zero (stałych).
     * Wywołuje {@link #value(java.util.List)} przekazując jako argument
     * pustą listę.
     */
    Object value();

    /**
     * Convenience method.
     * Konwertuje tablicę do postaci listy i wywołuje
     * {@link #value(java.util.List)}.
     */
    Object value(Object... args);

    /**
     * Metoda dla tych funkcji, które są tworzone puste i dopiero potem
     * wypełniane zawartością.
     * Próba wywołania tej metody w relacjach innego typu prawdopodobnie
     * skończy się rzuceniem wyjątku UnsupportedOperationException.
     */
    void add(java.util.List<Object> args, Object value);

    /**
     * Convenience method.
     * Konwertuje tablicę do postaci listy i wywołuje
     * {@link #add(java.util.List, Object)}.
     */
    void add(Object[] args, Object value);

    /**
     * Convenience method.
     * Konwertuje elementy tablicy z wyjątkiem ostatniego do postaci listy
     * i wywołuje {@link #add(java.util.List, Object)}.
     */
    void add(Object... args_and_value);

    /**
     * Convenience method dla funkcji o arności zero (stałych).
     * Wywołuje {@link #add(java.util.List, Object)} przekazując jako
     * pierwszy argument pustą listę.
     */
    void set(Object value);
}

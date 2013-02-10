package folf;

import java.util.List;
import java.util.Map;

/**
 * Rezultat operacji wartościowania formuły logicznej.
 * Oprócz samego wyniku dostarczane są różne dodatkowe informacje.
 */
public class Result
{
    boolean value;
    String message;
    Map<String, Object> qVarsState;
    List< Map<String, Object> > qVarsStates;

    Result(boolean value, String message, Map<String, Object> qVarsState,
                List< Map<String, Object> > qVarsStates)
    {
        this.value = value;
        this.message = message;
        this.qVarsState = qVarsState;
        this.qVarsStates = qVarsStates;
    }

    /** Wynik wartościowania formuły (prawda lub fałsz). */
    public boolean getValue()
    {
        return value;
    }

    /**
     * Opcjonalna wiadomość związana z otrzymanym wynikiem.
     * W przypadku formuł testujących własności layoutów pod fałsz będą
     * podwiązane komunikaty typu "Brak drzwi wejściowych".
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Wartości głównych zmiennych formuły decydujące o uzyskanym wyniku.
     * Jeśli całość formuły jest pod kwantyfikatorem (np. "forall x : P(x)",
     * albo "exists a, b : Q(a, b)") to przez główne zmienne rozumiemy te
     * związane z tym kwantyfikatorem.
     * Dla pierwszego przykładu istotne jest znalezienie takiego x, że P(x)
     * jest fałszywe; dla drugiego takich a, b że Q(a, b) jest prawdziwe.
     * Jeśli się udało je znaleźć, to ta metoda poda nam te wartości.
     * Jeśli nie, albo jeśli formuła nie jest kwantyfikowana, to null.
     */
    public Map<String, Object> getQVarsState()
    {
        if (qVarsState != null)
            return qVarsState;
        if (qVarsStates != null)
            return qVarsStates.get(0);
        return null;
    }

    /**
     * Metoda dająca dostęp do kolejnych decydujących zestawów.
     * Trzeba specjalnie zażyczyć sobie ich ustalenia; normalnie obliczenia
     * są przerywane natychmiast gdy tylko uda się ustalić wynik.
     */
    public Map<String, Object> getQVarsState(int i)
    {
        if (qVarsStates != null)
            return i < qVarsStates.size() ? qVarsStates.get(i) : null;
        if (i == 0 && qVarsState != null)
            return qVarsState;
        return null;
    }

    public String getResult()
    {
        String ret="";
        boolean b =  getValue();
        String msg = getMessage();
        ret+=msg == null ? b : b + " : " + msg+" \n";
        if ( getQVarsState() != null) {
            for (int i = 0; ; ++i) {
                Map<String, Object> qvars =  getQVarsState(i);
                if (qvars == null)
                    break;
                for (String name : qvars.keySet())
                    ret+=name + "=" + qvars.get(name) + " ";
               ret+="; ";
            }
            ret+=" \n";
        }
        return ret;
    }
    public void printResult()
    {
       System.out.println(getResult());
    }
}

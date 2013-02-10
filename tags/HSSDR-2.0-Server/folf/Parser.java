package folf;

import java.io.IOException;
import org.antlr.runtime.*;

/**
 * Oficjalny punkt wejścia do parsingu formuł.
 * Tworzysz Parser, przy pomocy którego kreujesz obiekty typu Sentence,
 * Definition, itp., i następnie te formuły wartościujesz czy co tam.
 */
public class Parser
{
    private FormulaLexer lexer;
    private CommonTokenStream tokenStream;
    private FormulaParser parser;

    boolean status, failedIO, failedANTLR;
    StringBuilder errorMessages;

    public Parser()
    {
        ANTLRStringStream s = new ANTLRStringStream("");
        lexer = new FormulaLexer(s);
        lexer.owner = this;
        tokenStream = new CommonTokenStream(lexer);
        parser = new FormulaParser(tokenStream);
        parser.owner = this;
        errorMessages = new StringBuilder();
        status = false;
    }

    void appendErrorMessage(String msg)
    {
        errorMessages.append(msg);
        errorMessages.append("\n");
    }

    private void reset(CharStream s)
    {
        lexer.setCharStream(s);
        tokenStream.setTokenSource(lexer);
        parser.setTokenStream(tokenStream);
        errorMessages.setLength(0);
    }

    private void openString(String str)
    {
        failedIO = false;
        reset(new ANTLRStringStream(str));
    }

    private void openFile(String filename)
    {
        try {
            failedIO = false;
            reset(new ANTLRFileStream(filename, "UTF-8"));
        } catch (IOException e) {
            failedIO = true;
            reset(new ANTLRStringStream(""));
            appendErrorMessage(e.toString());
        }
    }

    private void before(Vocabulary voc, FOLF form)
    {
        parser.voc = voc;
        parser.allowNumbers = voc.areNumbersAllowed();
        parser.allowStrings = voc.areStringsAllowed();
        parser.outerFOLF = form;
        failedANTLR = false;
    }

    private FOLF after()
    {
        FOLF form = parser.outerFOLF;
        status = (! failedIO && parser.getNumberOfSyntaxErrors() == 0 &&
                lexer.getNumberOfSyntaxErrors() == 0 && ! failedANTLR);
        parser.voc = null;
        parser.outerFOLF = null;
        return status ? form : null;
    }

    public Sentence sentenceFromString(String str, Vocabulary voc)
    {
        openString(str);
        before(voc, new Sentence());
        try {
            parser.sentence();
        } catch (RecognitionException e) {
            failedANTLR = true;
        }
        return (Sentence) after();
    }

    public Sentence sentenceFromFile(String filename, Vocabulary voc)
    {
        openFile(filename);
        if (failedIO)
            return null;
        before(voc, new Sentence());
        try {
            parser.sentence();
        } catch (RecognitionException e) {
            failedANTLR = true;
        }
        return (Sentence) after();
    }

    public Definition definitionFromString(String str, Vocabulary voc)
    {
        openString(str);
        before(voc, new Definition());
        try {
            parser.definition();
        } catch (RecognitionException e) {
            failedANTLR = true;
        }
        return (Definition) after();
    }

    public Definition definitionFromFile(String filename, Vocabulary voc)
    {
        openFile(filename);
        if (failedIO)
            return null;
        before(voc, new Definition());
        try {
            parser.definition();
        } catch (RecognitionException e) {
            failedANTLR = true;
        }
        return (Definition) after();
    }

    public Suite suiteFromString(String str, Vocabulary voc)
    {
        openString(str);
        Suite suite = new Suite();
        parser.outerSuite = suite;
        before(voc, null);
        try {
            parser.suite();
        } catch (RecognitionException e) {
            failedANTLR = true;
        }
        after();
        parser.outerSuite = null;
        return status ? suite : null;
    }

    public Suite suiteFromFile(String filename, Vocabulary voc)
    {
        openFile(filename);
        if (failedIO)
            return null;
        Suite suite = new Suite();
        parser.outerSuite = suite;
        before(voc, null);
        try {
            parser.suite();
        } catch (RecognitionException e) {
            failedANTLR = true;
        }
        after();
        parser.outerSuite = null;
        return status ? suite : null;
    }

    public boolean getStatus()
    {
        return status;
    }

    public String getErrorMessages()
    {
        return errorMessages.length() > 0 ? errorMessages.toString() : null;
    }
}

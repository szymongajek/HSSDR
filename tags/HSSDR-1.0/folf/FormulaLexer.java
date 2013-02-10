// $ANTLR 3.2 Sep 23, 2009 12:02:23 Formula.g 2010-11-23 21:34:54

    package folf;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class FormulaLexer extends Lexer {
    public static final int GE=21;
    public static final int LT=18;
    public static final int EXPONENT=23;
    public static final int UNICODE_ESC=26;
    public static final int OCTAL_ESC=25;
    public static final int NUMBER=22;
    public static final int FORALL=10;
    public static final int WHITESPACE=29;
    public static final int HEX_DIGIT=27;
    public static final int NOT=15;
    public static final int AND=14;
    public static final int ID=6;
    public static final int EOF=-1;
    public static final int LPAREN=4;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RPAREN=5;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int ESC_SEQ=24;
    public static final int IN=9;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int EQUIVALENCE=7;
    public static final int OR=13;
    public static final int GT=20;
    public static final int IMPLICATION=12;
    public static final int EXISTS=11;
    public static final int EQ=16;
    public static final int COMMENT=28;
    public static final int LE=19;
    public static final int STRING=8;
    public static final int NE=17;

        folf.Parser owner;

        public void emitErrorMessage(String msg)
        {
            owner.appendErrorMessage(msg);
        }


    // delegates
    // delegators

    public FormulaLexer() {;} 
    public FormulaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public FormulaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Formula.g"; }

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:19:8: ( '(' )
            // Formula.g:19:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:20:8: ( ')' )
            // Formula.g:20:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:21:7: ( ';' )
            // Formula.g:21:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:22:7: ( ',' )
            // Formula.g:22:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:23:7: ( 'success_msg' )
            // Formula.g:23:9: 'success_msg'
            {
            match("success_msg"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:24:7: ( 'failure_msg' )
            // Formula.g:24:9: 'failure_msg'
            {
            match("failure_msg"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:25:7: ( ':' )
            // Formula.g:25:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:26:7: ( '+' )
            // Formula.g:26:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:27:7: ( '-' )
            // Formula.g:27:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:28:7: ( '*' )
            // Formula.g:28:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:29:7: ( '/' )
            // Formula.g:29:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "FORALL"
    public final void mFORALL() throws RecognitionException {
        try {
            int _type = FORALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:562:8: ( 'forall' | '\\\\forall' | '\\u2200' )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 'f':
                {
                alt1=1;
                }
                break;
            case '\\':
                {
                alt1=2;
                }
                break;
            case '\u2200':
                {
                alt1=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // Formula.g:562:10: 'forall'
                    {
                    match("forall"); 


                    }
                    break;
                case 2 :
                    // Formula.g:562:21: '\\\\forall'
                    {
                    match("\\forall"); 


                    }
                    break;
                case 3 :
                    // Formula.g:562:34: '\\u2200'
                    {
                    match('\u2200'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FORALL"

    // $ANTLR start "EXISTS"
    public final void mEXISTS() throws RecognitionException {
        try {
            int _type = EXISTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:564:8: ( 'exists' | '\\\\exists' | '\\u2203' )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 'e':
                {
                alt2=1;
                }
                break;
            case '\\':
                {
                alt2=2;
                }
                break;
            case '\u2203':
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Formula.g:564:10: 'exists'
                    {
                    match("exists"); 


                    }
                    break;
                case 2 :
                    // Formula.g:564:21: '\\\\exists'
                    {
                    match("\\exists"); 


                    }
                    break;
                case 3 :
                    // Formula.g:564:34: '\\u2203'
                    {
                    match('\u2203'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXISTS"

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:566:4: ( 'in' | '\\\\in' | '\\u220A' )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 'i':
                {
                alt3=1;
                }
                break;
            case '\\':
                {
                alt3=2;
                }
                break;
            case '\u220A':
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // Formula.g:566:6: 'in'
                    {
                    match("in"); 


                    }
                    break;
                case 2 :
                    // Formula.g:566:13: '\\\\in'
                    {
                    match("\\in"); 


                    }
                    break;
                case 3 :
                    // Formula.g:566:22: '\\u220A'
                    {
                    match('\u220A'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IN"

    // $ANTLR start "EQUIVALENCE"
    public final void mEQUIVALENCE() throws RecognitionException {
        try {
            int _type = EQUIVALENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:568:13: ( '<=>' | '\\\\Leftrightarrow' | '\\u21D4' )
            int alt4=3;
            switch ( input.LA(1) ) {
            case '<':
                {
                alt4=1;
                }
                break;
            case '\\':
                {
                alt4=2;
                }
                break;
            case '\u21D4':
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // Formula.g:568:15: '<=>'
                    {
                    match("<=>"); 


                    }
                    break;
                case 2 :
                    // Formula.g:568:23: '\\\\Leftrightarrow'
                    {
                    match("\\Leftrightarrow"); 


                    }
                    break;
                case 3 :
                    // Formula.g:568:44: '\\u21D4'
                    {
                    match('\u21D4'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUIVALENCE"

    // $ANTLR start "IMPLICATION"
    public final void mIMPLICATION() throws RecognitionException {
        try {
            int _type = IMPLICATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:570:13: ( '=>' | '\\\\Rightarrow' | '\\u21D2' )
            int alt5=3;
            switch ( input.LA(1) ) {
            case '=':
                {
                alt5=1;
                }
                break;
            case '\\':
                {
                alt5=2;
                }
                break;
            case '\u21D2':
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // Formula.g:570:15: '=>'
                    {
                    match("=>"); 


                    }
                    break;
                case 2 :
                    // Formula.g:570:22: '\\\\Rightarrow'
                    {
                    match("\\Rightarrow"); 


                    }
                    break;
                case 3 :
                    // Formula.g:570:39: '\\u21D2'
                    {
                    match('\u21D2'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IMPLICATION"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:572:5: ( 'and' | '\\\\wedge' | '\\u2227' )
            int alt6=3;
            switch ( input.LA(1) ) {
            case 'a':
                {
                alt6=1;
                }
                break;
            case '\\':
                {
                alt6=2;
                }
                break;
            case '\u2227':
                {
                alt6=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // Formula.g:572:7: 'and'
                    {
                    match("and"); 


                    }
                    break;
                case 2 :
                    // Formula.g:572:15: '\\\\wedge'
                    {
                    match("\\wedge"); 


                    }
                    break;
                case 3 :
                    // Formula.g:572:27: '\\u2227'
                    {
                    match('\u2227'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:574:4: ( 'or' | '\\\\vee' | '\\u2228' )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 'o':
                {
                alt7=1;
                }
                break;
            case '\\':
                {
                alt7=2;
                }
                break;
            case '\u2228':
                {
                alt7=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // Formula.g:574:6: 'or'
                    {
                    match("or"); 


                    }
                    break;
                case 2 :
                    // Formula.g:574:13: '\\\\vee'
                    {
                    match("\\vee"); 


                    }
                    break;
                case 3 :
                    // Formula.g:574:23: '\\u2228'
                    {
                    match('\u2228'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:576:5: ( 'not' | '!' | '\\\\neg' | '\\u00AC' )
            int alt8=4;
            switch ( input.LA(1) ) {
            case 'n':
                {
                alt8=1;
                }
                break;
            case '!':
                {
                alt8=2;
                }
                break;
            case '\\':
                {
                alt8=3;
                }
                break;
            case '\u00AC':
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // Formula.g:576:7: 'not'
                    {
                    match("not"); 


                    }
                    break;
                case 2 :
                    // Formula.g:576:15: '!'
                    {
                    match('!'); 

                    }
                    break;
                case 3 :
                    // Formula.g:576:21: '\\\\neg'
                    {
                    match("\\neg"); 


                    }
                    break;
                case 4 :
                    // Formula.g:576:31: '\\u00AC'
                    {
                    match('\u00AC'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:578:4: ( '=' )
            // Formula.g:578:6: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "NE"
    public final void mNE() throws RecognitionException {
        try {
            int _type = NE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:580:4: ( '!=' | '<>' | '\\\\neq' | '\\u2260' )
            int alt9=4;
            switch ( input.LA(1) ) {
            case '!':
                {
                alt9=1;
                }
                break;
            case '<':
                {
                alt9=2;
                }
                break;
            case '\\':
                {
                alt9=3;
                }
                break;
            case '\u2260':
                {
                alt9=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // Formula.g:580:6: '!='
                    {
                    match("!="); 


                    }
                    break;
                case 2 :
                    // Formula.g:580:13: '<>'
                    {
                    match("<>"); 


                    }
                    break;
                case 3 :
                    // Formula.g:580:20: '\\\\neq'
                    {
                    match("\\neq"); 


                    }
                    break;
                case 4 :
                    // Formula.g:580:30: '\\u2260'
                    {
                    match('\u2260'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NE"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:582:4: ( '<' )
            // Formula.g:582:6: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:584:4: ( '<=' | '\\\\leq' | '\\u2264' )
            int alt10=3;
            switch ( input.LA(1) ) {
            case '<':
                {
                alt10=1;
                }
                break;
            case '\\':
                {
                alt10=2;
                }
                break;
            case '\u2264':
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // Formula.g:584:6: '<='
                    {
                    match("<="); 


                    }
                    break;
                case 2 :
                    // Formula.g:584:13: '\\\\leq'
                    {
                    match("\\leq"); 


                    }
                    break;
                case 3 :
                    // Formula.g:584:23: '\\u2264'
                    {
                    match('\u2264'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:586:4: ( '>' )
            // Formula.g:586:6: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:588:4: ( '>=' | '\\\\geq' | '\\u2265' )
            int alt11=3;
            switch ( input.LA(1) ) {
            case '>':
                {
                alt11=1;
                }
                break;
            case '\\':
                {
                alt11=2;
                }
                break;
            case '\u2265':
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // Formula.g:588:6: '>='
                    {
                    match(">="); 


                    }
                    break;
                case 2 :
                    // Formula.g:588:13: '\\\\geq'
                    {
                    match("\\geq"); 


                    }
                    break;
                case 3 :
                    // Formula.g:588:23: '\\u2265'
                    {
                    match('\u2265'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:590:4: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // Formula.g:590:6: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // Formula.g:590:26: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='0' && LA12_0<='9')||(LA12_0>='A' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='a' && LA12_0<='z')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // Formula.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:592:8: ( ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>='0' && LA19_0<='9')) ) {
                alt19=1;
            }
            else if ( (LA19_0=='.') ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // Formula.g:592:10: ( '0' .. '9' )+ ( '.' ( '0' .. '9' )+ )? ( EXPONENT )?
                    {
                    // Formula.g:592:10: ( '0' .. '9' )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // Formula.g:592:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt13 >= 1 ) break loop13;
                                EarlyExitException eee =
                                    new EarlyExitException(13, input);
                                throw eee;
                        }
                        cnt13++;
                    } while (true);

                    // Formula.g:592:20: ( '.' ( '0' .. '9' )+ )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0=='.') ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // Formula.g:592:21: '.' ( '0' .. '9' )+
                            {
                            match('.'); 
                            // Formula.g:592:25: ( '0' .. '9' )+
                            int cnt14=0;
                            loop14:
                            do {
                                int alt14=2;
                                int LA14_0 = input.LA(1);

                                if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                                    alt14=1;
                                }


                                switch (alt14) {
                            	case 1 :
                            	    // Formula.g:592:25: '0' .. '9'
                            	    {
                            	    matchRange('0','9'); 

                            	    }
                            	    break;

                            	default :
                            	    if ( cnt14 >= 1 ) break loop14;
                                        EarlyExitException eee =
                                            new EarlyExitException(14, input);
                                        throw eee;
                                }
                                cnt14++;
                            } while (true);


                            }
                            break;

                    }

                    // Formula.g:592:37: ( EXPONENT )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='E'||LA16_0=='e') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // Formula.g:592:37: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // Formula.g:592:49: '.' ( '0' .. '9' )+ ( EXPONENT )?
                    {
                    match('.'); 
                    // Formula.g:592:53: ( '0' .. '9' )+
                    int cnt17=0;
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // Formula.g:592:53: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt17 >= 1 ) break loop17;
                                EarlyExitException eee =
                                    new EarlyExitException(17, input);
                                throw eee;
                        }
                        cnt17++;
                    } while (true);

                    // Formula.g:592:63: ( EXPONENT )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0=='E'||LA18_0=='e') ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // Formula.g:592:63: EXPONENT
                            {
                            mEXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // Formula.g:595:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // Formula.g:595:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // Formula.g:595:22: ( '+' | '-' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='+'||LA20_0=='-') ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // Formula.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // Formula.g:595:33: ( '0' .. '9' )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>='0' && LA21_0<='9')) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // Formula.g:595:34: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:597:8: ( '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"' )
            // Formula.g:597:10: '\"' ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // Formula.g:597:14: ( ESC_SEQ | ~ ( '\\\\' | '\"' ) )*
            loop22:
            do {
                int alt22=3;
                int LA22_0 = input.LA(1);

                if ( (LA22_0=='\\') ) {
                    alt22=1;
                }
                else if ( ((LA22_0>='\u0000' && LA22_0<='!')||(LA22_0>='#' && LA22_0<='[')||(LA22_0>=']' && LA22_0<='\uFFFF')) ) {
                    alt22=2;
                }


                switch (alt22) {
            	case 1 :
            	    // Formula.g:597:16: ESC_SEQ
            	    {
            	    mESC_SEQ(); 

            	    }
            	    break;
            	case 2 :
            	    // Formula.g:597:26: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "ESC_SEQ"
    public final void mESC_SEQ() throws RecognitionException {
        try {
            // Formula.g:600:9: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | OCTAL_ESC | UNICODE_ESC )
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt23=1;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt23=2;
                    }
                    break;
                case 'u':
                    {
                    alt23=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // Formula.g:600:11: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 
                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // Formula.g:600:55: OCTAL_ESC
                    {
                    mOCTAL_ESC(); 

                    }
                    break;
                case 3 :
                    // Formula.g:600:67: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "ESC_SEQ"

    // $ANTLR start "OCTAL_ESC"
    public final void mOCTAL_ESC() throws RecognitionException {
        try {
            // Formula.g:603:11: ( '\\\\' '0' .. '3' '0' .. '7' '0' .. '7' | '\\\\' '0' .. '7' '0' .. '7' | '\\\\' '0' .. '7' )
            int alt24=3;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='\\') ) {
                int LA24_1 = input.LA(2);

                if ( ((LA24_1>='0' && LA24_1<='3')) ) {
                    int LA24_2 = input.LA(3);

                    if ( ((LA24_2>='0' && LA24_2<='7')) ) {
                        int LA24_4 = input.LA(4);

                        if ( ((LA24_4>='0' && LA24_4<='7')) ) {
                            alt24=1;
                        }
                        else {
                            alt24=2;}
                    }
                    else {
                        alt24=3;}
                }
                else if ( ((LA24_1>='4' && LA24_1<='7')) ) {
                    int LA24_3 = input.LA(3);

                    if ( ((LA24_3>='0' && LA24_3<='7')) ) {
                        alt24=2;
                    }
                    else {
                        alt24=3;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // Formula.g:603:13: '\\\\' '0' .. '3' '0' .. '7' '0' .. '7'
                    {
                    match('\\'); 
                    matchRange('0','3'); 
                    matchRange('0','7'); 
                    matchRange('0','7'); 

                    }
                    break;
                case 2 :
                    // Formula.g:603:47: '\\\\' '0' .. '7' '0' .. '7'
                    {
                    match('\\'); 
                    matchRange('0','7'); 
                    matchRange('0','7'); 

                    }
                    break;
                case 3 :
                    // Formula.g:604:3: '\\\\' '0' .. '7'
                    {
                    match('\\'); 
                    matchRange('0','7'); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "OCTAL_ESC"

    // $ANTLR start "UNICODE_ESC"
    public final void mUNICODE_ESC() throws RecognitionException {
        try {
            // Formula.g:607:13: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // Formula.g:607:15: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            match('\\'); 
            match('u'); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 
            mHEX_DIGIT(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "UNICODE_ESC"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // Formula.g:610:11: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            // Formula.g:
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:612:9: ( '#' ( . )* ( '\\r' | '\\n' ) )
            // Formula.g:612:11: '#' ( . )* ( '\\r' | '\\n' )
            {
            match('#'); 
            // Formula.g:612:15: ( . )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0=='\n'||LA25_0=='\r') ) {
                    alt25=2;
                }
                else if ( ((LA25_0>='\u0000' && LA25_0<='\t')||(LA25_0>='\u000B' && LA25_0<='\f')||(LA25_0>='\u000E' && LA25_0<='\uFFFF')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // Formula.g:612:15: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            if ( input.LA(1)=='\n'||input.LA(1)=='\r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Formula.g:616:12: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // Formula.g:616:14: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    public void mTokens() throws RecognitionException {
        // Formula.g:1:8: ( LPAREN | RPAREN | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | FORALL | EXISTS | IN | EQUIVALENCE | IMPLICATION | AND | OR | NOT | EQ | NE | LT | LE | GT | GE | ID | NUMBER | STRING | COMMENT | WHITESPACE )
        int alt26=30;
        alt26 = dfa26.predict(input);
        switch (alt26) {
            case 1 :
                // Formula.g:1:10: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 2 :
                // Formula.g:1:17: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 3 :
                // Formula.g:1:24: T__30
                {
                mT__30(); 

                }
                break;
            case 4 :
                // Formula.g:1:30: T__31
                {
                mT__31(); 

                }
                break;
            case 5 :
                // Formula.g:1:36: T__32
                {
                mT__32(); 

                }
                break;
            case 6 :
                // Formula.g:1:42: T__33
                {
                mT__33(); 

                }
                break;
            case 7 :
                // Formula.g:1:48: T__34
                {
                mT__34(); 

                }
                break;
            case 8 :
                // Formula.g:1:54: T__35
                {
                mT__35(); 

                }
                break;
            case 9 :
                // Formula.g:1:60: T__36
                {
                mT__36(); 

                }
                break;
            case 10 :
                // Formula.g:1:66: T__37
                {
                mT__37(); 

                }
                break;
            case 11 :
                // Formula.g:1:72: T__38
                {
                mT__38(); 

                }
                break;
            case 12 :
                // Formula.g:1:78: FORALL
                {
                mFORALL(); 

                }
                break;
            case 13 :
                // Formula.g:1:85: EXISTS
                {
                mEXISTS(); 

                }
                break;
            case 14 :
                // Formula.g:1:92: IN
                {
                mIN(); 

                }
                break;
            case 15 :
                // Formula.g:1:95: EQUIVALENCE
                {
                mEQUIVALENCE(); 

                }
                break;
            case 16 :
                // Formula.g:1:107: IMPLICATION
                {
                mIMPLICATION(); 

                }
                break;
            case 17 :
                // Formula.g:1:119: AND
                {
                mAND(); 

                }
                break;
            case 18 :
                // Formula.g:1:123: OR
                {
                mOR(); 

                }
                break;
            case 19 :
                // Formula.g:1:126: NOT
                {
                mNOT(); 

                }
                break;
            case 20 :
                // Formula.g:1:130: EQ
                {
                mEQ(); 

                }
                break;
            case 21 :
                // Formula.g:1:133: NE
                {
                mNE(); 

                }
                break;
            case 22 :
                // Formula.g:1:136: LT
                {
                mLT(); 

                }
                break;
            case 23 :
                // Formula.g:1:139: LE
                {
                mLE(); 

                }
                break;
            case 24 :
                // Formula.g:1:142: GT
                {
                mGT(); 

                }
                break;
            case 25 :
                // Formula.g:1:145: GE
                {
                mGE(); 

                }
                break;
            case 26 :
                // Formula.g:1:148: ID
                {
                mID(); 

                }
                break;
            case 27 :
                // Formula.g:1:151: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 28 :
                // Formula.g:1:158: STRING
                {
                mSTRING(); 

                }
                break;
            case 29 :
                // Formula.g:1:165: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 30 :
                // Formula.g:1:173: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;

        }

    }


    protected DFA26 dfa26 = new DFA26(this);
    static final String DFA26_eotS =
        "\5\uffff\2\41\7\uffff\1\41\1\uffff\1\41\1\uffff\1\55\1\uffff\1\56"+
        "\1\uffff\1\41\1\uffff\1\41\1\uffff\1\41\1\34\3\uffff\1\62\6\uffff"+
        "\3\41\1\uffff\1\41\1\21\1\36\2\uffff\1\41\1\31\1\41\1\uffff\3\41"+
        "\1\uffff\1\41\1\27\1\34\12\41\1\15\1\17\10\41\1\120\1\121\2\uffff";
    static final String DFA26_eofS =
        "\122\uffff";
    static final String DFA26_minS =
        "\1\11\4\uffff\1\165\1\141\5\uffff\1\114\1\uffff\1\170\1\uffff\1"+
        "\156\1\uffff\1\75\1\uffff\1\76\1\uffff\1\156\1\uffff\1\162\1\uffff"+
        "\1\157\1\75\3\uffff\1\75\6\uffff\1\143\1\151\1\162\1\145\1\151\1"+
        "\60\1\76\2\uffff\1\144\1\60\1\164\1\uffff\1\143\1\154\1\141\1\147"+
        "\1\163\2\60\1\145\1\165\1\154\1\164\1\163\1\162\1\154\2\163\1\145"+
        "\2\60\2\137\2\155\2\163\2\147\2\60\2\uffff";
    static final String DFA26_maxS =
        "\1\u2265\4\uffff\1\165\1\157\5\uffff\1\167\1\uffff\1\170\1\uffff"+
        "\1\156\1\uffff\1\76\1\uffff\1\76\1\uffff\1\156\1\uffff\1\162\1\uffff"+
        "\1\157\1\75\3\uffff\1\75\6\uffff\1\143\1\151\1\162\1\145\1\151\1"+
        "\172\1\76\2\uffff\1\144\1\172\1\164\1\uffff\1\143\1\154\1\141\1"+
        "\161\1\163\2\172\1\145\1\165\1\154\1\164\1\163\1\162\1\154\2\163"+
        "\1\145\2\172\2\137\2\155\2\163\2\147\2\172\2\uffff";
    static final String DFA26_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\2\uffff\1\7\1\10\1\11\1\12\1\13\1\uffff"+
        "\1\14\1\uffff\1\15\1\uffff\1\16\1\uffff\1\17\1\uffff\1\20\1\uffff"+
        "\1\21\1\uffff\1\22\2\uffff\1\23\1\25\1\27\1\uffff\1\31\1\32\1\33"+
        "\1\34\1\35\1\36\7\uffff\1\26\1\24\3\uffff\1\30\35\uffff\1\5\1\6";
    static final String DFA26_specialS =
        "\122\uffff}>";
    static final String[] DFA26_transitionS = {
            "\2\45\2\uffff\1\45\22\uffff\1\45\1\33\1\43\1\44\4\uffff\1\1"+
            "\1\2\1\12\1\10\1\4\1\11\1\42\1\13\12\42\1\7\1\3\1\22\1\24\1"+
            "\37\2\uffff\32\41\1\uffff\1\14\4\uffff\1\26\3\41\1\16\1\6\2"+
            "\41\1\20\4\41\1\32\1\30\3\41\1\5\7\41\61\uffff\1\34\u2125\uffff"+
            "\1\25\1\uffff\1\23\53\uffff\1\15\2\uffff\1\17\6\uffff\1\21\34"+
            "\uffff\1\27\1\31\67\uffff\1\35\3\uffff\1\36\1\40",
            "",
            "",
            "",
            "",
            "\1\46",
            "\1\47\15\uffff\1\50",
            "",
            "",
            "",
            "",
            "",
            "\1\23\5\uffff\1\25\22\uffff\1\17\1\15\1\40\1\uffff\1\21\2\uffff"+
            "\1\36\1\uffff\1\51\7\uffff\1\31\1\27",
            "",
            "\1\52",
            "",
            "\1\53",
            "",
            "\1\54\1\35",
            "",
            "\1\25",
            "",
            "\1\57",
            "",
            "\1\60",
            "",
            "\1\61",
            "\1\35",
            "",
            "",
            "",
            "\1\40",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\23",
            "",
            "",
            "\1\70",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\71",
            "",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\34\11\uffff\1\35",
            "\1\75",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            ""
    };

    static final short[] DFA26_eot = DFA.unpackEncodedString(DFA26_eotS);
    static final short[] DFA26_eof = DFA.unpackEncodedString(DFA26_eofS);
    static final char[] DFA26_min = DFA.unpackEncodedStringToUnsignedChars(DFA26_minS);
    static final char[] DFA26_max = DFA.unpackEncodedStringToUnsignedChars(DFA26_maxS);
    static final short[] DFA26_accept = DFA.unpackEncodedString(DFA26_acceptS);
    static final short[] DFA26_special = DFA.unpackEncodedString(DFA26_specialS);
    static final short[][] DFA26_transition;

    static {
        int numStates = DFA26_transitionS.length;
        DFA26_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA26_transition[i] = DFA.unpackEncodedString(DFA26_transitionS[i]);
        }
    }

    class DFA26 extends DFA {

        public DFA26(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 26;
            this.eot = DFA26_eot;
            this.eof = DFA26_eof;
            this.min = DFA26_min;
            this.max = DFA26_max;
            this.accept = DFA26_accept;
            this.special = DFA26_special;
            this.transition = DFA26_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( LPAREN | RPAREN | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | FORALL | EXISTS | IN | EQUIVALENCE | IMPLICATION | AND | OR | NOT | EQ | NE | LT | LE | GT | GE | ID | NUMBER | STRING | COMMENT | WHITESPACE );";
        }
    }
 

}
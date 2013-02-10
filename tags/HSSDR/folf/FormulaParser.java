// $ANTLR 3.2 Sep 23, 2009 12:02:23 Formula.g 2010-11-23 21:34:54

    package folf;
    import java.util.List;
    import java.util.Map;
    import java.util.Set;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.LinkedHashMap;
    import java.util.HashSet;
    import java.util.LinkedHashSet;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class FormulaParser extends org.antlr.runtime.Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "LPAREN", "RPAREN", "ID", "EQUIVALENCE", "STRING", "IN", "FORALL", "EXISTS", "IMPLICATION", "OR", "AND", "NOT", "EQ", "NE", "LT", "LE", "GT", "GE", "NUMBER", "EXPONENT", "ESC_SEQ", "OCTAL_ESC", "UNICODE_ESC", "HEX_DIGIT", "COMMENT", "WHITESPACE", "';'", "','", "'success_msg'", "'failure_msg'", "':'", "'+'", "'-'", "'*'", "'/'"
    };
    public static final int GE=21;
    public static final int EXPONENT=23;
    public static final int LT=18;
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
    public static final int ESC_SEQ=24;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int IN=9;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int OR=13;
    public static final int EQUIVALENCE=7;
    public static final int GT=20;
    public static final int IMPLICATION=12;
    public static final int EXISTS=11;
    public static final int EQ=16;
    public static final int COMMENT=28;
    public static final int LE=19;
    public static final int STRING=8;
    public static final int NE=17;

    // delegates
    // delegators


        public FormulaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public FormulaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return FormulaParser.tokenNames; }
    public String getGrammarFileName() { return "Formula.g"; }


        // zmienne ustawiane z zewnatrz przez folf.Parser

        folf.Parser owner;
        Vocabulary voc;
        boolean allowNumbers;
        boolean allowStrings;
        FOLF outerFOLF;
        Suite outerSuite;

        // zmienne za ktorych ustawienie odpowiedzialny jest niniejszy kod

        LinkedHashMap<String, FOLF.Variable> visibleVariables;
        HashSet<String> out_of_scope_variables;
        ArrayList<FOLF.Variable> allVariables;

        void resetVarLists()
        {
            visibleVariables = new LinkedHashMap<String, FOLF.Variable>();
            out_of_scope_variables = new HashSet<String>();
            allVariables = new ArrayList<FOLF.Variable>();
        }

        void updateOuterFOLF(FOLF.Formula form)
        {
            outerFOLF.formula = form;
            allVariables.trimToSize();
            outerFOLF.allVariables = allVariables;
            outerFOLF.freeVariables =
                    new ArrayList<FOLF.Variable>(visibleVariables.values());
        }

        boolean isRelation(Token t)
        {
            return t.getType() == ID &&
                    voc.getType(t.getText()) == Vocabulary.RELATION;
        }

        boolean isFunction(Token t)
        {
            return t.getType() == ID &&
                    voc.getType(t.getText()) == Vocabulary.FUNCTION;
        }

        boolean isConstant(Token t)
        {
            return t.getType() == ID &&
                    voc.getType(t.getText()) == Vocabulary.CONSTANT;
        }

        boolean isUnknown(Token t)
        {
            return t.getType() == ID &&
                    voc.getType(t.getText()) == Vocabulary.UNKNOWN;
        }

        int arity(Token t)
        {
            return t.getType() == ID ? voc.getArity(t.getText()) : -1;
        }

        String unescape(String s)
        {
            if (s == null)
                return null;
            return s.substring(1, s.length() - 1); // utnij pierwszy i ostatni
            // *** WRITEME *** \n, \t, \001, itp.
        }

        // nadpisane metody ANTLR-a

        public void emitErrorMessage(String msg)
        {
            owner.appendErrorMessage(msg);
        }



    // $ANTLR start "formula"
    // Formula.g:119:1: formula : formula2 ;
    public final void formula() throws RecognitionException {
        try {
            // Formula.g:119:9: ( formula2 )
            // Formula.g:119:11: formula2
            {
            pushFollow(FOLLOW_formula2_in_formula94);
            formula2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "formula"


    // $ANTLR start "sentence"
    // Formula.g:121:1: sentence : sentence2 ;
    public final void sentence() throws RecognitionException {
        try {
            // Formula.g:121:10: ( sentence2 )
            // Formula.g:121:12: sentence2
            {
            pushFollow(FOLLOW_sentence2_in_sentence103);
            sentence2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "sentence"


    // $ANTLR start "definition"
    // Formula.g:123:1: definition : definition2 ;
    public final void definition() throws RecognitionException {
        try {
            // Formula.g:123:12: ( definition2 )
            // Formula.g:123:14: definition2
            {
            pushFollow(FOLLOW_definition2_in_definition112);
            definition2();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "definition"


    // $ANTLR start "suite"
    // Formula.g:125:1: suite : suite_entry[defs,sens] ( ';' suite_entry[defs,sens] )* ( ';' )? ;
    public final void suite() throws RecognitionException {

            // slownik bedzie rozszerzany o nowe symbole, nie modyfikuj oryginalnego
            voc = new Vocabulary(voc);
            ArrayList<Definition> defs = new ArrayList<Definition>();
            ArrayList<Sentence> sens = new ArrayList<Sentence>();

        try {
            // Formula.g:131:3: ( suite_entry[defs,sens] ( ';' suite_entry[defs,sens] )* ( ';' )? )
            // Formula.g:132:5: suite_entry[defs,sens] ( ';' suite_entry[defs,sens] )* ( ';' )?
            {
            pushFollow(FOLLOW_suite_entry_in_suite130);
            suite_entry(defs, sens);

            state._fsp--;
            if (state.failed) return ;
            // Formula.g:132:28: ( ';' suite_entry[defs,sens] )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==30) ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1==LPAREN||LA1_1==ID||LA1_1==STRING||(LA1_1>=FORALL && LA1_1<=EXISTS)||LA1_1==NOT||LA1_1==NUMBER||(LA1_1>=32 && LA1_1<=33)||(LA1_1>=35 && LA1_1<=36)) ) {
                        alt1=1;
                    }


                }


                switch (alt1) {
            	case 1 :
            	    // Formula.g:132:30: ';' suite_entry[defs,sens]
            	    {
            	    match(input,30,FOLLOW_30_in_suite135); if (state.failed) return ;
            	    pushFollow(FOLLOW_suite_entry_in_suite137);
            	    suite_entry(defs, sens);

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // Formula.g:132:60: ( ';' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==30) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // Formula.g:132:60: ';'
                    {
                    match(input,30,FOLLOW_30_in_suite143); if (state.failed) return ;

                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      if (sens.size() == 0)
                          throw new FailedPredicateException(input, "suite",
                                      "must contain at least one test");
                      outerSuite.localRelations = defs.toArray(new Definition[defs.size()]);
                      outerSuite.tests = sens.toArray(new Sentence[sens.size()]);
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "suite"


    // $ANTLR start "suite_entry"
    // Formula.g:144:1: suite_entry[List<Definition> dlist, List<Sentence> slist] : ({...}? definition2 | sentence2 );
    public final void suite_entry(List<Definition> dlist, List<Sentence> slist) throws RecognitionException {
         Definition d = null; Sentence s = null; 
        try {
            // Formula.g:145:51: ({...}? definition2 | sentence2 )
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // Formula.g:146:5: {...}? definition2
                    {
                    if ( !(( isUnknown(input.LT(1)) && input.LA(2) == LPAREN )) ) {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        throw new FailedPredicateException(input, "suite_entry", " isUnknown(input.LT(1)) && input.LA(2) == LPAREN ");
                    }
                    if ( state.backtracking==0 ) {
                       d = new Definition(); outerFOLF = d; 
                    }
                    pushFollow(FOLLOW_definition2_in_suite_entry184);
                    definition2();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {

                              if (d.formula != null) {
                                  voc.addRelation(d.getName(), d.getArity());
                                  dlist.add(d);
                              }
                          
                    }

                    }
                    break;
                case 2 :
                    // Formula.g:156:5: sentence2
                    {
                    if ( state.backtracking==0 ) {
                       s = new Sentence(); outerFOLF = s; 
                    }
                    pushFollow(FOLLOW_sentence2_in_suite_entry204);
                    sentence2();

                    state._fsp--;
                    if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       if (s.formula != null) slist.add(s); 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "suite_entry"


    // $ANTLR start "definition2"
    // Formula.g:161:1: definition2 : ID '(' parameter_list ')' EQUIVALENCE f= implication ;
    public final void definition2() throws RecognitionException {
        Token ID1=null;
        FOLF.Formula f = null;



                resetVarLists();
                int arity = -1;
                // nizej modyfikujemy slownik, ale to ma byc chwilowa modyfikacja
                Vocabulary orig_voc = voc;
                voc = new Vocabulary(voc);

        try {
            // Formula.g:172:1: ( ID '(' parameter_list ')' EQUIVALENCE f= implication )
            // Formula.g:173:5: ID '(' parameter_list ')' EQUIVALENCE f= implication
            {
            ID1=(Token)match(input,ID,FOLLOW_ID_in_definition2233); if (state.failed) return ;
            if ( state.backtracking==0 ) {

                      if (! isUnknown(ID1))
                          throw new FailedPredicateException(input, "definition",
                                  "redefinition of a known symbol");
                  
            }
            match(input,LPAREN,FOLLOW_LPAREN_in_definition2237); if (state.failed) return ;
            pushFollow(FOLLOW_parameter_list_in_definition2239);
            parameter_list();

            state._fsp--;
            if (state.failed) return ;
            match(input,RPAREN,FOLLOW_RPAREN_in_definition2241); if (state.failed) return ;
            if ( state.backtracking==0 ) {

                      arity = visibleVariables.size();
                  
            }
            match(input,EQUIVALENCE,FOLLOW_EQUIVALENCE_in_definition2245); if (state.failed) return ;
            if ( state.backtracking==0 ) {

                      // dodajemy nazwe wlasnie definiowanej relacji do slownika, aby dalo
                      // sie definiowac relacje rekurencyjne
                      voc.addRelation((ID1!=null?ID1.getText():null), arity);
                  
            }
            pushFollow(FOLLOW_implication_in_definition2251);
            f=implication();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {

                      if (visibleVariables.size() != arity)
                          throw new FailedPredicateException(input, "definition",
                                  "free variables in definition body");
                      if (visibleVariables.containsKey((ID1!=null?ID1.getText():null)) ||
                                  out_of_scope_variables.contains((ID1!=null?ID1.getText():null)))
                          throw new FailedPredicateException(input, "definition",
                                  "name conflict with a variable");
                      updateOuterFOLF(f);
                      ((Definition) outerFOLF).name = (ID1!=null?ID1.getText():null);
                  
            }

            }

            if ( state.backtracking==0 ) {

                      voc = orig_voc;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "definition2"


    // $ANTLR start "parameter_list"
    // Formula.g:196:1: parameter_list : parameter ( ',' parameter )* ;
    public final void parameter_list() throws RecognitionException {
        try {
            // Formula.g:196:16: ( parameter ( ',' parameter )* )
            // Formula.g:197:5: parameter ( ',' parameter )*
            {
            pushFollow(FOLLOW_parameter_in_parameter_list266);
            parameter();

            state._fsp--;
            if (state.failed) return ;
            // Formula.g:197:15: ( ',' parameter )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==31) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Formula.g:197:17: ',' parameter
            	    {
            	    match(input,31,FOLLOW_31_in_parameter_list270); if (state.failed) return ;
            	    pushFollow(FOLLOW_parameter_in_parameter_list272);
            	    parameter();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "parameter_list"


    // $ANTLR start "parameter"
    // Formula.g:200:1: parameter : ID ;
    public final void parameter() throws RecognitionException {
        Token ID2=null;

        try {
            // Formula.g:200:11: ( ID )
            // Formula.g:201:5: ID
            {
            ID2=(Token)match(input,ID,FOLLOW_ID_in_parameter288); if (state.failed) return ;
            if ( state.backtracking==0 ) {

                      if (! isUnknown(ID2))
                          throw new FailedPredicateException(input, "parameter",
                                  "vocabulary symbol cannot be used as a variable");
                      if (visibleVariables.get((ID2!=null?ID2.getText():null)) != null)
                          throw new FailedPredicateException(input, "parameter",
                                  "duplicate parameter names");
                      FOLF.Variable var = visibleVariables.get((ID2!=null?ID2.getText():null));
                      visibleVariables.put((ID2!=null?ID2.getText():null), var);
                      allVariables.add(var);
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "parameter"


    // $ANTLR start "sentence2"
    // Formula.g:214:1: sentence2 : (m= messages )? f= general_formula ;
    public final void sentence2() throws RecognitionException {
        FormulaParser.messages_return m = null;

        FOLF.Formula f = null;


         resetVarLists(); 
        try {
            // Formula.g:216:1: ( (m= messages )? f= general_formula )
            // Formula.g:217:5: (m= messages )? f= general_formula
            {
            // Formula.g:217:6: (m= messages )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=32 && LA5_0<=33)) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // Formula.g:217:6: m= messages
                    {
                    pushFollow(FOLLOW_messages_in_sentence2310);
                    m=messages();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_general_formula_in_sentence2315);
            f=general_formula();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {

                      if (visibleVariables.size() == 0)
                          updateOuterFOLF(f);
                      else
                          throw new FailedPredicateException(input, "sentence",
                                      "free variables are present");
                      outerFOLF.successMsg = (m!=null?m.s_msg:null);
                      outerFOLF.failureMsg = (m!=null?m.f_msg:null);
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "sentence2"


    // $ANTLR start "formula2"
    // Formula.g:229:1: formula2 : f= general_formula ;
    public final void formula2() throws RecognitionException {
        FOLF.Formula f = null;


         resetVarLists(); 
        try {
            // Formula.g:231:1: (f= general_formula )
            // Formula.g:232:5: f= general_formula
            {
            pushFollow(FOLLOW_general_formula_in_formula2341);
            f=general_formula();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {

                      updateOuterFOLF(f);
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "formula2"

    public static class messages_return extends org.antlr.runtime.ParserRuleReturnScope {
        public String s_msg;
        public String f_msg;
    };

    // $ANTLR start "messages"
    // Formula.g:240:1: messages returns [String s_msg, String f_msg] : ( 'success_msg' s= STRING ( 'failure_msg' f= STRING )? | 'failure_msg' f= STRING ( 'success_msg' s= STRING )? ) ;
    public final FormulaParser.messages_return messages() throws RecognitionException {
        FormulaParser.messages_return retval = new FormulaParser.messages_return();
        retval.start = input.LT(1);

        Token s=null;
        Token f=null;

        try {
            // Formula.g:240:47: ( ( 'success_msg' s= STRING ( 'failure_msg' f= STRING )? | 'failure_msg' f= STRING ( 'success_msg' s= STRING )? ) )
            // Formula.g:241:5: ( 'success_msg' s= STRING ( 'failure_msg' f= STRING )? | 'failure_msg' f= STRING ( 'success_msg' s= STRING )? )
            {
            // Formula.g:241:5: ( 'success_msg' s= STRING ( 'failure_msg' f= STRING )? | 'failure_msg' f= STRING ( 'success_msg' s= STRING )? )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==32) ) {
                alt8=1;
            }
            else if ( (LA8_0==33) ) {
                alt8=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // Formula.g:242:9: 'success_msg' s= STRING ( 'failure_msg' f= STRING )?
                    {
                    match(input,32,FOLLOW_32_in_messages376); if (state.failed) return retval;
                    s=(Token)match(input,STRING,FOLLOW_STRING_in_messages380); if (state.failed) return retval;
                    // Formula.g:242:32: ( 'failure_msg' f= STRING )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==33) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // Formula.g:242:34: 'failure_msg' f= STRING
                            {
                            match(input,33,FOLLOW_33_in_messages384); if (state.failed) return retval;
                            f=(Token)match(input,STRING,FOLLOW_STRING_in_messages388); if (state.failed) return retval;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // Formula.g:243:11: 'failure_msg' f= STRING ( 'success_msg' s= STRING )?
                    {
                    match(input,33,FOLLOW_33_in_messages403); if (state.failed) return retval;
                    f=(Token)match(input,STRING,FOLLOW_STRING_in_messages407); if (state.failed) return retval;
                    // Formula.g:243:34: ( 'success_msg' s= STRING )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==32) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // Formula.g:243:36: 'success_msg' s= STRING
                            {
                            match(input,32,FOLLOW_32_in_messages411); if (state.failed) return retval;
                            s=(Token)match(input,STRING,FOLLOW_STRING_in_messages415); if (state.failed) return retval;

                            }
                            break;

                    }


                    }
                    break;

            }

            if ( state.backtracking==0 ) {

                      retval.s_msg = unescape((s!=null?s.getText():null));     // null jesli nie dopasowano
                      retval.f_msg = unescape((f!=null?f.getText():null));
                  
            }

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "messages"


    // $ANTLR start "general_formula"
    // Formula.g:250:1: general_formula returns [FOLF.Formula form] : f= equivalence ;
    public final FOLF.Formula general_formula() throws RecognitionException {
        FOLF.Formula form = null;

        FOLF.Formula f = null;


        try {
            // Formula.g:250:45: (f= equivalence )
            // Formula.g:251:5: f= equivalence
            {
            pushFollow(FOLLOW_equivalence_in_general_formula445);
            f=equivalence();

            state._fsp--;
            if (state.failed) return form;
            if ( state.backtracking==0 ) {
               form = f; 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return form;
    }
    // $ANTLR end "general_formula"


    // $ANTLR start "quantified_formula"
    // Formula.g:301:1: quantified_formula returns [FOLF.Formula form] : q= quantifier quantified_var[names, vars, saved] ( ',' quantified_var[names, vars, saved] )* ( IN quantifier_range[vars, ranges] ',' quantified_var[names, vars, saved] ( ',' quantified_var[names, vars, saved] )* )* ( IN quantifier_range[vars, ranges] )? ':' f= general_formula ;
    public final FOLF.Formula quantified_formula() throws RecognitionException {
        FOLF.Formula form = null;

        int q = 0;

        FOLF.Formula f = null;



            ArrayList<String> names = new ArrayList<String>();
            ArrayList<FOLF.Variable> vars = new ArrayList<FOLF.Variable>();
            ArrayList<String> ranges = new ArrayList<String>();
            ArrayList<FOLF.Variable> saved = new ArrayList<FOLF.Variable>();
            FOLF.Variable var;

        try {
            // Formula.g:308:3: (q= quantifier quantified_var[names, vars, saved] ( ',' quantified_var[names, vars, saved] )* ( IN quantifier_range[vars, ranges] ',' quantified_var[names, vars, saved] ( ',' quantified_var[names, vars, saved] )* )* ( IN quantifier_range[vars, ranges] )? ':' f= general_formula )
            // Formula.g:309:5: q= quantifier quantified_var[names, vars, saved] ( ',' quantified_var[names, vars, saved] )* ( IN quantifier_range[vars, ranges] ',' quantified_var[names, vars, saved] ( ',' quantified_var[names, vars, saved] )* )* ( IN quantifier_range[vars, ranges] )? ':' f= general_formula
            {
            pushFollow(FOLLOW_quantifier_in_quantified_formula478);
            q=quantifier();

            state._fsp--;
            if (state.failed) return form;
            pushFollow(FOLLOW_quantified_var_in_quantified_formula484);
            quantified_var(names, vars, saved);

            state._fsp--;
            if (state.failed) return form;
            // Formula.g:311:5: ( ',' quantified_var[names, vars, saved] )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==31) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Formula.g:311:7: ',' quantified_var[names, vars, saved]
            	    {
            	    match(input,31,FOLLOW_31_in_quantified_formula493); if (state.failed) return form;
            	    pushFollow(FOLLOW_quantified_var_in_quantified_formula495);
            	    quantified_var(names, vars, saved);

            	    state._fsp--;
            	    if (state.failed) return form;

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // Formula.g:312:5: ( IN quantifier_range[vars, ranges] ',' quantified_var[names, vars, saved] ( ',' quantified_var[names, vars, saved] )* )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==IN) ) {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1==ID) ) {
                        int LA11_3 = input.LA(3);

                        if ( (LA11_3==31) ) {
                            alt11=1;
                        }


                    }


                }


                switch (alt11) {
            	case 1 :
            	    // Formula.g:313:9: IN quantifier_range[vars, ranges] ',' quantified_var[names, vars, saved] ( ',' quantified_var[names, vars, saved] )*
            	    {
            	    match(input,IN,FOLLOW_IN_in_quantified_formula515); if (state.failed) return form;
            	    pushFollow(FOLLOW_quantifier_range_in_quantified_formula517);
            	    quantifier_range(vars, ranges);

            	    state._fsp--;
            	    if (state.failed) return form;
            	    match(input,31,FOLLOW_31_in_quantified_formula520); if (state.failed) return form;
            	    pushFollow(FOLLOW_quantified_var_in_quantified_formula530);
            	    quantified_var(names, vars, saved);

            	    state._fsp--;
            	    if (state.failed) return form;
            	    // Formula.g:315:9: ( ',' quantified_var[names, vars, saved] )*
            	    loop10:
            	    do {
            	        int alt10=2;
            	        int LA10_0 = input.LA(1);

            	        if ( (LA10_0==31) ) {
            	            alt10=1;
            	        }


            	        switch (alt10) {
            	    	case 1 :
            	    	    // Formula.g:315:11: ',' quantified_var[names, vars, saved]
            	    	    {
            	    	    match(input,31,FOLLOW_31_in_quantified_formula543); if (state.failed) return form;
            	    	    pushFollow(FOLLOW_quantified_var_in_quantified_formula545);
            	    	    quantified_var(names, vars, saved);

            	    	    state._fsp--;
            	    	    if (state.failed) return form;

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop10;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            // Formula.g:317:5: ( IN quantifier_range[vars, ranges] )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==IN) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Formula.g:317:7: IN quantifier_range[vars, ranges]
                    {
                    match(input,IN,FOLLOW_IN_in_quantified_formula564); if (state.failed) return form;
                    pushFollow(FOLLOW_quantifier_range_in_quantified_formula566);
                    quantifier_range(vars, ranges);

                    state._fsp--;
                    if (state.failed) return form;

                    }
                    break;

            }

            match(input,34,FOLLOW_34_in_quantified_formula576); if (state.failed) return form;
            pushFollow(FOLLOW_general_formula_in_quantified_formula580);
            f=general_formula();

            state._fsp--;
            if (state.failed) return form;
            if ( state.backtracking==0 ) {

                      while (ranges.size() < vars.size()) {
                          ranges.add(null);
                      }
                      out_of_scope_variables.addAll(names);
                      for (int i = 0; i < names.size(); ++i) {
                          var = saved.get(i);
                          if (var == null)
                              visibleVariables.remove(names.get(i));
                          else
                              visibleVariables.put(names.get(i), var);
                      }
                      form = outerFOLF.new Quantifier(q, vars, ranges, f);
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return form;
    }
    // $ANTLR end "quantified_formula"


    // $ANTLR start "quantifier"
    // Formula.g:335:1: quantifier returns [int type] : ( FORALL | EXISTS );
    public final int quantifier() throws RecognitionException {
        int type = 0;

        try {
            // Formula.g:335:31: ( FORALL | EXISTS )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==FORALL) ) {
                alt13=1;
            }
            else if ( (LA13_0==EXISTS) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // Formula.g:336:5: FORALL
                    {
                    match(input,FORALL,FOLLOW_FORALL_in_quantifier603); if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                       type = FOLF.UNIVERSAL; 
                    }

                    }
                    break;
                case 2 :
                    // Formula.g:337:7: EXISTS
                    {
                    match(input,EXISTS,FOLLOW_EXISTS_in_quantifier613); if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                       type = FOLF.EXISTENTIAL; 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "quantifier"


    // $ANTLR start "quantified_var"
    // Formula.g:340:1: quantified_var[List<String> names, List<FOLF.Variable> vars,\n List<FOLF.Variable> saved] : v= ID ;
    public final void quantified_var(List<String> names, List<FOLF.Variable> vars, List<FOLF.Variable> saved) throws RecognitionException {
        Token v=null;


            FOLF.Variable var, old_var;

        try {
            // Formula.g:344:3: (v= ID )
            // Formula.g:345:5: v= ID
            {
            v=(Token)match(input,ID,FOLLOW_ID_in_quantified_var637); if (state.failed) return ;
            if ( state.backtracking==0 ) {

                      if (! isUnknown(v)) {
                          throw new FailedPredicateException(input, "quantified_formula",
                                      "vocabulary symbol cannot be used as a variable");
                      }
                      old_var = visibleVariables.get((v!=null?v.getText():null));
                      saved.add(old_var);
                      var = outerFOLF.new Variable((v!=null?v.getText():null));
                      names.add((v!=null?v.getText():null));
                      vars.add(var);
                      visibleVariables.put((v!=null?v.getText():null), var);
                      allVariables.add(var);
                      // jesli wykomentujesz ponizsze throw gramatyka bedzie dopuszczac
                      // konstrukcje typu 'forall x : (forall x : x > 13) and x < 44'
                      if (old_var != null) {
                          throw new FailedPredicateException(input, "quantified_formula",
                                      "conflicting variable names");
                      }
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "quantified_var"


    // $ANTLR start "quantifier_range"
    // Formula.g:367:1: quantifier_range[List<FOLF.Variable> vars, List<String> ranges] : r= ID ;
    public final void quantifier_range(List<FOLF.Variable> vars, List<String> ranges) throws RecognitionException {
        Token r=null;

        try {
            // Formula.g:368:1: (r= ID )
            // Formula.g:369:5: r= ID
            {
            r=(Token)match(input,ID,FOLLOW_ID_in_quantifier_range660); if (state.failed) return ;
            if ( state.backtracking==0 ) {

                      if (arity(r) != 1) {
                          throw new FailedPredicateException(input, "quantified_formula",
                                  "only arity 1 relations allowed as quantification ranges");
                      }
                      while (ranges.size() < vars.size()) {
                          ranges.add((r!=null?r.getText():null));
                      }
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "quantifier_range"


    // $ANTLR start "equivalence"
    // Formula.g:381:1: equivalence returns [FOLF.Formula form] : f= implication ( EQUIVALENCE f= implication )? ;
    public final FOLF.Formula equivalence() throws RecognitionException {
        FOLF.Formula form = null;

        FOLF.Formula f = null;


        try {
            // Formula.g:381:41: (f= implication ( EQUIVALENCE f= implication )? )
            // Formula.g:382:5: f= implication ( EQUIVALENCE f= implication )?
            {
            pushFollow(FOLLOW_implication_in_equivalence685);
            f=implication();

            state._fsp--;
            if (state.failed) return form;
            if ( state.backtracking==0 ) {
               
                      form = f;
                  
            }
            // Formula.g:384:7: ( EQUIVALENCE f= implication )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==EQUIVALENCE) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // Formula.g:384:9: EQUIVALENCE f= implication
                    {
                    match(input,EQUIVALENCE,FOLLOW_EQUIVALENCE_in_equivalence691); if (state.failed) return form;
                    pushFollow(FOLLOW_implication_in_equivalence695);
                    f=implication();

                    state._fsp--;
                    if (state.failed) return form;
                    if ( state.backtracking==0 ) {

                              form = outerFOLF.new Connective(FOLF.BICONDITIONAL, form, f);
                          
                    }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return form;
    }
    // $ANTLR end "equivalence"


    // $ANTLR start "implication"
    // Formula.g:389:1: implication returns [FOLF.Formula form] : f= alternative ( IMPLICATION f= alternative )? ;
    public final FOLF.Formula implication() throws RecognitionException {
        FOLF.Formula form = null;

        FOLF.Formula f = null;


        try {
            // Formula.g:389:41: (f= alternative ( IMPLICATION f= alternative )? )
            // Formula.g:390:5: f= alternative ( IMPLICATION f= alternative )?
            {
            pushFollow(FOLLOW_alternative_in_implication719);
            f=alternative();

            state._fsp--;
            if (state.failed) return form;
            if ( state.backtracking==0 ) {
               
                      form = f;
                  
            }
            // Formula.g:392:7: ( IMPLICATION f= alternative )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==IMPLICATION) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // Formula.g:392:9: IMPLICATION f= alternative
                    {
                    match(input,IMPLICATION,FOLLOW_IMPLICATION_in_implication725); if (state.failed) return form;
                    pushFollow(FOLLOW_alternative_in_implication729);
                    f=alternative();

                    state._fsp--;
                    if (state.failed) return form;
                    if ( state.backtracking==0 ) {

                              form = outerFOLF.new Connective(FOLF.IMPLICATION, form, f);
                          
                    }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return form;
    }
    // $ANTLR end "implication"


    // $ANTLR start "alternative"
    // Formula.g:397:1: alternative returns [FOLF.Formula form] : f= conjunction ( OR f= conjunction )* ;
    public final FOLF.Formula alternative() throws RecognitionException {
        FOLF.Formula form = null;

        FOLF.Formula f = null;


        try {
            // Formula.g:397:41: (f= conjunction ( OR f= conjunction )* )
            // Formula.g:398:5: f= conjunction ( OR f= conjunction )*
            {
            pushFollow(FOLLOW_conjunction_in_alternative753);
            f=conjunction();

            state._fsp--;
            if (state.failed) return form;
            if ( state.backtracking==0 ) {

                      form = f;
                  
            }
            // Formula.g:400:7: ( OR f= conjunction )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==OR) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // Formula.g:400:9: OR f= conjunction
            	    {
            	    match(input,OR,FOLLOW_OR_in_alternative759); if (state.failed) return form;
            	    pushFollow(FOLLOW_conjunction_in_alternative763);
            	    f=conjunction();

            	    state._fsp--;
            	    if (state.failed) return form;
            	    if ( state.backtracking==0 ) {

            	              form = outerFOLF.new Connective(FOLF.DISJUNCTION, form, f);
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return form;
    }
    // $ANTLR end "alternative"


    // $ANTLR start "conjunction"
    // Formula.g:405:1: conjunction returns [FOLF.Formula form] : f= primary_formula ( AND f= primary_formula )* ;
    public final FOLF.Formula conjunction() throws RecognitionException {
        FOLF.Formula form = null;

        FOLF.Formula f = null;


        try {
            // Formula.g:405:41: (f= primary_formula ( AND f= primary_formula )* )
            // Formula.g:406:5: f= primary_formula ( AND f= primary_formula )*
            {
            pushFollow(FOLLOW_primary_formula_in_conjunction787);
            f=primary_formula();

            state._fsp--;
            if (state.failed) return form;
            if ( state.backtracking==0 ) {

                      form = f;
                  
            }
            // Formula.g:408:7: ( AND f= primary_formula )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==AND) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // Formula.g:408:9: AND f= primary_formula
            	    {
            	    match(input,AND,FOLLOW_AND_in_conjunction793); if (state.failed) return form;
            	    pushFollow(FOLLOW_primary_formula_in_conjunction797);
            	    f=primary_formula();

            	    state._fsp--;
            	    if (state.failed) return form;
            	    if ( state.backtracking==0 ) {

            	              form = outerFOLF.new Connective(FOLF.CONJUNCTION, form, f);
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return form;
    }
    // $ANTLR end "conjunction"


    // $ANTLR start "primary_formula"
    // Formula.g:413:1: primary_formula returns [FOLF.Formula form] : ( ( '(' general_formula ')' )=> '(' f= general_formula ')' | f= quantified_formula | NOT f= primary_formula | {...}?f= relation_invocation | a= general_term c= comparision_op b= general_term );
    public final FOLF.Formula primary_formula() throws RecognitionException {
        FOLF.Formula form = null;

        FOLF.Formula f = null;

        FOLF.Term a = null;

        int c = 0;

        FOLF.Term b = null;


        try {
            // Formula.g:413:45: ( ( '(' general_formula ')' )=> '(' f= general_formula ')' | f= quantified_formula | NOT f= primary_formula | {...}?f= relation_invocation | a= general_term c= comparision_op b= general_term )
            int alt18=5;
            alt18 = dfa18.predict(input);
            switch (alt18) {
                case 1 :
                    // Formula.g:414:5: ( '(' general_formula ')' )=> '(' f= general_formula ')'
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_primary_formula830); if (state.failed) return form;
                    pushFollow(FOLLOW_general_formula_in_primary_formula834);
                    f=general_formula();

                    state._fsp--;
                    if (state.failed) return form;
                    match(input,RPAREN,FOLLOW_RPAREN_in_primary_formula836); if (state.failed) return form;
                    if ( state.backtracking==0 ) {
                       form = f; 
                    }

                    }
                    break;
                case 2 :
                    // Formula.g:417:5: f= quantified_formula
                    {
                    pushFollow(FOLLOW_quantified_formula_in_primary_formula852);
                    f=quantified_formula();

                    state._fsp--;
                    if (state.failed) return form;
                    if ( state.backtracking==0 ) {
                       form = f; 
                    }

                    }
                    break;
                case 3 :
                    // Formula.g:419:5: NOT f= primary_formula
                    {
                    match(input,NOT,FOLLOW_NOT_in_primary_formula862); if (state.failed) return form;
                    pushFollow(FOLLOW_primary_formula_in_primary_formula866);
                    f=primary_formula();

                    state._fsp--;
                    if (state.failed) return form;
                    if ( state.backtracking==0 ) {

                              form = outerFOLF.new Connective(FOLF.NEGATION, f, null);
                          
                    }

                    }
                    break;
                case 4 :
                    // Formula.g:423:5: {...}?f= relation_invocation
                    {
                    if ( !(( isRelation(input.LT(1)) )) ) {
                        if (state.backtracking>0) {state.failed=true; return form;}
                        throw new FailedPredicateException(input, "primary_formula", " isRelation(input.LT(1)) ");
                    }
                    pushFollow(FOLLOW_relation_invocation_in_primary_formula880);
                    f=relation_invocation();

                    state._fsp--;
                    if (state.failed) return form;
                    if ( state.backtracking==0 ) {
                       form = f; 
                    }

                    }
                    break;
                case 5 :
                    // Formula.g:426:5: a= general_term c= comparision_op b= general_term
                    {
                    pushFollow(FOLLOW_general_term_in_primary_formula896);
                    a=general_term();

                    state._fsp--;
                    if (state.failed) return form;
                    pushFollow(FOLLOW_comparision_op_in_primary_formula900);
                    c=comparision_op();

                    state._fsp--;
                    if (state.failed) return form;
                    pushFollow(FOLLOW_general_term_in_primary_formula904);
                    b=general_term();

                    state._fsp--;
                    if (state.failed) return form;
                    if ( state.backtracking==0 ) {

                              form = outerFOLF.new Comparator(c, a, b);
                          
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return form;
    }
    // $ANTLR end "primary_formula"


    // $ANTLR start "comparision_op"
    // Formula.g:433:1: comparision_op returns [int type] : ( EQ | NE | {...}? => LT | {...}? => LE | {...}? => GT | {...}? => GE );
    public final int comparision_op() throws RecognitionException {
        int type = 0;

        try {
            // Formula.g:433:35: ( EQ | NE | {...}? => LT | {...}? => LE | {...}? => GT | {...}? => GE )
            int alt19=6;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==EQ) ) {
                alt19=1;
            }
            else if ( (LA19_0==NE) ) {
                alt19=2;
            }
            else if ( (LA19_0==LT) && (( allowNumbers ))) {
                alt19=3;
            }
            else if ( (LA19_0==LE) && (( allowNumbers ))) {
                alt19=4;
            }
            else if ( (LA19_0==GT) && (( allowNumbers ))) {
                alt19=5;
            }
            else if ( (LA19_0==GE) && (( allowNumbers ))) {
                alt19=6;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // Formula.g:434:5: EQ
                    {
                    match(input,EQ,FOLLOW_EQ_in_comparision_op925); if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                       type = FOLF.EQUAL; 
                    }

                    }
                    break;
                case 2 :
                    // Formula.g:435:7: NE
                    {
                    match(input,NE,FOLLOW_NE_in_comparision_op935); if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                       type = FOLF.NOT_EQUAL; 
                    }

                    }
                    break;
                case 3 :
                    // Formula.g:436:7: {...}? => LT
                    {
                    if ( !(( allowNumbers )) ) {
                        if (state.backtracking>0) {state.failed=true; return type;}
                        throw new FailedPredicateException(input, "comparision_op", " allowNumbers ");
                    }
                    match(input,LT,FOLLOW_LT_in_comparision_op948); if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                       type = FOLF.LESS_THAN; 
                    }

                    }
                    break;
                case 4 :
                    // Formula.g:437:7: {...}? => LE
                    {
                    if ( !(( allowNumbers )) ) {
                        if (state.backtracking>0) {state.failed=true; return type;}
                        throw new FailedPredicateException(input, "comparision_op", " allowNumbers ");
                    }
                    match(input,LE,FOLLOW_LE_in_comparision_op961); if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                       type = FOLF.LESS_OR_EQUAL; 
                    }

                    }
                    break;
                case 5 :
                    // Formula.g:438:7: {...}? => GT
                    {
                    if ( !(( allowNumbers )) ) {
                        if (state.backtracking>0) {state.failed=true; return type;}
                        throw new FailedPredicateException(input, "comparision_op", " allowNumbers ");
                    }
                    match(input,GT,FOLLOW_GT_in_comparision_op974); if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                       type = FOLF.GREATER_THAN; 
                    }

                    }
                    break;
                case 6 :
                    // Formula.g:439:7: {...}? => GE
                    {
                    if ( !(( allowNumbers )) ) {
                        if (state.backtracking>0) {state.failed=true; return type;}
                        throw new FailedPredicateException(input, "comparision_op", " allowNumbers ");
                    }
                    match(input,GE,FOLLOW_GE_in_comparision_op987); if (state.failed) return type;
                    if ( state.backtracking==0 ) {
                       type = FOLF.GREATER_OR_EQUAL; 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return type;
    }
    // $ANTLR end "comparision_op"


    // $ANTLR start "relation_invocation"
    // Formula.g:442:1: relation_invocation returns [FOLF.Formula form] : ID '(' l= term_list ')' ;
    public final FOLF.Formula relation_invocation() throws RecognitionException {
        FOLF.Formula form = null;

        Token ID3=null;
        List<FOLF.Term> l = null;


        try {
            // Formula.g:442:49: ( ID '(' l= term_list ')' )
            // Formula.g:443:5: ID '(' l= term_list ')'
            {
            ID3=(Token)match(input,ID,FOLLOW_ID_in_relation_invocation1006); if (state.failed) return form;
            match(input,LPAREN,FOLLOW_LPAREN_in_relation_invocation1008); if (state.failed) return form;
            pushFollow(FOLLOW_term_list_in_relation_invocation1012);
            l=term_list();

            state._fsp--;
            if (state.failed) return form;
            match(input,RPAREN,FOLLOW_RPAREN_in_relation_invocation1014); if (state.failed) return form;
            if ( state.backtracking==0 ) {

                      if (! isRelation(ID3)) {
                          throw new FailedPredicateException(input, "relation_invocation",
                                          "not a relation symbol");
                      }
                      if (arity(ID3) != l.size()) {
                          throw new FailedPredicateException(input, "relation_invocation",
                                          "bad number of arguments");
                      }
                      form = outerFOLF.new Predicate((ID3!=null?ID3.getText():null), l);
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return form;
    }
    // $ANTLR end "relation_invocation"


    // $ANTLR start "term_list"
    // Formula.g:457:1: term_list returns [List<FOLF.Term> list] : t= general_term ( ',' t= general_term )* ;
    public final List<FOLF.Term> term_list() throws RecognitionException {
        List<FOLF.Term> list = null;

        FOLF.Term t = null;


        try {
            // Formula.g:457:42: (t= general_term ( ',' t= general_term )* )
            // Formula.g:458:5: t= general_term ( ',' t= general_term )*
            {
            if ( state.backtracking==0 ) {
               list = new ArrayList<FOLF.Term>(); 
            }
            pushFollow(FOLLOW_general_term_in_term_list1045);
            t=general_term();

            state._fsp--;
            if (state.failed) return list;
            if ( state.backtracking==0 ) {
               list.add(t); 
            }
            // Formula.g:460:5: ( ',' t= general_term )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==31) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // Formula.g:460:7: ',' t= general_term
            	    {
            	    match(input,31,FOLLOW_31_in_term_list1055); if (state.failed) return list;
            	    pushFollow(FOLLOW_general_term_in_term_list1059);
            	    t=general_term();

            	    state._fsp--;
            	    if (state.failed) return list;
            	    if ( state.backtracking==0 ) {
            	       list.add(t); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            if ( state.backtracking==0 ) {
               ((ArrayList<FOLF.Term>) list).trimToSize(); 
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return list;
    }
    // $ANTLR end "term_list"


    // $ANTLR start "general_term"
    // Formula.g:464:1: general_term returns [FOLF.Term term] : ({...}? =>t= additive_expr | {...}? =>t= primary_term );
    public final FOLF.Term general_term() throws RecognitionException {
        FOLF.Term term = null;

        FOLF.Term t = null;


        try {
            // Formula.g:464:39: ({...}? =>t= additive_expr | {...}? =>t= primary_term )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( ((LA21_0>=35 && LA21_0<=36)) && (( allowNumbers ))) {
                alt21=1;
            }
            else if ( (LA21_0==LPAREN) && ((( ! allowNumbers )||( allowNumbers )))) {
                int LA21_3 = input.LA(2);

                if ( (( allowNumbers )) ) {
                    alt21=1;
                }
                else if ( (( ! allowNumbers )) ) {
                    alt21=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return term;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 3, input);

                    throw nvae;
                }
            }
            else if ( (LA21_0==ID) && ((( ! allowNumbers )||( allowNumbers )))) {
                int LA21_4 = input.LA(2);

                if ( (( allowNumbers )) ) {
                    alt21=1;
                }
                else if ( (( ! allowNumbers )) ) {
                    alt21=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return term;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 4, input);

                    throw nvae;
                }
            }
            else if ( (LA21_0==NUMBER) && (((( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||( allowNumbers )||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))||(( ! allowNumbers )&&( allowNumbers ))))) {
                int LA21_5 = input.LA(2);

                if ( (( allowNumbers )) ) {
                    alt21=1;
                }
                else if ( ((( ! allowNumbers )&&( allowNumbers ))) ) {
                    alt21=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return term;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 5, input);

                    throw nvae;
                }
            }
            else if ( (LA21_0==STRING) && (((( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))||(( ! allowNumbers )&&( allowStrings ))))) {
                int LA21_6 = input.LA(2);

                if ( ((( allowNumbers )&&( allowStrings ))) ) {
                    alt21=1;
                }
                else if ( ((( ! allowNumbers )&&( allowStrings ))) ) {
                    alt21=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return term;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 6, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return term;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // Formula.g:465:5: {...}? =>t= additive_expr
                    {
                    if ( !(( allowNumbers )) ) {
                        if (state.backtracking>0) {state.failed=true; return term;}
                        throw new FailedPredicateException(input, "general_term", " allowNumbers ");
                    }
                    pushFollow(FOLLOW_additive_expr_in_general_term1092);
                    t=additive_expr();

                    state._fsp--;
                    if (state.failed) return term;
                    if ( state.backtracking==0 ) {
                       term = t; 
                    }

                    }
                    break;
                case 2 :
                    // Formula.g:466:7: {...}? =>t= primary_term
                    {
                    if ( !(( ! allowNumbers )) ) {
                        if (state.backtracking>0) {state.failed=true; return term;}
                        throw new FailedPredicateException(input, "general_term", " ! allowNumbers ");
                    }
                    pushFollow(FOLLOW_primary_term_in_general_term1107);
                    t=primary_term();

                    state._fsp--;
                    if (state.failed) return term;
                    if ( state.backtracking==0 ) {
                       term = t; 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return term;
    }
    // $ANTLR end "general_term"


    // $ANTLR start "additive_expr"
    // Formula.g:469:1: additive_expr returns [FOLF.Term term] : t= multiplicative_expr ( ( '+' | '-' ) t= multiplicative_expr )* ;
    public final FOLF.Term additive_expr() throws RecognitionException {
        FOLF.Term term = null;

        FOLF.Term t = null;


         int type = -1; 
        try {
            // Formula.g:471:1: (t= multiplicative_expr ( ( '+' | '-' ) t= multiplicative_expr )* )
            // Formula.g:472:5: t= multiplicative_expr ( ( '+' | '-' ) t= multiplicative_expr )*
            {
            pushFollow(FOLLOW_multiplicative_expr_in_additive_expr1133);
            t=multiplicative_expr();

            state._fsp--;
            if (state.failed) return term;
            if ( state.backtracking==0 ) {
               term = t; 
            }
            // Formula.g:473:5: ( ( '+' | '-' ) t= multiplicative_expr )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>=35 && LA23_0<=36)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // Formula.g:474:9: ( '+' | '-' ) t= multiplicative_expr
            	    {
            	    // Formula.g:474:9: ( '+' | '-' )
            	    int alt22=2;
            	    int LA22_0 = input.LA(1);

            	    if ( (LA22_0==35) ) {
            	        alt22=1;
            	    }
            	    else if ( (LA22_0==36) ) {
            	        alt22=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return term;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 22, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt22) {
            	        case 1 :
            	            // Formula.g:474:11: '+'
            	            {
            	            match(input,35,FOLLOW_35_in_additive_expr1154); if (state.failed) return term;
            	            if ( state.backtracking==0 ) {
            	               type = FOLF.ADD; 
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Formula.g:474:38: '-'
            	            {
            	            match(input,36,FOLLOW_36_in_additive_expr1160); if (state.failed) return term;
            	            if ( state.backtracking==0 ) {
            	               type = FOLF.SUB; 
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_multiplicative_expr_in_additive_expr1176);
            	    t=multiplicative_expr();

            	    state._fsp--;
            	    if (state.failed) return term;
            	    if ( state.backtracking==0 ) {
            	       term = outerFOLF.new Operator(type, term, t); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return term;
    }
    // $ANTLR end "additive_expr"


    // $ANTLR start "multiplicative_expr"
    // Formula.g:480:1: multiplicative_expr returns [FOLF.Term term] : t= unary_expr ( ( '*' | '/' ) t= unary_expr )* ;
    public final FOLF.Term multiplicative_expr() throws RecognitionException {
        FOLF.Term term = null;

        FOLF.Term t = null;


         int type = -1; 
        try {
            // Formula.g:482:1: (t= unary_expr ( ( '*' | '/' ) t= unary_expr )* )
            // Formula.g:483:5: t= unary_expr ( ( '*' | '/' ) t= unary_expr )*
            {
            pushFollow(FOLLOW_unary_expr_in_multiplicative_expr1218);
            t=unary_expr();

            state._fsp--;
            if (state.failed) return term;
            if ( state.backtracking==0 ) {
               term = t; 
            }
            // Formula.g:484:5: ( ( '*' | '/' ) t= unary_expr )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=37 && LA25_0<=38)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // Formula.g:485:9: ( '*' | '/' ) t= unary_expr
            	    {
            	    // Formula.g:485:9: ( '*' | '/' )
            	    int alt24=2;
            	    int LA24_0 = input.LA(1);

            	    if ( (LA24_0==37) ) {
            	        alt24=1;
            	    }
            	    else if ( (LA24_0==38) ) {
            	        alt24=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return term;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 24, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt24) {
            	        case 1 :
            	            // Formula.g:485:11: '*'
            	            {
            	            match(input,37,FOLLOW_37_in_multiplicative_expr1238); if (state.failed) return term;
            	            if ( state.backtracking==0 ) {
            	               type = FOLF.MUL; 
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // Formula.g:485:38: '/'
            	            {
            	            match(input,38,FOLLOW_38_in_multiplicative_expr1244); if (state.failed) return term;
            	            if ( state.backtracking==0 ) {
            	               type = FOLF.DIV; 
            	            }

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_unary_expr_in_multiplicative_expr1260);
            	    t=unary_expr();

            	    state._fsp--;
            	    if (state.failed) return term;
            	    if ( state.backtracking==0 ) {
            	       term = outerFOLF.new Operator(type, term, t); 
            	    }

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return term;
    }
    // $ANTLR end "multiplicative_expr"


    // $ANTLR start "unary_expr"
    // Formula.g:491:1: unary_expr returns [FOLF.Term term] : ( '+' t= unary_expr | '-' t= unary_expr | t= primary_term );
    public final FOLF.Term unary_expr() throws RecognitionException {
        FOLF.Term term = null;

        FOLF.Term t = null;


        try {
            // Formula.g:491:37: ( '+' t= unary_expr | '-' t= unary_expr | t= primary_term )
            int alt26=3;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==35) ) {
                alt26=1;
            }
            else if ( (LA26_0==36) ) {
                alt26=2;
            }
            else if ( (LA26_0==LPAREN||LA26_0==ID) ) {
                alt26=3;
            }
            else if ( (LA26_0==NUMBER) && (( allowNumbers ))) {
                alt26=3;
            }
            else if ( (LA26_0==STRING) && (( allowStrings ))) {
                alt26=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return term;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // Formula.g:492:5: '+' t= unary_expr
                    {
                    match(input,35,FOLLOW_35_in_unary_expr1294); if (state.failed) return term;
                    pushFollow(FOLLOW_unary_expr_in_unary_expr1298);
                    t=unary_expr();

                    state._fsp--;
                    if (state.failed) return term;
                    if ( state.backtracking==0 ) {
                       term = t; 
                    }

                    }
                    break;
                case 2 :
                    // Formula.g:494:5: '-' t= unary_expr
                    {
                    match(input,36,FOLLOW_36_in_unary_expr1308); if (state.failed) return term;
                    pushFollow(FOLLOW_unary_expr_in_unary_expr1312);
                    t=unary_expr();

                    state._fsp--;
                    if (state.failed) return term;
                    if ( state.backtracking==0 ) {
                       term = outerFOLF.new Operator(FOLF.UMINUS, t, null); 
                    }

                    }
                    break;
                case 3 :
                    // Formula.g:497:5: t= primary_term
                    {
                    pushFollow(FOLLOW_primary_term_in_unary_expr1328);
                    t=primary_term();

                    state._fsp--;
                    if (state.failed) return term;
                    if ( state.backtracking==0 ) {
                       term = t; 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return term;
    }
    // $ANTLR end "unary_expr"


    // $ANTLR start "primary_term"
    // Formula.g:500:1: primary_term returns [FOLF.Term term] : ( '(' t= general_term ')' | t= function_invocation | t= var_or_const | {...}? => NUMBER | {...}? => STRING );
    public final FOLF.Term primary_term() throws RecognitionException {
        FOLF.Term term = null;

        Token NUMBER4=null;
        Token STRING5=null;
        FOLF.Term t = null;


        try {
            // Formula.g:500:39: ( '(' t= general_term ')' | t= function_invocation | t= var_or_const | {...}? => NUMBER | {...}? => STRING )
            int alt27=5;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==LPAREN) ) {
                alt27=1;
            }
            else if ( (LA27_0==ID) ) {
                int LA27_2 = input.LA(2);

                if ( (LA27_2==LPAREN) ) {
                    alt27=2;
                }
                else if ( (LA27_2==EOF||LA27_2==RPAREN||LA27_2==EQUIVALENCE||(LA27_2>=IMPLICATION && LA27_2<=AND)||(LA27_2>=EQ && LA27_2<=GE)||(LA27_2>=30 && LA27_2<=31)||(LA27_2>=35 && LA27_2<=38)) ) {
                    alt27=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return term;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 2, input);

                    throw nvae;
                }
            }
            else if ( (LA27_0==NUMBER) && (( allowNumbers ))) {
                alt27=4;
            }
            else if ( (LA27_0==STRING) && (( allowStrings ))) {
                alt27=5;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return term;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // Formula.g:501:5: '(' t= general_term ')'
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_primary_term1347); if (state.failed) return term;
                    pushFollow(FOLLOW_general_term_in_primary_term1351);
                    t=general_term();

                    state._fsp--;
                    if (state.failed) return term;
                    match(input,RPAREN,FOLLOW_RPAREN_in_primary_term1353); if (state.failed) return term;
                    if ( state.backtracking==0 ) {
                       term = t; 
                    }

                    }
                    break;
                case 2 :
                    // Formula.g:503:5: t= function_invocation
                    {
                    pushFollow(FOLLOW_function_invocation_in_primary_term1365);
                    t=function_invocation();

                    state._fsp--;
                    if (state.failed) return term;
                    if ( state.backtracking==0 ) {
                       term = t; 
                    }

                    }
                    break;
                case 3 :
                    // Formula.g:505:5: t= var_or_const
                    {
                    pushFollow(FOLLOW_var_or_const_in_primary_term1377);
                    t=var_or_const();

                    state._fsp--;
                    if (state.failed) return term;
                    if ( state.backtracking==0 ) {
                       term = t; 
                    }

                    }
                    break;
                case 4 :
                    // Formula.g:507:5: {...}? => NUMBER
                    {
                    if ( !(( allowNumbers )) ) {
                        if (state.backtracking>0) {state.failed=true; return term;}
                        throw new FailedPredicateException(input, "primary_term", " allowNumbers ");
                    }
                    NUMBER4=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_primary_term1390); if (state.failed) return term;
                    if ( state.backtracking==0 ) {
                       term = outerFOLF.new Literal(Double.valueOf((NUMBER4!=null?NUMBER4.getText():null))); 
                    }

                    }
                    break;
                case 5 :
                    // Formula.g:510:5: {...}? => STRING
                    {
                    if ( !(( allowStrings )) ) {
                        if (state.backtracking>0) {state.failed=true; return term;}
                        throw new FailedPredicateException(input, "primary_term", " allowStrings ");
                    }
                    STRING5=(Token)match(input,STRING,FOLLOW_STRING_in_primary_term1407); if (state.failed) return term;
                    if ( state.backtracking==0 ) {
                       term = outerFOLF.new Literal(unescape((STRING5!=null?STRING5.getText():null)).intern()); 
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return term;
    }
    // $ANTLR end "primary_term"


    // $ANTLR start "function_invocation"
    // Formula.g:520:1: function_invocation returns [FOLF.Term term] : ID '(' l= term_list ')' ;
    public final FOLF.Term function_invocation() throws RecognitionException {
        FOLF.Term term = null;

        Token ID6=null;
        List<FOLF.Term> l = null;


        try {
            // Formula.g:520:46: ( ID '(' l= term_list ')' )
            // Formula.g:521:5: ID '(' l= term_list ')'
            {
            ID6=(Token)match(input,ID,FOLLOW_ID_in_function_invocation1436); if (state.failed) return term;
            match(input,LPAREN,FOLLOW_LPAREN_in_function_invocation1438); if (state.failed) return term;
            pushFollow(FOLLOW_term_list_in_function_invocation1442);
            l=term_list();

            state._fsp--;
            if (state.failed) return term;
            match(input,RPAREN,FOLLOW_RPAREN_in_function_invocation1444); if (state.failed) return term;
            if ( state.backtracking==0 ) {

                      if (! isFunction(ID6)) {
                          throw new FailedPredicateException(input, "function_invocation",
                                      "not a function symbol");
                      }
                      if (arity(ID6) != l.size()) {
                          throw new FailedPredicateException(input, "function_invocation",
                                      "bad number of arguments");
                      }
                      term = outerFOLF.new Function((ID6!=null?ID6.getText():null), l);
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return term;
    }
    // $ANTLR end "function_invocation"


    // $ANTLR start "var_or_const"
    // Formula.g:535:1: var_or_const returns [FOLF.Term term] : ID ;
    public final FOLF.Term var_or_const() throws RecognitionException {
        FOLF.Term term = null;

        Token ID7=null;

        try {
            // Formula.g:535:39: ( ID )
            // Formula.g:536:5: ID
            {
            ID7=(Token)match(input,ID,FOLLOW_ID_in_var_or_const1467); if (state.failed) return term;
            if ( state.backtracking==0 ) {

                      if (isConstant(ID7)) {
                          term = outerFOLF.new Constant((ID7!=null?ID7.getText():null));
                      } else {
                          if (! isUnknown(ID7)) {
                              throw new FailedPredicateException(input, "var_or_const",
                                          "rel/func symbol cannot be used as a variable");
                          }
                          FOLF.Variable var = visibleVariables.get((ID7!=null?ID7.getText():null));
                          if (var == null) {
                              var = outerFOLF.new Variable((ID7!=null?ID7.getText():null));
                              visibleVariables.put((ID7!=null?ID7.getText():null), var);
                              allVariables.add(var);
                          }
                          term = var;
                          if (out_of_scope_variables.contains((ID7!=null?ID7.getText():null))) {
                              throw new FailedPredicateException(input, "var_or_const",
                                          "conflicting variable names");
                          }
                      }
                  
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return term;
    }
    // $ANTLR end "var_or_const"

    // $ANTLR start synpred1_Formula
    public final void synpred1_Formula_fragment() throws RecognitionException {   
        // Formula.g:414:5: ( '(' general_formula ')' )
        // Formula.g:414:7: '(' general_formula ')'
        {
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred1_Formula821); if (state.failed) return ;
        pushFollow(FOLLOW_general_formula_in_synpred1_Formula823);
        general_formula();

        state._fsp--;
        if (state.failed) return ;
        match(input,RPAREN,FOLLOW_RPAREN_in_synpred1_Formula825); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_Formula

    // Delegated rules

    public final boolean synpred1_Formula() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Formula_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA3 dfa3 = new DFA3(this);
    protected DFA18 dfa18 = new DFA18(this);
    static final String DFA3_eotS =
        "\15\uffff";
    static final String DFA3_eofS =
        "\15\uffff";
    static final String DFA3_minS =
        "\1\4\1\0\13\uffff";
    static final String DFA3_maxS =
        "\1\44\1\0\13\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\2\11\uffff\1\1";
    static final String DFA3_specialS =
        "\1\uffff\1\0\13\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\2\1\uffff\1\1\1\uffff\1\2\1\uffff\2\2\3\uffff\1\2\6\uffff"+
            "\1\2\11\uffff\2\2\1\uffff\2\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "144:1: suite_entry[List<Definition> dlist, List<Sentence> slist] : ({...}? definition2 | sentence2 );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA3_1 = input.LA(1);

                         
                        int index3_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( isUnknown(input.LT(1)) && input.LA(2) == LPAREN )) ) {s = 12;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index3_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 3, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA18_eotS =
        "\14\uffff";
    static final String DFA18_eofS =
        "\14\uffff";
    static final String DFA18_minS =
        "\1\4\1\0\3\uffff\1\0\6\uffff";
    static final String DFA18_maxS =
        "\1\44\1\0\3\uffff\1\0\6\uffff";
    static final String DFA18_acceptS =
        "\2\uffff\1\2\1\uffff\1\3\1\uffff\1\5\1\uffff\2\5\1\1\1\4";
    static final String DFA18_specialS =
        "\1\0\1\1\3\uffff\1\2\6\uffff}>";
    static final String[] DFA18_transitionS = {
            "\1\1\1\uffff\1\5\1\uffff\1\11\1\uffff\2\2\3\uffff\1\4\6\uffff"+
            "\1\10\14\uffff\2\6",
            "\1\uffff",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "413:1: primary_formula returns [FOLF.Formula form] : ( ( '(' general_formula ')' )=> '(' f= general_formula ')' | f= quantified_formula | NOT f= primary_formula | {...}?f= relation_invocation | a= general_term c= comparision_op b= general_term );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA18_0 = input.LA(1);

                         
                        int index18_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA18_0==LPAREN) && ((( ! allowNumbers )||( allowNumbers )))) {s = 1;}

                        else if ( ((LA18_0>=FORALL && LA18_0<=EXISTS)) ) {s = 2;}

                        else if ( (LA18_0==NOT) ) {s = 4;}

                        else if ( (LA18_0==ID) ) {s = 5;}

                        else if ( ((LA18_0>=35 && LA18_0<=36)) && (( allowNumbers ))) {s = 6;}

                        else if ( (LA18_0==NUMBER) && (((( ! allowNumbers )&&( allowNumbers ))||( allowNumbers )))) {s = 8;}

                        else if ( (LA18_0==STRING) && (((( ! allowNumbers )&&( allowStrings ))||(( allowNumbers )&&( allowStrings ))))) {s = 9;}

                         
                        input.seek(index18_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA18_1 = input.LA(1);

                         
                        int index18_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_Formula()) ) {s = 10;}

                        else if ( ((( ! allowNumbers )||( allowNumbers ))) ) {s = 9;}

                         
                        input.seek(index18_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA18_5 = input.LA(1);

                         
                        int index18_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (( isRelation(input.LT(1)) )) ) {s = 11;}

                        else if ( ((( ! allowNumbers )||( allowNumbers ))) ) {s = 9;}

                         
                        input.seek(index18_5);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 18, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_formula2_in_formula94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sentence2_in_sentence103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_definition2_in_definition112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_suite_entry_in_suite130 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_suite135 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_suite_entry_in_suite137 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_suite143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_definition2_in_suite_entry184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sentence2_in_suite_entry204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_definition2233 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_LPAREN_in_definition2237 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_parameter_list_in_definition2239 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_definition2241 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EQUIVALENCE_in_definition2245 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_implication_in_definition2251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameter_in_parameter_list266 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_31_in_parameter_list270 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_parameter_in_parameter_list272 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_ID_in_parameter288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_messages_in_sentence2310 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_general_formula_in_sentence2315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_general_formula_in_formula2341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_messages376 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_messages380 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_33_in_messages384 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_messages388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_messages403 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_messages407 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_32_in_messages411 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_messages415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_equivalence_in_general_formula445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifier_in_quantified_formula478 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_quantified_var_in_quantified_formula484 = new BitSet(new long[]{0x0000000480000200L});
    public static final BitSet FOLLOW_31_in_quantified_formula493 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_quantified_var_in_quantified_formula495 = new BitSet(new long[]{0x0000000480000200L});
    public static final BitSet FOLLOW_IN_in_quantified_formula515 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_quantifier_range_in_quantified_formula517 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_quantified_formula520 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_quantified_var_in_quantified_formula530 = new BitSet(new long[]{0x0000000480000200L});
    public static final BitSet FOLLOW_31_in_quantified_formula543 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_quantified_var_in_quantified_formula545 = new BitSet(new long[]{0x0000000480000200L});
    public static final BitSet FOLLOW_IN_in_quantified_formula564 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_quantifier_range_in_quantified_formula566 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_quantified_formula576 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_general_formula_in_quantified_formula580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FORALL_in_quantifier603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXISTS_in_quantifier613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_quantified_var637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_quantifier_range660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_implication_in_equivalence685 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_EQUIVALENCE_in_equivalence691 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_implication_in_equivalence695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alternative_in_implication719 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_IMPLICATION_in_implication725 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_alternative_in_implication729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conjunction_in_alternative753 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_OR_in_alternative759 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_conjunction_in_alternative763 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_primary_formula_in_conjunction787 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_AND_in_conjunction793 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_primary_formula_in_conjunction797 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_LPAREN_in_primary_formula830 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_general_formula_in_primary_formula834 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_primary_formula836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantified_formula_in_primary_formula852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_primary_formula862 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_primary_formula_in_primary_formula866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relation_invocation_in_primary_formula880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_general_term_in_primary_formula896 = new BitSet(new long[]{0x00000000003F0000L});
    public static final BitSet FOLLOW_comparision_op_in_primary_formula900 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_general_term_in_primary_formula904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_comparision_op925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NE_in_comparision_op935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_comparision_op948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_comparision_op961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_comparision_op974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_comparision_op987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_relation_invocation1006 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_LPAREN_in_relation_invocation1008 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_term_list_in_relation_invocation1012 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_relation_invocation1014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_general_term_in_term_list1045 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_31_in_term_list1055 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_general_term_in_term_list1059 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_additive_expr_in_general_term1092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_term_in_general_term1107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicative_expr_in_additive_expr1133 = new BitSet(new long[]{0x0000001800000002L});
    public static final BitSet FOLLOW_35_in_additive_expr1154 = new BitSet(new long[]{0x0000001800400150L});
    public static final BitSet FOLLOW_36_in_additive_expr1160 = new BitSet(new long[]{0x0000001800400150L});
    public static final BitSet FOLLOW_multiplicative_expr_in_additive_expr1176 = new BitSet(new long[]{0x0000001800000002L});
    public static final BitSet FOLLOW_unary_expr_in_multiplicative_expr1218 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_37_in_multiplicative_expr1238 = new BitSet(new long[]{0x0000001800400150L});
    public static final BitSet FOLLOW_38_in_multiplicative_expr1244 = new BitSet(new long[]{0x0000001800400150L});
    public static final BitSet FOLLOW_unary_expr_in_multiplicative_expr1260 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_35_in_unary_expr1294 = new BitSet(new long[]{0x0000001800400150L});
    public static final BitSet FOLLOW_unary_expr_in_unary_expr1298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_unary_expr1308 = new BitSet(new long[]{0x0000001800400150L});
    public static final BitSet FOLLOW_unary_expr_in_unary_expr1312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_term_in_unary_expr1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_primary_term1347 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_general_term_in_primary_term1351 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_primary_term1353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_invocation_in_primary_term1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_or_const_in_primary_term1377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUMBER_in_primary_term1390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_primary_term1407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_function_invocation1436 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_LPAREN_in_function_invocation1438 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_term_list_in_function_invocation1442 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_function_invocation1444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_var_or_const1467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_synpred1_Formula821 = new BitSet(new long[]{0x0000001B00408D50L});
    public static final BitSet FOLLOW_general_formula_in_synpred1_Formula823 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RPAREN_in_synpred1_Formula825 = new BitSet(new long[]{0x0000000000000002L});

}
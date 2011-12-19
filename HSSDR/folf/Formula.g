// uwaga - za kazdym razem po wygenerowaniu FormulaParser.java trzeba w nim
// poprawic "extends Parser" na "extends org.antlr.runtime.Parser", bo mamy
// w biezacym katalogu inna klase nazywajaca sie Parser

grammar Formula;

options {
    language = Java;
}

tokens {
    LPAREN = '(';
    RPAREN = ')';
}

@parser::header {
    package folf;
    import java.util.List;
    import java.util.Map;
    import java.util.Set;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.LinkedHashMap;
    import java.util.HashSet;
    import java.util.LinkedHashSet;
}
@lexer::header {
    package folf;
}

@parser::members {
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
}

@lexer::members {
    folf.Parser owner;

    public void emitErrorMessage(String msg)
    {
        owner.appendErrorMessage(msg);
    }
}

// ---- reguly bedace punktami startowymi gramatyki ----

formula : formula2 ;

sentence : sentence2 ;

definition : definition2 ;

suite
@init {
    // slownik bedzie rozszerzany o nowe symbole, nie modyfikuj oryginalnego
    voc = new Vocabulary(voc);
    ArrayList<Definition> defs = new ArrayList<Definition>();
    ArrayList<Sentence> sens = new ArrayList<Sentence>();
} :
    suite_entry[defs,sens] ( ';' suite_entry[defs,sens] )* ';'?
    {
        if (sens.size() == 0)
            throw new FailedPredicateException(input, "suite",
                        "must contain at least one test");
        outerSuite.localRelations = defs.toArray(new Definition[defs.size()]);
        outerSuite.tests = sens.toArray(new Sentence[sens.size()]);
    }
;

// ---- tu sie zaczynaja reguly wywolywane przez inne reguly ---

suite_entry [List<Definition> dlist, List<Sentence> slist]
@init { Definition d = null; Sentence s = null; } :
    { isUnknown(input.LT(1)) && input.LA(2) == LPAREN }?
    { d = new Definition(); outerFOLF = d; }
    definition2
    {
        if (d.formula != null) {
            voc.addRelation(d.getName(), d.getArity());
            $dlist.add(d);
        }
    }
|
    { s = new Sentence(); outerFOLF = s; }
    sentence2
    { if (s.formula != null) $slist.add(s); }
;

definition2
@init {
        resetVarLists();
        int arity = -1;
        // nizej modyfikujemy slownik, ale to ma byc chwilowa modyfikacja
        Vocabulary orig_voc = voc;
        voc = new Vocabulary(voc);
}
@after {
        voc = orig_voc;
}
:
    ID {
        if (! isUnknown($ID))
            throw new FailedPredicateException(input, "definition",
                    "redefinition of a known symbol");
    } '(' parameter_list ')' {
        arity = visibleVariables.size();
    } EQUIVALENCE {
        // dodajemy nazwe wlasnie definiowanej relacji do slownika, aby dalo
        // sie definiowac relacje rekurencyjne
        voc.addRelation($ID.text, arity);
    } f=implication {
        if (visibleVariables.size() != arity)
            throw new FailedPredicateException(input, "definition",
                    "free variables in definition body");
        if (visibleVariables.containsKey($ID.text) ||
                    out_of_scope_variables.contains($ID.text))
            throw new FailedPredicateException(input, "definition",
                    "name conflict with a variable");
        updateOuterFOLF($f.form);
        ((Definition) outerFOLF).name = $ID.text;
    }
;

parameter_list :
    parameter ( ',' parameter )*
;

parameter :
    ID {
        if (! isUnknown($ID))
            throw new FailedPredicateException(input, "parameter",
                    "vocabulary symbol cannot be used as a variable");
        if (visibleVariables.get($ID.text) != null)
            throw new FailedPredicateException(input, "parameter",
                    "duplicate parameter names");
        FOLF.Variable var = visibleVariables.get($ID.text);
        visibleVariables.put($ID.text, var);
        allVariables.add(var);
    }
;

sentence2
@init { resetVarLists(); }
:
    m=messages? f=general_formula
    {
        if (visibleVariables.size() == 0)
            updateOuterFOLF($f.form);
        else
            throw new FailedPredicateException(input, "sentence",
                        "free variables are present");
        outerFOLF.successMsg = $m.s_msg;
        outerFOLF.failureMsg = $m.f_msg;
    }
;

formula2
@init { resetVarLists(); }
:
    f=general_formula
    {
        updateOuterFOLF($f.form);
    }
;

// ---- reguly podrzedne (czyli wywolywane przez kogos) ----

messages returns [String s_msg, String f_msg] :
    (
        'success_msg' s=STRING ( 'failure_msg' f=STRING )?
        | 'failure_msg' f=STRING ( 'success_msg' s=STRING )?
    ) {
        $s_msg = unescape($s.text);     // null jesli nie dopasowano
        $f_msg = unescape($f.text);
    }
;

general_formula returns [FOLF.Formula form] :
    f=equivalence
    { $form = $f.form; }
;

quantified_formula returns [FOLF.Formula form]
@init {
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<FOLF.Variable> vars = new ArrayList<FOLF.Variable>();
    ArrayList<String> ranges = new ArrayList<String>();
    ArrayList<FOLF.Variable> saved = new ArrayList<FOLF.Variable>();
    FOLF.Variable var;
} :
    q=quantifier
    quantified_var[names, vars, saved]
    ( ',' quantified_var[names, vars, saved] )*
    (
        IN quantifier_range[vars, ranges] ','
        quantified_var[names, vars, saved]
        ( ',' quantified_var[names, vars, saved] )*
    )*
    ( IN quantifier_range[vars, ranges] )?
    ':' f=general_formula
    {
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
        $form = outerFOLF.new Quantifier($q.type, vars, ranges, $f.form);
    }
;

quantifier returns [int type] :
    FORALL { $type = FOLF.UNIVERSAL; }
    | EXISTS { $type = FOLF.EXISTENTIAL; }
;

quantified_var [List<String> names, List<FOLF.Variable> vars,
        List<FOLF.Variable> saved]
@init {
    FOLF.Variable var, old_var;
} :
    v=ID
    {
        if (! isUnknown($v)) {
            throw new FailedPredicateException(input, "quantified_formula",
                        "vocabulary symbol cannot be used as a variable");
        }
        old_var = visibleVariables.get($v.text);
        saved.add(old_var);
        var = outerFOLF.new Variable($v.text);
        names.add($v.text);
        vars.add(var);
        visibleVariables.put($v.text, var);
        allVariables.add(var);
        // jesli wykomentujesz ponizsze throw gramatyka bedzie dopuszczac
        // konstrukcje typu 'exists x : (exists x : x < 13) and x > 44'
        if (old_var != null) {
            throw new FailedPredicateException(input, "quantified_formula",
                        "conflicting variable names");
        }
    }
;

quantifier_range [List<FOLF.Variable> vars, List<String> ranges]
:
    r=ID
    {
        if (arity($r) != 1) {
            throw new FailedPredicateException(input, "quantified_formula",
                    "only arity 1 relations allowed as quantification ranges");
        }
        while (ranges.size() < vars.size()) {
            ranges.add($r.text);
        }
    }
;

equivalence returns [FOLF.Formula form] :
    f=implication { 
        $form = $f.form;
    } ( EQUIVALENCE f=implication {
        $form = outerFOLF.new Connective(FOLF.BICONDITIONAL, $form, $f.form);
    } )?
;

implication returns [FOLF.Formula form] :
    f=alternative { 
        $form = $f.form;
    } ( IMPLICATION f=alternative {
        $form = outerFOLF.new Connective(FOLF.IMPLICATION, $form, $f.form);
    } )?
;

alternative returns [FOLF.Formula form] :
    f=conjunction {
        $form = $f.form;
    } ( OR f=conjunction {
        $form = outerFOLF.new Connective(FOLF.DISJUNCTION, $form, $f.form);
    } )*
;

conjunction returns [FOLF.Formula form] :
    f=primary_formula {
        $form = $f.form;
    } ( AND f=primary_formula {
        $form = outerFOLF.new Connective(FOLF.CONJUNCTION, $form, $f.form);
    } )*
;

primary_formula returns [FOLF.Formula form] :
    ( '(' general_formula ')' )=> '(' f=general_formula ')'
    { $form = $f.form; }
|
    f=quantified_formula { $form = $f.form; }
|
    NOT f=primary_formula {
        $form = outerFOLF.new Connective(FOLF.NEGATION, $f.form, null);
    }
|
    { isRelation(input.LT(1)) }? f=relation_invocation
    { $form = $f.form; }
|
    a=general_term c=comparision_op b=general_term {
        $form = outerFOLF.new Comparator($c.type, $a.term, $b.term);
    }
;
// predykaty, aby ANTLR wiedzial czy (f(t, ...)=t) czy tez (f(t, ...))=t,
// oraz czy ID(t, ...) czy tez moze ID(t, ...)=t

comparision_op returns [int type] :
    EQ { $type = FOLF.EQUAL; }
    | NE { $type = FOLF.NOT_EQUAL; }
    | { allowNumbers }?=> LT { $type = FOLF.LESS_THAN; }
    | { allowNumbers }?=> LE { $type = FOLF.LESS_OR_EQUAL; }
    | { allowNumbers }?=> GT { $type = FOLF.GREATER_THAN; }
    | { allowNumbers }?=> GE { $type = FOLF.GREATER_OR_EQUAL; }
;

relation_invocation returns [FOLF.Formula form] :
    ID '(' l=term_list ')'
    {
        if (! isRelation($ID)) {
            throw new FailedPredicateException(input, "relation_invocation",
                            "not a relation symbol");
        }
        if (arity($ID) != $l.list.size()) {
            throw new FailedPredicateException(input, "relation_invocation",
                            "bad number of arguments");
        }
        $form = outerFOLF.new Predicate($ID.text, $l.list);
    }
;

term_list returns [List<FOLF.Term> list] :
    { $list = new ArrayList<FOLF.Term>(); }
    t=general_term { $list.add($t.term); }
    ( ',' t=general_term { $list.add($t.term); } )*
    { ((ArrayList<FOLF.Term>) $list).trimToSize(); }
;

general_term returns [FOLF.Term term] :
    { allowNumbers }?=> t=additive_expr { $term = $t.term; }
    | { ! allowNumbers }?=> t=primary_term { $term = $t.term; }
;

additive_expr returns [FOLF.Term term]
@init { int type = -1; }
:
    t=multiplicative_expr { $term = $t.term; }
    ( 
        ( '+' { type = FOLF.ADD; } | '-' { type = FOLF.SUB; } )
        t=multiplicative_expr
        { $term = outerFOLF.new Operator(type, $term, $t.term); }
    )*
;

multiplicative_expr returns [FOLF.Term term] 
@init { int type = -1; }
:
    t=unary_expr { $term = $t.term; }
    (
        ( '*' { type = FOLF.MUL; } | '/' { type = FOLF.DIV; } )
        t=unary_expr
        { $term = outerFOLF.new Operator(type, $term, $t.term); }
    )*
;

unary_expr returns [FOLF.Term term] :
    '+' t=unary_expr { $term = $t.term; }
|
    '-' t=unary_expr
    { $term = outerFOLF.new Operator(FOLF.UMINUS, $t.term, null); }
|
    t=primary_term { $term = $t.term; }
;

primary_term returns [FOLF.Term term] :
    '(' t=general_term ')' { $term = $t.term; }
|
    t=function_invocation { $term = $t.term; }
|
    t=var_or_const { $term = $t.term; }
|
    { allowNumbers }?=> NUMBER
    { $term = outerFOLF.new Literal(Double.valueOf($NUMBER.text)); }
|
    { allowStrings }?=> STRING
    { $term = outerFOLF.new Literal(unescape($STRING.text).intern()); }
;

// przywroc kiedys { isFunction(input.LT(1)) }? i sprawdz jakie wtedy sa
// komunikaty o bledach; moze warto wrocic do:
// variable_name returns [String name]
//    : { isUnknown(input.LT(1)) }? ID { $name = $ID.text; }
//    ;

function_invocation returns [FOLF.Term term] :
    ID '(' l=term_list ')'
    {
        if (! isFunction($ID)) {
            throw new FailedPredicateException(input, "function_invocation",
                        "not a function symbol");
        }
        if (arity($ID) != $l.list.size()) {
            throw new FailedPredicateException(input, "function_invocation",
                        "bad number of arguments");
        }
        $term = outerFOLF.new Function($ID.text, $l.list);
    }
;

var_or_const returns [FOLF.Term term] :
    ID
    {
        if (isConstant($ID)) {
            $term = outerFOLF.new Constant($ID.text);
        } else {
            if (! isUnknown($ID)) {
                throw new FailedPredicateException(input, "var_or_const",
                            "rel/func symbol cannot be used as a variable");
            }
            FOLF.Variable var = visibleVariables.get($ID.text);
            if (var == null) {
                var = outerFOLF.new Variable($ID.text);
                visibleVariables.put($ID.text, var);
                allVariables.add(var);
            }
            $term = var;
            if (out_of_scope_variables.contains($ID.text)) {
                throw new FailedPredicateException(input, "var_or_const",
                            "conflicting variable names");
            }
        }
    }
;

// ---- tokeny leksera ----

FORALL : 'forall' | '\\forall' | '\u2200' ;

EXISTS : 'exists' | '\\exists' | '\u2203' ;

IN : 'in' | '\\in' | '\u220A' ;

EQUIVALENCE : '<=>' | '\\Leftrightarrow' | '\u21D4' ;

IMPLICATION : '=>' | '\\Rightarrow' | '\u21D2' ;

AND : 'and' | '\\wedge' | '\u2227' ;

OR : 'or' | '\\vee' | '\u2228' ;

NOT : 'not' | '!' | '\\neg' | '\u00AC' ;

EQ : '=' ;

NE : '!=' | '<>' | '\\neq' | '\u2260' ;

LT : '<' ;

LE : '<=' | '\\leq' | '\u2264' ;

GT : '>' ;

GE : '>=' | '\\geq' | '\u2265' ;

ID : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

NUMBER : '0'..'9'+ ('.' '0'..'9'+)? EXPONENT? | '.' '0'..'9'+ EXPONENT? ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

STRING : '"' ( ESC_SEQ | ~('\\'|'"') )* '"' ;

fragment
ESC_SEQ : '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\') | OCTAL_ESC | UNICODE_ESC ;

fragment
OCTAL_ESC : '\\' '0'..'3' '0'..'7' '0'..'7' | '\\' '0'..'7' '0'..'7'
| '\\' '0'..'7' ;

fragment
UNICODE_ESC : '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT ;

fragment
HEX_DIGIT : '0'..'9'|'a'..'f'|'A'..'F' ;

COMMENT : '#' .* ('\r'|'\n') { skip(); } ;
// nie trzeba dodawac "options { greedy=false; }", ANTLR sam wie ze w tym
// kontekscie nic innego nie mialoby sensu

WHITESPACE : (' '|'\t'|'\r'|'\n') { skip(); } ;

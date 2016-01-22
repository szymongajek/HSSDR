java -cp antlr-3.2.jar org.antlr.Tool folf\Formula.g 
ponizej zamiana w def klasy
### sed -i -e 's/extends Parser/extends org.antlr.runtime.Parser/' \
		FormulaParser.java

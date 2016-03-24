all:
	java -cp ~/lib/antlr-3.2.jar org.antlr.Tool Formula.g \
	&& sed -i -e 's/extends Parser/extends org.antlr.runtime.Parser/' \
		FormulaParser.java

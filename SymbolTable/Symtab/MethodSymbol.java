package Symtab;

import AST.*;
import java.util.ArrayList;

public class MethodSymbol extends Symbol
{
    private ArrayList<Symbol> parameters;

    public MethodSymbol(String n, String t)
    {
    	super(n, t);
        parameters = new ArrayList<Symbol>();
    }

    public void addParameter(Symbol v) {
        parameters.add(v);
    }

    public ArrayList<Symbol> getParameters() {
        return parameters;
    }
    
    public String toString() {
        String s = "";
        for ( int i=0; i<parameters.size(); i++ ) 
        {
        	s += ((i > 0) ? ", " : "") + parameters.get(i);
        }

        return "{METHOD}" + type + " " + name + "(" + s + ")";
    }
}
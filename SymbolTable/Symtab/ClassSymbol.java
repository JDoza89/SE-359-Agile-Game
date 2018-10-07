package Symtab;

import AST.*;
import java.util.ArrayList;

public class ClassSymbol extends Symbol
{
    private ArrayList<MethodSymbol> methods;
    private ArrayList<VarSymbol> variables;

    public ClassSymbol(String n)
    {
        super(n, "");
        methods = new ArrayList<MethodSymbol>();
        variables = new ArrayList<VarSymbol>();
    }

    public ClassSymbol(String n, String e)
    {
        super(n, e);
        methods = new ArrayList<MethodSymbol>();
        variables = new ArrayList<VarSymbol>();
    }

    public void addMethod(MethodSymbol m) {
        methods.add(m);
    }

    public void addVariable(VarSymbol v) {
        variables.add(v);
    }

    public ArrayList<MethodSymbol> getMethods() {
        return methods;
    }

    public ArrayList<VarSymbol> getVariables() {
        return variables;
    }

    public void extendsClass(ClassSymbol c) {
        type = c.name;
        methods.addAll(c.methods);
        variables.addAll(c.variables);
    }

    public String toString() {
        String c = "{CLASS}";
        if(type != null && !type.isEmpty() )
            c += "class " + name + " extends " + type;
        else
            c += "class " + name;

        if ( variables.size() > 0 )
        {
	        c += "\n    Variables:";
	        for(int i=0;i<variables.size();i++)
	        {
	            c += "\n        "+variables.get(i).toString() ;
	        }
        }
        
        if ( methods.size() > 0 )
        {
	        c += "\n    Methods:";
	        for(int i=0;i<methods.size();i++)
	        {
	            c += "\n        "+methods.get(i).toString();
	        }
        }
        return c;
    }
}
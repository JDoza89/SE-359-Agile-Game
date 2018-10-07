package Symtab;

import AST.*;

public abstract class Symbol
{
    protected String type;
    protected String name;
	
    public Symbol(String n, String t) 
    {
    	this.type = t;
    	this.name = n;
    }

    public String getType()
    {
        return type;
    }

    public String getName() {
        return name;
    }

    public abstract String toString();
}

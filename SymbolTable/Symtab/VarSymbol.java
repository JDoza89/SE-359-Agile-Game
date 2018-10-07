package Symtab;

import AST.*;

public class VarSymbol extends Symbol {
	public VarSymbol(String n, String t) {
		super(n, t);
	}
	
	public String toString() {
		return "{VAR}" + getType() + " " + getName();
	}
}
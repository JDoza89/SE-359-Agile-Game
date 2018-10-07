package Symtab;

import AST.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class SymbolTable {

    private HashMap<String, Symbol> ctable, vtable, mtable;
    private HashMap<String, SymbolTable> children;
    private SymbolTable parent = null;

    public SymbolTable() {
        ctable = new HashMap<String, Symbol>();
        mtable = new HashMap<String, Symbol>();
        vtable = new HashMap<String, Symbol>();
        children = new HashMap<String, SymbolTable>();
        parent = null;
    }

    public SymbolTable(SymbolTable p) {
        ctable = new HashMap<String, Symbol>();
        mtable = new HashMap<String, Symbol>();
        vtable = new HashMap<String, Symbol>();
        children = new HashMap<String, SymbolTable>();
        parent = p;
    }

    public HashMap<String, Symbol> getClassTable() {
    	return ctable;    	
    }
    
    public HashMap<String, Symbol> getMethodTable() {
    	return mtable;    	
    }
    
    public HashMap<String, Symbol> getVarTable() {
    	return vtable;    	
    }
    
    public void addSymbol(Symbol s) {
    	if ( s instanceof ClassSymbol )
    		ctable.put(s.getName(), s);
    	else if ( s instanceof MethodSymbol )
    		mtable.put(s.getName(), s);
    	else if ( s instanceof VarSymbol )
    		vtable.put(s.getName(), s);
    }

    Symbol getSymbol(String i) {
        Symbol s = vtable.get(i);
        if ( s == null )
        	s = mtable.get(i);
        if ( s == null )
        	s = ctable.get(i);
        return s;
    }

    public Symbol lookupSymbol(String i) {
        SymbolTable st = this;
        while ( st != null ) {
            Symbol s = st.getSymbol(i);
            if ( s != null ) return s;
            st = st.getParent();
        }
        return null;
    }

    public SymbolTable enterScope(String i) {
        SymbolTable st = this.getChild(i);
        if ( st == null ) {
            st = new SymbolTable(this);
            this.addChild(i, st);
        }
        return st;
    }

    public SymbolTable exitScope() {
    	return this.getParent();
    }

    public void addChild(String i, SymbolTable st) {
        children.put(i, st);
        st.parent = this;
    }

    public SymbolTable getChild(String i) {
        return children.get(i);
    }

    public SymbolTable getParent() {
        return parent;
    }

    public void printTabs(int tabs)
    {
        for ( int t=0; t<tabs*4; t++ ) {
        	System.out.print(" ");
        }
    }

    public void print(int level) {
        for ( Iterator i = ctable.keySet().iterator(); i.hasNext(); ) {
            String s = (String)i.next();
            Symbol sym = ctable.get(s);
            printTabs(level);
            System.out.println(sym.toString());
            SymbolTable child = children.get(s);
            if ( child != null ) {
            	child.print(level+1);
            }
        }
    	
        for ( Iterator i = mtable.keySet().iterator(); i.hasNext(); ) {
            String s = (String)i.next();
            Symbol sym = mtable.get(s);
            printTabs(level);
            System.out.println(sym.toString());
            SymbolTable child = children.get(s);
            if ( child != null ) {
            	child.print(level+1);
            }
        }

        for ( Iterator i = vtable.keySet().iterator(); i.hasNext(); ) {
            String s = (String)i.next();
            Symbol sym = vtable.get(s);
            printTabs(level);
            System.out.println(sym.toString());
        }
    }
}